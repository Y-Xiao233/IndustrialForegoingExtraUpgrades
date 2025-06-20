# Industrial Foregoing: Extra Upgrades Changelog

## zh_cn
### v1.0.0
- 初始化

### v1.0.1
- 修复了造石机和流体提取器kubejs适配错误的bug
- 修复了龙之套具伤害异常的bug

### v1.1.000
- 将1.20.1-2.0.103内容更新至当前版本

### v1.1.001
- 修复了流体工作台输出物品在数量改变时不会消耗输入物品的bug
- 为流体工作台添加了无序配方

### v1.1.002
- 为工业先锋溶解成形机添加了jei一键转移配方的支持

### v1.1.003
- 添加了龙发电机
- 修改了龙之星发电机的默认发电量
- 调整了部分源代码结构

### v1.1.004
- 将1.20.1-forge-2.0.107的内容同步至此版本

### v1.1.005
- 修复了制作出来的连接工具没有任务工具提示的bug
- 为流体传输器添加了默认可连接距离的提示
- 修复了连接工具部分提示错误的bug
- 添加了扳手(可扳下所有工业先锋的机器,以及带"ifeu:wrench_pickup"标签的方块)

### v1.1.006
- 调整了连接工具清除配置的逻辑(改为了对空气shift右键清除)
- 添加了配置工具(仅会复制粘贴传输面配置)

### v1.1.007
- 配置工具可以同步配置各种插件了

### v1.2.000
- 修复了无法连接至服务器的bug
- 修复了服务端无法打开时间,天气掌控者的bug
- 修复了服务端无法监听ctrl等按键按下的bug

### v1.2.100
- 修改了流体传输器输入输出的按钮图标
- 修改了部分物品配方
- 添加了大型溶解成形机
- 添加了新的插件: 线程
- 修复了流体传输器配方错误的bug

### v1.2.101
- 修复了扳手扳掉有nbt的物品时会错误将其nbt清除的bug
- 各种框架也可以被扳手扳下了

### v1.2.102
- 优化了大型溶解成型机
- 更新前置工业先锋版本至3.6.24
- 更新前置钛版本至4.0.35
- 修复了部分配方无效的bug

### v1.2.103
- 修复了与KubeJS同装时,配方正确但会报错的bug

