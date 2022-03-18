import java.awt.image.BufferedImage;

class Tube{

    int xPosition;
    int yPosition;
    static BufferedImage tube_image;

    final int width = 55;
    final int height = 400;

    //when creating a tube, make it slightly different from what it gets from mouse click
    Tube(int x, int y){
        this.xPosition = x;
        this.yPosition = y;

        if(tube_image==null)
            tube_image = View.loadImage("tube.png");
    }

    //Unmarshaling constructor
    Tube(Json ob){

        this.xPosition = (int)ob.getLong("xPosition");
        this.yPosition = (int)ob.getLong("yPosition");

    }
    //marchal Tube object to json
    Json marshal(){

        Json ob = new Json().newObject();
        ob.add("xPosition", this.xPosition);
        ob.add("yPosition", this.yPosition);

        return ob;
    }

    //detecting if this tube is being clicked, this logic is dependent on the "real" x and y position so the model is independent
    boolean isClicked(int x, int y){

        if( x > this.xPosition && x < this.xPosition + this.width
            && y > this.yPosition  && y < this.yPosition + this.height )
            return true;
        else
            return false;
    }

}
