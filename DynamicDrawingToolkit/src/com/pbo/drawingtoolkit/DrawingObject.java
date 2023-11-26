package com.pbo.drawingtoolkit;

import java.awt.Color;
import java.awt.Graphics;

abstract class DrawingObject
{
	private int x1,y1,x2,y2; //coordinates of shape
	private Color color; // color of shape
	private int type; //type of shape
	public boolean reverse = false;

	public DrawingObject()
	{
		x1=0;
		y1=0;
		x2=0;
		y2=0;
		color=Color.BLACK;
	}

	public DrawingObject(int x1, int y1, int x2, int y2, Color color)
	{
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.color=color;
	}
	
	public boolean intersect (int x, int y){
		return false;
	}
	
	public void translation (int x, int y) {
		
	}
	
	public void setType(int type)
	{
		this.type=type;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setX1(int x1)
	{
		this.x1=x1;
	}
	public void setY1(int y1)
	{
		this.y1=y1;
	}
	public void setX2(int x2)
	{
		this.x2=x2;
		
	}
	public void setY2(int y2)
	{
		this.y2=y2;
	}
	public void setColor(Color color)
	{
		this.color=color;
	}
	public int getX1()
	{
		return x1;
	}
	public int getY1()
	{
		return y1;
	}
	public int getX2()
	{
		return x2;
	}
	public int getY2()
	{
		return y2;
	}
	public Color getColor()
	{
		return color;
	}
	public void swapY(){
		int temp;
		temp = y1;
		y1 = y2;
		y2 = temp;
	}
	public void swapX(){
		int temp;
		temp = x1;
		x1 = x2;
		x2 = temp;
	}
	
	abstract public void draw( Graphics g );
}