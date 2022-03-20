import java.awt.image.BufferedImage;
import java.util.List;

public class Mario{

    int mario_x;
    int mario_y;
    int prev_x;
    int prev_y;
    final int width = 55;
    final int height = 96;
    int standGround;//所站之处的Y值

    static BufferedImage[] mario_images;

    int frame;//store which phase of mario it is, from 0 - 4
    double vert_vel;//the falling of mario is going to be more and more fast
    int frameCountOffGround;

    Mario(int x, int y){

        this.standGround = 500;
        this.mario_x = x;
        this.mario_y = y;
        this.frame = 0;
        if(mario_images==null){
            //initialize the images
            mario_images = new BufferedImage[5];
            mario_images[0] = View.loadImage("mario1.png");
            mario_images[1] = View.loadImage("mario2.png");
            mario_images[2] = View.loadImage("mario3.png");
            mario_images[3] = View.loadImage("mario4.png");
            mario_images[4] = View.loadImage("mario5.png");
        }

    }

    void rollForwardFrame(){// when calling this method, it will roll through 0 - 4 and assign the number to frame
        if(frame == 4)
        {
            frame = 0;
        }
        else
        {
            frame++;
        }
    }

    void rollBackwardFrame(){//the same way as rollForwardFrame() but it's backward
        if(frame == 0)
        {
            frame = 4;
        }
        else
        {
            frame--;
        }
    }

    void update()//keep adding vert_vel, and y position adds the value of vert_vel, so it will falling more and more quickly
    {
        fall();
        stand(this.standGround);
    }

    void jump(){
        if(this.frameCountOffGround < 5){//the jump has a limit
            this.vert_vel -= 5.0;
        }
    }

    void stand(int ground_y){//test whether it stands on something
        if(this.mario_y > ground_y )
        {
            this.vert_vel = 0.0;
            this.mario_y = ground_y; // snap back to the ground
            this.frameCountOffGround = 0;
        }else{
            this.frameCountOffGround++;//if it stands on the ground, this count will be set 0,so it only increments when it's away from ground
        }
    }

    void fall(){
        vert_vel += 1.2;
        this.mario_y += vert_vel;
    }

    void savePrevPos(){
        this.prev_x = this.mario_x;
        this.prev_y = this.mario_y;
    }

    void goBackPrevPos(){
        this.mario_x = this.prev_x;
        this.mario_y = this.prev_y;
    }

    void moveForward(){
        this.mario_x += 20;
    }

    void moveBackward(){
        this.mario_x -= 20;
    }


    //detect whether or not mario overlapped a tube
    boolean isOverlappedByTube(List<Tube> tubes){

        if( tubes.isEmpty() )//tubes为空自然是没有重合
            return false;
        for(Tube t : tubes){
            if(this.mario_x+ this.width < t.xPosition)
                return false;
            if(this.mario_x > t.xPosition+t.width)
                return false;
            if(this.mario_y+ this.height < t.yPosition) // assumes bigger is downward
            {
                return false;
            }else {
                this.standGround = t.yPosition;
            }
            if(this.mario_y > t.yPosition+t.height) // assumes bigger is downward
                return false;
        }


        return true;
    }
}
