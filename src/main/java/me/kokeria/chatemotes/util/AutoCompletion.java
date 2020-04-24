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

        List<String> completions = getListOfStringsMatchingWord(str, keylist);

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

    static List<String> getListOfStringsMatchingWord(String word, List<String> strings) {
        List<String> output = new ArrayList<>();
        for (String s : strings) {
            if (s.startsWith(word)) output.add(s);
        }
        return output;
    }

}
