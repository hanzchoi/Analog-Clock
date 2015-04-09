package nyc.c4q.personabe1984;
/**
 * Created by Hans on 4/8/15.
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;


/**
 * Created by Hans on 4/8/15.
 */
public class Component extends Applet implements Runnable{

    private static final long serialVersionUID = 1L;

    public static String name = "Not Mickey Mouse clock";

    public static int size = 600;

    public static boolean isRunning = false;

    public static Graphics g;

    public static Image screen;

    public Numbers number;

    public static JFrame frame;

    public static void main(String[] args)
    {
        Component component = new Component();

        frame = new JFrame();
        frame.add(component);
        frame.setSize(size + 6, size + 28);
        frame.setTitle(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        component.start();


    }

    public void start()
    {
        requestFocus();

        number = new Numbers();

        isRunning = true;
        Thread th = new Thread(this);
        th.start();
    }

    public void run(){
        screen = createVolatileImage(size, size);

        while(isRunning){
            tick();
            render(g);
            try{
                Thread.sleep(1);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void tick()
    {

    }

    public double time;
    public int anim;
    public int anim2;
    public int anim3;
    public int anim4;
    public int center = size /2;
    public int radius = (size -40)/2;

    public void render(Graphics g) {

        //Drawing to image

        screen = createImage(size, size);
        g = screen.getGraphics();

        // Drawing the Background

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, size, size);

        // Drawing the frame(outer circle)

        g.setColor(Color.black);
        g.fillOval(5, 5, size - 10, size - 10);
        g.setColor(Color.white);
        //g.setColor(new Color(new Random().next(255),new Random().nextInt(255), new Random().nextInt(255)));
        //g.drawOval(10,10, size -20, size-20);
        g.fillOval(20, 20, size - 40, size - 40);

        number.render(g);

        // Math and Drawing for Lines

        for(int i =0; i < 60; i++ ){
            radius = size - 40;
            anim = center + (int) ((Math.sin(i % 60.0 / 60 * Math.PI * 2) * (radius / 2)));
            anim2 = center - (int) ((Math.cos(i % 60.0 / 60 * Math.PI * 2) * (radius /2)));
            radius = size -60;
            anim3 = center + (int) ((Math.sin(i % 60.0 / 60 * Math.PI * 2) * (radius / 2)));
            anim4 = center - (int) ((Math.cos(i % 60.0 / 60 * Math.PI * 2) * (radius / 2)));
            g.drawLine(anim, anim2, anim3, anim4);

            if(i % 5 == 0){
                g.drawLine(anim + 1, anim2 , anim3 + 1, anim4);
                g.drawLine(anim, anim2 + 1 , anim3, anim4 + 1);
                g.drawLine(anim -1, anim2 ,anim3 -1, anim4);
                g.drawLine(anim, anim2 - 1, anim3, anim4 - 1);
            }
        }

        // Math for hour hand

        radius = size - 140;
        //time = System.currentTimeMillis() %3600000 / 3600000 * Math.PI;
        int t = (int) (System.currentTimeMillis() + 17300000) + 3600000 + 3600000 + 3600000;
        anim = center
                + (int) ((Math.sin(t % 43200000.0
                                           /43200000 * Math.PI *2) * (radius /2))) +7;

        anim2 = center
                - (int) ((Math.cos(t % 43200000.0
                                           /43200000 * Math.PI * 2) * (radius / 2)))+7;

        //Drawing the hour hand

        g.setColor(Color.black);
        g.fillOval(center - 8, center - 8, 16     , 16      );
        g.drawLine(center    , center    , anim   , anim2   );
        g.drawLine(center +1 , center    , anim +1, anim2   );
        g.drawLine(center    , center +1 , anim   , anim2 +1);
        g.drawLine(center -1 , center    , anim -1, anim2   );
        g.drawLine(center    , center -1 , anim   , anim2 -1);
        g.drawLine(center +1 , center +1 , anim   , anim2   );
        g.drawLine(center +1 , center -1 , anim   , anim2   );
        g.drawLine(center -1 , center +1 , anim   , anim2   );
        g.drawLine(center -1 , center -1 , anim   , anim2   );

        // Math for minute hand
        radius = size - 90;
        //time = System.currentTimeMillis() % 60000.0 / 60000 * Math.PI *2;

        anim = center
                + (int) ((Math.sin(System.currentTimeMillis() % 3600000.0
                                           / 3600000 * Math.PI * 2) * (radius /2 )));

        anim2 = center
                - (int) ((Math.cos(System.currentTimeMillis() % 3600000.0
                                           / 3600000 * Math.PI *2) * (radius /2 )));

        //Drawing the minute hand

        g.setColor(Color.black);
        g.drawLine(center    , center    , anim    , anim2    );
        g.drawLine(center + 1, center    , anim + 1, anim2    );
        g.drawLine(center    , center + 1, anim    , anim2 + 1);
        g.drawLine(center -1 , center    , anim - 1, anim2    );
        g.drawLine(center    , center - 1, anim    , anim2 - 1);

        //Math for second hand

        DateFormat dateFormat = new SimpleDateFormat("ss");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());

        radius = size -70;
        //time = System.currentTimeMillis() % 60000.0 / 60000 * Math.PI *2;
        anim = center
                + (int) ((Math.sin(Integer.parseInt(s) % 60.0 / 60 * Math.PI
                                           * 2) * (radius /2)));


        anim2 = center
                - (int) ((Math.cos(Integer.parseInt(s) % 60.0 / 60 * Math.PI
                                           * 2) * (radius /2)));

        //Drawing the second hand

        g.setColor(Color.red);
        g.drawLine(center    , center    , anim    , anim2    );
        g.drawLine(center + 1, center    , anim + 1, anim2    );
        g.drawLine(center    , center + 1, anim    , anim2 + 1);
        g.drawLine(center - 1, center    , anim - 1, anim2    );
        g.drawLine(center    , center -1 , anim    , anim2 -1 );

        //Center circle

        g.fillOval(center-5, center -5, 10, 10);
        g.setColor(Color.black);
        g.fillOval(center -2, center -2, 4, 4);

        //g.setColor(new Color(new Random().next(255),new Random().nextInt(255), new Random().nextInt(255)));
        //g.drawOval(0, 0, getWidth(), getHeight() );

        g = getGraphics();
        g.drawImage(screen, 0, 0, size, size, this);
        g.dispose();

    }

}
