package com.mygdx.game.player.blood;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.player.Player;

public class PlayerBlood extends Actor {

  private final Texture texture;
  private final float x;
  private final float y;

  public PlayerBlood(Player player) {
    texture = new Texture(MyAssetManager.getInstance().getBloodTexture());
    x = player.getRectangle().x;
    y = player.getRectangle().y + player.getRectangle().getHeight() / 3;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    batch.draw(texture, x, y);
    toBack();
  }
}
