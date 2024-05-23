import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*
;
import java.util.Random;

public class CarGame extends CarGameBase
{


	Rect player = new Rect(195, 400, 50, 100,"car-player.png");
	Rect gameOverRect = new Rect("BLACK",100, 200, 300, 200);
	Rect scoreRect = new Rect("YELLOW",385, 10, 110, 40);
	EnemyCar[] enemyCars = new EnemyCar[8];
	int score =0;
	
	Rect road = new Rect(50, 0, 400,600,"road.png");
	Rect grassLeft = new Rect("GREEN", 0,0,50,600);
	Rect grassRight = new Rect("GREEN", 450,0,50,600);
	
	BackgroundMusic bgMusic = new BackgroundMusic();
	BackgroundMusic crashSound = new BackgroundMusic();
	
	
	public void initialize()
	{
		
		setSize(500,600);
		makeCars();
		bgMusic.playMusic("../Audio/loopcity.wav");
		score=0;
		
	}
	
			
	
	public void inGameLoop()
	{
		
		if(pressing[LT])  player.moveLT(8);
		if(pressing[RT])  player.moveRT(8);
		if(player.overlaps(grassLeft)) player.pushedOutOf(grassLeft);
		if(player.overlaps(grassRight)) player.pushedOutOf(grassRight);
		if(pressing[_R]) System.out.println("DID I PRESS R");

		
		for (int i =0; i < enemyCars.length; i++) {
		
			if (enemyCars[i] != null) {
				enemyCars[i].move();
				if (enemyCars[i].isOffScreen(getHeight())) {
                    enemyCars[i] = null;
                    score+=10;
                    System.out.println("am I inside null for car");// Remove car if it goes off-screen
                }
				
			}
			
			if (enemyCars[i] == null && Math.random() < 0.1) {


				if(i%2 == 0) {
					enemyCars[i] = new EnemyCar(50 + i * 100, -110 * (i + 1),45,90,"car"+(i)+".png");
					}else {
						enemyCars[i] = new EnemyCar(50 + i * 50, -210 * (i + 1),45,90,"car"+(i)+".png");
					}

                break;  // Add only one car per frame
            }
			if(enemyCars[i] != null && enemyCars[i].overlaps(grassLeft)) {
				enemyCars[i].pushedOutOf(grassLeft);
			}else if(enemyCars[i] != null && enemyCars[i].overlaps(grassRight)){
				enemyCars[i].pushedOutOf(grassRight);
			}
			if(enemyCars[i] != null && enemyCars[i].overlaps(player)) {
				bgMusic.stopMusic();
				crashSound.playSoundEffect("../Audio/crash-sound.wav");
				gameOver=true;
			}
		}

	}
	

	
	public void paint(Graphics pen)
	{   
		road.draw(pen);
		grassLeft.draw(pen);
		grassRight.draw(pen);
//		car.draw(pen);
		player.draw(pen);
        for (EnemyCar car : enemyCars) {
            if (car != null) {
                car.draw(pen);
                scoreRect.draw(pen);
               
                Font scoreFont = new Font("Serif", Font.BOLD,24);
            	pen.setFont(scoreFont);
                pen.setColor(Color.RED);
                pen.drawString("Score " + score, 400, 38);
            }
        }
		
        if(gameOver) {
        	gameOverRect.draw(pen);
        	Font gameoverFont = new Font("Serif", Font.BOLD,34);
        	pen.setFont(gameoverFont);
        	pen.setColor(Color.RED);
        	pen.drawString("Game Over", 170, 275);
        	pen.drawString("Score " + score, 200, 325);    	
            
        	 
        }

	}
	


	public void makeCars() {
		int x = 50;
		int y = 0;
		for (int i =0; i < enemyCars.length; i++) {
			if(i%2 == 0) {

				enemyCars[i] = new EnemyCar(60 + i * 100, -110 * (i + 1),45,90,"car"+(i)+".png");
			
		

			}else {

				enemyCars[i] = new EnemyCar(50 + i * 100, -210 * (i + 1),45,90,"car"+(i)+".png");
			}

		}
	}


	

	

	
}