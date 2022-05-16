package net.codejava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class CRUD {
	public static void main(String[] args) {
		CRUD object=new CRUD();
		//object.create_data("5", "ash","pra");
		//object.read_data("5");
		//object.update_data("5","2","ashritha", "prakash");
		object.delete_data("5");
	}
	 
	public void read_data(String id) {
    	Dbconnection objdbconnnection=new Dbconnection();
    	Connection connection =objdbconnnection.get_connection();
    	
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		String sql = "select * from customer where id= ?" ;
    		ps=connection.prepareStatement(sql);
    		ps.setString(1, id);
    		
    		System.out.println(ps);
    		rs=ps.executeQuery();
    		
    		while(rs.next()) {
    			System.out.println("id- "+rs.getString("id"));
    			System.out.println("firstname- "+rs.getString("firstname"));
    			System.out.println("lastname- "+rs.getString("lastname"));
    		}
    	}catch(Exception e) {
    		System.out.println(e);
    	}}
	
    	public void update_data(String id,String newid,String firstname,String lastname) {
        	Dbconnection objdbconnnection=new Dbconnection();
        	Connection connection =objdbconnnection.get_connection();
        	
        	PreparedStatement ps=null;
        	try {
        		String sql = "update customer set id=?,set newid=?,firstname=?,lastname=?";
        		ps=connection.prepareStatement(sql);
        		ps.setString(1, id);
        		ps.setString(2, newid);
        		ps.setString(3, firstname);
        		ps.setString(4, lastname);
        		
        		System.out.println(ps);
        		ps.executeUpdate();
        	}catch(Exception e) {
        		System.out.println(e);
        	}}
    	public void delete_data(String id) {
        	Dbconnection objdbconnnection=new Dbconnection();
        	Connection connection =objdbconnnection.get_connection();
        	
        	PreparedStatement ps=null;
        	try {
        		String sql = "delete from customer where id=?";
        		
        		ps=connection.prepareStatement(sql);
        		ps.setString(1, id);
        		
        		System.out.println(ps);
        		ps.executeUpdate();
        	}catch(Exception e) {
        		System.out.println(e);
        	}}
	public void create_data(String id,String firstname,String lastname) {
    	Dbconnection objdbconnnection=new Dbconnection();
    	Connection connection =objdbconnnection.get_connection();
    	
    	PreparedStatement ps=null;
    	try {
    		String sql = "insert into customer"
    				+ " (id, firstname,lastname)" + " values (?, ?, ?)";
    		ps=connection.prepareStatement(sql);
    		ps.setString(1, id);
    		ps.setString(2, firstname);
    		ps.setString(3, lastname);
    		System.out.println(ps);
    		ps.executeUpdate();
    	}catch(Exception e) {
    		System.out.println(e);
    	}
    }
}
