package nyc.c4q.personabe1984;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.GrayFilter;
import javax.swing.JPanel;

/**
 * Created by Hans on 4/8/15.
 */
public class Numbers extends JPanel {
    private static final long serialVersionUID = 1L;

    public int size = Component.size;
    public int center = size /2;

    public void setFont(Font font){
        super.setFont(font);
        repaint();
    }

    public String getDay(){

        DateFormat dateFormat = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        int day = Integer.parseInt(s);
        String d = day + "";

            switch(day)
            {

                case 1:
                case 21:
                case 31:
                    d += "st";
                    break;
                case 2:
                case 22:
                    d += "nd";
                    break;
                case 3:
                case 23:
                    d += "rd";
                    break;
                default:
                    d += "th";
            }
            return d;
    }

    public String getHour(){
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Calendar cal = Calendar.getInstance();
        String s =dateFormat.format(cal.getTime());
        int hour = Integer.parseInt(s) -1;

        String n = hour + "";
        if(hour < 10){
            n = "0" + hour + "";
        }else{
            n = hour + "";
        }
        return n;
    }

    public String getMonth(){
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Calendar cal = Calendar.getInstance();
        String s =dateFormat.format(cal.getTime());
        int month = Integer.parseInt(s);

        String m = "";
        switch (month){
            case 1:
                m = "January";
                break;
            case 2:
                m = "February";
                break;
            case 3:
                m = "March";
                break;
            case 4:
                m = "April";
                break;
            case 5:
                m = "May";
                break;
            case 6:
                m = "June";
                break;
            case 7:
                m = "July";
                break;
            case 8:
                m = "August";
                break;
            case 9:
                m = "September";
                break;
            case 10:
                m = "October";
                break;
            case 11:
                m = "November";
                break;
            case 12:
                m = "December";
                break;
        }
        return m;
    }

    public void render(Graphics g){
        g.setColor(Color.black);

        DateFormat dateFormat = new SimpleDateFormat(":mm:ss");
        Calendar cal = Calendar.getInstance();
        String s = dateFormat.format(cal.getTime());
        int n = center - ((s.length() * 13) / 2);
        //265
        g.setFont(new Font("Arial", 1, 20));
        s = (Integer.parseInt(getHour(), 10) % 12 + 1) + "" + dateFormat.format(cal.getTime());
        n = center - (s.length() * 10/2);
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(250, 348, 100, 30, 6, 6);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(252, 350, 96, 26, 6, 6);
        g.setColor(Color.BLACK);
        g.drawString("TIME", 275, 345);
        g.drawString("DATE", 275, 225);
        g.drawString("AM", 255, 150);
        g.drawString("PM", 315, 150 );
        g.drawString(s,n, 370);
        int p = Integer.parseInt(getHour(), 10);
        if(p < 11 || p == 24){
            g.fillOval(265, 160, 10, 10);
            g.drawOval(325, 160, 10, 10);
        } else{
            g.drawOval(265, 160, 10, 10);
            g.fillOval(325, 160, 10, 10);
        }

        dateFormat = new SimpleDateFormat("yyyy");
        cal = Calendar.getInstance();
        s = getMonth() + " " + getDay() + "," + dateFormat.format(cal.getTime());
        n = center - (int) ((s.length() * 10.25) /2);

        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(200, 228, 200, 30 , 6, 6);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(202, 230, 196,26, 6, 6);
        g.setColor(Color.BLACK);
        g.drawString(s,n, 250);
        s = Component.name;
        n = center - (int)((s.length() * 10) /2);
        g.drawString(s, n, 450);

        g.setFont(new Font("Arial", 1, 30));
        int radius = size - 100;
        for(int i = 0; i<12; i++){
            double anim = (int) ((Math.sin((i+1) % 12.0 / 12 * Math.PI *2) * (radius / 2)));
            double anim2 = (int) ((Math.cos((i +1) % 12.0 / 12 * Math.PI * 2) * (radius / 2)));
            if(i >= 9){
                anim -= 10;
            }
            g.drawString((i +1) + "", center + (int) anim -6, center - (int) anim2 + 12);
        }



    }


}
