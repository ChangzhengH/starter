import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//Controller is nothing but just a controller, it has only logic related to control something in a former that implements all kinds of listeners
class Controller implements  MouseListener, KeyListener
{
	Model model;
	View view;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;

	Controller(Model m, View v)
	{
		this.model = m;
		this.view = v;
	}

	void setModel(Model m)
	{
		this.model = m;
	}


	//control mouse clicks
	public void mousePressed(MouseEvent e)
	{
		model.createOrRemoveTube(e.getX()+this.view.scrollPos, e.getY());
		System.out.println("you just clicked: x:"+e.getX()+",y:"+e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }



	//control key presses
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE:keySpace = true;break;
		}

		if(e.getKeyChar() == 's'){
			this.model.marshal().save("map.json");
		}

		if(e.getKeyChar() == 'l'){
			Json loadedJson = Json.load("map.json");
			this.model.unmarshal(loadedJson);
		}

	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE:	keySpace = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight) {
			this.view.scrollPos += 5;
			this.model.mario.moveForward();
			this.model.mario.rollForwardFrame();
		}
		if(keyLeft) {
			this.view.scrollPos -= 5;
			this.model.mario.moveBackward();
			this.model.mario.rollBackwardFrame();
		};
		if(keyDown) ;
		if(keyUp) ;

		if(keySpace){
			this.model.mario.jump();
		}
	}


}
