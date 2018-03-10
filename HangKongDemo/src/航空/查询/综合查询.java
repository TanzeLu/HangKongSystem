
package ����.��ѯ;

import ����.����.*;
import ����.����.SqlBean;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;

public class �ۺϲ�ѯ extends JPanel implements ActionListener,ItemListener
{
	   //�����������ݿⲢִ��SQL������bean
	static SqlBean sqlBean = new SqlBean();
	
	   //*********************************************************************
	   //ģ����Ͽ��λ��
    private static DefaultComboBoxModel modelPlace = new DefaultComboBoxModel();   
       //���ں��չ�˾�����ģ��
    private static DefaultComboBoxModel modelAirFirm = new DefaultComboBoxModel();
       //��������Ŀ
    private static Object[] year = {"2017","2018"};
       //����Ͽ�
 	private static Object[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
 	   //�����Ͽ�
 	private static Object[] day = {"1","2","3","4","5","6","7","8","9","10","11","12",
 	                               "13","14","15","16","17","18","19","20","21","22",
 	                               "23","24","25","26","27","28","29","30","31"}; 	                        
 	                         	
 	private  static JComboBox jcbStart = new JComboBox(),jcbFirstArrive = new JComboBox(),
 	                          jcbArrive = new JComboBox(),jcbAirFirm = new JComboBox(modelAirFirm),
 	                          jcbYear1 = new JComboBox(year),jcbYear2 = new JComboBox(year),
 	                          jcbMonth2 = new JComboBox(month),jcbMonth1 = new JComboBox(month),
 	                          jcbDay1 = new JComboBox(day),jcbDay2 = new JComboBox(day);
 	   //*********************************************************************
 	                   
 	private JRadioButton jrbSingle = new JRadioButton("����",true),
 	                     jrbDouble = new JRadioButton("����",false),
 	                     jrbMutiple = new JRadioButton("����",false);
 	   
 	   //����Ӧ�ö�̬�ظı��ǩ�е����ݣ���������Ӧ�ð�jlabelsΪ��Ա�������������ǾͿ�����������ÿһ�������иı����ǵ�����
 	private JLabel jlStart,jlFirstArrive,jlArrive,
 	               jlTime1,jlTime2,jlAirFirm,
 	               jlReplaceArrive,jlReplaceTime;
 	                   
       //����Ӧ�ö�̬��ɾ����������������С�������ѡ��Ĳ�ѯģʽ����������Ӧ�ð�JPanels��Ϊ��Ա������
 	   //�������ǾͿ�����������ÿ��������ɾ�����������е����
    private JPanel jpFirstArriveBox,jpTime2Box,jpReplaceArrive,jpArrive1,jpReplaceTime,jpTime2; 
       
    private JButton jbQuery = new JButton("��ѯ");
    
       //������Ŀѡ���ÿ����Ͽ�
    private String start,firstArrive,arrive,leaveWeek,leaveWeek2,backWeek,airFirm,
                   leaveYear,leaveMonth,leaveDay,backYear,backMonth,backDay,
                   leaveYear2,leaveMonth2,leaveDay2;
      
       //���ý���Ĺ��췽��
    public �ۺϲ�ѯ()
    {   
        jcbAirFirm.addItem("����");
        jcbAirFirm.setSelectedItem("����");
    
           //��ʱ����Ͽ���ʾ��ǰʱ��ĳ���
    	setDisplayPresentTime();
    	
    	   //*********************************************************************
    	
    	JPanel jp1 = new JPanel();
    	jp1.add(jrbSingle);
    	JPanel jp2 = new JPanel();
    	jp2.add(jrbDouble);
    	JPanel jp3 = new JPanel();
    	jp3.add(jrbMutiple);    	
    	
    	JPanel jpRadioButton = new JPanel();
    	jpRadioButton.setLayout(new GridLayout(5,1)); 
    	jpRadioButton.add(new JLabel("       "));   	
    	jpRadioButton.add(jp1);
    	jpRadioButton.add(jp2);
    	jpRadioButton.add(jp3);
    	jpRadioButton.add(new JLabel("       "));
    	
    	ButtonGroup bg = new ButtonGroup();
    	bg.add(jrbSingle);
    	bg.add(jrbDouble);
    	bg.add(jrbMutiple);
    	
    	   //*********************************************************************
    	
    	JPanel jpStart = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpStart.add(jlStart = new JLabel("        ��������:"));
    	
    	JPanel jpFirstArrive = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpFirstArrive.add(jlFirstArrive = new JLabel("                "));
    	
    	JPanel jpArrive = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpArrive.add(jlArrive = new JLabel("        �������:"));
    	
    	JPanel jpTime1Tip = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpTime1Tip.add(jlTime1 = new JLabel("        ��������:"));
    	
    	JPanel jpTime2Tip = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpTime2Tip.add(jlTime2 = new JLabel("              "));
    	
    	JPanel jpAirFirm = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    	jpAirFirm.add(jlAirFirm = new JLabel("       ���չ�˾:"));
    	
    	JPanel jpLabels = new JPanel();
    	jpLabels.setLayout(new GridLayout(7,1));    	
    	jpLabels.add(jpStart);
    	jpLabels.add(jpFirstArrive);
    	jpLabels.add(jpArrive);
    	jpLabels.add(jpTime1Tip);
    	jpLabels.add(jpTime2Tip);
    	jpLabels.add(jpAirFirm);
    	jpLabels.add(new JLabel("            "));
    	
    	   //*********************************************************************
    	            
        JPanel jpStartBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpStartBox.add(jcbStart);
        
           //***********************
           //��Ͽ�jcbfirstarrive��Ӧ��̬ɾ������ӵ�jpfirstarrivebox��
           //��ˣ�����һ��������һ���յı�ǩ�������滻��������Ͽ�jcbfirstarrive���������ǲ���Ҫ��ʱ
        jpReplaceArrive = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpReplaceArrive.add(jlReplaceArrive = new JLabel("            "));
    	
    	jpArrive1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpArrive1.add(jcbFirstArrive);
    	
        jpFirstArriveBox = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    	jpFirstArriveBox.add(jpReplaceArrive);
    	   //***********************    	   
    	  
    	JPanel jpArriveBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpArriveBox.add(jcbArrive);
    	
    	JPanel jpTime1Box = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpTime1Box.add(jcbYear1);
    	jpTime1Box.add(jcbMonth1);
    	jpTime1Box.add(jcbDay1);
    	
    	   //***********************
    	jpReplaceTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpReplaceTime.add(jlReplaceTime = new JLabel("            ")); 
    	
    	jpTime2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpTime2.add(jcbYear2);
    	jpTime2.add(jcbMonth2);
    	jpTime2.add(jcbDay2);
    	
    	jpTime2Box = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    	jpTime2Box.add(jpReplaceTime); 
    	  	//***********************    	  	 	
    	    
    	JPanel jpAirFirmBox = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpAirFirmBox.add(jcbAirFirm);
    	
    	JPanel jpButton = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	jpButton.add(jbQuery);
    	
    	JPanel jpComboBox = new JPanel();
    	jpComboBox.setLayout(new GridLayout(7,1));    
    	jpComboBox.add(jpStartBox);
    	jpComboBox.add(jpFirstArriveBox);
    	jpComboBox.add(jpArriveBox);
    	jpComboBox.add(jpTime1Box);
    	jpComboBox.add(jpTime2Box);
    	jpComboBox.add(jpAirFirmBox);
    	jpComboBox.add(jpButton);
    	
    	    //*********************************************************************
        
        JPanel jpQuery = new JPanel();
        jpQuery.setLayout(new BorderLayout());
        jpQuery.add(jpLabels,BorderLayout.WEST);
        jpQuery.add(jpComboBox,BorderLayout.CENTER);
        
        JPanel jpDown = new JPanel();
        jpDown.setLayout(new BorderLayout());
        jpDown.add(jpRadioButton,BorderLayout.WEST);
        jpDown.add(jpQuery,BorderLayout.CENTER);
        
        JLabel jlTitle = new JLabel("�ۺϲ�ѯ");
        jlTitle.setHorizontalAlignment(JLabel.CENTER);
        jlTitle.setFont(new Font("Tims",Font.BOLD,23));
        this.setLayout(new BorderLayout());
        this.add(jlTitle,BorderLayout.NORTH);
        this.add(jpDown,BorderLayout.CENTER);;
        
           //�������������ѡ��ť 
           //radiolistener��һ���ڲ��࣬��������
        jrbSingle.addActionListener(new RadioListener());
        jrbDouble.addActionListener(new RadioListener());
        jrbMutiple.addActionListener(new RadioListener());
        
           //��Ӽ�������ʱ����Ͽ�
        jcbYear1.addItemListener(this);
        jcbYear2.addItemListener(this);
        jcbMonth1.addItemListener(this);
        jcbMonth2.addItemListener(this);
        
           //����������ӵ���ѯ��ť
        jbQuery.addActionListener(this);    	
    }
    
    public static void updatePlaceComboBox(String newPlace,int insertOrDelete)
    {
    	if (insertOrDelete == 1)
    	{
    		if (modelPlace.getIndexOf(newPlace) == -1)
    		{
	    		modelPlace.addElement(newPlace);
	    		jcbStart.addItem(newPlace);	
	    		jcbFirstArrive.addItem(newPlace);	
	    		jcbArrive.addItem(newPlace);	
    		}
	    	
    	}
    	else if (insertOrDelete == 2)
    	{
    		if (modelPlace.getIndexOf(newPlace) != -1)
    		{
	    		modelPlace.removeElement(newPlace);
	    		jcbStart.removeItem(newPlace);
	    		jcbFirstArrive.removeItem(newPlace);
	    		jcbArrive.removeItem(newPlace);	
    		}    		
    	}   	
    }
    
    public static void updateAirFirmComboBox(String newAirFirm,int insertOrDelete)
    {
    	if (insertOrDelete == 1)
    	{
    		if (modelAirFirm.getIndexOf(newAirFirm) == -1)
    		   jcbAirFirm.addItem(newAirFirm);
    	}
    	   
    	else if (insertOrDelete == 2)
    	{
    		if (modelAirFirm.getIndexOf(newAirFirm) != -1)
	    	   jcbAirFirm.removeItem(newAirFirm);
    	}
    	   
    }
    
       //��ʱ�����ʾ��ǰʱ��ķ����������һ�β���ʱ
    public void setDisplayPresentTime()
    {
    	   //��ȡ���ڻ�ȡ��ǰʱ��
    	Calendar cal = Calendar.getInstance();
    	
    	   //��Ϊ������������(java.util.Date--java.sql.Date)
    	   //��������Ӧ��Ϊת��Ϊָ����ʽ��ָ������
    	cal.setTime(new java.util.Date());
    	
    	   //�õ����ڣ��꣬�£���
    	String year = String.valueOf(cal.get(Calendar.YEAR));
    	String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
    	String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    	
    	   //��ʱ����Ͽ���ʾ��ǰʱ��
    	jcbYear1.setSelectedItem(year);
    	jcbYear2.setSelectedItem(year);
    	jcbMonth1.setSelectedItem(month);
    	jcbMonth2.setSelectedItem(month);
    	
    	   //����Ӧ�øı���Ŀ����ComboBox�Ķ�̬��������ݺ��·�
    	updateDay(year,month,jcbDay1);
    	updateDay(year,month,jcbDay2);    	
    	
    	jcbDay1.setSelectedItem(day);
    	jcbDay2.setSelectedItem(day);    	
    }
    
       //�ı�����
    private void updateDay(String year,String month,JComboBox jcb)
    {
    	   //��30�����4,6,9,11
    	if (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11"))    	   
    	{
    		   //jcb.getitemcount��������31��ζ����31�죬����ʵ��ֻ��30�죬����Ҫ��Combox���Ƴ�31��
    		if (jcb.getItemCount() == 31)
    		   jcb.removeItem("31");	
    		else if(jcb.getItemCount() == 29)    		
    		   jcb.addItem("30");    	
    		else if (jcb.getItemCount() == 28)
    		{
    			jcb.addItem("29");
    			jcb.addItem("30");
    		}    	
    	}
    	   //2����28���29��
    	else if (month.equals("2"))
    	{
    		int years = Integer.parseInt(year);
    		
    		   //����������
    		if ( (years % 400 == 0) || (years %4 == 0 && years % 100 != 0))
    		{
    			if (jcb.getItemCount() == 31)
    			{
    				jcb.removeItem("30");
    			    jcb.removeItem("31");    		    			
    			}
    			else if (jcb.getItemCount() == 30)
    			{
    				jcb.removeItem("30");
    			}
    			else if (jcb.getItemCount() == 28)
    			{
    				jcb.addItem("29");
    			}
    		}
    		   //���겻������
    		else 
    		{
    			if (jcb.getItemCount() == 29)
    			{
    				jcb.removeItem("29");
    			}
    			else if (jcb.getItemCount() == 30)
    			{
    				jcb.removeItem("29");
    				jcb.removeItem("30");
    			}
    			else if (jcb.getItemCount() == 31)
    			{
    				jcb.removeItem("29");
    				jcb.removeItem("30");
    				jcb.removeItem("31");
    			}
    		}
    	}
    	   //��ʣ�µ��·�����31��   	
    	else 
    	{
    		if (jcb.getItemCount() == 28)
    		{
    		    jcb.addItem("29");
    		    jcb.addItem("30");
    		    jcb.addItem("31");	
    		}
    		else if (jcb.getItemCount() == 29)
    		{
    			jcb.addItem("30");
    			jcb.addItem("31");    			
    		}
    		else if (jcb.getItemCount() == 30)
    		{
    			jcb.addItem("31");
    		}    		
    	}
    }
    
       //ʱ�����Ͽ�ļ�ⷽ��
    public void itemStateChanged(ItemEvent e)
    {
    	   //������ѡ������¸ı������
    	if (e.getSource() == jcbYear1 || e.getSource() == jcbMonth1)
    	{
    		String year = (String)jcbYear1.getSelectedItem();
    		String month = (String)jcbMonth1.getSelectedItem();    		
    		   
    		updateDay(year,month,jcbDay1);
    	}
    	   //The same reason as the above one
    	if (e.getSource() == jcbYear2 || e.getSource() == jcbMonth2)
    	{
    		String year = (String)jcbYear2.getSelectedItem();
    		String month = (String)jcbMonth2.getSelectedItem();
    		
    		updateDay(year,month,jcbDay2);
    	}
    }
    
       //����JRadioButton�����������ڲ���
    class RadioListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		   //������ѡ��Ĳ�ѯģʽ ��̬��ɾ�����������������
               
               //�����ѡ�񵥲�ѯģʽ  		  
    		if (jrbSingle.isSelected())
    		{
    			jlFirstArrive.setText("            ");
    			   //��ȥ��jpfirstarrivebox���Ŀǰ�����
    			jpFirstArriveBox.removeAll(); 
    			   //��ָ�������ӵ�ָ����ѯģʽ�е������
    			jpFirstArriveBox.add(jpReplaceArrive);
    			   //ʹ�÷���repaint()�����������ӵ���岢����������ʾ
    			jpFirstArriveBox.repaint();
    			
    			jlArrive.setText("    �������:");
    			jlTime1.setText("    ��������:");
    			jlTime2.setText("            ");
    			
    			jpTime2Box.removeAll();
    			jpTime2Box.add(jpReplaceTime); 
    			jpTime2Box.repaint();  			
    		}
    		   //���ѡ�񷵳�
    		else if(jrbDouble.isSelected())
    		{
    			jlFirstArrive.setText("            ");
    			jpFirstArriveBox.removeAll();
    			jpFirstArriveBox.add(jpReplaceArrive);
    			jpFirstArriveBox.repaint();
    			
    			jlArrive.setText("    �������:");    			
    			jlTime1.setText("    ��������:");
    			jlTime2.setText("    ��������:");
    			
    			jpTime2Box.removeAll();
    			jpTime2Box.add(jpTime2);
    			jpTime2Box.repaint();
    		}
    		   //���ѡ���·��ѯģʽ
    		else if (jrbMutiple.isSelected())
    		{
    			jlFirstArrive.setText("��һ�������:");
    			jpFirstArriveBox.removeAll();
    			jpFirstArriveBox.add(jpArrive1);
    			jpFirstArriveBox.repaint();
    			
    			jlArrive.setText("�ڶ��������:");
    			jlTime1.setText("��һ��������:");
    			jlTime2.setText("�ڶ���������:");
    			
    			jpTime2Box.removeAll();
    			jpTime2Box.add(jpTime2);
    			jpTime2Box.repaint();
    		}    		
    	}
    }
    
