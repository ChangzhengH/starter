public class Mario{

    int mario_x;
    int mario_y;

    int frame;//store which phase of mario it is, from 0 - 4

    Mario(int x, int y){

        this.mario_x = x;
        this.mario_y = y;
        this.frame = 0;
    }

    void rollFrame(){// when calling this method, it will roll through 0 - 4 and assign the number to frame
        if(frame == 4)
        {
            frame = 0;
        }
        else
        {
            frame++;
        }
    }




}
