package net.yxiao233.ifeu.common.utils;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.yxiao233.ifeu.common.gui.AllGuiTextures;

import java.util.Objects;

public enum WeatherGetter {
    WEATHER_CLEAR("clear",AllGuiTextures.WEATHER_CLEAR),
    WEATHER_RAIN("rain",AllGuiTextures.WEATHER_RAIN),
    WEATHER_THUNDER("thunder",AllGuiTextures.WEATHER_THUNDER);
    private String name;
    private AllGuiTextures texture;
    private WeatherGetter(String name,AllGuiTextures texture){
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public AllGuiTextures getTexture(){
        return texture;
    }
    public void setWeather(ServerLevel serverLevel){
        if(this.name.equals(WEATHER_CLEAR.name)){
            serverLevel.setWeatherParameters(getDuration(serverLevel,-1,ServerLevel.RAIN_DELAY),0,false,false);
        }else if(this.name.equals(WEATHER_RAIN.name)){
            serverLevel.setWeatherParameters(0,getDuration(serverLevel,-1,ServerLevel.RAIN_DURATION),true,false);
        }else if(this.name.equals(WEATHER_THUNDER.name)){
            serverLevel.setWeatherParameters(0,getDuration(serverLevel,-1,ServerLevel.THUNDER_DURATION),true,true);
        }
    }

    private static int getDuration(ServerLevel serverLevel, int time, IntProvider pTimeProvider) {
        return time == -1 ? pTimeProvider.sample(serverLevel.getRandom()) : time;
    }
}
