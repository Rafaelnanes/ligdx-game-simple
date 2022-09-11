package com.mygdx.game.enemy.catus;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.context.MyStage;
import com.mygdx.game.player.Player;

public class CactusEnemy extends Actor {

  private final Rectangle cactusRectangle;
  private final Texture texture;

  public CactusEnemy(Rectangle rectangle) {
    this.cactusRectangle = rectangle;
    texture = new Texture(MyAssetManager.getInstance().getCactusTexture());
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    MyStage myStage = (MyStage) getStage();
    final Player player = myStage.getPlayer();
    if (cactusRectangle.overlaps(player.getRectangle())) {
      remove();
      player.getStateMachine().activateGetHit();
    }
    batch.draw(texture, cactusRectangle.getX(), cactusRectangle.getY());
  }
}
