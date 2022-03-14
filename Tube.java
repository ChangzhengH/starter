

class Tube{

    int xPosition;
    int yPosition;

    //when creating a tube, make it slightly different from what it gets from mouse click
    Tube(int x, int y){
        this.xPosition = x-35;
        this.yPosition = y-150;// the real solution would be attaching mouse listener to the JPanel which is View, but not JFrame which is the Game. But I am happy with what I have for now
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

        if( x > this.xPosition + 12 && x < this.xPosition + 64
            && y > this.yPosition + 45 && y < this.yPosition + 445 )
            return true;
        else
            return false;
    }

}
