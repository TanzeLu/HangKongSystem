package ����.������;

import ����.��ѯ.*;
import ����.����.*;
import ����.��Ʊ.*;
import ����.��Ʊ.��Ʊ;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.net.URL;

class ����  extends JPanel implements ActionListener
{
	private ��ѯ���� query;
	static �������ݿ�  manager;
	private ��Ʊ tuiPiao;
	private ���� dingPiao;

	private JLabel j1 = new JLabel("������Ʊϵͳ");
	private JLabel j2 = new JLabel("--��ӭ����ʹ��");
	private JButton jb��ѯ = new JButton("��ѯ");
	private JButton jb��Ʊ = new JButton("��Ʊ");
	private JButton jb��Ʊ = new JButton("��Ʊ");
	private JButton jb�˳� = new JButton("�˳�");
	private JButton jb���� = new JButton("����");
	private JButton jb���� = new JButton("����");
	public ����()
	{
		this.setLayout(null);                 //����û�и�ʽ����
		this.add(jb��ѯ);                       //���ð�ť
		this.add(jb����);
		this.add(jb��Ʊ);
		this.add(jb��Ʊ);
		this.add(jb�˳�);
		this.add(jb����);
		this.add(j1);
		this.add(j2);
		
		j1.setFont(new Font("����",1,60));
		j1.setForeground(Color.WHITE);
		j2.setFont(new Font("����",1,35));
		j2.setForeground(Color.WHITE);
		jb��ѯ.setFont(new Font("Times",Font.PLAIN,24));      //����
		jb����.setFont(new Font("Times",Font.PLAIN,24));
		jb��Ʊ.setFont(new Font("Times",Font.PLAIN,24));
		jb��Ʊ.setFont(new Font("Times",Font.PLAIN,24));
		jb�˳�.setFont(new Font("Times",Font.PLAIN,24));
		jb����.setFont(new Font("Times",Font.PLAIN,24));
		
		j1.setBounds(150,20,600,300);
		j2.setBounds(600,80,600,300);
		jb��ѯ.setBounds(200,350,100,80);
		jb����.setBounds(200,480,100,80);
		jb��Ʊ.setBounds(700,350,100,80);
		jb��Ʊ.setBounds(450,350,100,80);
		jb�˳�.setBounds(700,480,100,80);
		jb����.setBounds(450,480,100,80);
		
		jb��ѯ.addActionListener(this);     //��Ӽ�����
		jb����.addActionListener(this);
		jb��Ʊ.addActionListener(this);
		jb��Ʊ.addActionListener(this);
		jb�˳�.addActionListener(this);
		jb����.addActionListener(this);
	}
	
	public void paintComponent(Graphics g){	    //����ͼ	
		ImageIcon imageIcon = new ImageIcon("image.gif");
		Image image = imageIcon.getImage();
		g.drawImage(image,0,0, this);	
		}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == jb��ѯ)
		{
			query = new ��ѯ����();
			query.setSize(470,370);
			query.setLocation(750, 350);
			query.setResizable(false);
		    query.setTitle("�����ѯϵͳ");
		    query.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    query.setVisible(true);
		 
		}
		   
		else if (e.getSource() == jb���� )
		{
			String zhangHao = JOptionPane.showInputDialog(null,"����������ʺ�:",
			                                              "�ʺ���֤",JOptionPane.PLAIN_MESSAGE);
			if (zhangHao == null)
			   return;
			if (!zhangHao.equals("LBYLLH") )
			{
				JOptionPane.showMessageDialog(null,"�Բ���!����ʺŲ���ȷ!",
				                              "�ʺŴ���",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String password = JOptionPane.showInputDialog(null,"�������������:",
			                                              "������֤",JOptionPane.PLAIN_MESSAGE);
			if (password == null )
			    return;
			if (!password.equals("424418"))
			{
				JOptionPane.showMessageDialog(null,"�Բ���!������벻��ȷ!",
				                              "�ʺŴ���",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			manager = new �������ݿ�();
			
			manager.setSize(470,370);
			manager.setLocation(750, 350);
			manager.setResizable(false);
		    manager.setTitle("�������ϵͳ");
		    manager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			manager.setVisible(true);
		}
		
		else if (e.getSource() == jb��Ʊ)
		{
			dingPiao = new ����();
			
			dingPiao.setSize(430,300);
			dingPiao.setLocation(750, 350);
			dingPiao.setResizable(false);
			dingPiao.setTitle("���ڻ�Ʊʵʱ�ٶ�");
			dingPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);			
			dingPiao.setVisible(true);
		}
		   
		else if (e.getSource() == jb��Ʊ)
		{
			tuiPiao = new ��Ʊ();
			
			tuiPiao.setSize(470,370);
			tuiPiao.setLocation(750, 350);
			tuiPiao.setResizable(false);
	    	tuiPiao.setTitle("����������Ʊ");
	    	tuiPiao.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	    	
	    	tuiPiao.setVisible(true);
		}
		else if (e.getSource()==jb����){
			String information = "������:" + "   ������  ����Ө    " + "\n" +
	  	                         "ָ����ʦ: " + "   ���崺��ʦ " + "\n" + 
	  	                         "ʱ��: " + "   2017-8-2" + "\n" +
	  	                         "��ַ: " + "   ��ݸ��ѧԺ����ѧԺ";
	  	                 JOptionPane.showMessageDialog(null,information,"����",JOptionPane.INFORMATION_MESSAGE);
		}
		// TODO �Զ����ɵķ������
		else if (e.getSource()==jb�˳�){
			System.exit(0);
			this.setVisible(false);
		}
			
	}
}