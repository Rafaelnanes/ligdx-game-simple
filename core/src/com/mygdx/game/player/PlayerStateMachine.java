package com.mygdx.game.player;

import com.mygdx.game.player.state.PlayerState;
import com.mygdx.game.player.state.PlayerStateBlocked;
import com.mygdx.game.player.state.PlayerStateDead;
import com.mygdx.game.player.state.PlayerStateGetHit;
import com.mygdx.game.player.state.PlayerStateIdle;
import com.mygdx.game.player.state.PlayerStateMoving;

public class PlayerStateMachine {

  private final PlayerStateIdle idle;
  private final PlayerStateMoving moving;
  private final PlayerStateBlocked blocked;
  private final PlayerStateGetHit getHit;
  private final PlayerStateDead dead;
  private final Player player;

  public PlayerStateMachine(Player player) {
    this.idle = new PlayerStateIdle();
    this.moving = new PlayerStateMoving();
    this.blocked = new PlayerStateBlocked();
    this.getHit = new PlayerStateGetHit();
    this.dead = new PlayerStateDead();
    this.player = player;
  }

  public PlayerStateIdle getIdle() {
    return idle;
  }

  public PlayerStateMoving getMoving() {
    return moving;
  }

  public PlayerStateBlocked getBlocked() {
    return blocked;
  }

  public PlayerState getHit() {
    return getHit;
  }
  
  public PlayerState getDead() {
    return dead;
  }

  public PlayerState activateIdle() {
    setState(idle);
    return idle;
  }

  public PlayerState activateBlocked() {
    setState(blocked);
    return blocked;
  }

  public PlayerState activateMoving() {
    setState(moving);
    return moving;
  }

  public PlayerState activateGetHit() {
    setState(getHit);
    return getHit;
  }

  public PlayerState activateDead() {
    setState(dead);
    return dead;
  }

  public void setState(PlayerState state) {
    if (dead.isActive()) {
      return;
    }
    state.action(player);
  }

}
