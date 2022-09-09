package com.mygdx.game.zelda;

import com.mygdx.game.zelda.state.ZeldaState;
import com.mygdx.game.zelda.state.ZeldaStateBlocked;
import com.mygdx.game.zelda.state.ZeldaStateIdle;
import com.mygdx.game.zelda.state.ZeldaStateMoving;

public class ZeldaStateMachine {

  private final ZeldaStateIdle idle;
  private final ZeldaStateMoving moving;
  private final ZeldaStateBlocked blocked;
  private final ZeldaPlayer player;
  private ZeldaState currentState;

  public ZeldaStateMachine(ZeldaPlayer player) {
    this.idle = new ZeldaStateIdle();
    this.moving = new ZeldaStateMoving();
    this.blocked = new ZeldaStateBlocked();
    this.player = player;
    currentState = idle;
  }

  public ZeldaState getIdle() {
    return idle;
  }

  public ZeldaState changeToIdle() {
    setState(idle);
    return currentState;
  }

  public ZeldaState changeToBlocked() {
    setState(blocked);
    return currentState;
  }

  public ZeldaState changeToMoving() {
    setState(moving);
    return currentState;
  }

  public void setState(ZeldaState playerState) {
    final ZeldaState update = currentState.update(player, playerState);
    if (update != null) {
      currentState = update.action(player, playerState);
    }
  }

}
