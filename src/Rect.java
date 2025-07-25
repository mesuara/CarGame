import java.applet.Applet;
import java.awt.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class Rect
{
	int x;
	int y;
	
	int w;
	int h;
	
	int old_x;
	int old_y;
	Image image;
	ColorChoice fillColor;
	String colorName;
	


	boolean held = false;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

		old_x = x;
		old_y = y;	
	}
	

	public Rect(int x, int y, int w, int h, String imgName)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
		this.image = Toolkit.getDefaultToolkit().getImage("../Image/"+imgName);

		old_x = x;
		old_y = y;	
	}
	public Rect(String color, int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

		old_x = x;
		old_y = y;	
		this.colorName = color;
		
	}
	public void grabbed()
	{
		held = true;
	}
	
	public void dropped()
	{
		held = false;
	}
	
	public void moveLT(int dx)
	{
		old_x = x;
		
		x -= dx;		
	}
	
	public void moveRT(int dx)
	{
		old_x = x;
		
		x += dx;		
	}
	
	public void moveUP(int dy)
	{
		old_y = y;
		
		y -= dy;
	}
	public void moveDN(int dy)
	{
		old_y = y;
		
		y += dy;
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		
		y += dy;
	}
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		
		h += dh;
	}
	
	public void chase(Rect r, int dx)
	{
		if(isLeftOf(r))   moveRT(dx); 
		if(isRightOf(r))  moveLT(dx); 
		if(isAbove(r))    moveDN(dx); 
		if(isBelow(r))    moveUP(dx); 
	}
	
	public void evade(Rect r, int dx)
	{
		if(isLeftOf(r))   moveLT(dx); 
		if(isRightOf(r))  moveRT(dx); 
		if(isAbove(r))    moveUP(dx); 
		if(isBelow(r))    moveDN(dx); 
	}
	
	public boolean isLeftOf(Rect r)
	{
		return x + w < r.x;
	}
	
	public boolean isRightOf(Rect r)
	{
		return r.x + r.w < x;
	}
	
	public boolean isAbove(Rect r)
	{
		return y + h < r.y;
	}
	
	public boolean isBelow(Rect r)
	{
		return r.y + r.h < y;
	}
	
	
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x    )  &&
			   (mx <= x + w)  &&
			   (my >= y    )  &&
			   (my <= y + h);
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	public void pushedOutOf(Rect r)
	{
		if(cameFromAbove(r)) 	pushbackUpFrom(r);
		if(cameFromBelow(r))    pushbackDownFrom(r);
		if(cameFromLeftOf(r))   pushbackLeftFrom(r);		
		if(cameFromRightOf(r))	pushbackRightFrom(r);
	}
	
	public boolean cameFromLeftOf(Rect r)
	{
		return old_x + w < r.x;
	}
	
	public boolean cameFromRightOf(Rect r)
	{
		return r.x + r.w < old_x;
	}
	
	public boolean cameFromAbove(Rect r)
	{
		return old_y + h < r.y;
	}
	
	public boolean cameFromBelow(Rect r)
	{
		return r.y + r.h < old_y;
	}
	
	public void pushbackLeftFrom(Rect r)
	{
		x = r.x - w - 1;
	}
	
	public void pushbackRightFrom(Rect r)
	{
		x = r.x + r.w + 1;
	}
	
	public void pushbackUpFrom(Rect r)
	{
		y = r.y - h - 1;
	}
	
	public void pushbackDownFrom(Rect r)
	{
		y = r.y + r.h + 1;
	}
	
	
	public void draw(Graphics pen)
	{

		if(image != null) {
			pen.drawImage(image, x - Camera.x, y - Camera.y, w, h, null);
		}if(colorName != null) {
			pen.setColor(fillColor.getColorFromString(colorName)); // Drawing the background as gray
            pen.fillRect(x,y,w,h);
            pen.drawRect(x, y, w, h);
			
		}
			
			else {
				
			pen.drawRect(x, y, w, h);
		}
	}
	
	
	public String toString()
	{
		return "new Rect(" + x + ", " + y + ", " + w + ", " + h + "),";
	}

}