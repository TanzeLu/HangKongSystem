package ����.����;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.sql.*;

public class �������ݿ� extends JFrame
{
	private JTabbedPane tab=new JTabbedPane();
	
	public �������ݿ�()
	{
		tab.add("    ��   ��    ",new �������());
		tab.add("    ɾ   ��    ",new ɾ�����());
		tab.add("    ��   ��    ",new �������());
		tab.add("  �� �� �� �� ��  ",new ��ʾ());
		//tab.setBorder(new MatteBorder(new ImageIcon("image.gif")));
		this.getContentPane().add(tab);
		
		this.addWindowListener(new WindowAdapter()
	                          {
	                          	public void windowClosing(WindowEvent e)
	                          	{
	                          		�������ݿ�.this.setVisible(false);
	                          		�������ݿ�.this.dispose();
	                          	}
	                          }
	                      );	
		
	}
}