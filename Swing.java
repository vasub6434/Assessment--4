package com.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Swing implements ActionListener {
	
	JFrame f;
	JLabel l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4,t5;
	JButton b1,b2,b3,b4;
	public Swing()
	{
		f=new JFrame("my Assement");
		f.setVisible(true);
		f.setSize(370, 500);
		f.setLayout(null);
		
		l1=new JLabel("id");
		l2=new JLabel("name");
		l3=new JLabel("fees");
		l4=new JLabel("duration");
		l5=new JLabel("detail");
		
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		
		l1.setBounds(50, 100, 100, 20);
		l2.setBounds(50, 150, 100, 20);
		l3.setBounds(50, 200, 100, 20);
		l4.setBounds(50, 250, 100, 20);
		l5.setBounds(50, 300, 100, 20);
		
		t1=new JTextField(20);
		t2=new JTextField(20);
		t3=new JTextField(20);
		t4=new JTextField(20);
		t5=new JTextField(20);
		
		f.add(t1);
		f.add(t2);		
		f.add(t3);		
		f.add(t4);		
		f.add(t5);	
		
		t1.setBounds(150, 100, 150, 20);
		t2.setBounds(150, 150, 150, 20);
		t3.setBounds(150, 200, 150, 20);
		t4.setBounds(150, 250, 150, 20);
		t5.setBounds(150, 300, 150, 20);
		
		b1=new JButton("insert");
		b2=new JButton("select");
		b3=new JButton("update");
		b4=new JButton("delete");
		
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		
		b1.setBounds(50, 350, 100, 30);
		b2.setBounds(200, 350, 100, 30);
		b3.setBounds(50, 400, 100, 30);
		b4.setBounds(200, 400, 100, 30);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		
		
	}
	public static void main(String[] args) {
		new Swing();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1)
		{
			if(t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))
			{
				JOptionPane.showMessageDialog(f, "all mendatory field");
			}
			else
			{
				try 
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/yellow","root","");
					String sql="insert into student(name,fees,duration,detail)values(?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1, t2.getText());
					pst.setInt(2, Integer.parseInt(t3.getText()));
					pst.setString(3, t4.getText());
					pst.setString(4, t5.getText());
					pst.executeUpdate();
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					
					JOptionPane.showMessageDialog(f, "data inserted");
				}
				catch(Exception d1)
				{
					d1.printStackTrace();
				}
			}
			
		}
		else if(e.getSource()==b2)
		{
			if(t1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(f, "id is mendatory field");
			}
			else
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/yellow","root","");
					String sql="select * from student where id=?";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(t1.getText()));
					
					ResultSet rs=pst.executeQuery();
					if(rs.next())
					{
						t2.setText(rs.getString("name"));
						t3.setText(rs.getString("fees"));
						t4.setText(rs.getString("duration"));
						t5.setText(rs.getString("detail"));	
					}
					else
					{
						t2.setText("");
						t3.setText("");
						t4.setText("");
						t5.setText("");
						JOptionPane.showMessageDialog(f, "id not found");
						
					}
				}
				catch(Exception d2)
				{
					d2.printStackTrace();
				}
			}
		}
		else if(e.getSource()==b3)
		{
			if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("") || t5.getText().equals(""))
			{
				JOptionPane.showMessageDialog(f, "All mendatory field");
			}
			else
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/yellow","root","");
					String sql="update student set name=?,fees=?,duration=?,detail=? where id=?";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setString(1, t2.getText());
					pst.setString(2, t3.getText());
					pst.setString(3, t4.getText());
					pst.setString(4, t5.getText());
					pst.setInt(5, Integer.parseInt(t1.getText()));
					pst.executeUpdate();
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					JOptionPane.showMessageDialog(f, "Updated");
					
				}
				catch(Exception d3)
				{
					d3.printStackTrace();
				}
				
			}
		}
		else if(e.getSource()==b4)
		{
			if(t1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(f, "id mendatory field");
			}
			else
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/yellow","root","");
					String sql="delete from student where id=?";
					PreparedStatement pst=conn.prepareStatement(sql);
					pst.setInt(1, Integer.parseInt(t1.getText()));
					pst.executeUpdate();
					
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					JOptionPane.showMessageDialog(f, "delete data found");
				}
				catch(Exception d4)
				{
					d4.printStackTrace();
				}
			}
		}
		
	}

}
