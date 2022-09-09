package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public class PlayerStateGetHit extends AbstractPlayerState {

  @Override
  public void action(Player player) {
    enable();
    player.getHit();
    if (player.getHealth() <= 0) {
      player.getStateMachine().activateDead();
    }
  }
}
