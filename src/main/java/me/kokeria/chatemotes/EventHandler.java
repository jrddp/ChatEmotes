package me.kokeria.chatemotes;

import me.kokeria.chatemotes.util.AutoCompletion;
import me.kokeria.chatemotes.util.ChatHelper;
import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Map;

public class EventHandler {

    public static boolean runReplacement(TextFieldWidget field) {

        String msg = field.getText();
        int cursor = field.getCursor();
        String word = ChatHelper.getWord(msg, cursor);

        Map<String, String> emotes = FileHandler.getEMOTES();

        if (emotes.containsKey(word)) {
            ChatHelper.replaceWord(field, emotes.get(word));
            return true;
        }

        return false;
    }

    public static boolean runAutocomplete(TextFieldWidget field) {

        return AutoCompletion.autoComplete(field);

    }

}
