# Industrial Foregoing: Extra Upgrades Changelog

## zh_cn
### v1.0.0
- 初始化

### v1.0.1
- 添加了模组机器的配置文件

### v1.0.2
- 添加了配方以及插件:速度/效率/加工的KubeJS支持

### v1.0.3
- 为液态龙息以及液态幽匿物质添加了放置粒子效果以及声音(可在模组对应的config文件配置)
- 添加了龙之星发电机

### v1.0.4
- 添加了工业先锋所有支持json文件添加配方的KubeJS支持
- 为该mod新添加的灌注器添加了所有流体支持
- 添加了各种龙之星等级的工具

### v1.0.5
- 添加了日语翻译[\#1 by twister716](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/1)

### v1.0.6
- 添加了奥数龙蛋锻造炉,同时也支持使用KubeJS添加配方
- 添加了创造电容器
- 添加了失活的龙蛋,龙 镭射聚焦透镜

### v1.0.7
- 为龙之星发电机添加了jei集成,并删除了直接的tooltip
- 为两个仅作展示的jei配方添加了提示
- 修复了溶解成形机和奥数龙蛋锻造炉的可选流体输出在使用KubeJS添加时无法正确被添加的bug

### v1.0.8
- 将失活的龙蛋 -> 龙蛋配方中的下界之星替换为了龙之星
- 添加了时间,天气,规则掌控者
- 添加了终极机器框架,龙之星块
- 修改了部分配方

### v1.0.9
- 为时间掌控者添加了可调节按钮信息
- 修复了部分情况按住shift/ctrl时无法粗调某些值

### v1.0.91
- 修复了在游戏中使用reload指令会导致配方jei出现各种问题的bug

### v2.0.0
- 修复了在失活的龙蛋服务端加载时报错的bug[\#3 by IceBlues](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/3)

### v2.0.100
- 添加了流体合成台

### v2.0.101
- 为流体工作台添加了可配置的流体渲染按钮
- 为流体工作台添加了KubeJS集成

### v2.0.102
- 更新了jei的版本,并根据其修改内容更改了mixin内容
- 修复了两个有关kjs集成的bug

### v2.0.103
- 修复了流体工作台无法正常挖掘的bug
- 修复了服务端JEI被破坏的bug[\#8 by MashiroYae](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/8)

### v2.0.104
- 修复了流体工作台输出物品在数量改变时不会消耗输入物品的bug
- 为流体工作台添加了无序配方

### v2.0.105
- 为工业先锋溶解成形机添加了jei一键转移配方的支持

### v2.0.106
- 添加了龙发电机
- 修改了龙之星发电机的默认发电量
- 调整了部分源代码结构

### v2.0.107
- 添加了流体传输器
- 添加了KubeJS对范围升级注册支持

### v2.0.108
- 修复了制作出来的连接工具没有任务工具提示的bug
- 为流体传输器添加了默认可连接距离的提示
- 修复了连接工具部分提示错误的bug
- 添加了扳手(可扳下所有工业先锋的机器,以及带"ifeu:wrench_pickup"标签的方块)

### v2.0.109
- 调整了连接工具清除配置的逻辑(改为了对空气shift右键清除)
- 添加了配置工具(仅会复制粘贴传输面配置)

### v2.0.110
- 配置工具可以同步配置各种插件了

### v2.1.000
- 修复了服务端无法打开时间,天气掌控者的bug
- 修复了服务端无法监听ctrl等按键按下的bug

### v2.1.100
- 修改了流体传输器输入输出的按钮图标
- 修改了部分物品配方
- 添加了大型溶解成形机
- 添加了黑洞电容器
- 添加了新的插件: 线程

### v2.1.101
- 修复了扳手扳掉有nbt的物品时会错误将其nbt清除的bug
- 各种框架也可以被扳手扳下了

### v2.1.101-hotfix
- 修复了mixin导致的崩溃

### v2.1.102
- 优化了大型溶解成型机

### v2.1.103
- 降低了速度升级数值
- 更新了jp_jp[\#36 by twister716](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/36)

### v2.1.104
- 更改了部分文件结构
- 添加了KubeJS对本模组多方块机器结构的支持(可以使用KubeJS修改机器所需的结构)

### v2.1.105
- 为灌注器新添了一些配方
- 添加了新的插件(苹果插件,用于水培床增加苹果产量)
- 添加了下界之星增产方法

### v2.1.106
- 为大部分配方添加了内建的builder类
- 为苹果插件添加了KubeJS支持
- 添加了灌注器内液态龙息+玻璃瓶合成龙息的配方

### v2.1.107
- 将工业先锋1.21中新添加的特性移植到了1.20

### v2.1.108
- 可以在KubeJS中添加多方块的渲染了(目前仅支持有方块实体的方块)
- TooltipHelper, RotatableBlock目前默认绑定至KubeJS了,不需要额外对其loadClass

### v2.2.000
- 修复了不安装KubeJS无法进入游戏的bug
- 更改了一些命名
- 更新了KubeJS及其依赖的版本至当前最新版

### v2.2.001
- 添加了平台建造器[WIP!]
- 新增特性,手持连接器时高亮显示被链接的两个方块
- 新增特性,配置工具粘贴配置时背包无对应升级,聊天栏提示
- 修复了水培床模拟处理器的部分工具提示错误[\#57 by TheDesiringOne](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/57)

## en_us
### v1.0.0
- init

### v1.0.1
- Added Config file for mod machines

### v1.0.2
- Added KubeJS support for "recipe and Addon: speed/efficiency/processing"

### v1.0.3
- Added particles and sound for liquid dragon breath and liquid sculk matter (can be configured in the corresponding config file of the mod)
- Added Dragon Star generator

### v1.0.4
- Added KubeJS support for IndustrialForegoing's "All Supports Adding recipe to JSON Files" feature
- Added all fluid support for the newly added Infuser in this mod
- Added various Dragon Star Tier tools

### v1.0.5
- Add japanese translation[\#1 by twister716](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/1)

### v1.0.6
- Added Arcane Dragon Egg Forging,It also supports adding recipes using KubeJS
- Added Creative Capacitor
- Added Dragon Laser Lens

### v1.0.7
- Added JEI integration for the Dragon Star Generator and removed the direct tooltip
- Added tips for two JEI recipes for display purposes only
- Fixed a bug where the optional fluid output of the Dissolution Chamber and the Arcane Dragon Egg Forging could not be correctly added when using KubeJS
- 
### v1.0.8
- Replace the Nether Star to Dragon Star of the recipe: Dead Dragon Egg -> Dragon Egg
- Added time, weather, and rule controller
- Added Ultimate Machine framework, Block of Dragon Star
- Modified some recipes

### v1.0.9
- Added adjustable button information for time controllers
- Fixed some situations where holding down shift/ctrl prevents coarse adjustment of certain values

### v1.0.91
- Fixed a bug where using the reload command in the game would cause various issues with the jei

### v2.0.0
- Fixed the bug of reporting errors when loading on the server[\#3 by IceBlues](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/3)

### v2.0.100
-Added fluid crafting table

### v2.0.101
-Added configurable fluid rendering buttons for the fluid crafting table
-Added KubeJS integration for fluid crafting table

### v2.0.102
-Updated the version of JEI and changed the mixin content based on its modifications

### v2.0.103
- Fixed the bug where the fluid crafting table could not excavate properly
- Fixed the bug where the server-side JEI was compromised[\#8 by MashiroYae](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/8)

### v2.0.104
- Fixed bug where fluid crafting table output items do not consume input items when quantity changes
- Added shapeless recipe to the fluid crafting table

### v2.0.105
- Added support for JEI one key transfer recipe to the dissolution chamber

### v2.0.106
- Added Dragon Generator
- Modified the default power generation of the Dragon Star generator
- Adjusted some of the source code structure

### v2.0.107
- Added Fluid Transfer
- Added KubeJS support for addon upgrade registration

### v2.0.108
- Fixed the bug where the created connection tool did not have tooltips
- Added a tooltip for default connectable distance for fluid transfer
- Fixed the bug where the connection tool displayed an error message
- Added wrench (capable of pulling down all industrial foregoing machines, as well as blocks labeled with "ifeu: wrench_pickup"

### v2.0.109
- Adjusted the logic of clearing configuration for the connection tool (changed to shift + right-click clearing configuration)
- Added configuration tool (only capable of copying and pasting transmission surface configurations)

### v2.0.110
- The configuration tool can synchronize the configuration of various addons now

### v2.1.000
- Fixed the bug where the server cannot open the time and weather controller
- Fixed the bug where the server could not listen for keystrokes such as Ctrl

### v2.1.100
- Modified the button icon for the input and output of the fluid transfer
- Modified some items' recipe
- Added a big dissolution chamber
- Added black hole capacitor
- Added a new addon: thread

### v2.1.101
- Fixed the bug where the wrench mistakenly cleared the nbt of items with nbt when pulling them off
- Various machine frame can also be pulled down with a wrench

### v2.1.101-hotfix
- Fixed the crash caused by mixin

### v2.1.102
- Optimized the big dissolution chamber

### v2.1.103
- 降低了速度升级数值
- Update jp_jp[\#36 by twister716](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/pull/36)

### v2.1.104
- Changed some file structures
- Added KubeJS support for the MultiBlock machine structure of this mod (you can use KubeJS to modify the structure required by the machine)

### v2.1.105
- Added some new recipes to the infuser
- Added a new addon (Apple addon for increasing apple yield in hydroponic beds)
- Added the Lower bound Star method for increasing production

### v2.1.106
- Added built-in builder class for most recipes
- Added KubeJS support for Apple Addon
- Added the recipe of liquid Dragon Breath in the infuser and Dragon Breath in glass bottles

### v2.1.107
- Transplanted the newly added features from Industrial Foregoing 1.21 to 1.20

### v2.1.108
- MultiBlock rendering can now be added in KubeJS (currently only supports blocks with block entity)
- TooltipHelper, RotatableBlock is currently bound to KubeJS by default, and there is no need to separately load its class

### v2.2.000
- Fixed bug where unable to enter the game without installing KubeJS
- Changed some names
- Updated KubeJS and its dependencies to the current latest version

### v2.2.001
- Added platform builder[WIP!]
- New feature: Highlight the two linked fluid transfer blocks when holding the connect tool
- New feature: When the configuration tool pastes the configuration, there is no corresponding upgrade for the inventory, and the chat bar prompts
- Fixed some tooltip errors in the hydroponic bed simulation processor[\#57 by TheDesiringOne](https://github.com/Y-Xiao233/IndustrialForegoingExtraUpgrades/issues/57)