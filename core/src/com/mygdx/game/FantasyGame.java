package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.context.MyAssetManager;
import com.mygdx.game.player.Player;
import com.mygdx.game.text.FloatingText;

public class FantasyGame extends ApplicationAdapter {
  private OrthographicCamera camera;
  private TiledMapRenderer tiledMapRenderer;

  private Stage stage;
  private FloatingText floatingText;

  @Override
  public void create() {
    float w = Gdx.graphics.getWidth();
    float h = Gdx.graphics.getHeight();
    camera = new OrthographicCamera();
    camera.setToOrtho(false, w, h);
    camera.update();
    TiledMap tiledMap = MyAssetManager.getInstance().getTileMap();

    tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

    stage = new Stage();

    Player zelda = new Player();
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
    stage.dispose();
  }

}