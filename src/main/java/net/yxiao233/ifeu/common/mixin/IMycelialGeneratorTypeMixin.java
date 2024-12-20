package net.yxiao233.ifeu.common.mixin;

import com.buuz135.industrial.block.generator.mycelial.IMycelialGeneratorType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(IMycelialGeneratorType.class)
public interface IMycelialGeneratorTypeMixin{
    @Accessor("TYPES")
    List<IMycelialGeneratorType> getTypes();

    @Accessor("TYPES")
    void setTypes(List<IMycelialGeneratorType> types);
}
