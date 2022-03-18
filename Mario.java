import java.awt.image.BufferedImage;

public class Mario{

    int mario_x;
    int mario_y;
    int prev_x;
    int prev_y;
    final int width = 55;
    final int height = 96;

    static BufferedImage[] mario_images;

    int frame;//store which phase of mario it is, from 0 - 4
    double vert_vel;//the falling of mario is going to be more and more fast
    int frameCountOffGround;

    Mario(int x, int y){

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
        savePrevPos();
        this.frameCountOffGround++;//if it stands on the ground, this count will be set 0,so it only increments when it's away from ground
        if(this.mario_y > 500 && this.vert_vel > 0.0)
        {
            this.vert_vel = 0.0;
            this.mario_y = 500; // snap back to the ground
            this.frameCountOffGround = 0;
        }

        vert_vel += 1.2;
        this.mario_y += vert_vel;
    }

    void jump(){
        if(this.frameCountOffGround < 5){//the jump has a limit
            this.vert_vel -= 5.0;
        }
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
}
