package com.Angry.birds;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AngryGame extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen(this));
    }
    public void dispose(){
        super.dispose();
        getScreen().dispose();
    }
}
