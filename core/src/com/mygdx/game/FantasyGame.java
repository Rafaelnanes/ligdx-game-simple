package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.player.Player;

public class FantasyGame extends ApplicationAdapter {
  TiledMap tiledMap;
  OrthographicCamera camera;
  TiledMapRenderer tiledMapRenderer;

  Stage stage;
  SpriteBatch sb;
  Player zelda;

  @Override
  public void create() {
    sb = new SpriteBatch();
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, w, h);
    camera.update();
    tiledMap = MyAssetManager.getInstance().getTileMap();
    final MapLayer collisionLayer = tiledMap.getLayers().get("Blockers");
    final MapLayer buffLayer = tiledMap.getLayers().get("Flowers");
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    zelda = new Player(collisionLayer.getObjects(), buffLayer.getObjects());

    stage = new Stage();
    stage.addActor(zelda);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(1, 0, 0, 1);
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    camera.update();
    tiledMapRenderer.setView(camera);
    tiledMapRenderer.render();

    stage.draw();

  }

  @Override
  public void dispose() {
    super.dispose();
    sb.dispose();
    stage.dispose();
  }

}