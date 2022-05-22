package io.gamemerald.sanedependencyloader.mixins;

import io.gamemerald.sanedependencyloader.SaneDepLoader;
import io.gamemerald.sanedependencyloader.gui.FinishedScreen;
import io.gamemerald.sanedependencyloader.gui.ForgotJsonScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MainMenuMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        if(SaneDepLoader.forgotJson == 1){
            MinecraftClient.getInstance().setScreen(new ForgotJsonScreen(Text.of("")));
        }
        else if(SaneDepLoader.showWindow == 1){
            MinecraftClient.getInstance().setScreen(new FinishedScreen(Text.of("")));
        }
    }
}