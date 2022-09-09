package com.mygdx.game.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.zelda.state.ZeldaState;
import com.mygdx.game.zelda.state.ZeldaStateIdle;

import java.util.ArrayList;
import java.util.List;

public class ZeldaAnimation {

  public static final float FRAME_DURATION = 0.1f;
  private final List<Rectangle> rectanglesCollisions;
  private final List<Rectangle> bufferCollisions;
  private final Rectangle playerRectangle;
  private final ZeldaPlayer player;
  private final Vector2 lastPosition = new Vector2();
  private float stateTime = 0f;
  private float speed;
  private TextureRegion textureRegion;

  public ZeldaAnimation(ZeldaPlayer player, MapObjects collisions, MapObjects bufferCollision) {
    this.player = player;
    rectanglesCollisions = getCollisions(collisions);
    bufferCollisions = getCollisions(bufferCollision);

    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("zelda/zelda.atlas"));

    Animation<TextureAtlas.AtlasRegion> idleAnimation =
        new Animation<>(FRAME_DURATION * 2, textureAtlas.findRegions("idle"), Animation.PlayMode.LOOP);

    final TextureAtlas.AtlasRegion anyRegion = idleAnimation.getKeyFrame(0);
    playerRectangle = new Rectangle(0, 0, anyRegion.getRegionWidth(), anyRegion.getRegionHeight());
  }

  public void draw(Batch batch) {
    lastPosition.set(playerRectangle.getX(), playerRectangle.getY());
    speed = player.getVelocity() * Gdx.graphics.getDeltaTime();
    stateTime += Gdx.graphics.getDeltaTime();

    if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
      speed *= 2;
    }

    for (Rectangle rectanglesCollision : bufferCollisions) {
      if (playerRectangle.overlaps(rectanglesCollision)) {
        speed *= 2;
        break;
      }
    }
    final ZeldaStateMachine stateMachine = player.getStateMachine();
    ZeldaState state = stateMachine.getIdle();

    if (Gdx.input.isKeyPressed(Input.Keys.A)
        || Gdx.input.isKeyPressed(Input.Keys.W)
        || Gdx.input.isKeyPressed(Input.Keys.D)
        || Gdx.input.isKeyPressed(Input.Keys.S)) {
      state = stateMachine.changeToMoving();
    }

    for (Rectangle rectanglesCollision : rectanglesCollisions) {
      if (playerRectangle.overlaps(rectanglesCollision)) {
        state = stateMachine.changeToBlocked();
        break;
      }
    }

    if (state instanceof ZeldaStateIdle) {
      stateMachine.changeToIdle();
    }

    batch.draw(textureRegion, playerRectangle.getX(), playerRectangle.getY());
  }

  public Rectangle getPlayerRectangle() {
    return playerRectangle;
  }

  public float getSpeed() {
    return speed;
  }

  public float getStateTime() {
    return stateTime;
  }

  public Vector2 getLastPosition() {
    return lastPosition;
  }

  public void setTextureRegion(TextureRegion textureRegion) {
    this.textureRegion = textureRegion;
  }

  private List<Rectangle> getCollisions(MapObjects collisions) {
    final List<Rectangle> rectangles = new ArrayList<>();
    for (int i = 0; i < collisions.getCount(); i++) {
      Rectangle rectangle = ((RectangleMapObject) collisions.get(i)).getRectangle();
      rectangles.add(rectangle);
    }
    return rectangles;
  }

}
