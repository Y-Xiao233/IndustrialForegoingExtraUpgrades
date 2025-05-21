package net.yxiao233.ifeu.common.structure;

import com.buuz135.industrial.utils.IndustrialTags;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.api.structure.MultiBlockStructureBuilder;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureEvents;
import net.yxiao233.ifeu.common.compact.kubejs.events.IFEUStructureRegistryJS;
import net.yxiao233.ifeu.common.registry.ModBlocks;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.HashMap;
import java.util.function.Supplier;

import static net.minecraft.world.item.ItemStack.ITEM_NON_AIR_CODEC;

public class IFEUMultiBlockStructures {
    public static final HashMap<ResourceLocation, IFEUMultiBlockStructures> values = new HashMap<>();
    public static final IFEUMultiBlockStructures BIG_DISSOLUTION_CHAMBER = new IFEUMultiBlockStructures(ifeu("big_dissolution_chamber"),() -> ModBlocks.BIG_DISSOLUTION_CHAMBER_CORE.asItem(),() -> new MultiBlockStructureBuilder()
            .pattern(
                    "ABA",
                    "BBB",
                    "A A"
            )
            .pattern(
                    "BBB",
                    "BCB",
                    "DMD"
            )
            .pattern(
                    "ABA",
                    "BBB",
                    "AEA"
            )
            .define('A', FRAME_SIMPLE())
            .define('B', Blocks.SCULK)
            .define('C', ModContents.DRAGON_STAR_BLOCK.get())
            .define('D', ITEM())
            .define('E', FLUID())
//                .define('F', ENERGY)
            .build()
    )
            ;

    private Supplier<MultiBlockStructure> structure;
    private Supplier<Item> machine;
    private ResourceLocation id;
    public IFEUMultiBlockStructures(ResourceLocation id, Supplier<Item> machine, Supplier<MultiBlockStructure> structure){
        this.machine = machine;
        this.structure = structure;
        this.id = id;
        if(!values.containsKey(id)){
            values.put(id,this);
        }
    }
    public static final Codec<IFEUMultiBlockStructures> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(IFEUMultiBlockStructures::getId),
            ResourceLocation.CODEC.fieldOf("machine").forGetter(struct -> {
                Item item = struct.getMachine();
                return BuiltInRegistries.ITEM.getKey(item);
            }),
            MultiBlockStructure.CODEC.fieldOf("structure").forGetter(IFEUMultiBlockStructures::getStructure)
    ).apply(instance, (id, machineId, structure) -> {
        Supplier<Item> machineSupplier = () -> BuiltInRegistries.ITEM.get(machineId);
        Supplier<MultiBlockStructure> structureSupplier = () -> (MultiBlockStructure) structure;
        return new IFEUMultiBlockStructures(id, machineSupplier, structureSupplier);
    }));

    public static IFEUMultiBlockStructures getById(ResourceLocation id){
        if(values.containsKey(id)){
            return values.get(id);
        }
        return null;
    }
    public Item getMachine(){
        return machine.get();
    }
    public ResourceLocation getId(){
        return id;
    }
    public MultiBlockStructure getStructure() {
        return structure.get();
    }
    public void modify(MultiBlockStructure newStructure){
        this.structure = () -> newStructure;
    }
    private static ResourceLocation ifeu(String path){
        return ResourceLocation.fromNamespaceAndPath(IndustrialForegoingExtraUpgrades.MODID,path);
    }
    //base structure block tag
    private static TagKey<Block> ENERGY(){
        return ModTags.Blocks.STORAGE_ENERGY;
    }

    private static TagKey<Block> FLUID(){
        return ModTags.Blocks.STORAGE_FLUID;
    }

    private static TagKey<Block> ITEM(){
        return ModTags.Blocks.STORAGE_ITEM;
    }

    private static TagKey<Block> FRAME_PITY(){
        return IndustrialTags.Blocks.MACHINE_FRAME_PITY;
    }

    private static TagKey<Block> FRAME_SIMPLE(){
        return IndustrialTags.Blocks.MACHINE_FRAME_SIMPLE;
    }

    private static TagKey<Block> FRAME_ADVANCED(){
        return IndustrialTags.Blocks.MACHINE_FRAME_ADVANCED;
    }

    private static TagKey<Block> FRAME_SUPREME(){
        return IndustrialTags.Blocks.MACHINE_FRAME_SUPREME;
    }
    private static TagKey<Block> FRAME_ULTIMATE(){
        return ModTags.Blocks.MACHINE_FRAME_ULTIMATE;
    }
}