package com.mygdx.game.player.state;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerAnimation;
import com.mygdx.game.player.PlayerStateMachine;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlayerStateBlocked extends AbstractPlayerState {

  private PlayerStateMachine stateMachine;

  @Override
  public PlayerState action(Player player) {
    enable();
    stateMachine.getIdle().disable();
    stateMachine.getMoving().disable();

    final PlayerAnimation animation = player.getAnimation();
    final Vector2 vector2 = animation.getLastPosition();

    animation.getPlayerRectangle().setPosition(vector2.x, vector2.y);
    return this;
  }
}
