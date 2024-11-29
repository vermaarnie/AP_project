package com.Angry.birds;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FirstScreen implements Screen {
    private Game game;
    private Texture starting_back_ground;
    private SpriteBatch batch;
    private BitmapFont line;
    private GlyphLayout glp;
    private Stage stg;
    public FirstScreen(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        line = new BitmapFont();
        glp = new GlyphLayout();
        stg = new Stage();
        starting_back_ground = new Texture(Gdx.files.internal("backmenu.jpg"));
        batch = new SpriteBatch();
        DJWala.getInstance().play();
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();
        batch.draw(starting_back_ground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        glp.setText(line, "Tab to continue");
        float X = (Gdx.graphics.getWidth() - glp.width) / 2;
        float Y = 50;
        line.draw(batch, glp, X, Y);
        batch.end();
        stg.act(delta);
        stg.draw();
        if (Gdx.input.isTouched()) {
            game.setScreen(new loadingScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stg.getViewport().update(width, height, true);
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
        starting_back_ground.dispose();
        line.dispose();
        batch.dispose();
        stg.dispose();
    }
}
