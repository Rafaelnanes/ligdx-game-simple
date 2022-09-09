package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public class PlayerStateShoot implements PlayerState {
  @Override
  public PlayerState action(Player player, PlayerState nextState) {
    player.shoot();
    return this;
  }
}
