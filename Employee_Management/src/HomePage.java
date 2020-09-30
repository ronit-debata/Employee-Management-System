
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;


class HomePage extends JFrame
{

JButton btnAdd,btnModify,btnDelete,btnView;

HomePage()
{
super("  Home Page  ");
setBounds(100, 100, 500, 300);
setResizable(false);    
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

JPanel contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

btnAdd=new JButton("Enroll");
btnModify=new JButton("Modify");
btnDelete=new JButton("Delete");
btnView=new JButton("View Details");

JLabel Header = new JLabel("Employee Information System");
Header.setHorizontalAlignment(SwingConstants.CENTER);
Header.setForeground(Color.RED);
Header.setFont(new Font("Calibri", Font.BOLD, 20));
Header.setBounds(0, 0, 500, 50);

btnAdd.setBounds(105, 60, 120, 70);
btnModify.setBounds(105, 170, 120, 70);
btnDelete.setBounds(275, 60, 120, 70);
btnView.setBounds(275, 170, 120, 70);

contentPane.add(Header);
contentPane.add(btnAdd);
contentPane.add(btnModify);
contentPane.add(btnDelete);
contentPane.add(btnView);


setLocationRelativeTo(null);   
setVisible(true);


btnAdd.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
Enroll a = new Enroll();
dispose();//dispose the current frame
}

});

//navigation to modify
btnModify.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
ModifyFrame f = new ModifyFrame();
dispose();//dispose the current frame
}

});

btnDelete.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
DeleteFrame d = new DeleteFrame();
dispose();//dispose the current frame
}

});

btnView.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent e)
{
ViewFrame v= new ViewFrame();
dispose();//dispose the current frame
}

});



}


public static void main(String args[])
{
HomePage h=new HomePage();
}


}

class DatabaseHandler
{
static Connection con;

public static void getConnection(){

try
{
Class.forName("com.mysql.cj.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_mgmnt","root","ronit007");
}//end of try
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog(),"  "+e);
}

}//end getconnection


public void insert(int id,String name,String department,int salary)
{
try
{
getConnection();
String q="insert into employee values(?,?,?,?)";
PreparedStatement pst=con.prepareStatement(q);
pst.setInt(1,id);
pst.setString(2,name);
pst.setString(3,department);
pst.setInt(4,salary);

int i=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog()," 1 record added");

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," Record Already Exists. ");
}


}//insert end

public void modify(int id,String name,String department,int salary)
{
try
{
getConnection();
String q="update employee set Name=?, Department=?, Salary=? where ID=?";
PreparedStatement pst=con.prepareStatement(q);
pst.setInt(4,id); 
pst.setString(1,name);
pst.setString(2,department);
pst.setInt(3,salary);

int i=pst.executeUpdate();
if(i==0){
JOptionPane.showMessageDialog(new JDialog()," 0 records modified");
}
else
{
JOptionPane.showMessageDialog(new JDialog()," 1 records modified");
}

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," Record cannot be modified. ");
}


}//modify end

public void delete(int id)
{
try
{
getConnection();
String q="delete from employee where ID=?";
PreparedStatement pst=con.prepareStatement(q);
pst.setInt(1,id); 


int i=pst.executeUpdate();
if(i==0){
JOptionPane.showMessageDialog(new JDialog()," 0 records deleted");
}
else
{
JOptionPane.showMessageDialog(new JDialog()," 1 records deleted");
}

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," Record cannot be deleted. ");
}


}//delete end

public String query()
{
StringBuffer sb=new StringBuffer();
try
{
getConnection();
String view="select * from employee";
Statement st=con.createStatement();
ResultSet rs=st.executeQuery(view);

sb.append("ID"+"\t"+"NAME"+"\t"+"SALARY"+"\t"+"DEPARTMENT"+"\n");
while(rs.next())
{
sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(4)+"\t"+rs.getString(3)+"\n");
}
rs.close();

}
catch(Exception e)
{
JOptionPane.showMessageDialog(new JDialog()," "+e);
}

return sb.toString();

}


}


