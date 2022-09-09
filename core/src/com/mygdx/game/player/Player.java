package com.mygdx.game.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;

@Getter
public class Player extends Actor {

  private final PlayerAnimation animation;
  private final PlayerStateMachine stateMachine;
  private final int velocity = 100;

  public Player() {
    animation = new PlayerAnimation(this);
    stateMachine = new PlayerStateMachine(this);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    animation.draw(batch);
  }

}
