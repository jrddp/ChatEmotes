package me.kokeria.chatemotes.event;

import me.kokeria.chatemotes.util.ChatHelper;
import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;

import java.util.HashMap;

public class EventHandler {

    protected static boolean runReplacement(GuiChat chat) {
        GuiTextField field = chat.inputField;
        String msg = field.getText();
        int cursor = field.getCursorPosition();
        String word = ChatHelper.getWord(msg, cursor);
        boolean isValid = word.length() > 2 && word.charAt(0) == ':' && word.charAt(word.length()-1) == ':';
        if (!isValid) return false;
        String key = word.substring(1, word.length()-2);
        HashMap<String, String> keyset = FileHandler.getKEYSET();
        if (!keyset.containsKey(key)) return false;
        ChatHelper.replaceWord(field, keyset.get(key));
        return true;
    }

}
