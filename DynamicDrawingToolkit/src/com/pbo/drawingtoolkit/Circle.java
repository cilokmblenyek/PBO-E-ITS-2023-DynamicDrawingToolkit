package com.pbo.drawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends DrawingObjectBounds
{
	private int circleWidth, circleHeight;
	private int xC, yC;
	public Circle()
	{
		super();
	}

	public Circle( int x1, int y1, int x2, int y2, Color color, boolean
			fill)
	{
		super(x1, y1, x2, y2, color,fill);
		this.setType(1);
		xC = x2-((x2-x1)/2);
		yC = y2-((y2-y1)/2);
		
	}
	
	public int getxC() {
		return xC;
	}
	
	public int getyC() {
		return yC;
	}

	@Override
	public void draw( Graphics g )
	{
		circleWidth = getWidth();
		circleHeight = getHeight();
		circleWidth = circleHeight;

		g.drawOval( getUpperLeftX(), getUpperLeftY(), circleWidth,
				circleHeight);
	}
	
	public static boolean isPointInsideCircle(int x, int y, int xC, int yC, int radius) {
	    // Calculate the squared distance from the point to the center of the circle
	    int distanceSquared = (x - xC) * (x - xC) + (y - yC) * (y - yC);

	    // Check if the squared distance is less than or equal to the square of the radius
	    return distanceSquared <= radius * radius;
	}
	
	public boolean intersect (int x, int y){
		if(getX1()>getX2()) swapX();
		if(getY1()>getY2()) swapY();
		if(x > getX1() && x < getX2() && y > getY1() && y <getY2()) 
			return true;
		else
			return false;
		
	}
	
	public void translation (int X, int Y) {
	    // Translate the line by adding the translation values to the coordinates
	    int deltaX, deltaY;
	    int xC = this.getX2()-((this.getX2()-this.getX1())/2);
	    int yC = this.getY2()-((this.getY2()-this.getY1())/2);
	    deltaX = X-xC;
	    deltaY = Y-yC;
		setX1(getX1() + deltaX);
	    setY1(getY1() + deltaY);
	    setX2(getX2() + deltaX);
	    setY2(getY2() + deltaY);
	}
}
