package ����.������;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class ���ID extends JFrame implements ActionListener
{ 
    private JTextField zhanghaoField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton jbOK = new JButton("ȷ��");
    private JButton jbCancel = new JButton("ȡ��");
    
    private String name = "1011";
    private String pw = "1011";    
   
    
    public ���ID()
    {
    	Container c = this.getContentPane();
    	
    	c.setLayout(new FlowLayout());
    	c.add(new JLabel("�ʺ�:"));
    	c.add(zhanghaoField);
    	c.add(new JLabel("����:"));
    	c.add(passwordField);
    	c.add(jbOK);
    	c.add(jbCancel);
    	
    	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    	int x = (int)(d.getWidth()-this.getWidth())/2;
    	int y = (int)(d.getHeight()- this.getHeight())/2;
    	
    	this.setLocation(x,y);
    	
    	this.setSize(180,120);
    	this.setResizable(false);
    	this.setVisible(true);
    	
    	jbOK.addActionListener(this);
    	jbCancel.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	String zhanghao = zhanghaoField.getText().trim();
    	String password = passwordField.getText().trim();    	
	
		if (e.getSource() == jbOK)
		{
			if (zhanghao.length() == 0)
			{
				JOptionPane.showMessageDialog(null,"�������ʺ�!","������Ϣ",JOptionPane.ERROR_MESSAGE);
				zhanghaoField.setText("");
				passwordField.setText("");				
				return;
			}
			
			//System.out.println("zhanghao:"+zhanghao+"   name:"+name);
			
			if (!zhanghao.equals(name) )
			{
				JOptionPane.showMessageDialog(null,"�Բ���!����ʺŲ���ȷ!",
				                              "�ʺŴ���",JOptionPane.ERROR_MESSAGE);
				zhanghaoField.setText("");
				passwordField.setText("");			
				return;
			}
			
			if (password.length() == 0)
			{
				JOptionPane.showMessageDialog(null,"����������!","������Ϣ",JOptionPane.ERROR_MESSAGE);
				zhanghaoField.setText("");
				passwordField.setText("");				
				return;
			}
			  
			if (!password.equals(pw))
			{
				JOptionPane.showMessageDialog(null,"�Բ���!������벻��ȷ!",
				                              "�ʺŴ���",JOptionPane.ERROR_MESSAGE);
				zhanghaoField.setText("");
				passwordField.setText("");				
				return;
			}
			
			if (zhanghao.equals(name) && password.equals(pw))
			{
				this.setVisible(false);
				this.dispose();
				
				����.manager = new ����.����.�������ݿ�();
				����.manager.setSize(470,370);
				����.manager.setResizable(false);
				����.manager.setTitle("�������ϵͳ");
				����.manager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				����.manager.setVisible(true);
			}
		}
		else if (e.getSource() == jbCancel)
		{
			this.setVisible(false);
			this.dispose();		
		}
	
    }
    
    
	
}