package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;


public class GenericLevelScreen extends ScreenAdapter {
    private Game game;
    private Screen nextScreen;
    public GenericLevelScreen(Game game, int levelIndex) {
        this.game = game;
        if (levelIndex == 2) {
            nextScreen = new LevelScreen2(game);
        } else if (levelIndex == 3) {
            nextScreen = new LevelScreen3(game);
        } else {
            throw new IllegalArgumentException("Invalid levelIndex: " + levelIndex);
        }
    }

    @Override
    public void show() {

        System.out.println("Generic Level Screen loaded.");
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void hide() {

    }
    public void moveToNextScreen() {
        game.setScreen(nextScreen);
    }
}

