package me.kokeria.chatemotes;

import me.kokeria.chatemotes.util.ChatHelper;
import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
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

    private void sendListToChat() {

        Map<String, String> emotes = FileHandler.getEMOTES();
        Iterator<String> iterator = emotes.keySet().iterator();

        if (!iterator.hasNext()) {
            ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, FileHandler.KEYFILE.getAbsolutePath()));
            style.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to open config file")));
            ChatHelper.addMsgToChat(new ChatComponentText(EnumChatFormatting.RED + "You have no emotes registered!").setChatStyle(style));
            return;
        }

        ChatHelper.addMsgToChat(EnumChatFormatting.GREEN + "Type an emote in chat and hit tab to replace!");

        while (iterator.hasNext()) {
            String key = iterator.next();
            String emote = emotes.get(key);
            String msg = EnumChatFormatting.GOLD + key + EnumChatFormatting.WHITE + " - " + EnumChatFormatting.YELLOW + emote;
            ChatComponentText component = new ChatComponentText(msg);
            ChatStyle style = new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, emote));
            style.setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to set chat to " + emote)));
            component.setChatStyle(style);
            ChatHelper.addMsgToChat(component);
        }

    }

}
