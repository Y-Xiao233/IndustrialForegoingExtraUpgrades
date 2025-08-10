package net.yxiao233.ifeu.common.structure;

import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.yxiao233.ifeu.IndustrialForegoingExtraUpgrades;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.api.structure.MultiBlockStructureBuilder;
import net.yxiao233.ifeu.common.registry.IFEUBlocks;
import net.yxiao233.ifeu.common.registry.IFEUContents;
import net.yxiao233.ifeu.common.registry.IFEUTags;

import java.util.HashMap;
import java.util.function.Supplier;

public class IFEUMultiBlockStructures {
    public static final HashMap<ResourceLocation, IFEUMultiBlockStructures> values = new HashMap<>();
    public static final IFEUMultiBlockStructures BIG_DISSOLUTION_CHAMBER = new IFEUMultiBlockStructures(ifeu("big_dissolution_chamber"),() -> IFEUBlocks.BIG_DISSOLUTION_CHAMBER_CORE.getLeft().get().asItem(),() -> new MultiBlockStructureBuilder()
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
            .define('C', IFEUContents.DRAGON_STAR_BLOCK.get())
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
        return new ResourceLocation(IndustrialForegoingExtraUpgrades.MODID,path);
    }
    //base structure block tag
    private static TagKey<Block> ENERGY(){
        return IFEUTags.Blocks.STORAGE_ENERGY;
    }

    private static TagKey<Block> FLUID(){
        return IFEUTags.Blocks.STORAGE_FLUID;
    }

    private static TagKey<Block> ITEM(){
        return IFEUTags.Blocks.STORAGE_ITEM;
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
        return IFEUTags.Blocks.MACHINE_FRAME_ULTIMATE;
    }
}