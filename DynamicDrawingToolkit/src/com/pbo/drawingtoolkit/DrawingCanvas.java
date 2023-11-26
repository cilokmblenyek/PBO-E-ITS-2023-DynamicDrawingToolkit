package com.pbo.drawingtoolkit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingCanvas extends JPanel
{
	private ArrayList<DrawingObject> myShapes;
	private int currentShapeType;
	private DrawingObject currentShapeObject;
	private Color currentShapeColor;
	private static final int SELECTION = 3, SQUARE = 2, CIRCLE = 1, LINE = 0;

	JLabel statusLabel;

	public DrawingCanvas(JLabel statusLabel){
		myShapes = new ArrayList<DrawingObject>();
		currentShapeType=0;
		currentShapeObject=null;
		currentShapeColor=Color.BLACK;

		this.statusLabel = statusLabel;
		setLayout(null);
		setBackground( Color.WHITE );
		add( statusLabel, BorderLayout.SOUTH );

		MouseHandler handler = new MouseHandler();
		addMouseListener( handler );
		addMouseMotionListener( handler );
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );

		for ( int counter= myShapes.size()-1; counter>=0; counter-- )
			myShapes.get(counter).draw(g);

		if (currentShapeObject!=null)
			currentShapeObject.draw(g);
	}

	public void setCurrentShapeType(int type)
	{
		currentShapeType=type;
	}
	public void clearDrawing()
	{
		myShapes.clear();
		repaint();
	}

	private class MouseHandler extends MouseAdapter
	{
		public void mousePressed( MouseEvent event )
		{
			switch (currentShapeType)
			{
			case LINE:
				currentShapeObject=null;
				currentShapeColor=Color.BLACK;
				currentShapeObject= new Line( event.getX(), event.getY(),
						event.getX(), event.getY(),currentShapeColor);
				break;
			case CIRCLE:
				currentShapeObject=null;
				currentShapeColor=Color.BLACK;
				currentShapeObject= new Circle( event.getX(), event.getY(),
						event.getX(), event.getY(), currentShapeColor, false);
				break;
			case SQUARE:
				currentShapeObject=null;
				currentShapeColor=Color.BLACK;
				currentShapeObject= new Square( event.getX(), event.getY(),
						event.getX(), event.getY(), currentShapeColor, true);
				break;
			case SELECTION:
				//select object by iteration
				boolean exist = false;
				for ( int counter= myShapes.size()-1; counter>=0; counter-- ){
					exist = myShapes.get(counter).intersect(event.getX(), event.getY());
					if (exist) {
						if(currentShapeObject!= null) currentShapeObject.setColor(Color.black);
						currentShapeObject= myShapes.get(counter);
						currentShapeObject.setColor(Color.blue);
						statusLabel.setText(String.format("Selected Object: %s",currentShapeObject));
						repaint();
						break;
					} 
				} if(!exist) {
				if(currentShapeObject!= null) currentShapeObject.setColor(Color.black);
				currentShapeObject= null;
				statusLabel.setText(String.format("Selected Object: none"));	
				repaint();
				break;	
				}
			}
		}

		public void mouseReleased( MouseEvent event )
		{
			switch (currentShapeType) {
			case SELECTION:
				if(currentShapeObject== null) currentShapeObject.setColor(Color.black);
				repaint();
				break;
			
			default:
				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
	
				myShapes.add(currentShapeObject);
				System.out.println(myShapes.toArray());
	
				currentShapeObject=null;
				repaint();
				break;
			}
		}

		public void mouseMoved( MouseEvent event )
		{
			statusLabel.setText(String.format("Mouse Coordinates X: %d Y:%d",event.getX(),event.getY()));
		}

		public void mouseDragged( MouseEvent event )
		{
			switch (currentShapeType)
			{
			case SELECTION:
				//translate object
				currentShapeObject.translation(event.getX(), event.getY());
				repaint();
				break;
			default:
				currentShapeObject.setX2(event.getX());
				currentShapeObject.setY2(event.getY());
				statusLabel.setText(String.format("Mouse Coordinates X: %d Y:%d",event.getX(),event.getY()));
				repaint();
				break;
			}
		}
	}
}
