package com.Angry.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bird {
    private Texture texture;
    private Sprite sprite;
    private Body body;
    private World world;
    private int hitCount = 0;
    public Bird(Texture texture, World world) {
        this.texture = texture;
        this.world = world;
        this.sprite = new Sprite(texture);
        sprite.setSize(30, 30);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(-100, -100);
        body = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(15);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.filter.maskBits = 0x0000;
        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void incrementHitCount() {
        hitCount++;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void resetHitCount() {
        hitCount = 0;
    }

    public void destroy() {
        if (body != null) {
            world.destroyBody(body);
            body = null;
        }
        hitCount = 0;
    }

    public void setPosition(float x, float y) {
        body.setTransform(x, y, 0);
        body.setLinearVelocity(0, 0);
        body.setAngularVelocity(0);
        sprite.setPosition(x - sprite.getWidth() / 2, y - sprite.getHeight() / 2);
    }

    public void setStatic(boolean isStatic) {
        body.setType(isStatic ? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody);
        Filter filter = body.getFixtureList().first().getFilterData();
        filter.maskBits = (short) (isStatic ? 0x0000 : 0xFFFF);
        body.getFixtureList().first().setFilterData(filter);
        body.setAwake(!isStatic);
    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
        sprite.draw(batch);
    }

    public void update(float delta) {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
    }

    public void launch(float velocityX, float velocityY) {
        body.setType(BodyDef.BodyType.DynamicBody);
        Filter filter = body.getFixtureList().first().getFilterData();
        filter.maskBits = (short) 0xFFFF;
        body.getFixtureList().first().setFilterData(filter);
        body.setLinearVelocity(velocityX, velocityY);
    }

    public boolean isOutOfBounds(int screenWidth, int screenHeight) {
        return body.getPosition().x < 0 || body.getPosition().x > screenWidth ||
            body.getPosition().y < 0 || body.getPosition().y > screenHeight;
    }

    public boolean hasCollided(Body otherBody) {
        return body.getPosition().dst(otherBody.getPosition()) < 50;
    }

    public void reset(Vector2 initialPosition) {
        setPosition(initialPosition.x, initialPosition.y);
        setStatic(true);
    }

    public Body getBody() {
        return body;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public void dispose() {
        texture.dispose();
    }
}
