package com.Angry.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class Block {
    private Texture texture;
    private Sprite sprite;
    private Body body;
    private World world;
    private int health;
    private boolean isDestroyed;

    public Block(Texture texture, World world, float x, float y, float width, float height, float density, int health) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
        this.world = world;
        this.health = health;
        this.isDestroyed = false;

        sprite.setSize(width, height);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.1f;

        body.createFixture(fixtureDef);
        shape.dispose();


        body.setAngularDamping(0f);
    }

    public void render(SpriteBatch batch) {
        if (!isDestroyed) {
            sprite.setPosition(
                body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2
            );
            sprite.setRotation((float) Math.toDegrees(body.getAngle()));
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
        return  health;
    }
}
