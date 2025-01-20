package net.yxiao233.ifeu.common.utils;

import net.minecraft.world.level.GameRules;

public enum GameRuleGetter {
    DO_FIRE_TICK("doFireTick",GameRules.RULE_DOFIRETICK),
    MOB_GRIEFING("mobGriefing",GameRules.RULE_MOBGRIEFING),
    KEEP_INVENTORY("keepInventory",GameRules.RULE_KEEPINVENTORY),
    DO_MOB_SPAWNING("doMobSpawning",GameRules.RULE_DOMOBSPAWNING),
    DO_MOB_LOOT("doMobLoot",GameRules.RULE_DOMOBLOOT),
    DO_BLOCK_DROPS("doBlockDrops",GameRules.RULE_DOBLOCKDROPS),
    DO_ENTITY_DROPS("doEntityDrops",GameRules.RULE_DOENTITYDROPS);
    private final String ruleName;
    private final GameRules.Key<GameRules.BooleanValue> ruleKey;
    private GameRuleGetter(String ruleName, GameRules.Key<GameRules.BooleanValue> ruleKey){
        this.ruleName = ruleName;
        this.ruleKey = ruleKey;
    }

    public String getRuleName() {
        return ruleName;
    }

    public GameRules.Key<GameRules.BooleanValue> getRuleKey() {
        return ruleKey;
    }
}
