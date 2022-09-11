package com.mygdx.game.player.state;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.blood.PlayerBlood;
import com.mygdx.game.text.FloatingText;

public class PlayerStateGetHit extends AbstractPlayerState {

  @Override
  public void action(Player player) {
    enable();
    final int hitValue = player.getHit();
    addFloatingText(player, hitValue);
    if (player.getHealth() <= 0) {
      player.getStateMachine().activateDead();
      return;
    }
    player.getStage().addActor(new PlayerBlood(player));
    decreaseHP(player, hitValue);
  }

  private void decreaseHP(Player player, int hitValue) {
    player.getHealthBar().updateValue(hitValue);
  }

  private void addFloatingText(Player player, int hitValue) {
    final Rectangle playerRectangle = player.getAnimation().getPlayerRectangle();
    final FloatingText floatingText = new FloatingText();
    floatingText.animate(playerRectangle.getX(),
        playerRectangle.getY() + playerRectangle.getWidth(),
        String.format("-%d", hitValue));
    player.getStage().addActor(floatingText);
  }

}
