package ����.��ѯ; 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class ��ѯ���� extends JFrame implements ActionListener,WindowListener
{	
	public ��ѯ����()
	{
		JTabbedPane jtp = new JTabbedPane();
		jtp.add(" �� ͨ �� ѯ ",new ��ͨ��ѯ());
		jtp.add(" �� �� �� ѯ",new �ۺϲ�ѯ());
		
		jtp.setBorder(new MatteBorder(new ImageIcon("f.gif")));
		this.getContentPane().add(jtp);
		
		this.addWindowListener(new WindowAdapter()
		                          {
		                          	public void windowClosing(WindowEvent e)
		                          	{
		                          		��ѯ����.this.setVisible(false);
		                          		��ѯ����.this.dispose();
		                          	}
		                          }
		                      );		
	}
	
	public void actionPerformed(ActionEvent e)
	{
	}
	
	   //ʵ�ִ����������ĳ��󷽷�
	    
	public void windowClosing(WindowEvent e)
	{	
	    closeDataBase();	
	}
	
	   //�˳�����ʱ�����ݿ�Ӧ�ùرա�Ϊ�˱���ռ�ü��������Դ
	public void closeDataBase()
	{
		�����ѯ.sqlBean.CloseDataBase();
		���չ�˾��ѯ.sqlBean.CloseDataBase();
		Ŀ�ĵز�ѯ.sqlBean.CloseDataBase();
		�ۺϲ�ѯ.sqlBean.CloseDataBase();
	}
	
	public void windowClosed(WindowEvent e)
	{		
	}
	
	public void windowOpened(WindowEvent e)
	{		
	}
	
	public void windowIconified(WindowEvent e)
	{		
	}
	
	public void windowDeiconified(WindowEvent e)
	{		
	}
	
	public void windowDeactivated(WindowEvent e)
	{		
	}
	
	public void windowActivated(WindowEvent e)
	{		
	}	

}