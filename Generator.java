import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Generator extends JFrame implements ActionListener
{
    public JLabel lb = new JLabel("Enter The Class Name : ");
    public JTextField tf = new JTextField(15);
    public JTextArea ta = new JTextArea();
    public JButton bt = new JButton (" Generate ");
    public JPanel jp = new JPanel();
    String first;
    public Generator()
    {
        setTitle("...Generator...");
        setVisible(true);
        setSize(444, 444);
       // setUndecorated(True);
        //setLayout(new FlowLayout());
        add();
    }
    public void add()
    {
        add(jp,BorderLayout.NORTH);
        jp.add(lb);
        jp.add(tf);
        jp.add(bt);
        add(ta,BorderLayout.CENTER);
        jp.setBackground(Color.GREEN);
        bt.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        first = tf.getText();
        first = "public class "+ first +"\n{\n\tpublic "+first+"(";
        String[] attribs = (ta.getText()).split("\n");

        first = first + genConstructor(attribs) + genAttrib(attribs)  + genSetter(attribs) + genGetter(attribs) + "}";

        ta.setText(first);

    }
    public String genConstructor(String[] attrib)
    {
        String ret = "";
        for(String str : attrib)
        {
            ret = ret+ " int " + str + "," ;
        }
        ret = ret.substring(0, ret.length()-1) + ")\n\t{\n";
        for(String str : attrib)
        {
            ret = ret+ "\t\tthis."+str+"="+str+";\n";
        }
        return ret + "\t}\n";
    }
    public String genAttrib(String[] attrib)
    {
        String ret = "";
        for(String str : attrib)
        {
            ret = ret + "\tprivate int " + str + ";\n";
        }
        return ret + "\n";
    }
    public String genSetter(String[] attrib)
    {
        String ret = "";
        for(String str : attrib)
        {
            //ret = ret + "public void set" + str.valueOf(0).toUpperCase() + ";\n";
            ret = ret + "\tpublic void set" + str + "(int "+str+"){ this."+str+"="+str+"; }\n";
        }
        return ret + "\n";
    }
    public String genGetter(String[] attrib)
    {
        String ret = "";
        for(String str : attrib)
        {
            ret = ret + "\tpublic int get" + str + "(){ return "+str+"; }\n";
        }
        return ret + "\n";
    }
    public void Hacked()
    {
    }

    public static void main(String[] args)
    {
       Generator gen = new Generator();
    }

}
