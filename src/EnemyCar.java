import java.applet.Applet;
import java.awt.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;


public class EnemyCar extends Rect{

	public EnemyCar(int x, int y, int w, int h, String img) {
		
		super(x, y, w, h, img);
		
	}
	
	public void move(int num) {
        this.y += num; // Move speed can be adjusted here
    }

    public void draw(Graphics pen) {
    	if(image != null) {
			pen.drawImage(image, x - Camera.x, y - Camera.y, w, h, null);
			}
			else {
				pen.drawRect(x, y, w, h);
			}
    }

    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight;
    }


	
}
