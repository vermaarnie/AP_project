package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Level1Screen implements Screen {

    private Texture level_background;
    private Texture ground;
    private Bird bird1;
    private Bird bird2;
    private Bird bird3;
    private Block block1;
    private Block block2;
    private Block block3;
    private  Block block4;
    private  Block block5;
    private  Block block6;
    private Pig pig1;
    private Pig pig2;
    private Pig pig3;

    private Stage stg;
    private Game game;
    private SpriteBatch batch;
    private Texture catp_1;
    private Texture catp_2;
    private Skin skin;
    boolean gameState;

    public Level1Screen(Game game){
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        catp_1 = new Texture(Gdx.files.internal("catp1.png"));
        catp_2 = new Texture(Gdx.files.internal("catp2.png"));




        level_background = new Texture(Gdx.files.internal("level_background.png"));
        ground = new Texture(Gdx.files.internal("ground.png"));
        stg = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stg);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        bird1=new Bird(new Texture(Gdx.files.internal("Red.png")),10,80);
        bird2=new Bird(new Texture(Gdx.files.internal("Red.png")),50,80);
        bird3=new Bird(new Texture(Gdx.files.internal("Chuck.png")),110,150);
        pig1 = new Pig(new Texture(Gdx.files.internal("pig.png")),540,143);
        pig2 = new Pig(new Texture(Gdx.files.internal("pig.png")),610,213);
        pig3 = new Pig(new Texture(Gdx.files.internal("pig.png")),680,143);
        block1 = new Block(new Texture(Gdx.files.internal("Wood1.png")),540,78);
        block2 = new Block(new Texture(Gdx.files.internal("Wood1.png")),610,78);
        block3 = new Block(new Texture(Gdx.files.internal("Wood1.png")),680,78);
        block4 = new Block(new Texture(Gdx.files.internal("Wood2.png")),470,78);
        block5 = new Block(new Texture(Gdx.files.internal("Wood1.png")),610,147);
        block6 = new Block(new Texture(Gdx.files.internal("Wood2_Rotated.png")),743,78);
        TextButton pause_button = new TextButton("Pause",skin);
        pause_button.setPosition(10,Gdx.graphics.getHeight() - 50);
        pause_button.addListener(new ClickListener(){
            public void clicked(InputEvent event,float x,float y){
                to_pausescreen();

            }
        });

        stg.addActor(pause_button);
    }


    public void to_pausescreen(){
        game.setScreen(new PauseScreen(game,Level1Screen.this));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);

        batch.begin();
        batch.draw(level_background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(ground,0,0,Gdx.graphics.getWidth(),100);
        batch.draw(catp_1,100,80);
        bird3.render(batch);
        batch.draw(catp_2,100,120);
        bird1.render(batch);
        bird2.render(batch);

        block1.render(batch);
        block2.render(batch);
        block3.render(batch);
        block4.render(batch);
        block5.render(batch);
        block6.render(batch);
        pig1.render(batch);
        pig2.render(batch);
        pig3.render(batch);


        batch.end();

        stg.act(delta);
        stg.draw();



    }

    @Override
    public void resize(int width, int height) {
        stg.getViewport().update(width,height,true);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        catp_1.dispose();
        catp_2.dispose();
        bird1.dispose();
        bird2.dispose();
        bird3.dispose();
        pig1.dispose();
        pig2.dispose();
        pig3.dispose();
        block1.dispose();
        block2.dispose();
        block3.dispose();
        block4.dispose();
        block5.dispose();
        block6.dispose();



        level_background.dispose();
        ground.dispose();
        batch.dispose();

        stg.dispose();
        skin.dispose();

    }
}
