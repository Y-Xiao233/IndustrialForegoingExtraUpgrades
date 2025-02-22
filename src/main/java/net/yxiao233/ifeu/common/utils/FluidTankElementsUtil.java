package net.yxiao233.ifeu.common.utils;

import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import net.minecraft.world.item.DyeColor;

public class FluidTankElementsUtil {
    private String id;
    private int xPos;
    private int yPos;
    private int position;
    private DyeColor color;
    private FluidTankComponent.Type type;
    private static final FluidTankElementsUtil DEFAULT = new FluidTankElementsUtil("input_fluid",44,19,0,DyeColor.LIME, FluidTankComponent.Type.NORMAL);
    public FluidTankElementsUtil(){
        this.id = DEFAULT.id;
        this.xPos = DEFAULT.xPos;
        this.yPos = DEFAULT.yPos;
        this.position = DEFAULT.position;
        this.color = DEFAULT.color;
        this.type = DEFAULT.type;
    }
    public FluidTankElementsUtil(String id, int xPos, int yPos, int position, DyeColor color, FluidTankComponent.Type type){
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.position = position;
        this.color = color;
        this.type = type;
    }

    public FluidTankElementsUtil setId(String id) {
        this.id = id;
        return this;
    }

    public FluidTankElementsUtil setXPos(int xPos) {
        this.xPos = xPos;
        return this;
    }

    public FluidTankElementsUtil setYPos(int yPos) {
        this.yPos = yPos;
        return this;
    }


    public FluidTankElementsUtil setPosition(int position) {
        this.position = position;
        return this;
    }

    public FluidTankElementsUtil setColor(DyeColor color) {
        this.color = color;
        return this;
    }

    public FluidTankElementsUtil setType(FluidTankComponent.Type type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }


    public int getPosition() {
        return position;
    }

    public DyeColor getColor() {
        return color;
    }

    public FluidTankComponent.Type getType() {
        return type;
    }
}
