package GUI;

import controls.ButtonListener;
import controls.Input;
import game.LocalPlayer;
import game.SinglePlayer;
import start.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SnakePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	// Game Size
	public int w = 800;
	public int h = 800;

	Screen screenState;

	// Panel Components
	Image snakeTitleIMG;
	Image playButtonIMG;
	Image menuButtonIMG;
	Image singleButtonIMG;
	Image localButtonIMG;
	Image onlineButtonIMG;
	Image backButtonIMG;
	Image connectButtonIMG;
	Image exitButtonIMG;

	JLabel authorCredit;

	public JTextField ipTextField;

	public JButton playButton;
	public JButton singleButton;
	public JButton localButton;
	public JButton onlineButton;
	public JButton backButton;
	public JButton menuButton;
	public JButton connectButton;
	public JButton returnButton;
	public JButton exitButton;


	// Controls
	Input input;

	ButtonListener buttonListener;

	public SnakePanel() {
		screenState = Screen.Menu;
		setFocusable(true);
		init();
		addComponents();
	}

	private void addComponents() {

		this.add(playButton);
		this.add(menuButton);
		this.add(singleButton);
		this.add(localButton);
		this.add(onlineButton);
		this.add(backButton);
		this.add(authorCredit);
		this.add(ipTextField);
		this.add(connectButton);
		this.add(returnButton);
		this.add(exitButton);

		playButton.addActionListener(buttonListener);
		menuButton.addActionListener(buttonListener);
		singleButton.addActionListener(buttonListener);
		localButton.addActionListener(buttonListener);
		onlineButton.addActionListener(buttonListener);
		backButton.addActionListener(buttonListener);
		connectButton.addActionListener(buttonListener);
		returnButton.addActionListener(buttonListener);
		ipTextField.addActionListener(buttonListener);
		exitButton.addActionListener(buttonListener);

		addKeyListener(input);
		setFocusable(true);
	}

	private void init() {

		buttonListener = new ButtonListener(this);
		input = new Input();
		authorCredit = new JLabel("");
		ipTextField = new JTextField();
		ipTextField.setFont(new Font("SansSerif", Font.PLAIN, 32));

		try {
			snakeTitleIMG = ImageIO.read(new File("res/snake_title.png"));
			playButtonIMG = ImageIO.read(new File("res/play_button.png"));
			menuButtonIMG = ImageIO.read(new File("res/menu_button.png"));
			singleButtonIMG = ImageIO.read(new File("res/single_button.png"));
			localButtonIMG = ImageIO.read(new File("res/local_button.png"));
			onlineButtonIMG = ImageIO.read(new File("res/online_button.png"));
			backButtonIMG = ImageIO.read(new File("res/back_button.png"));
			connectButtonIMG = ImageIO.read(new File("res/connect_button.png"));
			exitButtonIMG = ImageIO.read(new File("res/exit_button.png"));

			playButton = new JButton(new ImageIcon(playButtonIMG));
			menuButton = new JButton(new ImageIcon(menuButtonIMG));
			singleButton = new JButton(new ImageIcon(singleButtonIMG));
			localButton = new JButton(new ImageIcon(localButtonIMG));
			onlineButton = new JButton(new ImageIcon(onlineButtonIMG));
			backButton = new JButton(new ImageIcon(backButtonIMG));
			returnButton = new JButton(new ImageIcon(backButtonIMG));
			exitButton = new JButton(new ImageIcon(exitButtonIMG));

			connectButton = new JButton(new ImageIcon(connectButtonIMG));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setScreenMode(Screen s) {
		screenState = s;
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		int offsetX = this.getWidth()/2 - w/2;
		int offsetY = this.getHeight()/2 - h/2;

		switch(screenState) {
			case Menu:
				g.drawImage(snakeTitleIMG, this.getWidth()/2 - snakeTitleIMG.getWidth(this)/2, 50, this);

				playButton.setPreferredSize(new Dimension(154, 66));
				playButton.setLocation(this.getWidth()/2 - playButton.getWidth()/2, this.getHeight()/2 - playButton.getHeight()/2);

				exitButton.setPreferredSize(new Dimension(154, 66));
				exitButton.setLocation(this.getWidth()/2 - exitButton.getWidth()/2, this.getHeight()/2 - exitButton.getHeight()/2 + 80);

				authorCredit.setLocation(5, this.getHeight() - authorCredit.getHeight() - 5);

				ipTextField.setVisible(false);
				connectButton.setVisible(false);
				playButton.setVisible(true);
				authorCredit.setVisible(true);
				menuButton.setVisible(false);
				singleButton.setVisible(false);
				localButton.setVisible(false);
				onlineButton.setVisible(false);
				backButton.setVisible(false);
				returnButton.setVisible(false);
				exitButton.setVisible(true);

				break;
			case PlayMenu:
				playButton.setVisible(false);
				menuButton.setVisible(false);
				authorCredit.setVisible(true);
				ipTextField.setVisible(false);
				connectButton.setVisible(false);
				returnButton.setVisible(false);
				exitButton.setVisible(false);

				authorCredit.setLocation(5, this.getHeight() - authorCredit.getHeight() - 5);
				g.drawImage(snakeTitleIMG, this.getWidth()/2 - snakeTitleIMG.getWidth(this)/2, 50, this);

				singleButton.setPreferredSize(new Dimension(154, 66));
				singleButton.setLocation(this.getWidth()/2 - singleButton.getWidth()/2, this.getHeight()/2 - singleButton.getHeight()/2);
				singleButton.setVisible(true);

				localButton.setPreferredSize(new Dimension(154, 66));
				localButton.setLocation(this.getWidth()/2 - localButton.getWidth()/2, this.getHeight()/2 - localButton.getHeight()/2 + 20 + localButton.getHeight());
				localButton.setVisible(true);

				onlineButton.setPreferredSize(new Dimension(154, 66));
				onlineButton.setLocation(this.getWidth()/2 - onlineButton.getWidth()/2, this.getHeight()/2 - onlineButton.getHeight()/2 + 100 + onlineButton.getHeight());
				onlineButton.setVisible(true);

				backButton.setPreferredSize(new Dimension(154, 66));
				backButton.setLocation(this.getWidth()/2 - backButton.getWidth()/2, this.getHeight()/2 - backButton.getHeight()/2 + 180 + backButton.getHeight());
				backButton.setVisible(true);

				break;
			case Game:
				requestFocus();
				menuButton.setLocation(1005,750);
				menuButton.setPreferredSize(new Dimension(100, 40));

				playButton.setVisible(false);
				singleButton.setVisible(false);
				menuButton.setVisible(true);
				localButton.setVisible(false);
				authorCredit.setVisible(false);
				onlineButton.setVisible(false);
				backButton.setVisible(false);
				ipTextField.setVisible(false);
				connectButton.setVisible(false);
				returnButton.setVisible(false);
				exitButton.setVisible(false);

				g.setColor(Color.PINK);
				g.fillRect(offsetX, offsetY, w, h);

				//Render entities
				Main.singleGame.getFood().render(g, Color.GREEN, offsetX, offsetY);
				Main.singleGame.getSnake().render(g, Color.RED, offsetX, offsetY);

				if(SinglePlayer.paused) {

					String msg = "PAUSED";
					Font font = new Font("Ink Free", Font.BOLD, 40);
					FontMetrics metrices = getFontMetrics(font);

					g.setColor(Color.BLACK);
					g.setFont(font);
					g.drawString(msg,this.getWidth()/2 - 80, this.getHeight()/2 );
					//g.drawString("Paused", this.getWidth()/2, this.getHeight()/2);
				}

				break;
			case LocalGame:
				requestFocus();
				menuButton.setLocation(1005,750);
				menuButton.setPreferredSize(new Dimension(100, 40));

				playButton.setVisible(false);
				singleButton.setVisible(false);
				menuButton.setVisible(true);
				localButton.setVisible(false);
				authorCredit.setVisible(false);
				onlineButton.setVisible(false);
				backButton.setVisible(false);
				ipTextField.setVisible(false);
				connectButton.setVisible(false);
				returnButton.setVisible(false);
				exitButton.setVisible(false);

				g.setColor(Color.pink);
				g.fillRect(offsetX, offsetY, w, h);

				//Render entities
				Main.localGame.getFood().render(g, Color.GREEN, offsetX, offsetY);
				Main.localGame.getSnake(0).render(g, Color.RED, offsetX, offsetY);
				Main.localGame.getSnake(1).render(g, Color.BLUE, offsetX, offsetY);

				if(LocalPlayer.paused) {
					String msg = "PAUSED";
					Font font = new Font("Ink Free", Font.BOLD, 40);
					FontMetrics metrices = getFontMetrics(font);

					g.setColor(Color.BLACK);
					g.setFont(font);
					g.drawString(msg,this.getWidth()/2 - 80, this.getHeight()/2 );
					//g.drawString("Paused", this.getWidth()/2, this.getHeight()/2);
				}
				break;
			case OnlineMenu:
				playButton.setVisible(false);
				singleButton.setVisible(false);
				menuButton.setVisible(false);
				localButton.setVisible(false);
				authorCredit.setVisible(true);
				onlineButton.setVisible(false);
				backButton.setVisible(false);
				ipTextField.setVisible(true);
				connectButton.setVisible(true);
				returnButton.setVisible(true);
				exitButton.setVisible(false);

				ipTextField.requestFocus();
				ipTextField.setEditable(true);

				authorCredit.setLocation(5, this.getHeight() - authorCredit.getHeight() - 5);
				g.drawImage(snakeTitleIMG, this.getWidth()/2 - snakeTitleIMG.getWidth(this)/2, 50, this);
				connectButton.setPreferredSize(new Dimension(154, 66));
				ipTextField.setPreferredSize(new Dimension(400, 60));
				returnButton.setPreferredSize(new Dimension(154, 66));
				ipTextField.setLocation(this.getWidth()/2 - ipTextField.getWidth()/2, this.getHeight()/2 - ipTextField.getHeight()/2);
				connectButton.setLocation(this.getWidth()/2 - connectButton.getWidth()/2, this.getHeight()/2 - connectButton.getHeight()/2 + 80);
				returnButton.setLocation(this.getWidth()/2 - backButton.getWidth()/2, this.getHeight()/2 - backButton.getHeight()/2 + 180 + backButton.getHeight());

				break;
			case OnlineGame:
				requestFocus();
				menuButton.setLocation(1005,750);
				menuButton.setPreferredSize(new Dimension(100, 40));

				playButton.setVisible(false);
				singleButton.setVisible(false);
				menuButton.setVisible(true);
				localButton.setVisible(false);
				authorCredit.setVisible(false);
				onlineButton.setVisible(false);
				backButton.setVisible(false);
				ipTextField.setVisible(false);
				connectButton.setVisible(false);
				returnButton.setVisible(false);
				exitButton.setVisible(false);

				g.setColor(Color.pink);
				g.fillRect(offsetX, offsetY, w, h);

				//Render entities
				Main.onlineGame.getFood().render(g, Color.GREEN, offsetX, offsetY);
				Main.onlineGame.getSnake(0).render(g, Color.RED, offsetX, offsetY);
				Main.onlineGame.getSnake(1).render(g, Color.BLUE, offsetX, offsetY);

				break;
		}

	}

}
