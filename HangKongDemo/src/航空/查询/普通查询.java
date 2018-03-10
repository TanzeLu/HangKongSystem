package ����.��ѯ;

import ����.����.*;
import ����.����.SqlBean;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.sql.*;

public class ��ͨ��ѯ extends JPanel implements ActionListener,ItemListener
{
    static SqlBean sqlBean = new SqlBean();
    
	private JTextField flightField = new JTextField(8);	
	
	private static DefaultComboBoxModel modelFlight = new DefaultComboBoxModel();
	private static JComboBox jcbFlight = new JComboBox(modelFlight);
	
	private JButton jbFlightQuery = new JButton("��ѯ");
	
	
	   //********************************************************************
	private static DefaultComboBoxModel modelAirFirm = new DefaultComboBoxModel();
	private static JComboBox jcbAirFirm = new JComboBox(modelAirFirm);
	
	private JButton jbAirFirmQuery = new JButton("��ѯ");
	
	
       //********************************************************************	
	private static DefaultComboBoxModel model_1 = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelStart = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelArrive = new DefaultComboBoxModel();
	private static JComboBox jcb1 = new JComboBox(model_1),
	                         jcbStart = new JComboBox(modelStart),
	                         jcbArrive = new JComboBox(modelArrive);
	                  
    private JButton jbPlaceQuery1 = new JButton("��ѯ"),
                    jbPlaceQuery2 = new JButton("��ѯ"); 
         
     
       //�����洢��ѡ��ĺ����
	private String flightNumber;
	   //���ڴ洢��Ŀ����ѡ����Ͽ�
	private String airfirm;
	   //������Ŀѡ���ÿ����Ͽ�
    private String destination,start,arrive;
                      
    public ��ͨ��ѯ()
    {
    	JPanel jpFlight = new JPanel();
    	jpFlight.setBorder(new TitledBorder("������Ų�ѯ"));
    	jpFlight.add(new JLabel("�����뺽��Ż���б�ѡ��:"));
    	jpFlight.add(flightField);
    	jpFlight.add(jcbFlight);
    	jpFlight.add(jbFlightQuery);
    	
    	JPanel jpAirFirm = new JPanel();
    	jpAirFirm.setBorder(new TitledBorder("�����չ�˾��ѯ"));
    	jpAirFirm.add(new JLabel("��ѡ������Ҫ��ѯ�ĺ��չ�˾����:"));
    	jpAirFirm.add(jcbAirFirm);
    	jpAirFirm.add(jbAirFirmQuery);
    	
    	    	
    	JPanel jp1 = new JPanel();
    	jp1.add(new JLabel("��ѡ������Ҫ�����Ŀ�ĵ�:"));
    	jp1.add(jcb1);
    	jp1.add(new JLabel("    "));
    	jp1.add(jbPlaceQuery1);
    	
    	JPanel jpPlace1 = new JPanel();
    	jpPlace1.setLayout(new BorderLayout());
    	jpPlace1.add(new JLabel("��ѯ����һ:"),BorderLayout.NORTH);
    	jpPlace1.add(jp1,BorderLayout.CENTER);
    	
    	JPanel jp2 = new JPanel(); 
    	jp2.add(new JLabel("��������:"));
    	jp2.add(jcbStart);
    	jp2.add(new JLabel("�ִ����:"));
    	jp2.add(jcbArrive);
    	jp2.add(jbPlaceQuery2);
    	
    	JPanel jpPlace2 = new JPanel();
    	jpPlace2.setLayout(new BorderLayout());
    	jpPlace2.add(new JLabel("��ѯ������:"),BorderLayout.NORTH);
    	jpPlace2.add(new JLabel("��ѡ����ʼ���к͵ִ���н��в�ѯ:"),BorderLayout.CENTER);
    	jpPlace2.add(jp2,BorderLayout.SOUTH);
    	
    	
    	JPanel jpDestin = new JPanel();
    	jpDestin.setBorder(new TitledBorder("��Ŀ�ĵز�ѯ"));
    	jpDestin.setLayout(new BorderLayout());
    	jpDestin.add(jpPlace1,BorderLayout.NORTH);
    	jpDestin.add(jpPlace2,BorderLayout.CENTER);
    	
    	this.setLayout(new BorderLayout());
    	this.add(jpFlight,BorderLayout.NORTH);
    	this.add(jpAirFirm,BorderLayout.CENTER);
    	this.add(jpDestin,BorderLayout.SOUTH);
    	
    	jcbFlight.addItemListener(this);
    	
    	jbFlightQuery.addActionListener(this);
    	jbAirFirmQuery.addActionListener(this);
    	jbPlaceQuery1.addActionListener(this);
    	jbPlaceQuery2.addActionListener(this);
    	
    }
    
    public static void updateFlightComboBox(String newFlightNum,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		{
			if (modelFlight.getIndexOf(newFlightNum) == -1)
			   modelFlight.addElement(newFlightNum);
		}  
		else if (insertOrDelete == 2)
		{
			if (modelFlight.getIndexOf(newFlightNum) != -1)
			   modelFlight.removeElement(newFlightNum);			
		}		   
	}
	
