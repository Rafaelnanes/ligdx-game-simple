package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.context.MyStage;
import com.mygdx.game.enemy.catus.CactusEnemy;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.healthbar.HealthBar;

public class FantasyGame extends ApplicationAdapter {
  private OrthographicCamera camera;
  private TiledMapRenderer tiledMapRenderer;
  private MyStage stage;

  @Override
  public void create() {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.update();
    TiledMap tiledMap = MyAssetManager.getInstance().getTileMap();

    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    stage = new MyStage();
    createCactusEnemies(tiledMap);

    Player player = new Player();
    stage.addActor(player);

    HealthBar healthBar = new HealthBar(player);
    stage.addActor(healthBar);

    stage.setPlayer(player);

  }

  private void createCactusEnemies(TiledMap tiledMap) {
    MapObjects trapsCollisions = tiledMap.getLayers().get("Traps").getObjects();
    for (MapObject mapObject : trapsCollisions) {
      RectangleMapObject rectangleMapObject = (RectangleMapObject) mapObject;
      final Rectangle rectangle = rectangleMapObject.getRectangle();
      stage.addActor(new CactusEnemy(rectangle.getX(), rectangle.getY()));
    }
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();

    stage.act();
    stage.draw();

  }

  @Override
  public void dispose() {
    super.dispose();
    stage.dispose();
  }

}