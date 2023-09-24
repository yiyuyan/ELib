package cn.ksmcbrigade.elib;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.elib.Libs.getFunctionNames;
import static cn.ksmcbrigade.elib.Libs.runFunction;

@Mod.EventBusSubscriber
public class RunELibFunction {

    public static final String Command_Name = "RunELibFunction";

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal(Command_Name).executes(args -> {
            Player player = (Player) args.getSource().getEntity();
            String[] functions = getFunctionNames(Libs.class);
            player.sendMessage(Component.nullToEmpty("ELib functions:"),player.getUUID());
            for(String function:functions){
                player.sendMessage(Component.nullToEmpty(function),player.getUUID());
            }
            return 0;
        }).then(Commands.argument("functions", StringArgumentType.string()).then(Commands.argument("arguments",StringArgumentType.string()).executes(arg ->{
            String[] args = arg.getInput().split(" ");
            Player player = (Player) arg.getSource().getEntity();
            String[] functions = args[1].replace("\"","").split(",");
            player.sendMessage(Component.nullToEmpty("List of functions to run:"),player.getUUID());
            for(String function:functions){
                player.sendMessage(Component.nullToEmpty(function),player.getUUID());
            }
            player.sendMessage(Component.nullToEmpty("Running"),player.getUUID());
            for(String function:functions){
                try {
                    if(args[2].replace("\"","").equalsIgnoreCase("void")){
                        runFunction(Libs.class,function.replace("\"",""));
                        player.sendMessage(Component.nullToEmpty("Ran {functionName} function".replace("{functionName}",function)),player.getUUID());
                    }
                    else{
                        runFunction(Libs.class,function,args[2].replace("\"","").split(","));
                        player.sendMessage(Component.nullToEmpty("Ran {functionName} function".replace("{functionName}",function)),player.getUUID());
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
        }))));
    }
}
