package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public class PlayerStateDead implements PlayerState {

  @Override
  public PlayerState update(Player player, PlayerState nextState) {
    if (!player.getCurrentState().getClass().equals(PlayerStateDead.class)) {
      action(player, nextState);
    }
    return this;
  }

  @Override
  public PlayerState action(Player player, PlayerState nextState) {
    player.dead();
    return this;
  }
}
