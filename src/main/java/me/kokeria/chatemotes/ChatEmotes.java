package me.kokeria.chatemotes;

import me.kokeria.chatemotes.util.FileHandler;
import net.fabricmc.api.ClientModInitializer;

public class ChatEmotes implements ClientModInitializer {

    // todo add button for modlist to go to config file
    @Override
    public void onInitializeClient() {

        FileHandler.setEmotesFromFile();

    }
}
