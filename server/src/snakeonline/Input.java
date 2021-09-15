package snakeonline;

import entities.Direction;
import snakeonline.players.Player;

import java.io.IOException;

public class Input implements Runnable{
	
	Player player;
	
	public Input(Player p) {
		this.player = p;
	}

	@Override
	public void run() {
		while(true) {
			Direction d = Direction.right;
			try {
				d = (Direction) player.getObjectInputStream().readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			player.getSnake().setDirection(d);
		}
	}

}
