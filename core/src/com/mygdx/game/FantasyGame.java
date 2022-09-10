package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.context.MyState;
import com.mygdx.game.player.Player;
import com.mygdx.game.player.healthbar.HealthBar;

public class FantasyGame extends ApplicationAdapter {
  private OrthographicCamera camera;
  private TiledMapRenderer tiledMapRenderer;
  private MyState stage;

  @Override
  public void create() {
    camera = new OrthographicCamera();
    camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    camera.update();
    TiledMap tiledMap = MyAssetManager.getInstance().getTileMap();

    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    stage = new MyState();

    HealthBar healthBar = new HealthBar(200, 100);
    healthBar.setPosition(10, Gdx.graphics.getHeight() - 20);
    stage.addActor(healthBar);

    Player zelda = new Player();
    stage.addActor(zelda);

    stage.setHealthBar(healthBar);

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
    stage.act();

  }

  @Override
  public void dispose() {
    super.dispose();
    stage.dispose();
  }

}