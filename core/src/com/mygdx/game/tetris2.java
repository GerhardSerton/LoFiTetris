package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.TimeUtils;

public class tetris2 extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture gameOver;
	Texture gameWon;
	Texture img;
	Texture blockTex;
	Texture scoreBlockTex;
	Pixmap block;
	Pixmap nextBlock;
	Pixmap scoreBlock;
	Texture nextBlockTex;
	Sprite nextBlockSprite;
	Sprite sprite;
	Sprite scoreSprite;
	gameBoard board;

	long startTime = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background.jpg");
		gameOver = new Texture("gameover.jpg");
		gameWon = new Texture("gamewin.jpg");

		block = new Pixmap(40, 40, Pixmap.Format.RGBA8888);
		block.setColor(Color.WHITE);
		block.fill();
		blockTex = new Texture(block);
		block.dispose();
		sprite = new Sprite(blockTex);

		nextBlock = new Pixmap(10,10, Pixmap.Format.RGBA8888);
		nextBlock.setColor(Color.RED);
		nextBlock.fill();
		nextBlockTex = new Texture(nextBlock);
		nextBlock.dispose();
		nextBlockSprite = new Sprite(nextBlockTex);

		scoreBlock = new Pixmap(10,10,Pixmap.Format.RGBA8888);
		scoreBlock.setColor(Color.WHITE);
		scoreBlock.fill();
		scoreBlockTex = new Texture(scoreBlock);
		scoreBlock.dispose();
		scoreSprite = new Sprite(scoreBlockTex);


		Gdx.graphics.setContinuousRendering(true);

		board = new gameBoard();
		board.newPiece();
		board.applyToBoard();


		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		int startX = 50;
		int startY = 800;
		if (TimeUtils.timeSinceNanos(startTime) > 1000000000)
		{
			board.movePieceDown();
			startTime = TimeUtils.nanoTime();
		}

		batch.begin();
		batch.draw(img, 0, 0);

		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if(board.getCompositeBoard()[i][j] == 1)
				{
					sprite.setPosition(startX, startY);
					sprite.draw(batch);
				}
				startX += 40;
			}
			startX = 50;
			startY -= 40;
		}

		startX = 635;
		startY = 738;
		for (int i = 0; i < board.nextUp.length; i++)
		{
			for (int j = 0; j < board.nextUp[0].length; j++)
			{
				if (board.nextUp[i][j] == 1)
				{
					nextBlockSprite.setPosition(startX, startY);
					nextBlockSprite.draw(batch);
				}
				startX += 10;
			}
			startX = 635;
			startY -= 10;
		}

		int tempScore = board.score;
		startX = 590;
		startY = 285;
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if(tempScore > 0)
				{
					scoreSprite.setPosition(startX,startY);
					scoreSprite.draw(batch);
					tempScore -= 1;
				}
				startX += 35;
			}
			startX = 590;
			startY -= 35;
		}

		board.loseCondition();
		if (board.score > 15)
		{
			while(true) {
				batch.draw(gameWon, 0, 0);
			}

		}
		else if (board.lost)
		{
			batch.draw(gameOver,0,0);
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		blockTex.dispose();
	}

	@Override
	public boolean keyDown(int keycode){
			if(keycode == Keys.LEFT)
			{
				board.movePieceLeft();
			}
			if (keycode == Keys.RIGHT)
			{
				board.movePieceRight();
			}
			if (keycode == Keys.DOWN)
			{
				board.movePieceDown();
				startTime = TimeUtils.nanoTime();
			}
			if (keycode == Keys.UP)
			{
				board.rotatePiece();
			}
			if (keycode == Keys.SPACE)
			{
				board.spendPoint();
			}
		return true;
	}

	@Override
	public boolean keyUp(int keycode){
		return false;
	}

	@Override
	public boolean keyTyped(char character){
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button){
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button){
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer){
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y){
		return false;
	}

	@Override
	public boolean scrolled(int amount){
		return false;
	}

}
