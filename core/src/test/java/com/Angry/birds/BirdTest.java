package com.Angry.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BirdTest {
    private Bird bird;
    private World mockWorld;
    private Texture mockTexture;
    private Body mockBody;

    @BeforeEach
    void setUp() {
        mockTexture = mock(Texture.class);
        mockWorld = mock(World.class);
        mockBody = mock(Body.class);
        when(mockWorld.createBody(any(BodyDef.class))).thenReturn(mockBody);
        bird = new Bird(mockTexture, mockWorld);
    }






    @Test
    void testIncrementHitCount() {
        bird.incrementHitCount();
        bird.incrementHitCount();
        assertEquals(2, bird.getHitCount(), "Hit count should increment correctly");
    }

    @Test
    void testResetHitCount() {
        bird.incrementHitCount();
        bird.resetHitCount();
        assertEquals(0, bird.getHitCount(), "Hit count should reset to 0");
    }
    @Test
    void testDestroy() {
        bird.destroy();
        assertNull(bird.getBody(), "Body should be null after destruction");
        verify(mockWorld).destroyBody(mockBody);
    }


}
