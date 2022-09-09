package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public interface PlayerState {

  default PlayerState update(Player player, PlayerState nextState) {
    return nextState;
  }

  PlayerState action(Player player, PlayerState nextState);

}
