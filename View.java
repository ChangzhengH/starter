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
	int scrollPos;

	View(Model m)
	{
		//pass model to member reference
		this.model = m;
	}


	//Override this method which is already in Jpanel
	public void paintComponent(Graphics g)
	{
		//should i put this method here?
		adjustScrollPos();

		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//draw all the tubes
		for(int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(t.tube_image, t.xPosition - this.scrollPos, t.yPosition, null);
		}
		//draw the mario
		g.drawImage(this.model.mario.mario_images[this.model.mario.frame], this.model.mario.mario_x - this.scrollPos, this.model.mario.mario_y, null);
		//draw the ground
		g.setColor(Color.gray);
		g.drawLine(0, 596, 2000, 596);
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

	void adjustScrollPos(){
		if(this.model.mario.mario_x < scrollPos + 100)
			scrollPos -= 10;
		if(this.model.mario.mario_x > scrollPos + 1300)
			scrollPos += 10;
	}

}
