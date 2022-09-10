package com.mygdx.game.player.state;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.context.MyState;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.healthbar.HealthBar;
import com.mygdx.game.text.FloatingText;

public class PlayerStateGetHit extends AbstractPlayerState {

  @Override
  public void action(Player player) {
    enable();

    final int hitValue = player.getHit();
    if (player.getHealth() <= 0) {
      player.getStateMachine().activateDead();
      return;
    }

    final MyState stage = (MyState) player.getStage();
    decreaseHealth(stage, hitValue);
    addFloatingText(stage, player, hitValue);
  }

  private void addFloatingText(MyState stage, Player player, int hitValue) {
    final Rectangle playerRectangle = player.getAnimation().getPlayerRectangle();
    final FloatingText floatingText = new FloatingText();
    floatingText.animate(playerRectangle.getX(), playerRectangle.getY(), String.format("-%d", hitValue));
    stage.addActor(floatingText);
  }

  private void decreaseHealth(MyState stage, int hitValue) {
    final HealthBar healthBar = stage.getHealthBar();
    healthBar.setValue((float) (healthBar.getValue() - (hitValue * 0.01)));
  }

}
