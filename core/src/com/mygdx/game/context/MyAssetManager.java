package com.mygdx.game.context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MyAssetManager {
  private static MyAssetManager INSTANCE;
  private final AssetManager assetManager = new AssetManager();
  private final TiledMap tiledMap;
  private final FileHandle zeldaAtlas;

  private MyAssetManager() {
    tiledMap = new TmxMapLoader().load("tiled/first-map.tmx");
    zeldaAtlas = Gdx.files.internal("zelda/zelda.atlas");
  }

  public static MyAssetManager getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new MyAssetManager();
      INSTANCE.loadImages();
    }
    return INSTANCE;
  }

  private void loadImages() {
    assetManager.load("zelda/zelda.atlas", TextureAtlas.class);
    assetManager.load("enemy/cactus.png", Texture.class);
  }

  public FileHandle getZeldaAtlas() {
    return zeldaAtlas;
  }

  public TiledMap getTileMap() {
    return tiledMap;
  }

  public FileHandle getCactusTexture() {
    //    return assetManager.get("enemy/cactus.png", Texture.class);
    return Gdx.files.internal("enemy/cactus.png");
  }

  public FileHandle getBloodTexture() {
    //    return assetManager.get("enemy/cactus.png", Texture.class);
    return Gdx.files.internal("zelda/blood.png");
  }
}
