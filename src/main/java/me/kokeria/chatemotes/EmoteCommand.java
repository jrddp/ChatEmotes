package me.kokeria.chatemotes;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.kokeria.chatemotes.util.ChatHelper;
import me.kokeria.chatemotes.util.FileHandler;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import java.util.Iterator;
import java.util.Map;

//FIXME command is only working on single player
public class EmoteCommand {

    static void register(CommandDispatcher<ServerCommandSource> dispatcher) {

        dispatcher.register(CommandManager.literal("emote").executes(EmoteCommand::execute));

    }

    private static int execute(CommandContext<?> ctx) {

        FileHandler.setEmotesFromFile();

        Map<String, String> emotes = FileHandler.getEMOTES();
        Iterator<String> iterator = emotes.keySet().iterator();

        if (!iterator.hasNext()) {
            ChatHelper.addMsgToChat(Formatting.RED + "You have no emotes registered!");
            return 1;
        }

        ChatHelper.addMsgToChat(Formatting.GREEN + "Type an emote in chat and hit tab to replace!");

        while (iterator.hasNext()) {
            String key = iterator.next();
            String emote = emotes.get(key);
            String msg = Formatting.GOLD + key + Formatting.WHITE + " - " + Formatting.YELLOW + emote;
            Text component = new LiteralText(msg);
            Style style = new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, emote));
            style.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new LiteralText("Click to set chat to " + emote)));
            component.setStyle(style);
            ChatHelper.addMsgToChat(component);
        }

        return 1;

    }

}


