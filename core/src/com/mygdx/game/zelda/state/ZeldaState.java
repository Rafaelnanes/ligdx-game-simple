package com.mygdx.game.zelda.state;

import com.mygdx.game.zelda.ZeldaPlayer;

public interface ZeldaState {

  default ZeldaState update(ZeldaPlayer player, ZeldaState nextState) {
    return nextState;
  }

  ZeldaState action(ZeldaPlayer player, ZeldaState nextState);

}
