package net.yxiao233.ifeu.common.item;

import com.buuz135.industrial.item.IFCustomItem;
import com.buuz135.industrial.utils.IndustrialTags;
import com.hrznstudio.titanium.item.BasicItem;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.ChatFormatting;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.yxiao233.ifeu.common.utils.ItemStackUtil;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class HydroponicSimulationProcessorItem extends IFCustomItem {
    public HydroponicSimulationProcessorItem(TitaniumTab tab) {
        super("hydroponic_simulation_processor", tab, (new Item.Properties()).stacksTo(1));
    }

    public static double calculateEfficiency(double executions) {
        return Math.log(executions) / Math.log(200.0);
    }


    @Override
    public void addTooltipDetails(@Nullable BasicItem.@Nullable Key key, ItemStack stack, List<Component> tooltip, boolean advanced) {
        super.addTooltipDetails(key, stack, tooltip, advanced);
        if(stack.hasTag()){
            Simulation simulation = new Simulation(stack.getTag());
            double effi = Math.floor(calculateEfficiency((double)simulation.executions) * 100.0) / 100.0;
            tooltip.add(Component.translatable("tooltip.ifeu.hydroponic.simulating").withStyle(ChatFormatting.GRAY).append(Component.translatable(simulation.crop.isEmpty() ? "tooltip.industrialforegoing.hydroponic.nothing" : simulation.crop.getDescriptionId()).withStyle(ChatFormatting.GOLD)));
            tooltip.add(Component.translatable("tooltip.ifeu.hydroponic.executions").withStyle(ChatFormatting.GRAY).append(Component.literal((new DecimalFormat()).format(simulation.executions)).withStyle(ChatFormatting.GOLD)));
            tooltip.add(Component.translatable("tooltip.ifeu.hydroponic.efficiency").withStyle(ChatFormatting.GRAY).append(Component.literal("" + effi).withStyle(ChatFormatting.GOLD)));
            MutableComponent var10001 = Component.translatable("tooltip.ifeu.hydroponic.next_efficiency").withStyle(ChatFormatting.GRAY);
            String var10002 = this.getProgressBar((double)simulation.executions);
            tooltip.add(var10001.append(Component.literal("" + var10002).withStyle(ChatFormatting.GOLD)));
            tooltip.add(Component.translatable("tooltip.ifeu.hydroponic.potential_drops").withStyle(ChatFormatting.GRAY));
            Iterator<CountedStack> var8 = simulation.stacks.iterator();

            while(var8.hasNext()) {
                CountedStack stat = (CountedStack)var8.next();
                String var10 = String.valueOf(ChatFormatting.GRAY);
                tooltip.add(Component.literal(var10 + " - " + String.valueOf(ChatFormatting.WHITE) + (new DecimalFormat("0.00")).format((double)stat.amount / (double)simulation.executions * effi) + String.valueOf(ChatFormatting.GRAY) + "x " + String.valueOf(ChatFormatting.GOLD) + Component.translatable(stat.stack.getDescriptionId()).getString()));
            }

            tooltip.add(Component.empty());
            tooltip.add(Component.translatable("tooltip.ifeu.hydroponic.function_1").withStyle(ChatFormatting.DARK_GRAY));
            tooltip.add(Component.translatable("tooltip.ifeu.hydroponic.function_2").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    public boolean hasTooltipDetails(@Nullable BasicItem.@Nullable Key key) {
        return key == null;
    }

    private String getProgressBar(double executions) {
        double currentEff = Math.floor(calculateEfficiency(executions) * 100.0) / 100.0;
        double nextEff = this.calculateNextEfficiency(currentEff);
        double lastEff = Math.floor(currentEff * 100.0) / 100.0;
        lastEff = this.calculateNextEfficiency(lastEff - 0.01);
        int linesAmount = 30;
        int progress = (int)Math.floor((executions - lastEff) / (nextEff - lastEff) * (double)linesAmount);
        String var10000 = String.valueOf(ChatFormatting.GOLD);
        return var10000 + "[" + "|".repeat(progress) + String.valueOf(ChatFormatting.DARK_GRAY) + "|".repeat(linesAmount - progress) + String.valueOf(ChatFormatting.GOLD) + "]";
    }

    private double calculateNextEfficiency(double executions) {
        return Math.ceil(Math.pow(200.0, executions + 0.01));
    }

    @Override
    public void registerRecipe(Consumer<FinishedRecipe> consumer) {

    }

    public static class Simulation {
        private long executions;
        private ItemStack crop;
        private List<CountedStack> stacks;

        public Simulation() {
            this.executions = 0L;
            this.crop = ItemStack.EMPTY;
            this.stacks = new ArrayList<>();
        }

        public Simulation(CompoundTag nbt) {
            CompoundTag temp = new CompoundTag();
            if(nbt == null || nbt.isEmpty()){
                nbt = temp;
            }
            this.crop = ItemStackUtil.parseOptional(nbt.getCompound("Crop"));
            this.executions = (long)nbt.getInt("Executions");
            this.stacks = new ArrayList<>();
            CompoundTag statsCompound = nbt.getCompound("Stats");
            Iterator<String> iterator = statsCompound.getAllKeys().iterator();

            while(iterator.hasNext()) {
                String key = iterator.next();
                CompoundTag stat = statsCompound.getCompound(key);
                this.stacks.add(new CountedStack(ItemStackUtil.parseOptional(stat.getCompound("Stack")), stat.getInt("Count")));
            }

        }

        public CompoundTag toNBT(CompoundTag tag) {
            CompoundTag nbt = new CompoundTag();
            nbt.put("Crop", ItemStackUtil.saveOptional(this.crop,tag));
            nbt.putLong("Executions", this.executions);
            CompoundTag allStats = new CompoundTag();
            int key = 0;

            for(Iterator<CountedStack> countedStackIterator = this.stacks.iterator(); countedStackIterator.hasNext(); ++key) {
                CountedStack stat = countedStackIterator.next();
                CompoundTag statCompound = new CompoundTag();
                statCompound.put("Stack", ItemStackUtil.saveOptional(stat.stack,tag));
                statCompound.putLong("Count", stat.amount);
                allStats.put("" + key, statCompound);
            }

            nbt.put("Stats", allStats);
            return nbt;
        }

        public void acceptExecution(ItemStack crop, List<ItemStack> stacks) {
            if (this.crop.isEmpty()) {
                this.crop = crop.copyWithCount(1);
            }

            if (ItemStack.isSameItem(this.crop, crop)) {
                ++this.executions;
                Iterator var3 = stacks.iterator();

                while(true) {
                    ItemStack itemStack;
                    do {
                        if (!var3.hasNext()) {
                            return;
                        }

                        itemStack = (ItemStack)var3.next();
                    } while(itemStack.isEmpty());

                    boolean found = false;
                    Iterator var6 = this.stacks.iterator();

                    while(var6.hasNext()) {
                        CountedStack stat = (CountedStack)var6.next();
                        if (ItemStack.isSameItem(itemStack, stat.stack)) {
                            found = true;
                            stat.amount += (long)itemStack.getCount();
                            break;
                        }
                    }

                    if (!found) {
                        this.stacks.add(new CountedStack(itemStack.copy(), itemStack.getCount()));
                    }
                }
            }
        }

        public long getExecutions() {
            return this.executions;
        }

        public ItemStack getCrop() {
            return this.crop;
        }

        public List<CountedStack> getStats() {
            return this.stacks;
        }
    }

    public static final class CountedStack {
        private ItemStack stack;
        private long amount;

        private CountedStack(ItemStack stack, int amount) {
            this.stack = stack;
            this.amount = (long)amount;
        }

        public ItemStack stack() {
            return this.stack;
        }

        public long amount() {
            return this.amount;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (obj != null && obj.getClass() == this.getClass()) {
                CountedStack that = (CountedStack)obj;
                return Objects.equals(this.stack, that.stack) && this.amount == that.amount;
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.stack, this.amount});
        }

        public String toString() {
            String var10000 = String.valueOf(this.stack);
            return "CountedStack[stack=" + var10000 + ", amount=" + this.amount + "]";
        }
    }
}
