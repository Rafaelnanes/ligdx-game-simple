package com.mygdx.game.context;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.player.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyStage extends Stage {

  private Player player;

  public MyStage() {
    super();
  }

}
