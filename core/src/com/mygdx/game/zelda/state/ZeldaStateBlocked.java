package com.mygdx.game.zelda.state;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.zelda.ZeldaAnimation;
import com.mygdx.game.zelda.ZeldaPlayer;

public class ZeldaStateBlocked implements ZeldaState {

  public ZeldaStateBlocked() {
  }

  @Override
  public ZeldaState action(ZeldaPlayer player, ZeldaState nextState) {
    final ZeldaAnimation animation = player.getAnimation();
    final Vector2 vector2 = animation.getLastPosition();
    animation.getPlayerRectangle().setPosition(vector2.x, vector2.y);
    return this;
  }

}
