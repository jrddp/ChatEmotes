package me.kokeria.chatemotes.event;

import me.kokeria.chatemotes.util.AutoCompletion;
import me.kokeria.chatemotes.util.ChatHelper;
import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;

import java.util.Map;

public class EventHandler {

    protected static boolean runReplacement(GuiChat chat) {

        GuiTextField field = chat.inputField;
        String msg = field.getText();
        int cursor = field.getCursorPosition();
        String word = ChatHelper.getWord(msg, cursor);

        Map<String, String> emotes = FileHandler.getEMOTES();

        if (emotes.containsKey(word)) {
            ChatHelper.replaceWord(field, emotes.get(word));
            return true;
        }

        return false;
    }

    protected  static boolean runAutocomplete(GuiChat chat) {

        GuiTextField field = chat.inputField;
        return AutoCompletion.autoComplete(field);

    }

}
