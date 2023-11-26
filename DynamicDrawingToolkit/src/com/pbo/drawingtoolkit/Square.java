package com.pbo.drawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

public class Square extends DrawingObjectBounds
{
	private int SquareWidth, SquareHeight;
	public Square()
	{
		super();
	}
	public Square( int x1, int y1, int x2, int y2, Color color, boolean fill )
	{
		super(x1, y1, x2, y2, color, fill);
		this.setType(2);
	}

	@Override
	public void draw( Graphics g )
	{
		SquareWidth = getWidth();
		SquareHeight = getHeight();

		g.drawRect( getUpperLeftX(), getUpperLeftY(), SquareWidth,
				SquareHeight);
	}
	
	public static boolean InsideSquare(int x, int y, int x1, int y1, int x2, int y2) {
	    if (x2>x1 && y2>y1)
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		else if(x2>x1 && y2<y1) {
			int temp;
			temp = y1;
			y1 = y2;
			y2 = temp;
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		} else if(x2<x1 && y2>y1) {
			int temp;
			temp = x1;
			x1 = x2;
			x2 = temp;
			return x >= x1 && x <= x2 && y >= y1 && y <= y2;
		} else {
		int temp;
		temp = x1;
		x1 = x2;
		x2 = temp;
		temp = y1;
		y1 = y2;
		y2 = temp;
		return x >= x1 && x <= x2 && y >= y1 && y <= y2;	
		}
	}
	
	public boolean intersect (int x, int y){
		if(InsideSquare(x, y, this.getX1(), this.getY1(), this.getX2(), this.getY2()))
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