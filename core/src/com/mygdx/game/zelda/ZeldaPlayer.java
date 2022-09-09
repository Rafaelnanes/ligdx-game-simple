package com.mygdx.game.zelda;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;

@Getter
public class ZeldaPlayer extends Actor {

  private final ZeldaAnimation animation;
  private final ZeldaStateMachine stateMachine;
  private final int velocity = 100;

  public ZeldaPlayer(MapObjects collisions, MapObjects bufferCollision) {
    animation = new ZeldaAnimation(this, collisions, bufferCollision);
    stateMachine = new ZeldaStateMachine(this);
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    animation.draw(batch);
  }

}
