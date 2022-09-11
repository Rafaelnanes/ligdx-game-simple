package com.mygdx.game.player.healthbar;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HealthBarEventListener implements EventListener {

  private final HealthBar healthBar;

  @Override
  public boolean handle(Event event) {
    if (event instanceof HealthBarUpdateEvent) {
      HealthBarUpdateEvent healthBarUpdateEvent = (HealthBarUpdateEvent) event;
      healthBar.setValue((float) (healthBar.getValue() - (healthBarUpdateEvent.getHitValue() * 0.01)));
      return true;
    }
    if (event instanceof HealthBarEmptyEvent) {
      healthBar.setValue(0);
      return true;
    }
    return false;
  }

  @Getter
  @RequiredArgsConstructor
  public static class HealthBarUpdateEvent extends Event {
    private final int hitValue;
  }


  @Getter
  public static class HealthBarEmptyEvent extends Event {
  }

}
