package me.kokeria.chatemotes;

import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ChatEmoteCommand extends CommandBase {


    @Override
    public String getCommandName() {
        return "chatemotes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/chatemotes";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("emote");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {

        FileHandler.setEmotesFromFile();
        sendListToChat();

    }
// todo make listed emotes clickable
    private void sendListToChat() {

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> emotes = FileHandler.getEMOTES();
        Iterator<String> iterator = emotes.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            stringBuilder.append(EnumChatFormatting.GOLD).append(key);
            stringBuilder.append(EnumChatFormatting.WHITE).append(" - ");
            stringBuilder.append(EnumChatFormatting.YELLOW).append(emotes.get(key));
            if (iterator.hasNext()) stringBuilder.append('\n');
        }

        String msg = stringBuilder.toString();
        if (msg.isEmpty()) msg = EnumChatFormatting.RED + "You have no emotes registered!";
        else msg = EnumChatFormatting.GREEN + "Type an emote in chat and hit tab to replace!\n" + msg;

        addMsgToChat(msg);
    }

    private void addMsgToChat(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
    }

}
