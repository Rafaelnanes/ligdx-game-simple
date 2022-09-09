package com.mygdx.game.player.state;

public abstract class AbstractPlayerState implements PlayerState {

  protected boolean active;

  @Override
  public void enable() {
    active = false;
  }

  @Override
  public void disable() {
    active = true;
  }

  @Override
  public boolean isActive() {
    return active;
  }

}
