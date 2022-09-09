package com.mygdx.game.player;

import com.mygdx.game.player.state.PlayerState;
import com.mygdx.game.player.state.PlayerStateBlocked;
import com.mygdx.game.player.state.PlayerStateIdle;
import com.mygdx.game.player.state.PlayerStateMoving;

public class PlayerStateMachine {

  private final PlayerStateIdle idle;
  private final PlayerStateMoving moving;
  private final PlayerStateBlocked blocked;
  private final Player player;

  public PlayerStateMachine(Player player) {
    this.idle = new PlayerStateIdle(this);
    this.moving = new PlayerStateMoving(this);
    this.blocked = new PlayerStateBlocked(this);
    this.player = player;
    idle.action(player);
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

  public void setState(PlayerState state) {
    //TODO botar depois a regra para quando morrer
    state.action(player);
  }

}
