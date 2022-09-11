package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerStateMachine;

public class PlayerStateDead extends AbstractPlayerState {

  @Override
  public void action(Player player) {
    if (!player.getStateMachine().getDead().isActive()) {
      player.getHealthBar().setZeroValue();
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
