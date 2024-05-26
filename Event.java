import java.util.*;
import javax.swing.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.String;
import java.awt.*;  
import java.awt.event.*; 
class Event implements ActionListener
{
	JFrame f=new JFrame("College Event Management System");
	JLabel l0=new JLabel("Event_Name");
	JLabel l1=new JLabel("Date");
	JLabel l2=new JLabel("Venue");
	JLabel l3=new JLabel("Timings");
	JLabel l4=new JLabel();
	JTextField t0=new JTextField();
	JTextField t1=new JTextField();
	JTextField t2=new JTextField();
	JTextField t3=new JTextField();
	TextArea ta=new TextArea();
	JButton b1=new JButton("Save");
	JButton b2=new JButton("Search");
	JButton b3=new JButton("Remove");
	JButton b4=new JButton("Data");
	Event()
	{
		l0.setBounds(50,80,100,20);
		l1.setBounds(50,100,100,20);
		l2.setBounds(50,120,100,20);
		l3.setBounds(50,140,100,20);
		t0.setBounds(200,80,140,20);
		t1.setBounds(200,100,140,20);
		t2.setBounds(200,120,140,20);
		t3.setBounds(200,140,140,20);
		b1.setBounds(450,80,80,20);
		b2.setBounds(450,160,80,20);
		b3.setBounds(450,240,90,20);
		b4.setBounds(450,320,90,20);
		l4.setBounds(75,190,300,20);
		ta.setBounds(30,240,400,200);
		f.add(l0);
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(t0);
		f.add(t1);
		f.add(t2);
		f.add(t3);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(ta);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		f.setLayout(null);
		f.setVisible(true);
		f.setSize(600,500);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","mahesh","Mahesh");
			if(e.getSource()==b1)
			{
			
				String sql = "insert into Event values(\'"+t0.getText()+"\',\'"+t1.getText()+"\',\'"+t2.getText()+"\',\'"+t3.getText()+"\')";
				ps=con.prepareStatement(sql);
				ps.execute();
				l4.setText("Your Event has been registered");
				ta.setText("Event name: "+t0.getText()+",\nDate: "+t1.getText()+",\nvenue: "+t2.getText()+",\nTimings: "+t3.getText()+".");
			}
			if(e.getSource()==b2)
			{
				String sql = "select * from Event where Event_Name=\'"+t0.getText()+"\'";
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				ta.setText("");
				while(rs.next())
           		{           	   
					ta.append("\nEvent name: "+rs.getString(1)+", Date: "+rs.getString(2)+", venue: "+rs.getString(3)+", Timings: "+rs.getString(4)+".");
            	}
				l4.setText("Your Event has Found ");
			}
			if(e.getSource()==b3)
			{
				String sql="delete from event where Event_Name=\'"+t0.getText()+"\'";
				ps=con.prepareStatement(sql);
				ps.executeUpdate();
				l4.setText("Your Event has been Deleted");
			}
			if(e.getSource()==b4)
			{
				String sql = "select * from event";
				ps=con.prepareStatement(sql);
				rs=ps.executeQuery();
				ta.setText("");
				while(rs.next())
           		{    	   
					ta.append("\nEvent_Name: "+rs.getString(1)+", Date : "+rs.getString(2)+", Venue "+rs.getString(3)+", Timings : "+rs.getString(4)+".");
            	}
				l4.setText("Your Past Events are Displayed in TextArea");
			}
		}
		catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}

	}
	public static void main(String args[])
	{
		new Event();			
	}
}