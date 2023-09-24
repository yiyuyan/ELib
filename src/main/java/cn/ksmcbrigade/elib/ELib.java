package cn.ksmcbrigade.elib;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

import static cn.ksmcbrigade.elib.Libs.init;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("elib")
public class ELib {

    public static final String MOD_ID = "elib";

    public ELib() {
        MinecraftForge.EVENT_BUS.register(this);
        init();
    }
}
