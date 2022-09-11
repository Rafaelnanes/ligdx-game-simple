package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
  private final MapObjects trapsCollision;
  private final Rectangle playerRectangle;
  private final Player player;
  private final Vector2 lastPosition = new Vector2();
  private float stateTime = 0f;
  private float speed;
  private TextureRegion textureRegion;

  public PlayerAnimation(Player player) {
    this.player = player;

    final TiledMap tileMap = MyAssetManager.getInstance().getTileMap();
    final MapObjects collisions = tileMap.getLayers().get("Blockers").getObjects();
    final MapObjects buffLayer = tileMap.getLayers().get("Flowers").getObjects();
    trapsCollision = tileMap.getLayers().get("Traps").getObjects();
    rectanglesCollisions = getCollisions(collisions);
    bufferCollisions = getCollisions(buffLayer);

    final TextureAtlas.AtlasRegion anyRegion =
        new TextureAtlas(MyAssetManager.getInstance().getZeldaAtlas()).getRegions().get(0);
    playerRectangle = new Rectangle(0, 0, anyRegion.getRegionWidth(), anyRegion.getRegionHeight());
  }


  public void draw(Batch batch) {
    lastPosition.set(playerRectangle.getX(), playerRectangle.getY());
    speed = player.getVelocity() * Gdx.graphics.getDeltaTime();
    stateTime += Gdx.graphics.getDeltaTime();
    final PlayerStateMachine stateMachine = player.getStateMachine();
    PlayerState state = stateMachine.getIdle();

    if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
      speed *= 2;
    }

    checkMovementBuff();
    checkGetHit(stateMachine);
    state = checkMovement(stateMachine, state);
    state = checkCollision(stateMachine, state);

    if (state instanceof PlayerStateIdle) {
      stateMachine.activateIdle();
    }

    batch.draw(textureRegion, playerRectangle.getX(), playerRectangle.getY());

  }

  private PlayerState checkMovement(PlayerStateMachine stateMachine, PlayerState state) {
    if (Gdx.input.isKeyPressed(Input.Keys.A)
        || Gdx.input.isKeyPressed(Input.Keys.W)
        || Gdx.input.isKeyPressed(Input.Keys.D)
        || Gdx.input.isKeyPressed(Input.Keys.S)) {
      state = stateMachine.activateMoving();
    }
    return state;
  }


  private void checkMovementBuff() {
    for (Rectangle rectanglesCollision : bufferCollisions) {
      if (playerRectangle.overlaps(rectanglesCollision)) {
        speed *= 2;
        break;
      }
    }
  }

  private PlayerState checkCollision(PlayerStateMachine stateMachine, PlayerState state) {
    for (Rectangle rectanglesCollision : rectanglesCollisions) {
      if (playerRectangle.overlaps(rectanglesCollision)) {
        state = stateMachine.activateBlocked();
        break;
      }
    }
    return state;
  }

  private List<Rectangle> getCollisions(MapObjects collisions) {
    final List<Rectangle> rectangles = new ArrayList<>();
    for (MapObject collision : collisions) {
      Rectangle rectangle = ((RectangleMapObject) collision).getRectangle();
      rectangles.add(rectangle);
    }
    return rectangles;
  }

  private void checkGetHit(PlayerStateMachine stateMachine) {
    for (MapObject mapObject : trapsCollision) {
      RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
      if (playerRectangle.overlaps(rectangleMapObject.getRectangle())) {
        // não interfere no estado idle
        final MapProperties properties = mapObject.getProperties();
        final String isValidKey = "isValidKey";
        if (properties.containsKey(isValidKey) && !properties.get(isValidKey, Boolean.class)) {
          break;
        }
        stateMachine.activateGetHit();
        properties.put(isValidKey, Boolean.FALSE);
        break;
      }
    }
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

}
