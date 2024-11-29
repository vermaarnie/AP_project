package com.Angry.birds;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.lang.*;
import java.util.Random;

import static java.lang.Math.random;


public class LevelScreen implements Screen {
    private Game game;
    private Texture level_back_ground;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private final Array<String> levels;

    public LevelScreen(Game game) {
        this.game = game;
        this.levels = new Array<>(new String[]{"Level 1", "Level 2", "Level 3"});
    }

    @Override
    public void show() {

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        DJWala.getInstance().play();
        Random r =new Random();

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        TextButton random = new TextButton("Random Level",skin);
        random.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                int random_int = r.nextInt(6);
                if(random_int == 1){
                    game.setScreen(new RandomLevel_1(game));
                } else if (random_int == 2) {
                    game.setScreen(new RandomLevel_2(game));
                }
                else if(random_int == 3){
                    game.setScreen(new RandomLevel_3(game));
                }
                else if(random_int ==4){
                    game.setScreen(new RandomLevel_4(game));
                } else if (random_int == 5) {
                    game.setScreen(new RandomLevel_5(game));
                }
            }
        });


        for (int i = 0; i < levels.size; i++) {
            final int levelIndex = i;
            TextButton levelButton = new TextButton(levels.get(i), skin);
            levelButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    to_level1screen(levelIndex);
                }
            });
            table.add(levelButton).padBottom(20).row();
        }
        table.add(random).padBottom(20).row();


        TextButton to_home = new TextButton("Back", skin);
        to_home.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HomeScreen(game));
                DJWala.getInstance().pause();
            }
        });
        table.add(to_home).padTop(10);

        stage.addActor(table);

        level_back_ground = new Texture(Gdx.files.internal("background.jpg"));
        batch = new SpriteBatch();
    }

    private void to_level1screen(int levelIndex) {
        if (levelIndex == 0) {
            game.setScreen(new LevelScreen1(game));
        }
        else if(levelIndex==1) {
            game.setScreen(new LevelScreen2(game));
        }
        else{
            game.setScreen(new LevelScreen3(game));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(level_back_ground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        level_back_ground.dispose();
        skin.dispose();
    }
}
