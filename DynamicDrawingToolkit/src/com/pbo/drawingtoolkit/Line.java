package com.pbo.drawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends DrawingObject
{
	public Line()
	{
		super();
	}
	public Line( int x1, int y1, int x2, int y2, Color color )
	{
		super(x1, y1, x2, y2, color);
		this.setType(0);
	}

	@Override
	public void draw( Graphics g )
	{
		g.setColor( getColor() );
		g.drawLine( getX1(), getY1(), getX2(), getY2() );
	}
	
	public boolean intersect (int x, int y){
		if(isPointIntersectingLine(x, y, this.getX1(), this.getY1(), this.getX2(), this.getY2())) 
			return true;
		else 
			return false;
	}
	
	public static boolean isPointIntersectingLine(int x, int y, int x1, int y1, int x2, int y2) {
	    // Calculate the cross product of vectors (x, y1)-(x1, y1) and (x2, y2)-(x1, y1)
	    int crossProduct = (y - y1) * (x2 - x1) - (x - x1) * (y2 - y1);

	    // Check if the point is close to the line (considering a threshold for accuracy)
	    double distance = Math.abs(crossProduct) / Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

	    // You can adjust the threshold value based on your application's requirements
	    double threshold = 4.0;

	    return distance < threshold;
	}
	
	public void translation(int X, int Y) {
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
