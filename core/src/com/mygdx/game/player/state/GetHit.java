package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public class GetHit implements PlayerState {
  @Override
  public PlayerState action(Player player, PlayerState nextState) {
    player.getHit();
    if (player.getHealth() <= 0) {
      return player.getStateMachine().getDead().update(player, nextState);
    }
    return this;
  }
}
