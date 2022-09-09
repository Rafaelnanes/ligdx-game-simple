package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public class Run implements PlayerState {
  @Override
  public PlayerState update(Player player, PlayerState nextState) {
    if (nextState.getClass().equals(Shoot.class)) {
      return null;
    }
    return nextState;
  }

  @Override
  public PlayerState action(Player player, PlayerState nextState) {
    player.run();
    return this;
  }
}
