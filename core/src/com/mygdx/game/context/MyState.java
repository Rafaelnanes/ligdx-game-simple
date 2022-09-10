package com.mygdx.game.context;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.player.healthbar.HealthBar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyState extends Stage {

  private HealthBar healthBar;

  public MyState() {
    super();
  }

}
