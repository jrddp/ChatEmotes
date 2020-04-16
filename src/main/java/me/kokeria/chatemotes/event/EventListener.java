package me.kokeria.chatemotes.event;

import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class EventListener {

    @SubscribeEvent
    public void onGuiInput(GuiScreenEvent.KeyboardInputEvent event) {

        if (event.gui instanceof GuiChat && Keyboard.getEventKey() == Keyboard.KEY_TAB && Keyboard.getEventKeyState() && event.isCancelable()) {

            if (EventHandler.runReplacement((GuiChat) event.gui)) event.setCanceled(true);

        }

    }

}
