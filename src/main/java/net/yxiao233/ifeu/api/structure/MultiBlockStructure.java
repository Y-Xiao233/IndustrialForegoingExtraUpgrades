package net.yxiao233.ifeu.api.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiBlockStructure {
    public static final Codec<MultiBlockStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.listOf().fieldOf("pattern").forGetter(struct ->
                    List.of(struct.getStructure()[0])),
            Codec.unboundedMap(Codec.STRING,
                            BuiltInRegistries.BLOCK.byNameCodec())
                    .fieldOf("blocks")
                    .forGetter(struct -> {
                        Map<String, Block> map = new HashMap<>();
                        struct.defineMap.forEach((k, v) -> map.put(String.valueOf(k), v));
                        return map;
                    }),
            Codec.unboundedMap(Codec.STRING,
                            TagKey.codec(BuiltInRegistries.BLOCK.key()))
                    .fieldOf("tags")
                    .forGetter(struct -> {
                        Map<String, TagKey<Block>> map = new HashMap<>();
                        struct.defineTagMap.forEach((k, v) -> map.put(String.valueOf(k), v));
                        return map;
                    }),
            Codec.INT.fieldOf("machineX").orElse(-1).forGetter(struct ->
                    struct.getMachineLocation(struct.getStructure())[0]),
            Codec.INT.fieldOf("machineY").orElse(-1).forGetter(struct ->
                    struct.getMachineLocation(struct.getStructure())[1]),
            Codec.INT.fieldOf("machineZ").orElse(-1).forGetter(struct ->
                    struct.getMachineLocation(struct.getStructure())[2])
    ).apply(instance, (pattern, blocks, tags, machineX, machineY, machineZ) -> {
        MultiBlockStructureBuilder builder = new MultiBlockStructureBuilder();
        String[][] structurePattern = new String[pattern.size()][];
        for (int i = 0; i < pattern.size(); i++) {
            structurePattern[i] = new String[]{pattern.get(i)};
        }
        builder.pattern(structurePattern);
        blocks.forEach((k, v) -> builder.define(k.charAt(0), v));
        tags.forEach((k, v) -> builder.define(k.charAt(0), v));
        return new MultiBlockStructure(builder);
    }));
    private final HashMap<Character, Block> defineMap;
    private final HashMap<Character, TagKey<Block>> defineTagMap;
    private final MultiBlockStructureBuilder builder;
    private final HashMap<Character,List<Block>> tagBlocksMap;
    public MultiBlockStructure(MultiBlockStructureBuilder builder){
        this.defineMap = builder.getDefineMap();
        this.defineTagMap = builder.getDefineTagMap();
        this.tagBlocksMap = builder.getTagBlocksMap();
        this.builder = builder;
    }

    //structure
    public String[][] getStructure(){
        return builder.getStructure();
    }

    //check
    private boolean tagMapContainsSymbol(char symbol){
        return defineTagMap.containsKey(symbol);
    }
    private boolean tagBlocksMapContainsSymbol(char symbol){
        return tagBlocksMap.containsKey(symbol);
    }

    private boolean mapContainsSymbol(char symbol){
        return defineMap.containsKey(symbol);
    }
    public boolean check(char symbol){
        return mapContainsSymbol(symbol) || tagMapContainsSymbol(symbol);
    }

    //machine location
    private int[] getMachineLocation(String[][] structure){
        int machineX = -1, machineY = -1, machineZ = -1;
        for (int y = 0; y < structure.length; y++) {
            for (int z = 0; z < structure[y].length; z++) {
                int x = structure[y][z].indexOf('M');
                if (x != -1) {
                    machineX = x;
                    machineY = y;
                    machineZ = z;
                    break;
                }
            }
            if (machineX != -1) break;
        }
        return new int[]{machineX,machineY,machineZ};
    }

    private int[] adjustForDirection(int x, int y, int z, Direction direction) {
        return switch (direction) {
            case NORTH -> new int[]{-x, y, -z};
            case SOUTH -> new int[]{x, y, z};
            case WEST -> new int[]{-z, y, x};
            case EAST -> new int[]{z, y, -x};
            default -> new int[]{x, y, z};
        };
    }

    //get block
    public Block getBlock(char symbol){
        if(mapContainsSymbol(symbol)){
            return defineMap.get(symbol);
        }else{
            return Blocks.AIR;
        }
    }

    public TagKey<Block> getBlockTag(char symbol){
        if(tagMapContainsSymbol(symbol)){
            return defineTagMap.get(symbol);
        }else{
            return null;
        }
    }

    public List<Block> getTagBlocks(char symbol){
        if(tagBlocksMapContainsSymbol(symbol)){
            return tagBlocksMap.get(symbol);
        }else{
            return null;
        }
    }

    //structure
    public Pair<List<Pair<BlockPos,Block>>,List<Pair<BlockPos,TagKey<Block>>>> parseStructure(Direction direction, BlockPos machinePos) {
        List<Pair<BlockPos,Block>> blockList = new ArrayList<>();
        List<Pair<BlockPos,TagKey<Block>>> tagBlockList = new ArrayList<>();
        String[][] structure = this.getStructure();

        int[] machineLocation = getMachineLocation(structure);

        int machineX = machineLocation[0];
        int machineY = machineLocation[1];
        int machineZ = machineLocation[2];


        for (int y = 0; y < structure.length; y++) {
            for (int z = 0; z < structure[y].length; z++) {
                String row = structure[y][z];
                for (int x = 0; x < row.length(); x++) {
                    char blockChar = row.charAt(x);

                    int relativeX = x - machineX;
                    int relativeY = machineY - y;
                    int relativeZ = z - machineZ;

                    int[] relativePos = adjustForDirection(relativeX, relativeY, relativeZ, direction);

                    if(this.getBlock(blockChar) != Blocks.AIR){
                        blockList.add(Pair.of(machinePos.offset(relativePos[0],relativePos[1],relativePos[2]),this.getBlock(blockChar)));
                    }
                    if(this.getBlockTag(blockChar) != null){
                        tagBlockList.add(Pair.of(machinePos.offset(relativePos[0],relativePos[1],relativePos[2]),this.getBlockTag(blockChar)));
                    }

                }
            }
        }
        return Pair.of(blockList,tagBlockList);
    }

    public Pair<List<Pair<BlockPos,Block>>,List<Pair<BlockPos,Pair<List<Block>,TagKey<Block>>>>> getRenderStructure(Direction direction, BlockPos machinePos){
        List<Pair<BlockPos,Block>> blockList = new ArrayList<>();
        List<Pair<BlockPos,Pair<List<Block>,TagKey<Block>>>> tagBlockList = new ArrayList<>();
        String[][] structure = this.getStructure();

        int[] machineLocation = getMachineLocation(structure);

        int machineX = machineLocation[0];
        int machineY = machineLocation[1];
        int machineZ = machineLocation[2];


        for (int y = 0; y < structure.length; y++) {
            for (int z = 0; z < structure[y].length; z++) {
                String row = structure[y][z];
                for (int x = 0; x < row.length(); x++) {
                    char blockChar = row.charAt(x);

                    int relativeX = x - machineX;
                    int relativeY = machineY - y;
                    int relativeZ = z - machineZ;

                    int[] relativePos = adjustForDirection(relativeX, relativeY, relativeZ, direction);

                    if(this.getBlock(blockChar) != Blocks.AIR){
                        blockList.add(Pair.of(machinePos.offset(relativePos[0],relativePos[1],relativePos[2]),this.getBlock(blockChar)));
                    }
                    if(this.getTagBlocks(blockChar) != null){
                        tagBlockList.add(Pair.of(machinePos.offset(relativePos[0],relativePos[1],relativePos[2]),Pair.of(getTagBlocks(blockChar),getBlockTag(blockChar))));
                    }

                }
            }
        }
        return Pair.of(blockList,tagBlockList);
    }


    public boolean checkStructure(Level level, Direction direction, BlockPos machinePos){
        if(!level.isClientSide()){
            Pair<List<Pair<BlockPos,Block>>,List<Pair<BlockPos,TagKey<Block>>>> poses = parseStructure(direction,machinePos);
            AtomicInteger i = new AtomicInteger(0);
            int max = poses.getRight().size() + poses.getLeft().size();

            for (int m = 0; m < poses.getRight().size(); m++) {
                var pair = poses.getRight().get(m);
                if(level.getBlockState(pair.getLeft()).is(pair.getRight())){
                    i.getAndIncrement();
                }else{
                    return false;
                }
            }

            for (int m = 0; m < poses.getLeft().size(); m++) {
                var pair = poses.getLeft().get(m);
                if(level.getBlockState(pair.getLeft()).is(pair.getRight())){
                    i.getAndIncrement();
                }else{
                    return false;
                }
            }

            return i.get() == max;
        }
        return false;
    }

    public int getShouldRenderMinY(Level level, Direction direction, BlockPos machinePos){
        Pair<List<Pair<BlockPos,Block>>,List<Pair<BlockPos,TagKey<Block>>>> list = parseStructure(direction,machinePos);
        AtomicInteger minBlockY = new AtomicInteger(level.getMaxBuildHeight());
        AtomicInteger minTagBlockY = new AtomicInteger(level.getMaxBuildHeight());

        list.getLeft().forEach(pair ->{
            if(!level.getBlockState(pair.getLeft()).is(pair.getRight())){
                minBlockY.set(Math.min(pair.getLeft().getY(),minBlockY.get()));
            }
        });

        list.getRight().forEach(pair ->{
            if(!level.getBlockState(pair.getLeft()).is(pair.getRight())){
                minTagBlockY.set(Math.min(pair.getLeft().getY(),minTagBlockY.get()));
            }
        });

        return Math.min(minBlockY.get(),minTagBlockY.get());
    }


    public List<MutableComponent> getMaterialList(){
        HashMap<Character,Integer> map = new HashMap<>();
        String[][] structure = getStructure();
        List<MutableComponent> components = new ArrayList<>();

        for (int i = 0; i < structure.length; i++) {
            for (int m = 0; m < structure[i].length; m++) {
                char[] chars = structure[i][m].toCharArray();
                for (int n = 0; n < chars.length; n++) {
                    char c = chars[n];
                    if(map.containsKey(c)){
                        int value = map.get(c);
                        map.merge(c,value + 1,(oldValue, newValue) ->{
                            return newValue;
                        });
                    }else{
                        map.put(c,1);
                    }
                }
            }
        }


        map.forEach(((character, integer) -> {
            ItemStack stack;
            String tag;
            if(mapContainsSymbol(character)){
                stack = new ItemStack(getBlock(character),integer);
                components.add(Component.literal(integer+"x ").append(Component.translatable(stack.getDescriptionId())));
            }else if(tagMapContainsSymbol(character)){
                tag = getBlockTag(character).location().toString();
                components.add(Component.literal(integer + "x "+ tag));
            }
        }));

        return components;
    }

    public Pair<List<ItemStack>,List<Pair<TagKey<Block>,Integer>>> materialListForJei(){
        HashMap<Character,Integer> map = new HashMap<>();
        String[][] structure = getStructure();
        List<ItemStack> stacks = new ArrayList<>();
        List<Pair<TagKey<Block>,Integer>> tags = new ArrayList<>();

        for (int i = 0; i < structure.length; i++) {
            for (int m = 0; m < structure[i].length; m++) {
                char[] chars = structure[i][m].toCharArray();
                for (int n = 0; n < chars.length; n++) {
                    char c = chars[n];
                    if(map.containsKey(c)){
                        int value = map.get(c);
                        map.merge(c,value + 1,(oldValue, newValue) ->{
                            return newValue;
                        });
                    }else{
                        map.put(c,1);
                    }
                }
            }
        }


        map.forEach(((character, integer) -> {
            ItemStack stack;
            TagKey<Block> tag;
            if(mapContainsSymbol(character)){
                stack = new ItemStack(getBlock(character),integer);
                stacks.add(stack);
            }else if(tagMapContainsSymbol(character)){
                tag = getBlockTag(character);
                tags.add(Pair.of(tag,integer));
            }
        }));

        return Pair.of(stacks,tags);
    }
}
