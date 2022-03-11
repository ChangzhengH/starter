import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;

class View extends JPanel
{
	JButton b1;

	View(Controller c)
	{
		b1 = new JButton("Never push me");
		b1.addActionListener(c);
		this.add(b1);
	}
}
