package com.mygdx.game.player.healthbar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.player.Player;

public class HealthBar extends ProgressBar {

  public HealthBar(int width, int height, Player player) {
    super(0f, 1f, 0.01f, false, new ProgressBarStyle());
    getStyle().background = getColoredDrawable(width, height, Color.RED);
    getStyle().knob = getColoredDrawable(0, height, Color.GREEN);
    getStyle().knobBefore = getColoredDrawable(width, height, Color.GREEN);

    setWidth(width);
    setHeight(height);

    setAnimateDuration(0.0f);
    setValue(100);
    setAnimateDuration(0.25f);
    player.getChildren().insert(0, this);

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