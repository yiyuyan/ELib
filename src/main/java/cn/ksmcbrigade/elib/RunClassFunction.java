package cn.ksmcbrigade.elib;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.elib.Libs.*;
import static cn.ksmcbrigade.elib.Libs.runFunction;

@Mod.EventBusSubscriber
public class RunClassFunction {

    public static final String Command_Name = "RunClassFunction";

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
        }).then(Commands.argument("function",StringArgumentType.string()).then(Commands.argument("arguments",StringArgumentType.string()).executes(arg -> {
            String[] args = arg.getInput().split(" ");
            Player player = (Player) arg.getSource().getEntity();
            String[] functions = args[2].replace("\"","").split(",");
            player.sendMessage(Component.nullToEmpty("List of functions to run:"),player.getUUID());
            for(String function:functions){
                player.sendMessage(Component.nullToEmpty(function),player.getUUID());
            }
            player.sendMessage(Component.nullToEmpty("Running"),player.getUUID());
            for(String function:functions){
                try {
                    if(args[3].replace("\"","").equalsIgnoreCase("void")){
                        runFunctionFromString(args[1].replace("\"",""),function.replace("\"",""));
                        player.sendMessage(Component.nullToEmpty("Ran {functionName} function in {class}".replace("{functionName}",function).replace("{class}",args[1].replace("\"",""))),player.getUUID());
                    }
                    else{
                        runFunctionFromString(args[1].replace("\"",""),function,args[3].replace("\"","").split(","));
                        player.sendMessage(Component.nullToEmpty("Ran {functionName} function in {class}".replace("{functionName}",function).replace("{class}",args[1].replace("\"",""))),player.getUUID());
                    }
                } catch (Exception e) {
                    player.sendMessage(Component.nullToEmpty("Exception:"),player.getUUID());
                    for(String exception:e.toString().split("\n")){
                        player.sendMessage(Component.nullToEmpty(exception),player.getUUID());
                    }
                }
            }
            player.sendMessage(Component.nullToEmpty("Everything is Ok!"),player.getUUID());

            return 0;
        })))));
    }
}
