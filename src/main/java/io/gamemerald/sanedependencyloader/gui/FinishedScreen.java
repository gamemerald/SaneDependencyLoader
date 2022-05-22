package io.gamemerald.sanedependencyloader.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;


@Environment(EnvType.CLIENT)
public class FinishedScreen extends Screen {

    public FinishedScreen(Text title) {
        super(Text.of(""));
    }

    @Override
    protected void init() {
        int l = this.height / 4 + 48;

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 50 , l + 62 + 12, 98, 20, new TranslatableText("Proceed"), (button) -> {
            this.client.scheduleStop();
        }));

    }

    public boolean shouldPause() {
        return false;
    }

    public boolean shouldCloseOnEsc() {
        return false;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawStringWithShadow(matrices, this.textRenderer, "Sane Dependency Loader", this.width / 2 - 55, this.height / 4 - 60 + 60 + 0, 10526880);
        drawStringWithShadow(matrices, this.textRenderer, "Please reluanch as mods have been successfully downloaded", this.width / 2 - 140, this.height / 4 - 60 + 60 + 18, 10526880);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
