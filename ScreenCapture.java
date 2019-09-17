import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

/*
 *      JavaScreenCapture
 *      Vizay Soni (Vs4vijay@gmail.com)
 *      V Sem CE(Govt. Engg. College Ajmer)
 */

public class ScreenCapture extends JFrame implements ActionListener
{
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension dim = tk.getScreenSize();
    Rectangle rect = new Rectangle(dim);
    BufferedImage image;
    JButton bt1 = new JButton("Capture");
    JButton bt2 = new JButton("Save");
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    Robot robot;
    Graphics g;

    public ScreenCapture()
    {
        setVisible(true);
        setSize(444,444);
        setTitle("JavaScreenCapture");
        add(jp1,BorderLayout.CENTER);
        add(jp2,BorderLayout.SOUTH);
        jp2.add(bt1);
        jp2.add(bt2);
        jp2.setBackground(Color.GREEN);
        bt1.addActionListener(this);
        bt2.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent at)
    {
        if (at.getSource() == bt1)
        {
            try
            {
                robot = new Robot();
            }
            catch(Exception ex){System.out.println(ex);}
            image = robot.createScreenCapture(rect);
            g = jp1.getGraphics();
            g.drawImage(image, 0, 0, jp1.getWidth(),jp1.getHeight(),jp1);
        }
        if(at.getSource() == bt2)
        {
            File file = new File("Screen001.png");
            try
            {
                ImageIO.write(image, "png", file);
            } 
            catch (IOException ex)
            {
                System.out.println(ex);
            }
        }
    }
    
    public static void main(String[] args)
    {
       ScreenCapture sc = new ScreenCapture();
    }

}
