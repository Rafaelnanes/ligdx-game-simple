package com.mygdx.game.player;

import com.mygdx.game.player.state.PlayerState;
import lombok.Getter;

@Getter
public class Player {

  private final StateMachine stateMachine = new StateMachine(this);
  private int health = 100;
  private int bullets = 10;

  public void shoot() {
    bullets -= 1;
    System.out.println("Player shooting");
  }

  public void run() {
    System.out.println("Player running");
  }

  public void getHit() {
    health -= 40;
    System.out.println("Got hit: " + health);
  }

  public void dead() {
    health = 0;
    System.out.println("Dead");
  }

  public PlayerState getCurrentState() {
    return stateMachine.getCurrentState();
  }

}
