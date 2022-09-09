package com.mygdx.game.player.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.PlayerAnimation;
import com.mygdx.game.player.PlayerStateMachine;

import static com.mygdx.game.player.PlayerAnimation.FRAME_DURATION;

public class PlayerStateMoving extends AbstractPlayerState {

  private final Animation<TextureAtlas.AtlasRegion> runRightAnimation;
  private final Animation<TextureAtlas.AtlasRegion> runLeftAnimation;
  private final Animation<TextureAtlas.AtlasRegion> runUpAnimation;
  private final Animation<TextureAtlas.AtlasRegion> runDownAnimation;

  public PlayerStateMoving() {
    TextureAtlas textureAtlas = new TextureAtlas(MyAssetManager.getInstance().getZeldaAtlas());
    runRightAnimation =
        new Animation<>(FRAME_DURATION, textureAtlas.findRegions("runRight"),
            Animation.PlayMode.LOOP_REVERSED);
    runLeftAnimation =
        new Animation<>(FRAME_DURATION, textureAtlas.findRegions("runLeft"), Animation.PlayMode.LOOP);
    runUpAnimation = new Animation<>(FRAME_DURATION, textureAtlas.findRegions("runUp"), Animation.PlayMode.LOOP);
    runDownAnimation = new Animation<>(FRAME_DURATION, textureAtlas.findRegions("runDown"), Animation.PlayMode.LOOP);
  }

  @Override
  public PlayerState action(Player player) {
    enable();
    final PlayerStateMachine stateMachine = player.getStateMachine();
    stateMachine.getIdle().disable();
    stateMachine.getBlocked().disable();

    final PlayerAnimation animation = player.getAnimation();
    final Rectangle playerRectangle = animation.getPlayerRectangle();
    final float speed = animation.getSpeed();
    final float stateTime = animation.getStateTime();
    TextureRegion textureRegion = null;

    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
      playerRectangle.setX(playerRectangle.getX() + speed);
      textureRegion = runRightAnimation.getKeyFrame(stateTime, true);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
      playerRectangle.setX(playerRectangle.getX() - speed);
      textureRegion = runLeftAnimation.getKeyFrame(stateTime, true);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
      playerRectangle.setY(playerRectangle.getY() + speed);
      textureRegion = runUpAnimation.getKeyFrame(stateTime, true);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
      playerRectangle.setY(playerRectangle.getY() - speed);
      textureRegion = runDownAnimation.getKeyFrame(stateTime, true);
    }

    animation.setTextureRegion(textureRegion);
    return this;
  }

}