       //��jbquery����ť��ⷽ��
    public void actionPerformed(ActionEvent e)
    {
    	   //������ѡ��Ĳ�ѯģʽ�������ǲ�ͬ�ġ�
    	   
    	   //�����ѡ�񵥲�ѯģʽ
        if (jrbSingle.isSelected())
        {
        	   //ȡ�ó�����
        	start = (String)jcbStart.getSelectedItem();	
        	start = start.trim();
        	
        	   //���Ŀ�ĵ�
        	arrive = (String)jcbArrive.getSelectedItem();
        	arrive = arrive.trim();
        	
        	   //�õ�����ʱ��
        	leaveYear = (String)jcbYear1.getSelectedItem();
        	leaveMonth = (String)jcbMonth1.getSelectedItem();
        	leaveDay = (String)jcbDay1.getSelectedItem();
        	
        	   //�ж���ѡ���ʱ���Ƿ���Ч
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay))
        	{
        		   //���ʱ����Ч�����û���ʾ������Ϣ
        		JOptionPane.showMessageDialog(null,"�Ѿ����˳���ʱ��,�������趨����ѯ",
        		                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	   //����timetoweek��������ָ����һ�����գ������"2004,12,25" ת������(Saturday)
        	leaveWeek = timeToWeek(leaveYear,leaveMonth,leaveDay);
        	
        	   //�������Ҫ�ĺ��๫˾
        	airFirm = (String)jcbAirFirm.getSelectedItem();
        	airFirm = airFirm.trim();
        	
        	   //ִ�в�ѯ����
        	executeSingleQuery();
        }
           //���ѡ�񷵳�ģʽ
        else if(jrbDouble.isSelected())
        {
        	start = (String)jcbStart.getSelectedItem();
        	start = start.trim();        	   
        	
        	arrive = (String)jcbArrive.getSelectedItem();
        	arrive = arrive.trim();
        	
        	   //��ó���ʱ��
        	leaveYear = (String)jcbYear1.getSelectedItem();
        	leaveMonth = (String)jcbMonth1.getSelectedItem();
        	leaveDay = (String)jcbDay1.getSelectedItem();
        	   //��õ���ʱ��
        	backYear = (String)jcbYear2.getSelectedItem();
        	backMonth = (String)jcbMonth2.getSelectedItem();
            backDay = (String)jcbDay2.getSelectedItem();
        	
        	   //�ж���ѡ���ʱ���Ƿ���Ч
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay))
        	{
        		JOptionPane.showMessageDialog(null,"�Ѿ����˳���ʱ��,�������趨����ѯ",
        		                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay,backYear,backMonth,backDay))
        	{
        		JOptionPane.showMessageDialog(null,"�������ڲ��ܱȳ���������,�������趨����ѯ",
        		                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	leaveWeek = timeToWeek(leaveYear,leaveMonth,leaveDay);
        	backWeek = timeToWeek(backYear,backMonth,backDay);
        	
        	airFirm = (String)jcbAirFirm.getSelectedItem();
        	airFirm = airFirm.trim();
        	
        	executeDoubleQuery();
        }
           //���ѡ���·��ѯģʽ
        else if (jrbMutiple.isSelected())
        {
        	start = (String)jcbStart.getSelectedItem();
        	start = start.trim();
        	   //�����;Ŀ�ĵ�
        	firstArrive = (String)jcbFirstArrive.getSelectedItem();
        	firstArrive = firstArrive.trim();
        	   //���Ŀ��Ŀ�ĵ�
        	arrive = (String)jcbArrive.getSelectedItem();
        	arrive = arrive.trim();
        	   
        	   //��ó������еĳ���ʱ��
        	leaveYear = (String)jcbYear1.getSelectedItem();
        	leaveMonth = (String)jcbMonth1.getSelectedItem();
        	leaveDay = (String)jcbDay1.getSelectedItem();
        	   //����м���еĳ���ʱ��
        	leaveYear2 = (String)jcbYear2.getSelectedItem();
        	leaveMonth2 = (String)jcbMonth2.getSelectedItem();
        	leaveDay2 = (String)jcbDay2.getSelectedItem();
        	
        	   //�ж���ѡ���ʱ���Ƿ���Ч
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay))
        	{
        		JOptionPane.showMessageDialog(null,"�Ѿ����˳���ʱ��,�������趨����ѯ",
        		                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	if (!isTimeValid(leaveYear,leaveMonth,leaveDay,leaveYear2,leaveMonth2,leaveDay2))
        	{
        		JOptionPane.showMessageDialog(null,"�������ڲ��ܱȳ���������,�������趨����ѯ",
        		                              "������Ϣ",JOptionPane.ERROR_MESSAGE);
        		return;
        	}
        	
        	leaveWeek = timeToWeek(leaveYear,leaveMonth,leaveDay);
        	leaveWeek2 = timeToWeek(leaveYear2,leaveMonth2,leaveDay2);
        	
        	airFirm = (String)jcbAirFirm.getSelectedItem();
        	airFirm = airFirm.trim();
        	
        	executeMutipleQuery();
        }        	
    }  
    
