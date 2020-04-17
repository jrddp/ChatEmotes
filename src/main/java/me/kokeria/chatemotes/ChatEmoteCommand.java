package me.kokeria.chatemotes;

import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        switch (args.length) {
            case 1:
                return getListOfStringsMatchingLastWord(args, "list", "reload");
        }
        return Collections.emptyList();
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {

        if (args.length <= 0) {
            sendListToChat();
            return;
        }

        switch (args[1]) {
            case "list":
                sendListToChat();
                break;
            case "reload":
                FileHandler.setKeyset();
                break;
        }

    }

    private void sendListToChat() {

        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, String> keyset = FileHandler.getKEYSET();
        Iterator<String> iterator = keyset.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            stringBuilder.append(EnumChatFormatting.GOLD).append(key).append(':').append(key).append(':');
            stringBuilder.append(EnumChatFormatting.WHITE).append(" - ");
            stringBuilder.append(EnumChatFormatting.YELLOW).append(keyset.get(key));
            if (iterator.hasNext()) stringBuilder.append('\n');
        }

        String msg = stringBuilder.toString();
        if (msg.isEmpty()) msg = EnumChatFormatting.RED + "You have no emotes registered!";
        else msg = EnumChatFormatting.GREEN + "The following emotes are available:\n" + msg;

        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));

    }

}
