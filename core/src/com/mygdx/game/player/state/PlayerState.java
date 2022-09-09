package com.mygdx.game.player.state;

import com.mygdx.game.player.Player;

public interface PlayerState {

  void action(Player player);

  void enable();

  void disable();

  boolean isActive();

}
