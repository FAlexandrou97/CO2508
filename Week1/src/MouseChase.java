import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class MouseChase extends JFrame implements MouseMotionListener, Runnable {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    private int posX;
    private int posY;
    private int xVel = 2;
    private int yVel = 2;
    private int ovalWidth = 20;
    private int ovalHeight = 20;
    private boolean bounceEffectX = false;
    private boolean bounceEffectY = false;

    Thread move = new Thread(this);

    // Had to start the thread ("this.move.start();") in the class constructor for this function to be called.
    public void run() {
        try {
            while (true) {
                posX = posX + xVel;
                posY = posY + yVel;
                repaint();
                // Sleep 10ms instead of 100 to increase movement smoothness
                move.sleep(10);
                if (posX >= getWidth() - 20) {
                    xVel = -2;
                    bounceEffectX = true;
                } else if (posX <= 5) {
                    xVel = 2;
                    bounceEffectX = true;
                }
                if (posY >= getHeight() - 20) {
                    yVel = -2;
                    bounceEffectY = true;
                } else if (posY <= 20) {
                    yVel = 2;
                    bounceEffectY = true;
                }
                bounceEffect();
            }
        } catch (Exception ex) {
            System.out.println("excepted!");
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        System.out.println("Launching GUI ...");
        final MouseChase mouseChase = new MouseChase();
        mouseChase.setSize(new Dimension(WIDTH, HEIGHT));
        mouseChase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mouseChase.setVisible(true);
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height); // draws the background as black
        g.setColor(Color.WHITE);
        g.fillOval(posX, posY, ovalWidth, ovalHeight); // draws a white circle in the middle
    }

    // Constructor
    public MouseChase() {
        posX = WIDTH / 2;
        posY = HEIGHT / 2;
        addMouseMotionListener(this);
        this.move.start();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {}

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        this.posX = mouseEvent.getX();
        this.posY = mouseEvent.getY();
        repaint();
    }

    // Decrease oval's width and height by half whenever it hits the window borders.
    public void bounceEffect() {
        if (bounceEffectX && ovalWidth > 10) {
            ovalWidth = ovalWidth - 1;
        }
        if (ovalWidth <= 10)
            bounceEffectX = false;

        // Recover original size
        if (!bounceEffectX && ovalWidth < 20) {
            ovalWidth = ovalWidth + 1;
        }

        if (bounceEffectY && ovalHeight > 10) {
            ovalHeight = ovalHeight - 1;
        }
        if (ovalHeight <= 10)
            bounceEffectY = false;

        // Recover original size
        if (!bounceEffectY && ovalHeight < 20) {
            ovalHeight = ovalHeight + 1;
        }
    }
}
