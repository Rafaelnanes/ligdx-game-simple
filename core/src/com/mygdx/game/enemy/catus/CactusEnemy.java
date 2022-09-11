package com.mygdx.game.enemy.catus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.context.MyAssetManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CactusEnemy extends Actor {

  private final float x, y;

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.draw(new Texture(MyAssetManager.getInstance().getCactusTexture()), x, y);
  }
}
