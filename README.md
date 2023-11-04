# CocoaInput
Support IME - Input Method Editor(Japanese, Chinese, Korean) in Minecraft on Multi-platforms(macOS, Windows, X11).

## Compiling
1. Clone git repository
```Bash
git clone https://github.com/Korea-Minecraft-Forum/CocoaInput.git
cd CocoaInput
```

2. Compile Mod<br>
Type below command.
Forge mod will be located in "forge/build/libs".
Fabric mod will be located in "fabric/build/libs".
```Bash
./gradlew build
```

## Installing
CocoaInput official binaries has been distributed on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/cocoainput).
Get jar from above URL or trying "Compiling" task.
Place it in your mods directory.

CocoaInput requires [MinecraftForge](https://github.com/MinecraftForge/MinecraftForge) or [Fabric](https://github.com/FabricMC/fabric-loader).

## Troubleshooting (macOS 14.0+)
If you are using macOS Sonoma or later versions, you may experience the following issue:
- Some characters are skipped when typing very quickly.
- The client crashes when a system key is pressed (e.g., input source switch).

Most of the causes are due to the Input Tooltip added in Sonoma. However, Apple has not provided an API to disable it.

![macOS Sonoma Indicator](https://github.com/LemonCaramel/caramelChat/assets/45729082/e1d34917-1892-4cb6-aa3f-38fdab58fad9)


You can disable the Input Tooltip system-wide through the following guide.

Open the Terminal and enter the following command:
```Bash
sudo mkdir -p /Library/Preferences/FeatureFlags/Domain
sudo /usr/libexec/PlistBuddy -c "Add 'redesigned_text_cursor:Enabled' bool false" /Library/Preferences/FeatureFlags/Domain/UIKit.plist
```
And then, reboot your Macintosh. This will return you to the input environment from before Sonoma.


## License
Minecraft Mod Public License Japanese Translation
