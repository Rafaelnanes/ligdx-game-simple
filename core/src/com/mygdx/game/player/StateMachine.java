package com.mygdx.game.player;

import com.mygdx.game.player.state.Dead;
import com.mygdx.game.player.state.GetHit;
import com.mygdx.game.player.state.PlayerState;
import com.mygdx.game.player.state.Run;
import com.mygdx.game.player.state.Shoot;

public class StateMachine {

  private final Shoot shoot;
  private final Run run;
  private final GetHit getHit;
  private final Dead dead;
  private final Player player;
  private PlayerState currentState;

  StateMachine(Player player) {
    this.player = player;
    run = new Run();
    shoot = new Shoot();
    getHit = new GetHit();
    dead = new Dead();
    currentState = run;
  }

  public PlayerState getShoot() {
    return this.shoot;
  }

  public PlayerState getHit() {
    return this.getHit;
  }

  public PlayerState getDead() {
    return this.dead;
  }

  public PlayerState getRun() {
    return this.run;
  }

  public void changeToGetHit() {
    setState(getHit);
  }

  public void changeToShoot() {
    setState(shoot);
  }

  public void changeToRun() {
    setState(run);
  }

  private void setState(PlayerState playerState) {
    if (currentState instanceof Dead) {
      System.out.println("PLayer is dead");
      return;
    }
    final PlayerState update = currentState.update(player, playerState);
    if (update != null) {
      currentState = update.action(player, playerState);
    }
  }

  public PlayerState getCurrentState() {
    return currentState;
  }
}
