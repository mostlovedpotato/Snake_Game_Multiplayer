package entities;

import start.Constants;
import start.Point;

import java.awt.*;
import java.util.ArrayList;

public class Snake extends Entity{
	
	// Snake properties
	private Direction direction;
	
	// Declare arrayList with block locations
	public ArrayList<Point> location;
	
	private int originX;
	private int originY;
	private Direction defaultDirection;
	
	public Snake() {
		location = new ArrayList<Point>();
		direction = Direction.right;
		location.add(new Point(0, 0));
		originX = 0;
		originY = 0;
		defaultDirection = Direction.right;
	}
	
	public Snake(int x, int y, Direction d) {
		location = new ArrayList<Point>();
		direction = d;
		location.add(new Point(x, y));
		defaultDirection = d;
		originX = x;
		originY = y;
	}
	
	public void setLocation(ArrayList<Point> loc) {
		this.location = loc;
	}
	
	public boolean checkCollisionWith(Entity e) {
		if(e instanceof Food) {
			if(location.get(0).getX() == e.getX() && location.get(0).getY() == e.getY()) {
				increaseLength();
				return true;
			}
		}
		
		if(e instanceof Snake) {
			
			Snake other = (Snake) e;
			
			for(int i = 0; i < other.location.size(); i++) {
				if(location.get(0).equals(other.location.get(i))) {
					
					if(i == 0) {
						other.reset();
					}
					
					reset();
				}
			}
		}
		
		
		return false;
	}
	
	public void reset() {
		int size = location.size();
		
		for(int i = size - 1; i > 0; i--) {
			location.remove(i);
		}
		
		location.get(0).setPoint(originX, originY);
		setDirection(defaultDirection);
	}
	
	public void increaseLength() {
		location.add(new Point(location.get(location.size()-1)));
	}
	
	public void setDirection(Direction d) {
		this.direction = d;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	@Override
	public void tick() {
		
		for(int i = (location.size() - 1); i > 0; i--) {
			location.get(i).setPoint(location.get(i-1));
		}
		
		switch(direction) {
		case up:
			location.get(0).setY(location.get(0).getY() - Constants.size);
			break;
		case right:
			location.get(0).setX(location.get(0).getX() + Constants.size);
			break;
		case down:
			location.get(0).setY(location.get(0).getY() + Constants.size);
			break;
		case left:
			location.get(0).setX(location.get(0).getX() - Constants.size);
			break;
		}
		
		// Check for self collision.
		for(int i = 1; i < location.size(); i++) {
			if(location.get(0).equals(location.get(i)))
				reset();
		}
		
		// Check for out of bounds.
		if(location.get(0).getX() >= 800|| location.get(0).getX() < 0 || location.get(0).getY() >= 800 || location.get(0).getY() < 0) {
			reset();
		}
		
		
		
	}
	
	@Override
	public void render(Graphics g, Color c, int offX, int offY) {
		
		g.setColor(c);
		
		for(int i = 0; i < location.size(); i++) {
			g.fillRect(location.get(i).getX() + offX, location.get(i).getY() + offY, Constants.size, Constants.size);
		}
		
		g.setColor(Color.WHITE);
		g.drawString(String.valueOf(location.size()), location.get(0).getX() + offX, location.get(0).getY() + offY);
	}
	
}
