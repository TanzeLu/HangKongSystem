package ����.��ѯ;

import ����.����.*;
import ����.����.SqlBean;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class ���չ�˾��ѯ extends JPanel implements ActionListener
{
	   //�����������ݿⲢִ��SQL������bean��
	static SqlBean sqlBean = new SqlBean();	
	   	
	private static DefaultComboBoxModel model = new DefaultComboBoxModel();
	private static JComboBox jcb = new JComboBox(model);
	
	   //���ڴ洢��Ŀ����ѡ����Ͽ�
	private String airfirm;
	
	private JButton jbQuery = new JButton("��ѯ");	
	
	   //���ý���Ĺ��췽��
	public ���չ�˾��ѯ()
	{	
	       //*****************************************************	
	       
		JLabel jl = new JLabel("  ���չ�˾��ѯ");
	    jl.setFont(new Font("Times",Font.BOLD,23));
	    JPanel jpTop = new JPanel();
	    jpTop.add(jl);    
	   
	    JLabel jl2 = new JLabel("        �����뺽�����ƻ���,����ѯ��");
	    jl2.setHorizontalAlignment(JLabel.CENTER);
	    jl2.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl3 = new JLabel("�ṩ��ú��չ�˾���еĺ������:");
	    jl3.setHorizontalAlignment(JLabel.CENTER);
	    jl3.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel = new JPanel(new BorderLayout());
	    jpLabel.add(jl2,BorderLayout.NORTH);
	    jpLabel.add(jl3,BorderLayout.CENTER);
	    
	       //******************************************************
	    
	    JPanel jpBox = new JPanel();
	    jpBox.add(jcb);
	    
	    JPanel jpButton = new JPanel();
	    jpButton.add(jbQuery);
	    
	    JPanel jp = new JPanel();
	    jp.add(jpBox);
	    jp.add(jpButton);
	    
	       //*****************************************************
	    
	    JPanel jpCenter = new JPanel();
	    jpCenter.setLayout(new BorderLayout());
	    jpCenter.add(jpLabel,BorderLayout.NORTH);
	    jpCenter.add(jp,BorderLayout.CENTER);
	    
	    JPanel jpQuery = new JPanel();
	    jpQuery.setLayout(new BorderLayout());
	    jpQuery.add(jpTop,BorderLayout.NORTH);
	    jpQuery.add(jpCenter,BorderLayout.CENTER);	  
	    
	    this.setLayout(new BorderLayout());
	    this.add(new JLabel("        "),BorderLayout.NORTH);
	    this.add(jpQuery,BorderLayout.CENTER);	  
	    
	       //����������ӵ���ѯ��ť
	    jbQuery.addActionListener(this);		
	}
	
	public static void updateAirFirmComboBox(String newPlace,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		{
			if (model.getIndexOf(newPlace) == -1)
			   jcb.addItem(newPlace);
		}
		   
		else if (insertOrDelete == 2)
		{
			if (model.getIndexOf(newPlace) != -1)
			   jcb.removeItem(newPlace);
		}
		   
	}
	
	public void actionPerformed(ActionEvent e)
	{
		this.airfirm = (String)jcb.getSelectedItem();
		
		   //ִ�в�ѯ����
		executeAirFirmQuery();
	}
	
	public void executeAirFirmQuery()
	{
		   //SQL�ַ���
		String sqlString = "SELECT DISTINCT * FROM " +"Plane " +"WHERE airfirm=" + "\'" + airfirm + "\'";              
	    ResultSet rs = sqlBean.executeSearch(sqlString);
	     
	    if (rs != null)
	       showResult(rs);
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);
	}
	
	   //�ӽ�����л�ȡ����ַ�����Ȼ���ڶԻ�������ʾ���   
	public void showResult(ResultSet rs)
	{
		String result = "                                                     " +
		                "���չ�˾��ѯ" + "\n";
		
		result += "��ѯ�ĺ��չ�˾:" + airfirm + "\n";
		result += "�����    ���չ�˾            ��ɵص�  �ִ�ص�  ���ʱ��  �ִ�ʱ��  " + 
		          "��ͯƱ��   ����Ʊ��   �ۿ�  ���� " + "\n";
		   
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
				   
				result += childFare + adultFare + discount1 +
				          rs.getString("week1");
				result += "\n";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		   //��ζ��û�з��ּ�¼�����ԣ����û�����Ϣ���Ҳ�����ص���Ϣ
		if (result.length() == originLength)
		{
			result += "                                                    " +
			          "�Բ���,�Ҳ�������Ҫ�ĺ�����Ϣ!" + "\n";
		}
		 	
		   //����Ϣ�Ի�������ʾ���			
		JOptionPane.showMessageDialog(null,result,"��ѯ���",JOptionPane.PLAIN_MESSAGE);
	}
	
	   //���ڸı�ʱ����ʽ�ķ�����
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