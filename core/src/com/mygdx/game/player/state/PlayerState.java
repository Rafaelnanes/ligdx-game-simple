package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public interface PlayerState {

  PlayerState action(Player player);

  void enable();

  void disable();

  boolean isActive();

}
