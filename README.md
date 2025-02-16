# Industrial Foregoing: Extra Upgrades
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/industrial-foregoing-extra-upgrades)
- [mcmod](https://www.mcmod.cn/class/17475.html)

## 该mod支持通过KubeJS来注册工业先锋的各种升级(强烈建议配合probejs一起食用)

### 注册插件
- 插件: 速度```event.create(id,"industrialforegoing:speed_addon").setTier(tier).setFormTier(formTier)```
- 插件: 效率```event.create(id,"industrialforegoing:efficiency_addon").setTier(tier).setFormTier(formTier)```
- 插件: 处理```event.create(id,"industrialforegoing:processing_addon").setTier(tier).setFormTier(formTier)```

### 示例
```JavaScript
StartupEvents.registry("item", event =>{
    //.setTier(int) 设置升级的等级
    //.setFormTier(int) 默认为设置的等级,可设置为其他整形,用来展示
    event.create("addon_item","industrialforegoing:speed_addon").setTier(15).setFormTier(15)
})
```

## 该mod支持通过KubeJS来添加工业先锋的机器配方(强烈建议配合probejs一起食用)
- Infuser 灌注器```event.recipes.ifeu.infuser(OutputItem,InputItem,InputFluid,ProcessingTime)```
```
参数详解:
    OutputItem:输出物品[ItemStack]
    InputItem:输入物品[ItemStack]
    InputFluid:输入流体[FluidStack]
    ProcessingTime:时间[long,单位为tick]
```

- Arcane Dragon Egg Forging 奥数龙蛋锻造炉```event.recipes.ifeu.arcane_dragon_egg_forging(OutputItem,InputItem,InputFluid1,InputFluid2,ProcessingTime,OutputFluid)```
```
参数详解:
    OutputItem:输出物品[ItemStack]
    InputItem:输入物品[ItemStack]
    InputFluid1:输入流体[FluidStack]
    InputFluid2:输入流体[FluidStack]
    ProcessingTime:时间[long,单位为tick]
    OutputFluid:输出流体[FluidStack,可选,默认为空]
```

- Shaped Fluid Crafting Recipe 有序流体工作台```event.recipes.ifeu.shaped(OutputItem,InputItems,InputFluid)```
```
参数详解:
    OutputItem:输出物品[ItemStack]
    InputItems:输入物品[[ItemStack...],最多9个输入物品]
    InputFluid:输入流体[FluidStack]
```

- Shapeless Fluid Crafting Recipe 有序流体工作台```event.recipes.ifeu.shapeless(OutputItem,InputItems,InputFluid)```
```
参数详解:
    OutputItem:输出物品[ItemStack]
    InputItems:输入物品[[ItemStack...],最多9个输入物品]
    InputFluid:输入流体[FluidStack]
```

- StoneWork Generate 造石加工机```event.recipes.industrialforegoing.stonework_generate(OutputItem,waterNeed,lavaNeed,waterConsume,lavaConsume)```
```
参数详解:
    OutputItem:输出物品[ItemStack]
    waterNeed:所需水量[int]
    lavaNeed:所需熔岩量[int]
    waterConsume:每次消耗水量[int]
    lavaConsume:每次消耗熔岩量[int]
```

- Crusher 粉碎```event.recipes.industrialforegoing.crusher(OutputItem,InputItem)```
```
粉碎配方的输入物品需要是造石机的输出
如果造石机本身有这个物品配方,则不需要额外添加

参数详解:
	OutputItem:输出物品[Ingredient]
	InputItem:输入物品[Ingredient]
```

- Dissolution Chamber 溶解成形机```event.recipes.industrialforegoing.dissolution_chamber(OutputItem,InputItems,InputFluid,ProcessingTime,OutputFluid)```
```
参数详解:
	OutputItem:输出物品[ItemStack]
	InputItems:输入物品列表[[Ingredient...],最多8个输入物品]
	InputFluid:输入流体[FluidStack]
	ProcessingTime:时间[long,单位为tick]
	OutputFluid:输出流体[FluidStack,可选,默认为空]
```

- Fluid Extractor 液体提取机```event.recipes.industrialforegoing.fluid_extractor(OutputFluid,InputItem,breakChance,result,defaultRecipe)```
```
参数详解:
    OutputFluid:输出流体[FluidStack]
    InputItem:输入物品列表[Ingredient]
    breakChance:破坏概率[float]
    result:方块在被破坏后会变成什么[BlockState]
    defaultRecipe:是否为默认配方[booleam,默认为false,若配方添加不成功请在结尾写上该参数]
```

### 示例
```JavaScript
ServerEvents.recipes(event => {
    //Infuser
    event.recipes.ifeu.infuser("minecraft:diamond","minecraft:coal_block",Fluid.of("minecraft:lava",1000),100)
    
    //Arcane Dragon Egg Forging
    event.recipes.ifeu.arcane_dragon_egg_forging("16x minecraft:egg","minecraft:dragon_egg",Fluid.of("minecraft:water",1000),Fluid.of("minecraft:lava",1000),200,Fluid.of("minecraft:water",100))

    //Shaped Fluid Crafting Recipe
    //顺序写入物品,如当前格子输入为空请写为['ifeu:air']
    /*下面示例配方为该样式
        '   ',
        ' A ',
        '   '
    */
    
    event.recipes.ifeu.shaped('4x minecraft:oak_planks',[
        'ifeu:air','ifeu:air','ifeu:air',
        'ifeu:air','minecraft:oak_log','ifeu:air',
        'ifeu:air','ifeu:air','ifeu:air'
    ],Fluid.of("minecraft:water"))
    
    //Shapeless Fluid Crafting Recipe
    //随意写入,有几个写几个
    event.recipes.ifeu.shapeless('4x minecraft:oak_planks',['minecraft:oak_log'],Fluid.of("minecraft:water"))
    
    
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
    event.recipes.industrialforegoing.fluid_extractor(Fluid.of("minecraft:water",2),Item.of('minecraft:wet_sponge'),0.1,Blocks.SPONGE.defaultBlockState(),false)
})
```