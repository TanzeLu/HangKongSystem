package ����.��ѯ;

import ����.����.*;
import ����.����.SqlBean;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

public class Ŀ�ĵز�ѯ extends JPanel implements ActionListener
{
	   //�����������ݿⲢִ��SQL������bean
	static SqlBean sqlBean = new SqlBean();
	
	private static DefaultComboBoxModel model_1 = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelStart = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelArrive = new DefaultComboBoxModel();
	private static JComboBox jcb1 = new JComboBox(model_1),
	                         jcbStart = new JComboBox(modelStart),
	                         jcbArrive = new JComboBox(modelArrive);
	                  
    private JButton jbQuery1 = new JButton("��ѯ"),
                    jbQuery2 = new JButton("��ѯ"); 
    
       //������Ŀѡ���ÿ����Ͽ�
    private String destination,start,arrive;
    
       //���ý���Ĺ��췽��
    public Ŀ�ĵز�ѯ()
    {    
           //*****************************************************
           
    	JLabel jl = new JLabel("Ŀ�ĵز�ѯ");
	    jl.setFont(new Font("Times",Font.BOLD,23));
	    JPanel jpTop = new JPanel();
	    jpTop.add(jl);    
	   
	    JLabel jl2 = new JLabel("��ѯ����һ:");
	    jl2.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl3 = new JLabel("        ��ѡ��Ҫ����ĳ�������:");
	    jl3.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel1 = new JPanel(new BorderLayout());
	    jpLabel1.add(jl2,BorderLayout.NORTH);
	    jpLabel1.add(jl3,BorderLayout.CENTER);
	    
	    JPanel jpQuery1 = new JPanel();	   
	    jpQuery1.add(jcb1);
	    jpQuery1.add(jbQuery1);  
	    
	    JPanel jp1 = new JPanel();
	    jp1.setLayout(new BorderLayout());
	    jp1.add(jpLabel1,BorderLayout.NORTH);
	    jp1.add(jpQuery1,BorderLayout.CENTER); 
	    
	       //*****************************************************	    
	    
	    JLabel jl4 = new JLabel("��ѯ������:");
	    jl4.setFont(new Font("Times",Font.PLAIN,12));
	    
	    JLabel jl5 = new JLabel("        ��ѡ����ʼ���к͵�����н��в�ѯ:");
	    jl5.setFont(new Font("Times",Font.PLAIN,12));
	    JPanel jpLabel2 = new JPanel(new BorderLayout());
	    jpLabel2.add(jl4,BorderLayout.NORTH);
	    jpLabel2.add(jl5,BorderLayout.CENTER);
	    
	    JPanel jpQuery2 = new JPanel();
	    jpQuery2.add(new JLabel("��������:"));
	    jpQuery2.add(jcbStart);
	    
	    JPanel jpQuery3 = new JPanel();
	    jpQuery3.add(new JLabel("�ִ����:"));
	    jpQuery3.add(jcbArrive);
	    
	    JPanel jpButton  = new JPanel();
	    jpButton.add(jbQuery2);
	    
	    JPanel jp2 = new JPanel();
	    jp2.add(jpQuery2);
	    jp2.add(jpQuery3);
	    
	    JPanel jp3 = new JPanel();
	    jp3.setLayout(new BorderLayout());
	    jp3.add(jpLabel2,BorderLayout.NORTH);
	    jp3.add(jp2,BorderLayout.CENTER);
	    jp3.add(jpButton,BorderLayout.SOUTH);
	    
	       //*****************************************************	    
	    
	    JPanel jp4 = new JPanel();
	    jp4.setLayout(new BorderLayout());
	    jp4.add(jp1,BorderLayout.NORTH);
	    jp4.add(jp3,BorderLayout.CENTER);
	    
	    this.setLayout(new BorderLayout());
	    this.add(jpTop,BorderLayout.NORTH);
	    this.add(jp4,BorderLayout.CENTER);
	    this.add(new JLabel("             "),BorderLayout.SOUTH);
	    
	       //����������ӵ���ѯ��ť
	    jbQuery1.addActionListener(this);
	    jbQuery2.addActionListener(this);	    
    }
    
