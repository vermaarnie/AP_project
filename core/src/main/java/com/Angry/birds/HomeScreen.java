package com.Angry.birds;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class HomeScreen implements Screen{
    private Game game;
    private Texture level_back_ground;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;


    public HomeScreen(Game game){
        this.game=game;
    }

    @Override
    public void show(){
        stage=new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin=new Skin(Gdx.files.internal("uiskin.json"));

        TextButton newGameButton=new TextButton("New Game",skin);
        TextButton continueButton=new TextButton("Continue Game",skin);
        TextButton exitButton=new TextButton("Exit Game",skin);


        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                startNewGame();
            }
        });

        continueButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                continueSavedGame();
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x,float y){
                Gdx.app.exit();
            }
        });

        Table table=new Table();
        table.setFillParent(true);
        table.center();
        table.add(newGameButton).padBottom(20).row();
        table.add(continueButton).padBottom(20).row();
        table.add(exitButton);

        stage.addActor(table);

        level_back_ground=new Texture(Gdx.files.internal("background.jpg"));

        batch=new SpriteBatch();
        DJWala.getInstance().play();
    }

    private void startNewGame(){
        game.setScreen(new LevelScreen(game));
    }

    private void continueSavedGame(){

        game.setScreen(new ContinueLevelScreen(game));
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(Gdx.gl.GL_COLOR_BUFFER_BIT);



        batch.begin();
        batch.draw(level_back_ground,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width,int height){


        stage.getViewport().update(width,height,true);
    }

    @Override
    public void pause(){}

    @Override
    public void resume(){}

    @Override
    public void hide(){
        dispose();
    }

    @Override
    public void dispose(){


        level_back_ground.dispose();
        batch.dispose();
        skin.dispose();
        stage.dispose();

    }
}