	public static void updateAirFirmComboBox(String newPlace,int insertOrDelete)
	{
		if (insertOrDelete == 1)
		{
			if (modelAirFirm.getIndexOf(newPlace) == -1)
			   jcbAirFirm.addItem(newPlace);
		}
		   
		else if (insertOrDelete == 2)
		{
			if (modelAirFirm.getIndexOf(newPlace) != -1)
			   jcbAirFirm.removeItem(newPlace);
		}		   
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
    
    public void itemStateChanged(ItemEvent e)
    {
    	if (e.getSource() == jcbFlight)
    	{
    		flightField.setText( (String)jcbFlight.getSelectedItem() );
    	}
    }
    
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource() == jbFlightQuery)
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
    	
    	else if (e.getSource() == jbAirFirmQuery)
    	{
    		this.airfirm = (String)jcbAirFirm.getSelectedItem();
		
			   //���в�ѯ����
			executeAirFirmQuery();
    	}
    	
    	else if (e.getSource() == jbPlaceQuery1)
    	{
    		   //��ȡҪ��ѯ��Ŀ�ĵء�
    		destination = (String)jcb1.getSelectedItem();
    		destination = destination.trim();
    		
    		   
    		executeDestinQuery(1);
    	}
    	
    	else if (e.getSource() == jbPlaceQuery2)
    	{
    		   //ȡ�ó�����
    		start = (String)jcbStart.getSelectedItem();
    		start = start.trim();
    		   //��Ŀ�ĵ�
    		arrive = (String)jcbArrive.getSelectedItem();
    		arrive = arrive.trim();
    	
    		executeDestinQuery(2);
    	}    	
    }
    
    public void executeFlightQuery()
	{
		   // SQL
		String sqlString = "SELECT DISTINCT * FROM " +
		                   "Plane " +
		                   "WHERE flight=" + "\'" + flightNumber + "\'";
	
	    ResultSet rs = sqlBean.executeSearch(sqlString);		        
	
	    if (rs != null)
	    {
	    	   //��ʽ�Ľ���ַ���
	        String result = "                                                    " + 
			                "����Ų�ѯ"; 
			result += "��ѯ�ĺ����:" + flightNumber + "\n";
			   //������������Ϣ�γ��ض��Ľ���ַ�����    
	        result += formResult(rs);    	
	        
	           //�ڶԻ�������ʾ���
	        showResult(result);
	    }	     
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);
	}
	
	public void executeAirFirmQuery()
	{
		   //SQL
		String sqlString = "SELECT DISTINCT * FROM " +
		                   "Plane " +
		                   "WHERE airfirm=" + "\'" + airfirm + "\'";
	                  
	    ResultSet rs = sqlBean.executeSearch(sqlString);
	     
	    if (rs != null)
        {
        	   
	        String result = "                                                    " + 
			                "���չ�˾��ѯ"; 
			result += "��ѯ�ĺ��չ�˾:" + airfirm + "\n";
			    
	        result += formResult(rs);    	
	        
	       
	        showResult(result);
        }	       
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);
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
        {
        	String result = "                                                    " + 
		                "Ŀ�ĵز�ѯ " + "\n";
		                
        	   //���ֲ�ѯ�Ľ���ַ�����ͬ��
			if (flag == 1)
			   result += "���� " + destination + " �����к���:" + "\n";
			else
			   result += "ʼ����: " + start + "------" + "Ŀ�ĵ�: " + arrive + "\n";
			   
			result += formResult(rs);
			   
			showResult(result);
        }
	       
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);  
    }
	
	public String formResult(ResultSet rs)
	{
		String result = "�����    ���չ�˾          ��ɵص�  �ִ�ص�  ���ʱ��  �ִ�ʱ��  " + 
		                "��ͯƱ��   ����Ʊ��   �ۿ�    ���� " + "\n";
		   
		   //����ȷ���Ƿ�û�м�¼��
		int originLength = result.length();
		
		String time1,time2;
		String childFare,adultFare,discount1,discount2,seat;	
		
		try
		{
			while(rs.next())
			{
				result +=rs.getString("flight") + "    "+rs.getString("airfirm") +"           "+ rs.getString("start") + 
						"         "+rs.getString("destination");
				          
				    //����ӽ������ʱ�򣬾����ǡ�1200������������Ӧ�ð����ĳɡ�12:00����
				time1 = rs.getString("leaveTime");
				time2 = rs.getString("arriveTime");
				    //ʱ�䣨�ַ������������ı�ɱ�׼ʱ��
				time1 = getTime(time1);
				time2 = getTime(time2);
				
				result +="        "+ time1 + "      " + time2  + "      ";
					
				  
				childFare = String.valueOf(rs.getFloat("childFare"));
				adultFare = String.valueOf(rs.getFloat("adultFare"));
				discount1 = String.valueOf(rs.getFloat("discount1"));
				discount2 = String.valueOf(rs.getFloat("discount2"));
				seat = String.valueOf(rs.getInt("seat"));
				
			
				while(childFare.length() != 11)
				   childFare += "  ";
				while(adultFare.length() != 11)
				   adultFare += "  ";
				while(discount1.length() != 8)
				   discount1 += "  ";					
				   
				result += childFare + adultFare + discount1 +
				          rs.getString("week1");
				result += "\n";
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		if (result.length() == originLength)
		{
			result += "                                                    " +
			          "�Բ���,�Ҳ�������Ҫ�ĺ�����Ϣ!" + "\n";
		}	
		
		return result;
	}
	
	
    public void showResult(String result)
    {
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
}