# Industrial Foregoing: Extra Upgrades
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/industrial-foregoing-extra-upgrades)
- [mcmod](https://www.mcmod.cn/class/17475.html)

### 该mod支持通过KubeJS来注册工业先锋的各种升级
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

### 该mod支持通过KubeJS来添加工业先锋的机器配方(目前仅支持本mod新添加的机器)
```javaSrcipt
event.recipes.ifeu.infuser(OutputItem,InputItem,InputFluid,ProcressingTime)

ServerEvents.recipes(event => {
    event.recipes.ifeu.infuser("minecraft:diamond","minecraft:coal",Fluid.of("minecraft:lava",1000),100)
})
```