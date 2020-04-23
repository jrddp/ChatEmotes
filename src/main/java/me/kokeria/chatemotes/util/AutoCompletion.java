package me.kokeria.chatemotes.util;

import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.ArrayList;
import java.util.List;

public class AutoCompletion {

    public static boolean autoComplete(TextFieldWidget field) {

        String fullStr = field.getText();
        int cursor = field.getCursor();

        String str = ChatHelper.getWord(fullStr, cursor);

        if (!str.startsWith(":")) return false;

        List<String> keylist = new ArrayList<>(FileHandler.getEMOTES().keySet());
        String[] args = {str};

//        List<String> completions = CommandException.getListOfStringsMatchingLastWord(args, keylist);
        List<String> completions = keylist;

        if (completions.size() == 1) {
            ChatHelper.replaceWord(field, completions.get(0));
        } else if (completions.size() > 1) {
            String hint = completions.toString();
            hint = hint.substring(1, hint.length() - 1);
            ChatHelper.addMsgToChat(hint);
            return true;
        }

        return false;

    }

}
