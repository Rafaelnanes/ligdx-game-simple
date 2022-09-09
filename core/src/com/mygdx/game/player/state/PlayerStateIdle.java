package com.mygdx.game.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerAnimation;
import com.mygdx.game.player.PlayerStateMachine;

import static com.mygdx.game.player.PlayerAnimation.FRAME_DURATION;

public class PlayerStateIdle extends AbstractPlayerState {

  private final PlayerStateMachine stateMachine;

  private final Animation<TextureAtlas.AtlasRegion> idleAnimation;

  public PlayerStateIdle(PlayerStateMachine stateMachine) {
    this.stateMachine = stateMachine;
    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("zelda/zelda.atlas"));
    idleAnimation = new Animation<>(FRAME_DURATION * 2, textureAtlas.findRegions("idle"), Animation.PlayMode.LOOP);
  }

  @Override
  public PlayerState action(Player player) {
    enable();
    stateMachine.getMoving().disable();
    stateMachine.getBlocked().disable();

    final PlayerAnimation animation = player.getAnimation();
    final float stateTime = animation.getStateTime();

    TextureRegion textureRegion = idleAnimation.getKeyFrame(stateTime, true);
    animation.setTextureRegion(textureRegion);
    return this;
  }

}
