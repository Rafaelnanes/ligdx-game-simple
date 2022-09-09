package com.mygdx.game.player.state;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerAnimation;
import com.mygdx.game.player.PlayerStateMachine;

import static com.mygdx.game.player.PlayerAnimation.FRAME_DURATION;

public class PlayerStateIdle extends AbstractPlayerState {

  private final Animation<TextureAtlas.AtlasRegion> idleAnimation;

  public PlayerStateIdle() {
    TextureAtlas textureAtlas = new TextureAtlas(MyAssetManager.getInstance().getZeldaAtlas());
    idleAnimation = new Animation<>(FRAME_DURATION * 2, textureAtlas.findRegions("idle"), Animation.PlayMode.LOOP);
  }

  @Override
  public void action(Player player) {
    enable();
    final PlayerStateMachine stateMachine = player.getStateMachine();
    stateMachine.getMoving().disable();
    stateMachine.getBlocked().disable();

    final PlayerAnimation animation = player.getAnimation();
    final float stateTime = animation.getStateTime();

    TextureRegion textureRegion = idleAnimation.getKeyFrame(stateTime, true);
    animation.setTextureRegion(textureRegion);
  }

}
