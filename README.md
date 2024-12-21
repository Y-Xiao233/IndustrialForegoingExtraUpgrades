# Industrial Foregoing: Extra Upgrades
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/industrial-foregoing-extra-upgrades)
- [mcmod](https://www.mcmod.cn/class/17475.html)

### 该mod支持通过KubeJS来注册工业先锋的各种升级(强烈建议配合probejs一起食用)
```javaSrcipt
event.create(id,"industrialforegoing:speed_addon")
event.create(id,"industrialforegoing:efficiency_addon")
event.create(id,"industrialforegoing:processing_addon")

StartupEvents.registry("item", event =>{
    //.setTier(int) 设置升级的等级
    //.setFormTier(int) 默认为设置的等级,可设置为其他整形,用来展示
    event.create("addon_item","industrialforegoing:speed_addon").setTier(15).setFormTier(15)
})
```

### 该mod支持通过KubeJS来添加工业先锋的机器配方(强烈建议配合probejs一起食用)
```javaSrcipt
/*
各种参数详解:
    OutputItem:输出物品[物品]
    InputItem:输入物品[物品]
    InputItems:输入物品列表[一般格式为[InputItem],列表中最多8个元素]
    OutputFluid:输出流体[流体]
    InputFluid:输入流体[流体]
    ProcessingTime:时间[长整形]
    waterNeed:所需水量[整形]
    lavaNeed:所需熔岩量[整形]
    waterConsume:每次消耗水量[整形]
    lavaConsume:每次消耗熔岩量[整形]
    breakChance:破坏方块概率[浮点数]
    result:方块在被破坏后会变成什么[方块]
    defaultRecipe:是否是默认配方[布尔型,默认为false]
    catelyst:催化剂[物品]
    rarity:罕见度[列表]
    entity:实体[字符串]
    pointer:不知道是干什么的[整形,默认为0]
    
*/

//Infuser(灌注器)
event.recipes.ifeu.infuser(OutputItem,InputItem,InputFluid,ProcessingTime)

//StoneWork Generate(造石加工机) -> Crusher(粉碎)
//粉碎配方的输入物品需要是造石机的输出
//如果造石机本身有这个物品配方,则不需要额外添加
event.recipes.industrialforegoing.stonework_generate(OutputItem,waterNeed,lavaNeed,waterConsume,lavaConsume)
event.recipes.industrialforegoing.crusher(OutputItem,InputItem)

//Dissolution Chamber(溶解成形机)
event.recipes.industrialforegoing.dissolution_chamber(OutputItem,InputItems,InputFluid,ProcessingTime)

//Fluid Extractor(液体提取机)
event.recipes.industrialforegoing.fluid_extractor(OutputFluid,InputItem,breakChance,result,defaultRecipe)

//Laser Drill Fluid流体镭射钻基座
event.recipes.industrialforegoing.laser_drill_fluid(OutputFluid,catelyst,rarity,entity,pointer)

//Laser Drill Ore镭射钻基座
event.recipes.industrialforegoing.laser_drill_ore(OutputItem,catelyst,rarity,pointer)


ServerEvents.recipes(event => {
    //Infuser
    event.recipes.ifeu.infuser("minecraft:diamond","minecraft:coal_block",Fluid.of("minecraft:lava",1000),100)

    //StoneWork Generate -> Crusher
    event.recipes.industrialforegoing.stonework_generate("minecraft:blackstone",1000,1000,100,100)
    event.recipes.industrialforegoing.crusher("minecraft:coal","minecraft:blackstone")

    //StoneWork Generate(Original) -> Crusher
    event.recipes.industrialforegoing.crusher("minecraft:redstone","minecraft:netherrack")

    //Dissolution Chamber
    event.recipes.industrialforegoing.dissolution_chamber("minecraft:diamond",[
        "minecraft:coal_block","minecraft:coal_block",
        "minecraft:coal_block","minecraft:coal_block",
        "minecraft:coal_block","minecraft:coal_block",
        "minecraft:coal_block","minecraft:coal_block"
    ],Fluid.of("minecraft:lava",8000),200)


    //Fluid Extractor
    event.recipes.industrialforegoing.fluid_extractor(Fluid.of("minecraft:lava"),"minecraft:stone",0.01,"minecraft:air")

    //Laser Drill Fluid
    event.recipes.industrialforegoing.laser_drill_fluid(Fluid.of("minecraft:milk",50),'industrialforegoing:laser_lens0',[{
        "blacklist": {
            "type": "minecraft:worldgen/biome",
            "values": [
                "minecraft:the_end",
                "minecraft:the_void",
                "minecraft:small_end_islands",
                "minecraft:end_barrens",
                "minecraft:end_highlands",
                "minecraft:end_midlands"
            ]
        },
        "depth_max": 256,
        "depth_min": -64,
        "weight": 8,
        "whitelist": {}
    }],"minecraft:cow")


    //Laser Drill Ore
    event.recipes.industrialforegoing.laser_drill_ore("minecraft:sculk_catalyst","ifeu:laser_lens_sculk",[{
        "blacklist": {
            "type": "minecraft:worldgen/biome",
            "values": [
                "minecraft:the_end",
                "minecraft:the_void",
                "minecraft:small_end_islands",
                "minecraft:end_barrens",
                "minecraft:end_highlands",
                "minecraft:end_midlands"
            ]
        },
        "depth_max": 256,
        "depth_min": -64,
        "weight": 8,
        "whitelist": {}
    }])
})
```