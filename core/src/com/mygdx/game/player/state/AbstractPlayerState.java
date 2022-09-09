package com.mygdx.game.player.state;

public abstract class AbstractPlayerState implements PlayerState {

  protected boolean active;

  @Override
  public void enable() {
    active = true;
  }

  @Override
  public void disable() {
    active = false;
  }

  @Override
  public boolean isActive() {
    return active;
  }

}
