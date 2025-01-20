package net.yxiao233.ifeu.common.utils;

import net.minecraft.server.level.ServerLevel;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;

public enum TimeGetter {
    NOON("noon",6000),
    DAY("day",1000),
    NIGHT("night",13000),
    MIDNIGHT("midnight",18000),
    D("d",0),
    S("s",0),
    T("t",0);
    private String name;
    private long time;
    private TimeGetter(String name,long time){
        this.name = name;
        this.time = time;
    }

    public TimeGetter modifyTime(long time){
        this.time = time;
        return this;
    }

    public long getTime() {
        return time;
    }

    public boolean hasTexture(){
        return !this.name.equals("d") && !this.name.equals("t") && !this.name.equals("s");
    }

    public AllGuiTextures getTexture(){
        return switch (this.name) {
            case "day" -> AllGuiTextures.DAY;
            case "noon" -> AllGuiTextures.NOON;
            case "night" -> AllGuiTextures.NIGHT;
            case "midnight" -> AllGuiTextures.MIDNIGHT;
            default -> AllGuiTextures.EMPTY;
        };
    };

    public String getName() {
        return name;
    }

    public void setTime(ServerLevel serverLevel){
        serverLevel.setDayTime(this.time);
    }
}
