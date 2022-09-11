package com.mygdx.game.player;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.player.healthbar.HealthBar;
import lombok.Getter;

@Getter
public class Player extends Group {

  public static final int CHILD_HEALTHBAR_INDEX = 0;
  private final PlayerAnimation animation;
  private final PlayerStateMachine stateMachine;
  private final int velocity = 100;
  private int health = 100;

  public Player() {
    animation = new PlayerAnimation(this);
    stateMachine = new PlayerStateMachine(this);
    stateMachine.getIdle().action(this);

  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    animation.draw(batch);
  }

  public int getHit() {
    final int amount = 40;
    health -= amount;
    System.out.println("Health: " + health);
    return amount;
  }

  public void dead() {
    health = 0;
    System.out.println("Dead");
  }

  public int getHealth() {
    return health;
  }

  public HealthBar getHealthBar() {
    return (HealthBar) getChild(CHILD_HEALTHBAR_INDEX);
  }

  public Rectangle getRectangle() {
    return animation.getPlayerRectangle();
  }

}
