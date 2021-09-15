package start;

import GUI.SnakePanel;
import game.LocalPlayer;
import game.OnlineMode;
import game.SinglePlayer;

import javax.swing.*;
import java.awt.*;

public class Main {
	
	// Window Properties
	static JFrame frame;
	static SnakePanel panel;
	static ImageIcon imgIcon;
	static int WIDTH = 1200;
	static int HEIGHT = 800;
	
	// Game Modes
	public static SinglePlayer singleGame;
	public static LocalPlayer localGame;
	public static OnlineMode onlineGame;
	
	// Threads
	public static Thread singleThread;
	public static Thread localThread;
	public static Thread onlineThread;

	public static void main(String[] args) {
		init();
		configureWindow();
		run();
	}
	
	private static void run() {
		while(true) {
			render();
		}
	}
	
	private static void init() {
		frame = new JFrame("Snake Online");
		panel = new SnakePanel();
		imgIcon = new ImageIcon("res/icon.png");
		singleGame = new SinglePlayer();
		localGame = new LocalPlayer();
		onlineGame = new OnlineMode();
		singleThread = new Thread(singleGame);
		localThread = new Thread(localGame);
		onlineThread = new Thread(onlineGame);
	}
	
	private static void configureWindow() {
		// Panel properties
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// Frame properties
		frame.setIconImage(imgIcon.getImage());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
	}
	
	public static void startSinglePlayer() {
		singleGame.reset();
		singleThread = new Thread(singleGame);
		singleThread.start();
	}
	
	public static void startLocalPlayer() {
		localGame.reset();
		localThread = new Thread(localGame);
		localThread.start();
	}
	
	public static void startOnlineGame(String host) {
		onlineGame.setIP(host);
		onlineThread= new Thread(onlineGame);
		onlineThread.start();
	}

	public static void render() {
		panel.repaint();
	}

}
