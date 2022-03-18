import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;

	public Game()
	{
		this.model = new Model();
		this.view = new View(model);
		this.controller = new Controller(this.model,this.view);

		this.setTitle("Map Editor");
		this.setSize(1500, 1000);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		this.view.addMouseListener(controller);//attach mouse listener to JPanel
		this.addKeyListener(controller);

	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}


	public void run()
	{
		while(true)
		{
			this.controller.update();
			this.model.update();
			this.view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}

		}
	}
}
