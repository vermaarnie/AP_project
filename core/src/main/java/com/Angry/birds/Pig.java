package com.Angry.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pig {
    private Texture texture;
    private Vector2 position ;
    public Pig(Texture texture ,float x ,float y){
        this.texture=texture;
        this.position=new Vector2(x,y);
    }
    public void render(SpriteBatch batch){
        batch.draw(texture,position.x,position.y);
    }
    public int getx(){
        return (int) this.position.x;
    }
    public int gety(){
        return (int) this.position.y;
    }
    public void dispose(){
        texture.dispose();
    }
}