### v1.2.104
- 降低了速度升级数值
- 更新了jp_jp[\#36 by twister716](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/36)

### v1.2.105
- 更改了部分文件结构
- 添加了KubeJS对本模组多方块机器结构的支持(可以使用KubeJS修改机器所需的结构)

### v1.2.106
- 为灌注器新添了一些配方
- 添加了新的插件(苹果插件,用于水培床增加苹果产量)
- 添加了下界之星增产方法

### v1.2.107
- 为大部分配方添加了内建的builder类
- 为苹果插件添加了KubeJS支持
- 添加了灌注器内液态龙息+玻璃瓶合成龙息的配方

### v1.2.108
- 将作为前置的工业先锋的版本更新至1.21-3.6.29
- 修复了由于mixin注入失败导致的游戏崩溃[\#49 by penturt](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/49)
- 为模拟水培床也添加了苹果插件支持

### v1.2.109
- 可以在KubeJS中添加多方块的渲染了(目前仅支持有方块实体的方块)
- TooltipHelper, RotatableBlock目前默认绑定至KubeJS了,不需要额外对其loadClass

### v3.0.000
- 版本名更改至高于1.20.1的版本名
- 前置工业先锋的版本限制为1.21-3.6.29及以上
- 修复了不安装KubeJS无法进入游戏的bug
- 更改了一些命名
- 更新了KubeJS及其依赖的版本至当前最新版

### v3.1.000
- 添加了平台建造器[WIP!]
- 新增特性,手持连接器时高亮显示被链接的两个方块
- 新增特性,配置工具粘贴配置时背包无对应升级,聊天栏提示
- 为所有多方块结构添加了jei查看所需材料的支持
- 使用KubeJS添加的多方块渲染现在可以只在玩家手持特殊物品时才渲染了
- 修改了大部分与KubeJS有关的命名"IFEUEvents -> IFEUStructureEvents,structureModify -> modify,structureRender -> render"(详情见README.md)
- 添加了一个新的KubeJS事件"IFEUStructureEvents.registry"(详情见README.md)
- 修改了大部分IFEUStructureEvents的用法(详情见README.md)
- 修复了流体工作台在debug中疯狂输出null的bug

### v3.1.001
- 修复了部分贴图错误的bug

### v3.1.002
- 修复了制作奥数龙蛋锻造机配方时导致崩溃的bug[\#67 by gmofi](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/67)

### v3.1.003
- 添加了插件: 精准
- 调整了下界之星再生配方的不合理比例
- 几乎重写了连接工具的代码

### v3.1.004
- 添加了插件: 治愈
- 调整了部分配方的错误流体输入


## en_us
### v1.0.0
- init

### v1.0.1
- Fixed the bug of kubejs compact error for the stone work generator and fluid extractor
- Fixed the bug of abnormal damage caused by the Dragon Star Kit

### v1.1.000
- Update the content of 1.20.1-2.0.103 to the current version

### v2.0.104
- Fixed bug where fluid crafting table output items do not consume input items when quantity changes
- Added shapeless recipe to the fluid crafting table

### v1.1.002
- Added support for JEI one key transfer recipe to the dissolution chamber

### v1.1.003
- Added Dragon Generator
- Modified the default power generation of the Dragon Star generator
- Adjusted some of the source code structure

### v1.1.004
- Update the content of 1.20.1-2.0.107 to the current version

### v1.1.005
- Fixed the bug where the created connection tool did not have tooltips
- Added a tooltip for default connectable distance for fluid transfer
- Fixed the bug where the connection tool displayed an error message
- Added wrench (capable of pulling down all industrial foregoing machines, as well as blocks labeled with "ifeu: wrench_pickup")

### v1.1.006
- Adjusted the logic of clearing configuration for the connection tool (changed to shift + right-click clearing configuration)
- Added configuration tool (only capable of copying and pasting transmission surface configurations)

### v1.1.007
- The configuration tool can synchronize the configuration of various addons now

### v1.2.000
- Fixed bug where unable to connect to the server
- Fixed the bug where the server cannot open the time and weather controller
- Fixed the bug where the server could not listen for keystrokes such as Ctrl

### v1.2.100
- Modified the button icon for the input and output of the fluid transfer
- Modified some items' recipe
- Added a big dissolution chamber
- Added a new addon: thread
- Fixed the bug of incorrect recipe for fluid transfer

### v1.2.101
- Fixed the bug where the wrench mistakenly cleared the nbt of items with nbt when pulling them off
- Various machine frame can also be pulled down with a wrench

### v1.2.102
- Optimized the big dissolution chamber
- Update the industrial foregoing version to 3.6.24
- Update the titanium version to 4.0.35
- Fixed a bug where some recipes were invalid

### v1.2.103
- Fixed a bug where the recipe was correct but errors occurred when installed with KubeJS

### v1.2.104
- 降低了速度升级数值
- Update jp_jp[\#36 by twister716](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/36)

### v1.2.105
- Changed some file structures
- Added KubeJS support for the MultiBlock machine structure of this mod (you can use KubeJS to modify the structure required by the machine)

### v1.2.106
- Added some new recipes to the infuser
- Added a new addon (Apple addon for increasing apple yield in hydroponic beds)
- Added the Lower bound Star method for increasing production

### v1.2.107
- Added built-in builder class for most recipes
- Added KubeJS support for Apple Addon
- Added the recipe of liquid Dragon Breath in the infuser and Dragon Breath in glass bottles

### v1.2.108
- Update the version of the industrial foregoing to 1.21-3.6.29
- Fixed game crash caused by mixin injection failure[\#49 by penturt](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/49)
- Apple addon support has also been added to simulated hydroponic bed

### v1.2.109
- MultiBlock rendering can now be added in KubeJS (currently only supports blocks with block entity)
- TooltipHelper, RotatableBlock is currently bound to KubeJS by default, and there is no need to separately load its class

### v3.0.000
- Change the version name higher than 1.20.1
- The version limit for  industrial foregoing is 1.21-3.6.29 and above
- Fixed bug where unable to enter the game without installing KubeJS
- Changed some names
- Updated KubeJS and its dependencies to the current latest version

### v3.1.000
- Added platform builder[WIP!]
- New feature: Highlight the two linked fluid transfer blocks when holding the connect tool
- New feature: When the configuration tool pastes the configuration, there is no corresponding upgrade for the inventory, and the chat bar prompts
- Added support for viewing required materials for all MultiBlock structures using JEI
- The MultiBlock rendering added using KubeJS can now only be rendered when the player is holding a special item
- Modified most of the naming conventions related to KubeJS "IFEUEvents -> IFEUStructureEvents,structureModify -> modify,structureRender -> render"(For more details, please refer to README.md)
- Added a new KubeJS event "IFEUStructureEvents.registry"(For more details, please refer to README.md)
- Modified the usage of most IFEUStructureEvents(For more details, please refer to README.md)
- Fixed the bug where the fluid crafting table output "null" frantically in debug

### v3.1.001
- Fixed a bug with some texture errors

### v3.1.002
- Fixed a bug that caused a crash when making the recipe for the Arcane Dragon Egg forging machine[\#67 by gmofi](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/67)

### v3.1.003
- Added addon: Silk
- Adjusted the unreasonable proportion of the regeneration recipe for the nether star
- Almost rewrote the code of the connect tool

### v3.1.004
- Added addon: Heal
- Adjusted some incorrect fluid inputs in the recipes