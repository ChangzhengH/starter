import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;

class View extends JPanel
{
	Model model;
	BufferedImage tube_image;
	BufferedImage[] mario_images;
	int scrollPos;

	View(Model m)
	{
		//pass model to member reference
		this.model = m;

		//initialize the images
		tube_image = loadImage("tube.png");
		mario_images = new BufferedImage[5];
		mario_images[0] = loadImage("mario1.png");
		mario_images[1] = loadImage("mario2.png");
		mario_images[2] = loadImage("mario3.png");
		mario_images[3] = loadImage("mario4.png");
		mario_images[4] = loadImage("mario5.png");
	}

	void setModel(Model m)
	{
		this.model = m;
	}

	//Override this method which is already in Jpanel
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw all the tubes
		for(int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(tube_image, t.xPosition - this.scrollPos, t.yPosition, null);
		}
		//draw the mario
		g.drawImage(mario_images[this.model.mario.frame], this.model.mario.mario_x - this.scrollPos, this.model.mario.mario_y, null);

	}

	static BufferedImage loadImage(String imageFileName){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(imageFileName));
		} catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}

		return image;
	}

}