       //�����ж���ѡ���ʱ���Ƿ���Ч�ķ����������ѡ���ʱ��������磬�ǾͲ�����Ч�ġ�
    private boolean isTimeValid(String year,String month,String day)
    {
    	int y = Integer.parseInt(year);
    	int m = Integer.parseInt(month);
    	int d = Integer.parseInt(day);
    	
    	   //�õ����ڵ�ʱ��
    	Calendar cal = Calendar.getInstance();
    	 
    	cal.setTime(new java.util.Date());
    	
    	int py = cal.get(Calendar.YEAR);
    	int pm = cal.get(Calendar.MONTH) + 1;
    	int pd = cal.get(Calendar.DAY_OF_MONTH);
    	
    	if (y == py)
    	{
    		if (m < pm)
    		   return false;
    		else if(d < pd)
    		   return false;
    	}
    	
    	return true;
    }  
    
       //�÷���Ҳ�����ж�ʱ�䣬��ѡ�������Ч�Ļ���Ч��
    private boolean isTimeValid(String year1,String month1,String day1,
                                String year2,String month2,String day2)
    {
    	int y1 = Integer.parseInt(year1);
    	int m1 = Integer.parseInt(month1);
    	int d1 = Integer.parseInt(day1);
    	
    	int y2 = Integer.parseInt(year2);
    	int m2 = Integer.parseInt(month2);
    	int d2 = Integer.parseInt(day2);
    	
    	if (y1 < y2)
    	   return true;
    	else if (y1 == y2)
    	{
    		if (m1 < m2)
    		   return true;
    		else if (m1 == m2)
    		{
    			if (d1 < d2)
    			   return true;
    			else if (d1 == d2)
    			   return true;
    			else 
    			   return false;
    		}
    		else 
    		   return false;
    	}
    	else 
    	   return false;
    }    
    
