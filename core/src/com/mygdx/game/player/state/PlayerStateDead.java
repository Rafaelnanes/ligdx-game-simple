package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStateMachine;
import com.mygdx.game.player.healthbar.HealthBarEventListener;

public class PlayerStateDead extends AbstractPlayerState {

  @Override
  public void action(Player player) {
    if (!player.getStateMachine().getDead().isActive()) {
      player.getHealthBar().fire(new HealthBarEventListener.HealthBarEmptyEvent());
      player.dead();
    }
    enable();
    final PlayerStateMachine stateMachine = player.getStateMachine();
    stateMachine.getMoving().disable();
    stateMachine.getIdle().disable();
    stateMachine.getBlocked().disable();
    stateMachine.getHit().disable();
  }
}
