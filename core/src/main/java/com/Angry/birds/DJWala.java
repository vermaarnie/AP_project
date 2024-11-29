package com.Angry.birds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class DJWala{
    private static DJWala inst;
    private Music back_music;

    private DJWala() {
        back_music = Gdx.audio.newMusic(Gdx.files.internal("angry_game_music.mp3"));
        back_music.setLooping(true);
        back_music.setVolume(0.5f);
    }

    public static DJWala getInstance(){
        if (inst == null){
            inst =new DJWala();
        }
        return inst;
    }

    public void play(){
        if(!back_music.isPlaying()){
            back_music.play();
        }
    }
    public void pause(){
        if(back_music.isPlaying()){
            back_music.pause();
        }
    }

    public void dispose(){
        back_music.dispose();
    }

}
