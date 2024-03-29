import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	public int motionX,motionY;
	public Random random;
	private Pong pong;
	public int amountOfHits=0;
	public int x,y,width=25,height=25;
	
	public Ball(Pong pong){
		this.pong = pong;
		this.random = new Random();
		spawn();
	}
	public void update(Paddle paddle1,Paddle paddle2){
		int speed = 5;
		this.x += motionX * speed;
		this.y += motionY * speed;
		if(this.y + height - motionY > pong.height|| this.y + motionY < 0){
			if(this.motionY < 0){
				this.y = 0;
				this.motionY = random.nextInt(4);
				if(motionY == 0){
					motionY = 1;
				}
			}else{
				this.y = pong.height - height;
				this.motionY = -random.nextInt(4);
				if(motionY == 0){
					motionY = -1;
				}
			}
		}
		if (checkCollision(paddle1)==1){
			this.motionX = 1;
			this.motionY = -2 + random.nextInt(4);
			if(motionY == 0){
				motionY = 1;
			}
			amountOfHits++;
		}else if (checkCollision(paddle2)==1){
			this.motionX = -1;
			this.motionY = -2 + random.nextInt(4);
			if(motionY == 0){
				motionY = 1;
			}
		}
		if (checkCollision(paddle1)==2){
			paddle2.score++;
			spawn();
		}else if (checkCollision(paddle2)==2){
			paddle1.score++;
			spawn();
		}
	}
	public void spawn(){
		this.amountOfHits = 0;
		this.x = pong.width / 2 - this.width / 2;
		this.y = pong.height / 2 - this.height / 2;
		this.motionY = -2 + random.nextInt(4);
		if(motionY == 0){
			motionY = 1;
		}
		if(random.nextBoolean()){
			motionX = 1;
		}else{
			motionX = -1;
		}
	}
	public int checkCollision(Paddle paddle){
		if(this.x < paddle.x + paddle.width && this.x + width > paddle.x && this.y < paddle.y + paddle.height && this.y + height > paddle.y){
			return 1;
		}else if((paddle.x > x && paddle.paddleNum == 1) ||(paddle.x < x - width && paddle.paddleNum == 2)){
			return 2;
		}
		return 0;//nothing
	}
	public void render(Graphics g){
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);
	}
}
