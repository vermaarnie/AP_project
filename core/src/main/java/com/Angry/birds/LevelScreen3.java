package com.Angry.birds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;
import java.util.List;

public class LevelScreen3 implements Screen {
    private SpriteBatch batch;
    private World world;
    private Bird bird;
    private Texture background;
    private Texture groundTexture;
    private Body groundBody;
    private Texture imageTexture;
    private List<Pig> pigs;
    private Texture pigTexture1, pigTexture2, pigTexture3,pigTexture4,pigTexture5,pigTexture6;
    private List<Block> blocks;
    private Texture blockTexture1, blockTexture2, blockTexture3,blockTexture4,blockTexture5;
    private Stage stage; // For the UI
    private Skin skin;
    private Game game;
    private Vector2 initialPosition;
    private Vector2 draggedPosition;
    private boolean isDragging = false;
    private List<Bird> birds;
    private int currentBirdIndex = 0;
    private boolean isBirdLaunched = false;
    private final Vector2 initialBirdPosition ;


    public LevelScreen3(Game game) {
        this.game = game;
        world = new World(new Vector2(0, -9.8f), true);
        birds = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            birds.add(new Bird(new Texture(Gdx.files.internal("Red.png")), world));
        }
        bird = birds.get(currentBirdIndex);
        initialBirdPosition = new Vector2(100, 100);
        bird.setPosition(initialBirdPosition.x, initialBirdPosition.y);

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("level_background.png"));
        groundTexture = new Texture(Gdx.files.internal("ground.png"));
        imageTexture = new Texture(Gdx.files.internal("Slingshot.png"));
        pigTexture1 = new Texture(Gdx.files.internal("guard_pig.png"));
        pigTexture2 = new Texture(Gdx.files.internal("pig_king.png"));
        pigTexture3 = new Texture(Gdx.files.internal("guard_pig.png"));
        pigTexture4 = new Texture(Gdx.files.internal("guard_pig.png"));
        pigTexture5 = new Texture(Gdx.files.internal("pig.png"));
        pigTexture6 = new Texture(Gdx.files.internal("pig.png"));
        blockTexture1 = new Texture(Gdx.files.internal("glass_rotated.png"));
        blockTexture2 = new Texture(Gdx.files.internal("glass.png"));
        blockTexture3 = new Texture(Gdx.files.internal("glass_rotated.png"));
        blockTexture4 = new Texture(Gdx.files.internal("Wood1.png"));
        blockTexture5 = new Texture(Gdx.files.internal("Wood1.png"));

        blocks = new ArrayList<>();
        blocks.add(new Block(blockTexture1, world, 660, 70, 24, 206, 0.5f,2));
        blocks.add(new Block(blockTexture3, world, 760, 70, 24, 206, 0.5f,2));
        blocks.add(new Block(blockTexture2, world, 700, 300, 206, 24, 0.5f,2));
        blocks.add(new Block(blockTexture4, world, 540, 70, 70, 70, 0.5f,2));
        blocks.add(new Block(blockTexture5, world, 540, 140, 70, 70, 0.5f,2));
        blocks.add(new Block(blockTexture5, world, 540, 150, 206, 24, 0.5f,2));

        pigs = new ArrayList<>();
        pigs.add(new Pig(pigTexture1, world, 600, 70,1));
        pigs.add(new Pig(pigTexture2, world, 700, 70, 1));
        pigs.add(new Pig(pigTexture3, world, 800, 70, 1));



        pigs.add(new Pig(pigTexture4, world, 540, 200,1));
        pigs.add(new Pig(pigTexture5, world, 700, 320, 1));
        pigs.add(new Pig(pigTexture6, world, 480, 70, 1));
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton pauseButton = new TextButton("Pause", skin);
        pauseButton.setPosition(Gdx.graphics.getWidth() - 120, Gdx.graphics.getHeight() - 50);
        pauseButton.setSize(100, 40);
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PauseScreen(game, LevelScreen3.this));
            }
        });
        stage.addActor(pauseButton);
        Gdx.input.setInputProcessor(stage);
        createGround();
        bird = new Bird(new Texture(Gdx.files.internal("Bomb.png")), world);
        bird.reset(initialBirdPosition);
        bird.setPosition(initialBirdPosition.x, initialBirdPosition.y);

    }

    private void createGround() {

        BodyDef groundDef = new BodyDef();
        groundDef.type = BodyDef.BodyType.StaticBody;
        groundDef.position.set(Gdx.graphics.getWidth() / 2f, 50);

        groundBody = world.createBody(groundDef);

        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsBox(Gdx.graphics.getWidth() / 2f, -10);

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.friction = 10f;

        groundBody.createFixture(groundFixture);
        groundShape.dispose();
    }

    private void switchToNextBird() {

        bird.destroy();
        currentBirdIndex++;

        if (currentBirdIndex < birds.size()) {

            bird = birds.get(currentBirdIndex);
            bird.setPosition(initialBirdPosition.x, initialBirdPosition.y);
            isBirdLaunched = false;
        } else {

            System.out.println("No more birds! You lose!");
            game.setScreen(new LossingScreen(game, this));
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(1 / 60f, 6, 2);

        batch.begin();

        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(groundTexture, 0, 0, Gdx.graphics.getWidth(), 50);
        batch.draw(imageTexture, 100, 40);

        bird.render(batch);
        for (Pig pig : pigs) {
            pig.render(batch);
        }

        for (Block block : blocks) {
            block.render(batch);
        }
        stage.act(delta);
        stage.draw();


        batch.end();

        handleInput();
        bird.update(delta);
        Bird_performed_what_happening();

        if (bird.isOutOfBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()) || bird.hasCollided(groundBody)) {
            System.out.println("Bird stopped due to bounds or collision!");
            bird.reset(initialBirdPosition);
        }else if (isBirdLaunched && bird.getBody().getLinearVelocity().len() < 10f) {
            System.out.println("Bird velocity near zero. Destroying bird.");
            switchToNextBird();
        }

    }
    private void Bird_performed_what_happening() {

        for (Pig pig : pigs) {
            if (pig.isHit(bird.getBody())) {
                System.out.println("Bird hit a pig!");
                pig.takeDamage();
                bird.incrementHitCount();
            }
        }


        for (Block block : blocks) {
            if (block.isHit(bird.getBody())) {
                System.out.println("Bird hit a block!");
                block.takeDamage();
                bird.incrementHitCount();
            }
        }


        pigs.removeIf(Pig::isDestroyed);
        blocks.removeIf(Block::isDestroyed);


        if (pigs.isEmpty()) {
            System.out.println("All pigs destroyed! You win!");
            game.setScreen(new WinningScreen(game));
        }


        if (bird.getHitCount() >= 4) {
            System.out.println("Bird destroyed after max hits! You lose!");
            game.setScreen(new LossingScreen(game,this));
        }
    }

    private void handleInput() {
        if (Gdx.input.isTouched() && !isBirdLaunched) {
            if (!isDragging) {
                isDragging = true;
                initialPosition = new Vector2(bird.getX(), bird.getY());
                bird.setStatic(true);
            }
            Vector2 currentPosition = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            Vector2 dragVector = currentPosition.sub(initialPosition);


            if (dragVector.len() > 40f) {
                dragVector.nor().scl(40f);
            }

            draggedPosition = initialPosition.cpy().add(dragVector);


            bird.getBody().setTransform(draggedPosition.x, draggedPosition.y, 0);
            bird.setPosition(draggedPosition.x, draggedPosition.y);
        } else if (isDragging) {
            isDragging = false;
            isBirdLaunched = true;


            Vector2 releasePosition = new Vector2(bird.getX(), bird.getY());
            Vector2 impulse = initialPosition.sub(releasePosition);
            float distance = impulse.len();
            impulse.nor().scl(distance * 10);
            bird.setStatic(false);
            bird.launch(impulse.x, impulse.y);

            System.out.println("Bird launched with impulse: " + impulse);
        }
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        groundTexture.dispose();
        bird.dispose();
        pigTexture1.dispose();
        pigTexture2.dispose();
        pigTexture3.dispose();
        for (Pig pig : pigs) {
            pig.dispose();
        }
        for (Block block : blocks) {
            block.dispose();
        }
        stage.dispose();
        skin.dispose();
        world.dispose();
    }
}

