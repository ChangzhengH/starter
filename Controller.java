import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Controller implements ActionListener
{
	Controller()
	{
	}

	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Hey! I said never push that button!");
	}
}
