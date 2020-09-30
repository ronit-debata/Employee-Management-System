import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.regex.*;
import java.sql.*;


class Enroll extends JFrame
{
JPanel jp1,jp2;
JLabel lbl1ID,lbl2Name,lbl3Department,lbl4Salary;
JTextField txtID,txtName,txtSalary,txtDepartment;
JButton btnSave,btnBack;

Enroll()
{
super("  Enroll  ");
setSize(500,200);
setResizable(false);    //the resize option is greyed out

jp1=new JPanel();
jp1.setLayout(new FlowLayout());

lbl1ID=new JLabel("ID");
txtID=new JTextField(5);//that is 5 coloumns

lbl2Name=new JLabel("Name");
txtName=new JTextField(10);//that is 10 coloumns

lbl3Department=new JLabel("Department");
txtDepartment=new JTextField(10);//that is 10 coloumns

lbl4Salary=new JLabel("Salary");
txtSalary=new JTextField(10);//that is 10 coloumns

//now add all this to Jpane
jp1.add(lbl1ID);
jp1.add(txtID);
jp1.add(lbl2Name);
jp1.add(txtName);
jp1.add(lbl3Department);
jp1.add(txtDepartment);
jp1.add(lbl4Salary);
jp1.add(txtSalary);

//now add the pane to frame
add(jp1);

jp2=new JPanel();
jp2.setLayout(new FlowLayout());

//save and back buttons

btnSave=new JButton("Save");
btnBack=new JButton("Back");

jp2.add(btnSave);
jp2.add(btnBack);

add(jp2,BorderLayout.SOUTH);
setLocationRelativeTo(null);   //SETS THE WHOLE FRAME AT CENTRE
setVisible(true);

//back button should take us back to homeframe
btnBack.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent e)
{
HomePage h= new HomePage();
dispose();
}

});


//close button also should take us back to homeframe
addWindowListener(new WindowAdapter(){

public void windowClosing(WindowEvent e)
{
HomePage h= new HomePage();
dispose();
}

});

btnSave.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent ae)
{
int id=0;
String name="";
String department="";
int salary=0;

//id validation
try
{
id=Integer.parseInt(txtID.getText());
}//end try
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid id ");
//gets the focus back to id feild
txtID.setText("");
txtID.requestFocus();
return;
}//end catch

//id validation.id less than 0
if(id<=0){
JOptionPane.showMessageDialog(new JDialog()," Invalid ID. ID should be greater than 0. ");
txtID.setText("");
txtID.requestFocus();
return;
}

name=txtName.getText();

//name validation
if(name.length()==0)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid name. Enter name. ");
txtName.setText("");
txtName.requestFocus();
return;
}

try
{
if(!name.matches("[a-zA-Z0-9_.-]{3,}"))
{
JOptionPane.showMessageDialog(new JDialog()," Invalid name. ");
txtName.setText("");
txtName.requestFocus();
return;
}

}//end try
catch(PatternSyntaxException pse)
{
}

department=txtDepartment.getText();

//department validation
if(department.length()==0)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid Department. Enter Department. ");
txtDepartment.setText("");
txtDepartment.requestFocus();
return;
}

try
{
if(!department.matches("[a-zA-Z0-9_.-]{2,}"))
{
JOptionPane.showMessageDialog(new JDialog()," Invalid department. ");
txtDepartment.setText("");
txtDepartment.requestFocus();
return;
}

}//end try
catch(PatternSyntaxException pse)
{
}

//salary validation
try
{
salary=Integer.parseInt(txtSalary.getText());
}//end try
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(new JDialog()," Invalid Salary. ");
txtSalary.setText("");
txtSalary.requestFocus();
return;
}//end catch

if(salary<=0){
JOptionPane.showMessageDialog(new JDialog()," Invalid Salary. Salary should be greater than 0. ");
txtSalary.setText("");
txtSalary.requestFocus();
return;
}

DatabaseHandler query =new DatabaseHandler();
query.insert(id,name, department, salary);
txtID.setText("");
txtName.setText("");
txtDepartment.setText("");
txtSalary.setText("");

}

});

}
}