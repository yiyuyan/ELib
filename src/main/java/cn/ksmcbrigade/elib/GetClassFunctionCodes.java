package cn.ksmcbrigade.elib;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.elib.Libs.*;

@Mod.EventBusSubscriber
public class GetClassFunctionCodes {

    public static final String Command_Name = "GetClassFunctionCodes";

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal(Command_Name).then(Commands.argument("class",StringArgumentType.string()).executes(arg -> {
            String[] args = arg.getInput().split(" ");
            Player player = (Player) arg.getSource().getEntity();
            try {
                String[] functions = getFunctionNamesFromString(args[1].replace("\"",""));
                player.sendMessage(Component.nullToEmpty("{class} class functions:".replace("{class}",args[1].replace("\"",""))),player.getUUID());
                for(String function:functions){
                    player.sendMessage(Component.nullToEmpty(function),player.getUUID());
                }
            } catch (ClassNotFoundException e) {
                player.sendMessage(Component.nullToEmpty("Exception:"),player.getUUID());
                for(String exception:e.toString().split("\n")){
                    player.sendMessage(Component.nullToEmpty(exception),player.getUUID());
                }
            }
            return 0;
        }).then(Commands.argument("function",StringArgumentType.string()).executes(arg -> {
            String[] args = arg.getInput().split(" ");
            Player player = (Player) arg.getSource().getEntity();
            try {
                String[] functionCode = getFunctionCodeFromString(args[1].replace("\"",""),args[2].replace("\"","")).split("\n");
                player.sendMessage(Component.nullToEmpty("{class} {functionName} function codes:".replace("{functionName}",args[2].replace("\"","")).replace("{class}",args[1].replace("\"",""))),player.getUUID());
                for(String code:functionCode){
                    player.sendMessage(Component.nullToEmpty(code),player.getUUID());
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            return 0;
        }))));
    }
}
