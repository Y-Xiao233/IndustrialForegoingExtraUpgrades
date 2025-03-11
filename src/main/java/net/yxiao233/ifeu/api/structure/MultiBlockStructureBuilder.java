package net.yxiao233.ifeu.api.structure;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MultiBlockStructureBuilder {
    private final ArrayList<ArrayList<String>> structure = new ArrayList<>();
    private final HashMap<Character, Block> defineMap;
    private final HashMap<Character, TagKey<Block>> defineTagMap;
    public MultiBlockStructureBuilder(){
        this.defineMap = new HashMap<>();
        this.defineTagMap = new HashMap<>();
    }
    public MultiBlockStructureBuilder pattern(ArrayList<String> s){
        structure.add(s);
        return this;
    }

    public MultiBlockStructureBuilder pattern(String... strings){
        structure.add(new ArrayList<>(Arrays.asList(strings)));
        return this;
    }

    public MultiBlockStructureBuilder pattern(List<String> s){
        structure.add(new ArrayList<>(s));
        return this;
    }
    public String[][] getStructure(){
        int size1 = structure.size();
        int size2 = structure.get(0).size();
        String[][] temp = new String[size1][size2];

        for (int i = 0; i < temp.length; i++) {
            for (int m = 0; m < temp[i].length; m++) {
                temp[i][m] = structure.get(i).get(m);
            }
        }

        return temp;
    }


    public MultiBlockStructureBuilder define(char symbol, Block block){
        this.defineMap.put(symbol,block);
        return this;
    }

    public MultiBlockStructureBuilder define(char symbol, TagKey<Block> blockTag){
        this.defineTagMap.put(symbol,blockTag);
        return this;
    }

    public MultiBlockStructureBuilder define(char symbol, DeferredHolder<Block,Block> block){
        define(symbol,block.get());
        return this;
    }

    public HashMap<Character, Block> getDefineMap() {
        return defineMap;
    }

    public HashMap<Character, TagKey<Block>> getDefineTagMap() {
        return defineTagMap;
    }

    public MultiBlockStructure build(){
        return new MultiBlockStructure(this);
    }
}