       //������ָ�����ڸ�Ϊ�����յķ�����
    private String timeToWeek(String year,String month,String day)
    {
       int sum=0;
       int y = Integer.parseInt(year);
       int m = Integer.parseInt(month);
       int d = Integer.parseInt(day);
              
       int[] dayOfMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};  
      
          //��������ָ��ÿ�����һ�������ڼ�
       int firstDayOfYear = firstDay(y);
            
       for(int i = 1;i < m;i++)
        {
           sum=sum+dayOfMonth[i];
        }
      
       sum = sum+(d-1)+firstDayOfYear;

          //����·��Ƕ��£�ָ����������꣬������Ӧ��1

       if( (m >= 2) && ((y%4 == 0 && y%100 != 0) || (y%400 == 0)))
          sum ++;
          
       int week = 0;  
          //ָ���յĹ������ǣ�
       int x = sum % 7;       
       switch(x)
         {
          case 1:
             week = 1;
             break;            
          case 2:
             week = 2;
             break;
          case 3:
             week = 3;
             break;
          case 4:
             week = 4;
             break;
          case 5:
             week = 5;
             break;
          case 6:
             week = 6;
             break;
          case 0:
             week = 7;
             break;
         } 
         
       return String.valueOf(week);                  	    
    }
    
       //�÷���������������ָ��ÿ�����һ�������ڼ�
    private int firstDay(int year)
    {
    	int a,b;
    	
	    if(year <= 2000)
	    {
	        a=2000-year;
	        b=6-(a+a/4-a/100+a/400)%7;
	        return b;
	    }
	    else 
	    {
	        a=year-2000;
	        b=(a+1+(a-1)/4-(a-1)/100+(a-1)/400)%7+6;
	        return b%7;
	    }
    }
    
       //�����ѯģʽ�Ĳ�ѯ����
    public void executeSingleQuery()
    {
    	String sqlString = formSQLString(start,arrive);    	   
    	         
        ResultSet rs = sqlBean.executeSearch(sqlString);
        
        if (rs != null)
	    {
		       //����ַ���
	        String result = "                                                    " + 
			                "�ۺϲ�ѯ"; 
			   //������������Ϣ�γ��ض��Ľ���ַ�����     
	        result += formResult(rs,leaveYear,leaveMonth,leaveDay,leaveWeek,start,arrive);    	
	        
	           //�ڶԻ�������ʾ���
	        showResult(result);
	    }	       
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);           
    }
    

    public void executeDoubleQuery()
    {
    	  
    	String sqlString1 = formSQLString(start,arrive);        
        ResultSet rs1 = sqlBean.executeSearch(sqlString1);
        
        String sqlString2 = formSQLString(arrive,start);    	         
        ResultSet rs2 = sqlBean.executeSearch(sqlString2);
        
        if ( (rs1 != null) || (rs2 != null))
	    {
	    	String result = "                                                  " + 
		                    "�ۺϲ�ѯ ";
			   //ʽ���ɽ���ַ���              
	        result += formDoubleResult(rs1,rs2);    	
	        
	        showResult(result);
	    }
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);       
    }
    
       //�෽ʽ��ѯģʽ��ѯ
    public void executeMutipleQuery()
    {
    	   //����ͷ��صķ��������ѯ���ݿ����β����ҵ�
    	   //������Ϣ�ӿ�ʼ��Ŀ�ĵس��� 
    	   //����;�����յ�
    	String sqlString1 = formSQLString(start,firstArrive);  	         
        ResultSet rs1 = sqlBean.executeSearch(sqlString1);
        
        String sqlString2 = formSQLString(firstArrive,arrive); 	         
        ResultSet rs2 = sqlBean.executeSearch(sqlString2);
        
        if ((rs1 != null) || (rs2 != null))
	    {
	    	String result = "                                                               " + 
		                    "�ۺϲ�ѯ                                                 ";
			   //�γɶ�·��ѯģʽ�Ľ���ַ�����
	        result += formMutipleResult(rs1,rs2);    	
	        
	        showResult(result);
	    }
	    else 
	       JOptionPane.showMessageDialog(null,"û�����������ݿ�!",
	                                    "������Ϣ",JOptionPane.ERROR_MESSAGE);        
    }
    
    public String formSQLString(String begin,String end)
    {
    	String sqlString = "SELECT DISTINCT * FROM " + "Plane " +    	
						   "WHERE start=" + "\'" + begin + "\'" + " AND " +
			               "destination=" + "\'" + end + "\'";			           	
    	                
    	if (!airFirm.equals("����"))
    	   sqlString += " AND " + "airFirm=" + "\'" + airFirm + "\'";
    	   
    	return sqlString;
    }
    
       //�ӽ�����л�ȡ����ַ���
    public String formResult(ResultSet rs,String year,String month,String day,
                                          String week,String begin,String end)
    {		
		String result = "";
		   //��Ӣ�﹤���ո�Ϊ���Ĺ�����
		String weekDay = dayOfWeek(week);
		
		result += "\n" + "����:" + year + "��" + month + "��" + day + "��" +	
		          "(����" + weekDay + ")  " + begin + "----" + end + "\n"; 
		                  
		result += "�����    ���չ�˾          ��ɵص�  �ִ�ص�  ���ʱ��  �ִ�ʱ��  " + 
                "��ͯƱ��   ����Ʊ��   �ۿ�    ���� " + "\n";
		     
		   //����ȷ���Ƿ�û�м�¼��         
		int originLength = result.length();
		
		String time1,time2;
		String childFare,adultFare,discount1,discount2,seat;
		
		try
		{	
		    String tempResult = "";
		    String tempWeek;
			while(rs.next())
			{			
				tempResult = rs.getString("flight") + "    "+ rs.getString("airfirm") +"           "+ rs.getString("start") + 
						"         "+rs.getString("destination");
				             
				  
				time1 = rs.getString("leaveTime");
				time2 = rs.getString("arriveTime");
				  
				time1 = getTime(time1);
				time2 = getTime(time2);
				
				tempResult += "        "+time1 + "        "+ time2  + "      ";
				
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
				   
				tempWeek = rs.getString("week1");
		        tempResult += childFare + adultFare + discount1 +
				              tempWeek;
				tempResult += "\n";
				
				   //�������ʱ�̱�����û�ָ�������ڣ�
				   //��¼����ֻ��һ������
				   //������ǣ��ǲ��ǽ��
				if (tempWeek.indexOf(week) != -1)
				   result += tempResult;							
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		   //��ζ��û�з��ּ�¼ ���ԣ����û�����Ϣ���Ҳ�����ص���Ϣ
		if (result.length() == originLength)
		{
			result += "                                                    " +
			          "�Բ���,�Ҳ�������Ҫ�ĺ�����Ϣ!" + "\n";
		}	
		
		return result;
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
	 
      
    private String dayOfWeek(String weekNum)
    {
    	String week = "";
    	int num = Integer.parseInt(weekNum);
    	
		switch(num)
		{
			case 1:
             week = "һ";
             break;            
          case 2:
             week = "��";
             break;
          case 3:
             week = "��";
             break;
          case 4:
             week = "��";
             break;
          case 5:
             week = "��";
             break;
          case 6:
             week = "��";
             break;
          case 7:
             week = "��";
             break;
		}
		
		return week;
    }   
    
      
    public String formDoubleResult(ResultSet rs1,ResultSet rs2)
    {
    	String result1 = formResult(rs1,leaveYear,leaveMonth,leaveDay,leaveWeek,start,arrive);
    	String result2 = formResult(rs2,backYear,backMonth,backDay,backWeek,arrive,start);
    	
    	String result = result1 + result2;
    	return result;
    }
    
      
    public String formMutipleResult(ResultSet rs1,ResultSet rs2)
    {
    	String result1 = formResult(rs1,leaveYear,leaveMonth,leaveDay,leaveWeek,start,firstArrive);
    	String result2 = formResult(rs2,leaveYear2,leaveMonth2,leaveDay2,leaveWeek2,firstArrive,arrive);
    	
    	String result = result1 + result2;
    	return result;
    }
    
       //�ڶԻ�������ʾ���
    public void showResult(String result)
    {
    	JOptionPane.showMessageDialog(null,result,"��ѯ���",JOptionPane.PLAIN_MESSAGE);
    }         
}