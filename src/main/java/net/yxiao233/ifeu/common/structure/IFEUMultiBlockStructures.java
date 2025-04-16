package net.yxiao233.ifeu.common.structure;

import com.buuz135.industrial.utils.IndustrialTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.yxiao233.ifeu.api.structure.MultiBlockStructure;
import net.yxiao233.ifeu.api.structure.MultiBlockStructureBuilder;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.function.Supplier;

public enum IFEUMultiBlockStructures {
    BIG_DISSOLUTION_CHAMBER(() -> new MultiBlockStructureBuilder()
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
    IFEUMultiBlockStructures(Supplier<MultiBlockStructure> structure){
        this.structure = structure;
    }

    public MultiBlockStructure getStructure() {
        return structure.get();
    }
    public void modify(MultiBlockStructure newStructure){
        this.structure = () -> newStructure;
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
