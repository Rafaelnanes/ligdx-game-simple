package com.mygdx.game.player;

import com.mygdx.game.player.state.PlayerState;
import com.mygdx.game.player.state.PlayerStateDead;
import com.mygdx.game.player.state.PlayerStateGetHit;
import com.mygdx.game.player.state.PlayerStateRun;
import com.mygdx.game.player.state.PlayerStateShoot;

public class StateMachine {

  private final PlayerStateShoot playerStateShoot;
  private final PlayerStateRun playerStateRun;
  private final PlayerStateGetHit playerStateGetHit;
  private final PlayerStateDead playerStateDead;
  private final Player player;
  private PlayerState currentState;

  StateMachine(Player player) {
    this.player = player;
    playerStateRun = new PlayerStateRun();
    playerStateShoot = new PlayerStateShoot();
    playerStateGetHit = new PlayerStateGetHit();
    playerStateDead = new PlayerStateDead();
    currentState = playerStateRun;
  }

  public PlayerState getShoot() {
    return this.playerStateShoot;
  }

  public PlayerState getHit() {
    return this.playerStateGetHit;
  }

  public PlayerState getDead() {
    return this.playerStateDead;
  }

  public PlayerState getRun() {
    return this.playerStateRun;
  }

  public void changeToGetHit() {
    setState(playerStateGetHit);
  }

  public void changeToShoot() {
    setState(playerStateShoot);
  }

  public void changeToRun() {
    setState(playerStateRun);
  }

  private void setState(PlayerState playerState) {
    if (currentState instanceof PlayerStateDead) {
      System.out.println("Player is dead");
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
