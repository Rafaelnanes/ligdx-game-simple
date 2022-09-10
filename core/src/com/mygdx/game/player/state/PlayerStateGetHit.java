package com.mygdx.game.player.state;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.player.Player;
import com.mygdx.game.text.FloatingText;

public class PlayerStateGetHit extends AbstractPlayerState {

  @Override
  public void action(Player player) {
    enable();
    final int amount = player.getHit();
    if (player.getHealth() <= 0) {
      player.getStateMachine().activateDead();
      return;
    }

    final Rectangle playerRectangle = player.getAnimation().getPlayerRectangle();
    final FloatingText floatingText = new FloatingText();
    floatingText.animate(playerRectangle.getX(), playerRectangle.getY(), String.format("-%d", amount));
    player.getStage().addActor(floatingText);
  }

}
