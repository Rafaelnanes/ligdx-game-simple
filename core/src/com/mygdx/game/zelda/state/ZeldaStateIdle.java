package com.mygdx.game.zelda.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.zelda.ZeldaAnimation;
import com.mygdx.game.zelda.ZeldaPlayer;

import static com.mygdx.game.zelda.ZeldaAnimation.FRAME_DURATION;

public class ZeldaStateIdle implements ZeldaState {

  private final Animation<TextureAtlas.AtlasRegion> idleAnimation;

  public ZeldaStateIdle() {
    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("zelda/zelda.atlas"));
    idleAnimation = new Animation<>(FRAME_DURATION * 2, textureAtlas.findRegions("idle"), Animation.PlayMode.LOOP);
  }

  @Override
  public ZeldaState action(ZeldaPlayer player, ZeldaState nextState) {
    final ZeldaAnimation animation = player.getAnimation();
    final float stateTime = animation.getStateTime();

    TextureRegion textureRegion = idleAnimation.getKeyFrame(stateTime, true);
    animation.setTextureRegion(textureRegion);
    return this;
  }

}
