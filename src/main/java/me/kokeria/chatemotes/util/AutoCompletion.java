package me.kokeria.chatemotes.util;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.command.CommandBase;

import java.util.ArrayList;
import java.util.List;

public class AutoCompletion {

    public static boolean autoComplete(GuiTextField field) {

        String fullStr = field.getText();
        int cursor = field.getCursorPosition();

        String str = ChatHelper.getWord(fullStr, cursor);

        if (!str.startsWith(":")) return false;

        List<String> keylist = new ArrayList<>(FileHandler.getEMOTES().keySet());
        String[] args = {str};

        List<String> completions = CommandBase.getListOfStringsMatchingLastWord(args, keylist);

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
