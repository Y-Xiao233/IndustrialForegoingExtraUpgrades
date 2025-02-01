package net.yxiao233.ifeu.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BlockBoxHelper {
    private static final Gson Gson = new Gson();
    private int size;
    private JsonArray jsonArray;

    public BlockBoxHelper(String fileName) {
        try {
            InputStream is = BlockBoxHelper.class.getResourceAsStream("/assets/ifeu/models/block/" + fileName + ".json");
            if (is == null) {
                throw new IOException("File not found");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            JsonObject jsonObject = Gson.fromJson(reader, JsonObject.class);
            this.jsonArray = jsonObject.getAsJsonArray("elements");
            this.size = this.jsonArray.size();
        }catch (IOException e){}
    }

    public VoxelShape getVoxelShapes(){
        VoxelShape[] shapes = new VoxelShape[this.size];
            for(int i = 0; i < jsonArray.size(); i ++){
                String from =  jsonArray.get(i).getAsJsonObject().get("from").toString();
                String to = jsonArray.get(i).getAsJsonObject().get("to").toString();
                int[] froms = getXYZ(from);
                int[] tos = getXYZ(to);
                shapes[i] = Block.box(froms[0],froms[1],froms[2],tos[0],tos[1],tos[2]);
            }
        return Shapes.or(shapes[0],shapes);
    }

    private int[] getXYZ(String FROMorTO){
        int[] xyz = new int[3];
        int leftBound = FROMorTO.indexOf("[");
        int rightBound = FROMorTO.indexOf("]");
        int firstIndex = FROMorTO.indexOf(",");
        int lastIndex = FROMorTO.lastIndexOf(",");
        xyz[0] = Integer.valueOf(FROMorTO.substring(leftBound+1,firstIndex)).intValue();
        xyz[1] = Integer.valueOf(FROMorTO.substring(firstIndex+1,lastIndex)).intValue();
        xyz[2] = Integer.valueOf(FROMorTO.substring(lastIndex+1,rightBound)).intValue();
        return xyz;
    }
}
