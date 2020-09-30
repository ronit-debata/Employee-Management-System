import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;
import java.sql.*;


class ViewFrame extends JFrame
{
JTextArea ta;
JScrollPane sp1;

ViewFrame()
{
super("  View all Employees  ");
setSize(500,250);
setResizable(false);    
ta=new JTextArea(10,10);
ta.setEditable(false);

sp1=new JScrollPane(ta);
sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

add(sp1);


setLocationRelativeTo(null);   //SETS THE WHOLE FRAME AT CENTRE
setVisible(true);



DatabaseHandler q =new DatabaseHandler();
String s=q.query();
ta.setText(s);


addWindowListener(new WindowAdapter(){

public void windowClosing(WindowEvent e)
{
HomePage h= new HomePage();
dispose();
}

});


}
}