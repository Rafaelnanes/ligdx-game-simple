package com.mygdx.game.text;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.concurrent.TimeUnit;

public class FloatingText extends Actor {
  private final long animationDuration;
  private final BitmapFont font = new BitmapFont();
  private float deltaX;
  private float deltaY = 100;
  private boolean animated = false;
  private long animationStart;
  private String text = "";

  public FloatingText() {
    this.animationDuration = TimeUnit.SECONDS.toMillis(2);
  }

  public void setDeltaX(float deltaX) {
    this.deltaX = deltaX;
  }

  public void setDeltaY(float deltaY) {
    this.deltaY = deltaY;
  }

  public void animate(float x, float y, String text) {
    this.text = text;
    animated = true;
    animationStart = System.currentTimeMillis();
    setPosition(x, y);
  }

  public boolean isAnimated() {
    return animated;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    if (animated) {
      // The component will auto-destruct when animation is finished.
      //      if (isDisposable()) {
      //        dispose();
      //        return;
      //      }

      float elapsed = System.currentTimeMillis() - animationStart;

      // The text will be fading.
      font.setColor(getColor().r, getColor().g, getColor().b, parentAlpha * (1 - elapsed / animationDuration));

      if (text.length() > 0) {
        font.draw(batch, text, getX() + deltaX * elapsed / 1000f, getY() + deltaY * elapsed / 1000f);
      }
    }
  }

}
