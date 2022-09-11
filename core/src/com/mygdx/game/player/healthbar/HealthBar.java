package com.mygdx.game.player.healthbar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.game.player.Player;

public class HealthBar extends ProgressBar {

  public HealthBar(Player player) {
    super(0f, 1f, 0.01f, false, new ProgressBarStyle());
    int width = 300;
    int height = 25;

    getStyle().background =
        new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("healthbar/background.png"))));
    //    getStyle().knob = getColoredDrawable(0, height, Color.GREEN);
    getStyle().knobBefore =
        new TiledDrawable(new TextureRegion(new Texture(Gdx.files.internal("healthbar/Healthbar.png"))));

    setPosition(0, Gdx.graphics.getHeight() - 30);
    setWidth(width);
    setHeight(height);

    setValue(100);
    setAnimateDuration(0.25f);
    player.getChildren().insert(Player.CHILD_HEALTHBAR_INDEX, this);

  }

  public static Drawable getColoredDrawable(int width, int height, Color color) {
    Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
    pixmap.setColor(color);
    pixmap.fill();

    TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

    pixmap.dispose();

    return drawable;
  }

  public void updateValue(int hitValue) {
    setValue((float) (getValue() - (hitValue * 0.01)));
  }

  public void setZeroValue() {
    setValue(0);
  }

}