package io.gamemerald.sanedependencyloader;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

public class LaunchedStage implements ModInitializer {

    @Override
    public void onInitialize(){
        if(SaneDepLoader.showWindow == 1){
            MinecraftClient.getInstance().close();
        }
    }
}
