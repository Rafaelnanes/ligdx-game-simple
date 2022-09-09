package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.player.state.PlayerState;
import com.mygdx.game.player.state.PlayerStateIdle;

import java.util.ArrayList;
import java.util.List;

public class PlayerAnimation {

  public static final float FRAME_DURATION = 0.1f;
  private final List<Rectangle> rectanglesCollisions;
  private final List<Rectangle> bufferCollisions;
  private final Rectangle playerRectangle;
  private final Player player;
  private final Vector2 lastPosition = new Vector2();
  private float stateTime = 0f;
  private float speed;
  private TextureRegion textureRegion;

  public PlayerAnimation(Player player, MapObjects collisions, MapObjects bufferCollision) {
    this.player = player;
    rectanglesCollisions = getCollisions(collisions);
    bufferCollisions = getCollisions(bufferCollision);

    final TextureAtlas.AtlasRegion anyRegion =
        new TextureAtlas(MyAssetManager.getInstance().getZeldaAtlas()).getRegions().get(0);
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
    final PlayerStateMachine stateMachine = player.getStateMachine();
    PlayerState state = stateMachine.getIdle();

    if (Gdx.input.isKeyPressed(Input.Keys.A)
        || Gdx.input.isKeyPressed(Input.Keys.W)
        || Gdx.input.isKeyPressed(Input.Keys.D)
        || Gdx.input.isKeyPressed(Input.Keys.S)) {
      state = stateMachine.activateMoving();
    }

    for (Rectangle rectanglesCollision : rectanglesCollisions) {
      if (playerRectangle.overlaps(rectanglesCollision)) {
        state = stateMachine.activateBlocked();
        break;
      }
    }

    if (state instanceof PlayerStateIdle) {
      stateMachine.activateIdle();
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
