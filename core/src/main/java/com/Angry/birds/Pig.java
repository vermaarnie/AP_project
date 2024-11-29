package com.Angry.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Pig {
    private Texture texture;
    private Sprite sprite;
    private Body body;
    private World world;
    private int health;
    private boolean isDestroyed;

    public Pig(Texture texture, World world, float x, float y, int health) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.world = world;
        this.health = health;
        this.isDestroyed = false;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(15);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        if (!isDestroyed) {
            sprite.setPosition(
                body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2
            );
            sprite.draw(batch);
        }
    }

    public boolean isHit(Body otherBody) {

        return body.getPosition().dst(otherBody.getPosition()) < 40;
    }

    public void takeDamage() {
        if (!isDestroyed) {
            health--;
            if (health <= 0) {
                destroy();
            }
        }
    }

    public void destroy() {
        if (!isDestroyed) {
            world.destroyBody(body);
            body = null;
            isDestroyed = true;
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void dispose() {
        texture.dispose();
    }

    public Body getBody() {
        return body;
    }

    public int getHealth() {
        return health;
    }
}

