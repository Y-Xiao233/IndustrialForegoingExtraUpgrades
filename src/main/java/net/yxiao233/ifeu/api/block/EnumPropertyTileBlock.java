package net.yxiao233.ifeu.api.block;

import com.buuz135.industrial.block.IndustrialBlock;
import com.hrznstudio.titanium.block.tile.BasicTile;
import com.hrznstudio.titanium.tab.TitaniumTab;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class EnumPropertyTileBlock<T extends BasicTile<T>, E extends Enum<E> & IEnumProperty<E>> extends IndustrialBlock<T> {
    protected EnumProperty<E> PROPERTY = setProperty();
    protected abstract EnumProperty<E> setProperty();
    protected abstract E getDefault();
    public EnumPropertyTileBlock(String name, BlockBehaviour.Properties properties, Class<T> tileClass, TitaniumTab group){
        super(name,properties,tileClass,group);
        this.registerDefaultState(this.getStateDefinition().any().setValue(PROPERTY, getDefault()));
    }
    @Override
    public @NotNull
    abstract VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context);

    @Override
    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, LivingEntity placer, @NotNull ItemStack stack) {
        if(getDefault() != null && getDefault().getValues() != null){
            getDefault().getValues().forEach(value ->{
                if(!value.getBlockPos(pos).equals(pos)){
                    level.setBlock(value.getBlockPos(pos,placer.getDirection()),state.setValue(PROPERTY,value),3);
                }
            });
        }
    }
    @Override
    public @NotNull BlockState playerWillDestroy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        if (!level.isClientSide) {
            preventDropFromOtherPart(level, pos, state, player);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }


    protected void preventDropFromOtherPart(Level level, BlockPos pos, BlockState state, Player player) {
        List<E> values = getDefault().getValues();
        Direction direction = getDirection(state,getRotationType());
        E property = state.getValue(PROPERTY);
        BlockPos mainPos = property.getMainPos(pos,direction);
        values.forEach(value ->{
            BlockPos blockPos = value.getBlockPos(mainPos,direction);
            BlockState blockState = level.getBlockState(blockPos);
            if(blockState.is(state.getBlock())){
                BlockState blockState1 = blockState.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockPos,blockState1,35);
                level.levelEvent(player,2001,blockPos, Block.getId(blockState));
            }
            if(value == getDefault() && !player.isCreative()){
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        });
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        AtomicBoolean canPlaced = new AtomicBoolean(true);
        Direction direction = getDirection(state,getRotationType());
        E property = state.getValue(PROPERTY);
        BlockPos mainPos = property.getMainPos(pos,direction);

        getDefault().getValues().forEach(value ->{
            BlockPos blockPos = value.getBlockPos(mainPos,direction);
            if(!level.getBlockState(blockPos).isAir()){
                canPlaced.set(false);
            }
        });
        return canPlaced.get();
    }

    @Override
    @SuppressWarnings("deprecation")
    public long getSeed(BlockState state, BlockPos pos) {
        return Mth.getSeed(pos.getX(), state.getValue(PROPERTY).getBlockPos(pos).getY(), pos.getZ());
    }

    private EnumProperty<?> getProperty(){
        return this.PROPERTY != null ? PROPERTY : setProperty();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getProperty());
    }

    protected Direction getDirection(BlockState state, RotationType rotationType){
        return switch (rotationType){
            case FOUR_WAY -> state.getValue(FACING_HORIZONTAL).getOpposite();
            case SIX_WAY,TWENTY_FOUR_WAY -> state.getValue(FACING_ALL).getOpposite();
            default -> null;
        };
    }
}