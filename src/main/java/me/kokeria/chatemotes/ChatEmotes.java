package me.kokeria.chatemotes;

import me.kokeria.chatemotes.util.FileHandler;
import net.fabricmc.api.ClientModInitializer;

public class ChatEmotes implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        FileHandler.setEmotesFromFile();

    }
}
