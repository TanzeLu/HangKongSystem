package ����.������;

import ����.����.*;
import ����.����.UpdateComboBox;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ������ extends JFrame
{   
    private UpdateComboBox update;
    private ���� jiemian;
	public ������()
	{
		update = new UpdateComboBox();
		
		jiemian = new ����();
		
		this.getContentPane().add(jiemian);		
	}
	
	public static void main(String args[])
	{
		   //��ȡϵͳ��GUI�Ĳ���
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
	  
		������ frame =new ������();
		frame.setSize(1030,720);
		Dimension screenSize=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize=frame.getSize();
		frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2 );
		frame.setResizable(false);
		frame.setTitle("������Ʊ����ϵͳ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}