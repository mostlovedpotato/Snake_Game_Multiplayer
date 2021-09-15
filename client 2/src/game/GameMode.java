package game;

import controls.Input;
import entities.Food;
import entities.Snake;

public class GameMode {
	
	Snake snake;
	Food food;
	boolean paused = false;
	Input input;
	
	public Snake getSnake() {
		return snake;
	}
	
	public Food getFood() {
		return food;
	}
}
