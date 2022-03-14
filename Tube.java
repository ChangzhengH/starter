

class Tube{

    int xPosition;
    int yPosition;


    Tube(int x, int y){
        this.xPosition = x;
        this.yPosition = y;
    }


    boolean isClicked(int x, int y){

        if( x > this.xPosition + 12 && x < this.xPosition + 64
            && y > this.yPosition + 45 && y < this.yPosition + 445 )
            return true;
        else
            return false;
    }

}