    public static void updatePlaceComboBox(String newPlace,int insertOrDelete)
    {
    	if (insertOrDelete == 1)
    	{
    		if (model_1.getIndexOf(newPlace) == -1)
	    	   jcb1.addItem(newPlace);
	    	 
	    	if (modelStart.getIndexOf(newPlace) == -1)
	    	   jcbStart.addItem(newPlace);
	    	   
	    	if (modelArrive.getIndexOf(newPlace) == -1)
	    	   jcbArrive.addItem(newPlace);	
    	}
    	else if (insertOrDelete == 2)
    	{
    		if (model_1.getIndexOf(newPlace) != -1)
    		   jcb1.removeItem(newPlace);
    		
    		if (modelStart.getIndexOf(newPlace) != -1)
    	       jcbStart.removeItem(newPlace);
    		
    		if (modelArrive.getIndexOf(newPlace) != -1)
    		   jcbArrive.removeItem(newPlace);
    	}    	
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == jbQuery1)
    	{
    		   //��ȡҪ��ѯ��Ŀ�ĵء�
    		destination = (String)jcb1.getSelectedItem();
    		destination = destination.trim();
    		
    		   //����ѯ������������־= 1����ζ�����ǵ�һ���ѯ
    		executeDestinQuery(1);
    	}
    	else if (e.getSource() == jbQuery2)
    	{
    		   //ȡ�ó�����
    		start = (String)jcbStart.getSelectedItem();
    		start = start.trim();
    		   //��Ŀ�ĵ�
    		arrive = (String)jcbArrive.getSelectedItem();
    		arrive = arrive.trim();
    		
    		   //����ѯ������������־= 2����ζ�����ǵڶ����ѯ 
    		executeDestinQuery(2);
    	}
    }
    
    public void executeDestinQuery(int flag)
    {
    	   //SQL
    	String sqlString = "SELECT DISTINCT * FROM " + "Plane ";
    	
    	   //���ֲ�ѯ��SQL�ַ�����ͬ��
    	if (flag == 1)
    	   sqlString += "WHERE destination=" + "\'" + destination + "\'";
    	else 
    	   sqlString += "WHERE start=" + "\'" + start + "\'" + " AND " +
    	                "destination=" + "\'" + arrive + "\'";
    	         
        ResultSet rs = sqlBean.executeSearch(sqlString);
        
        if (rs != null)
	       showResult(rs,flag);
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);  
    }
    

    public void showResult(ResultSet rs,int flag)
    {
        String result = "                                                    " + 
		                "Ŀ�ĵز�ѯ " + "\n";
		
		   //���ֲ�ѯ�Ľ���ַ�����ͬ
		if (flag == 1)
		   result += "���� " + destination + " �����к���:" + "\n";
		else
		   result += "ʼ����: " + start + "------" + "Ŀ�ĵ�: " + arrive + "\n";
		   
		result += "�����    ���չ�˾            ��ɵص�  �ִ�ص�  ���ʱ��  �ִ�ʱ��  " + 
		          "��ͯƱ��   ����Ʊ��   �ۿ�   ���� " + "\n";
		   
		   //����ȷ���Ƿ�û�м�¼
		int originLength = result.length();
		
		String time1,time2;
		String childFare,adultFare,discount1,discount2,seat;	
		
		try
		{
			while(rs.next())
			{
				result += rs.getString("flight") + rs.getString("airfirm") + rs.getString("start") + 
				          rs.getString("destination");
				          
				
				time1 = rs.getString("leaveTime");
				time2 = rs.getString("arriveTime");
				 
				time1 = getTime(time1);
				time2 = getTime(time2);
				
				result += time1 + "     " + time2  + "     ";
				
				  
				childFare = String.valueOf(rs.getFloat("childFare"));
				adultFare = String.valueOf(rs.getFloat("adultFare"));
				discount1 = String.valueOf(rs.getFloat("discount1"));
				discount2 = String.valueOf(rs.getFloat("discount2"));
				seat = String.valueOf(rs.getInt("seat"));
				
				  
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
		
		   //��ζ���Ҳ�����¼�����Ը��û����ԣ��Ҳ�����صĲ�ѯָ�����͵���Ϣ
		if (result.length() == originLength && flag == 1)
		{
			JOptionPane.showMessageDialog(null,"û�е��� " + destination + "�ĺ���",
	    	                              "��ѯ���",JOptionPane.PLAIN_MESSAGE);
	    	return;
		}
		if (result.length() == originLength && flag == 2)
		{
			JOptionPane.showMessageDialog(null,"û�д� " + start +" �� " + arrive +" �ĺ���",
	    	                              "��ѯ���",JOptionPane.PLAIN_MESSAGE);
	    	return;
		}
				
	
		JOptionPane.showMessageDialog(null,result,"��ѯ���",JOptionPane.PLAIN_MESSAGE);		
	}
	

	private String getTime(String time)
	{
		String time1,time2;
		time1 = time.substring(0,2);
		time2 = time.substring(2,4);
		
		time1 = time1.concat(":");
		time1 = time1.concat(time2);
		
		return time1;
	}
    
}///:~