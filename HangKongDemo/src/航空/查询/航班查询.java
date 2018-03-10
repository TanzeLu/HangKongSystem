package ����.��ѯ;

import ����.����.*;
import ����.����.SqlBean;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;

public class �����ѯ extends JPanel implements ActionListener,ListSelectionListener
{   
       //�����������ݿⲢִ��SQL������bean
	static SqlBean sqlBean = new SqlBean();
	
	private static DefaultListModel model = new DefaultListModel();
    private JList list = new JList(model);	  
	
	private JTextField flightField = new JTextField(15);
	private JButton jbQuery = new JButton("��ѯ");	
	
	   //�����洢��ѡ��ĺ����
	private String flightNumber;
	
	   //���ý���Ĺ��췽��
	public �����ѯ()
	{		 
		   //��ѡ��
	    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			    
	   
	       //****************************************************
	    JLabel jl = new JLabel("�����ѯ");
	    jl.setFont(new Font("Times",Font.BOLD,23));
	    JPanel jpTop = new JPanel();
	    jpTop.add(jl);    
	   
	    JLabel jl2 = new JLabel("                   �����뺽�����ƻ���");
	    jl2.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl3 = new JLabel("           ѡ���ұߵĺ����б�:");
	    jl3.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel = new JPanel(new BorderLayout());
	    jpLabel.add(jl2,BorderLayout.NORTH);
	    jpLabel.add(jl3,BorderLayout.SOUTH);
	    
	       //*******************************************************
	    
	    JPanel jpField = new JPanel();
	    jpField.add(flightField);
	    
	    JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
	    jpButton.add(jbQuery);
	    
	    JPanel jp1 = new JPanel();
	    jp1.setLayout(new BorderLayout());
	    jp1.add(jpField,BorderLayout.NORTH);
	    jp1.add(jpButton,BorderLayout.CENTER);
	    
	    JPanel jp = new JPanel();
	    jp.setLayout(new BorderLayout());
	    jp.add(jpLabel,BorderLayout.NORTH);
	    jp.add(jp1);	   
	    
	       //*******************************************************
	    
	    JPanel jpList = new JPanel();
	    jpList.add(new JScrollPane(list));
	    
	    JPanel jpCenter = new JPanel();
	    jpCenter.setLayout(new BorderLayout());
	    jpCenter.add(jp,BorderLayout.CENTER);
	    jpCenter.add(jpList,BorderLayout.EAST);
	    
	    JPanel jpQuery = new JPanel();
	    jpQuery.setLayout(new BorderLayout());
	    jpQuery.add(jpTop,BorderLayout.NORTH);
	    jpQuery.add(jpCenter,BorderLayout.CENTER);
	    
	    this.setLayout(new BorderLayout());
	    this.add(new JLabel("      "),BorderLayout.NORTH);
	    this.add(jpQuery,BorderLayout.CENTER);
	    
	       //����������ӵ��б���
	    list.addListSelectionListener(this);
	       //����������ӵ���ѯ��ť
	    jbQuery.addActionListener(this);	
	}
	
	public static void updateFlightList(String newFlightNum,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		   model.addElement(newFlightNum);
		else if (insertOrDelete == 2)
		{
			if (model.contains(newFlightNum))
			   model.removeElement(newFlightNum);			
		}
		   
	}
	
	   //��������ص��б���
	public void valueChanged(ListSelectionEvent e)
	{
		String s = (String)list.getSelectedValue();
		
		if (s != null)
		   flightField.setText(s.trim());
		else 
		   flightField.setText("");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		this.flightNumber = flightField.getText().trim();	
		
		   //û�������κζ���
		if (flightNumber.length() == 0)
		{
			JOptionPane.showMessageDialog(null,"�����뺽��Ż��ߴ��б���ѡ��",
			                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		   //���в�ѯ����
		executeFlightQuery();
	}
	
	public void executeFlightQuery()
	{
		   //SQL�ַ��� 
		String sqlString = "select distinct * from " +"Plane " +"WHERE flight=" + "\'" + flightNumber + "\'";
	
	    ResultSet rs = sqlBean.executeSearch(sqlString);		        
	
	    if (rs != null)
	       showResult(rs);
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);
	}
	
	   //�ӽ�����л�ȡ����ַ���,Ȼ���ڶԻ�������ʾ�����
	public void showResult(ResultSet rs)
	{
		String result = "                                                    " + 
		                "�����ѯ" + "\n";
		
		result += "��ѯ�ĺ����:" + flightNumber + "\n";
		result += "�����    ���չ�˾            ��ɵص�  �ִ�ص�  ���ʱ��  �ִ�ʱ��  " + 
		          "��ͯƱ��   ����Ʊ��   �ۿ�1   �ۿ�2   ���� " + "\n";
		   
		   //����ȷ���Ƿ�û�м�¼��
		int originLength = result.length();
		
		String time1,time2;
		String childFare,adultFare,discount1,discount2,seat;	
		
		try
		{
			while(rs.next())
			{
				result += rs.getString("flight") + rs.getString("airfirm") + rs.getString("start") + 
				          rs.getString("destination");
				          
				    //����ӽ���еõ�ʱ�䣬��õ���1200������Ӧ�ð����ĳɡ�12:00����
				time1 = rs.getString("leaveTime");
				time2 = rs.getString("arriveTime");
				    //ʱ�䣨�ַ������������ı�ɱ�׼��ʽ
				time1 = getTime(time1);
				time2 = getTime(time2);
				
				result += time1 + "     " + time2  + "     ";
					
				   //ȷ��������Ŀ����ȷ��λ�ã��Ա�������������ĸ�ʽ��ʾ
				childFare = String.valueOf(rs.getFloat("childFare"));
				adultFare = String.valueOf(rs.getFloat("adultFare"));
				discount1 = String.valueOf(rs.getFloat("discount1"));
				discount2 = String.valueOf(rs.getFloat("discount2"));
				seat = String.valueOf(rs.getInt("seat"));
				
				   //ʹÿһ����Ŀ��ʽ����
				while(childFare.length() != 11)
				   childFare += " ";
				while(adultFare.length() != 11)
				   adultFare += " ";
				while(discount1.length() != 8)
				   discount1 += " ";
				while(discount2.length() != 8)
				   discount2 += " ";			
				   
				result += childFare + adultFare + discount1 + discount2 +
				          rs.getString("week1");
				result += "\n";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		   //��ʾû�з��ּ�¼�������ʾ������Ϣ�����û���ʾ�������ĺ����
		if (result.length() == originLength)
		{
			JOptionPane.showMessageDialog(null,"����Ų����ڣ���ȷ����������ȷ�ĺ����",
	    	                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
	    	return;
		}		
		
		   //����Ϣ�Ի�������ʾ���
		JOptionPane.showMessageDialog(null,result,"��ѯ���",JOptionPane.PLAIN_MESSAGE);		
	}
	
	   //���ڸı�ʱ����ʽ�ķ���
	private String getTime(String time)
	{
		String time1,time2;
		time1 = time.substring(0,2);
		time2 = time.substring(2,4);
		
		time1 = time1.concat(":");
		time1 = time1.concat(time2);
		
		return time1;
	}
}