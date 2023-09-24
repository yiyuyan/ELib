package cn.ksmcbrigade.elib;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.elib.Libs.getFunctionCode;
import static cn.ksmcbrigade.elib.Libs.getFunctionNames;

@Mod.EventBusSubscriber
public class GetELibFunctionCodes {

    public static final String Command_Name = "GetELibFunctionCodes";

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
        }).then(Commands.argument("function",StringArgumentType.string()).executes(arg -> {
            String[] args = arg.getInput().split(" ");
            Player player = (Player) arg.getSource().getEntity();
            String[] functionCode = getFunctionCode(Libs.class,args[1]).split("\n");
            player.sendMessage(Component.nullToEmpty("ELib {functionName} function codes:".replace("{functionName}",args[1])),player.getUUID());
            for(String code:functionCode){
                player.sendMessage(Component.nullToEmpty(code),player.getUUID());
            }

            return 0;
        })));
    }
}
