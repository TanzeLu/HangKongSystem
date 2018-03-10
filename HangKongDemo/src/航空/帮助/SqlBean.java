 package ����.����;

import java.io.*;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class SqlBean {
	//������Ҫ�Ķ���
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection ct=null;
	String dirverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private final String URL = "jdbc:sqlserver://localhost:1433;database=HangKongDB";
	 private final String user = "sa";
	 private final String password = "122026";
	//���캯������ʼ��ct���ݿ�����
	 public SqlBean()
		{
			try
			{
				Class.forName(dirverName);
			}
			catch(ClassNotFoundException e)
			{
			}
		}
		   
		   //select
		public ResultSet executeSearch(String sql)
		{
			rs=null;
			try
			{
				
				ct = DriverManager.getConnection(URL,user,password);
				
				Statement stmt=ct.createStatement();
				
				if(stmt==null)
				{
					System.out.println("stmtΪ��");
				}
				
				rs=stmt.executeQuery(sql);
				
				
				if(ct==null)
				{
					System.out.println("excuteQuery11,�������ݿ������");
					return null;
				}
			}
			catch(SQLException ex)
			{		
				System.out.println("excuteQuery,�������ݿ������");
			}
		
			if(rs==null)
			{
				System.out.println("excuteQuery22,�������ݿ������");
				return null;
			}
			
			return rs;
		}
		   
		   //insert
		public int executeInsert(String sql)
		{
			int num=0;
			try
			{
				ct = DriverManager.getConnection(URL,user,password);
				Statement stmt=ct.createStatement();
				num=stmt.executeUpdate(sql);
			}
			catch(SQLException ex)
			{
				System.out.println("excuteInsert,�������ݿ������");
			}
			
			return num;
		} 
		   
		   //delete
		public int executeDelete(String sql)
		{
			int num=0;
			try
			{
				ct = DriverManager.getConnection(URL,user,password);
				Statement stmt=ct.createStatement();
				num=stmt.executeUpdate(sql);
			}
			catch(SQLException e)
			{
				System.out.println("excuteDelete,�������ݿ������");
			}
		
			return num;
		}
		
		   //update
		public int executeUpdate(String sql)
		{
			int num=0;
			try
			{
				ct = DriverManager.getConnection(URL,user,password);
				Statement stmt=ct.createStatement();
				num=stmt.executeUpdate(sql);
			}
			catch(SQLException e)
			{
				System.out.println("excuteUpdate,�������ݿ������");
			}
		
			return num;
		}
		   
		   //close
		public void CloseDataBase()
		{
			try
			{
				ct.close();
			}
			catch(Exception e)
			{
			}
		}
}