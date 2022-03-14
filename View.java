import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	BufferedImage tube_image;
	int scrollPos;

	View(Model m)
	{
		//pass model to member reference
		this.model = m;

		//initialize tube image
		try{
			tube_image = ImageIO.read(new File("tube.png"));
		} catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
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

		for(int i = 0; i < model.tubes.size(); i++)
		{
			Tube t = model.tubes.get(i);
			g.drawImage(tube_image, t.xPosition - this.scrollPos, t.yPosition, null);
		}

	}

}
