package me.kokeria.chatemotes;

import me.kokeria.chatemotes.event.EventListener;
import me.kokeria.chatemotes.util.FileHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ChatEmotes.MODID, version = ChatEmotes.VERSION)
public class ChatEmotes
{
    public static final String MODID = "chatemotes";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FileHandler.genKeyfile();
        FileHandler.setKeyset();
        MinecraftForge.EVENT_BUS.register(new EventListener());
        ClientCommandHandler.instance.registerCommand(new ChatEmoteCommand());
    }

}
