package net.yxiao233.ifeu.common.utils;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class GameRuleUtil {
    private final Level level;
    public GameRuleUtil(Level level){
        this.level = level;
    }

    public void setGameRule(GameRules.Key<GameRules.BooleanValue> key ,boolean value){
        level.getGameRules().getRule(key).set(value,level.getServer());
    }

    public Boolean getGameRule(GameRules.Key<GameRules.BooleanValue> ruleKey){
        return level.getGameRules().getRule(ruleKey).get();
    }
    public static void setGameRule(Level level, GameRules.Key<GameRules.BooleanValue> key ,boolean value){
        level.getGameRules().getRule(key).set(value,level.getServer());
    }

    public static Boolean getGameRule(Level level, GameRules.Key<GameRules.BooleanValue> ruleKey){
        return level.getGameRules().getRule(ruleKey).get();
    }

}
