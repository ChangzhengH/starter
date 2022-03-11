import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	public Game()
	{
		Controller controller = new Controller();
		View view = new View(controller);
		this.setTitle("The most awesomest program ever!");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		Game g = new Game();
	}
}
