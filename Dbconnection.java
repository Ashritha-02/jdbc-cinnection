package railways;


import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnection{
	
	public static void main(String[] args) { 
		Dbconnection objdbconnnection=new Dbconnection();
		System.out.println("objdbconnnection.get connection()");
	}
	public Connection get_connection() {
	Connection connection=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/railway", "root", "ashritha");
	}
	catch(Exception e) {
		System.out.println(e);
	}
	return connection;
}
}





























/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
     private String jdbcURL="jdbc:mysql://localhost:3306/demo?useSSl=false";
     private String jdbcUsername="root";
     private String jdbcPassword="ashritha";
	
     private static final String INSERT="INSERT INTO customer"+"(firstname,lastname) VALUES"+"(?,?)";
     
     private static final String SELECTBYID="SELECT id,firstname FROM customer where id=?";
     
     private static final String SELECTALL="SELECT * FROM namdb.customers";
     
     private static final String DELETE  = "DELETE * FROM where id=?";
     
     private static final String UPDATE  ="UPDATE customer set firstname= ?,lastname=? where id=?";
     
     protected Connection getConnection() {
    	 Connection connection=null;
    	 
    		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} 
    		 catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
    		 
    		return connection;
    	 } 
     
     public void insertUser(User customer) {
    	 try(Connection connection =getConnection();
    			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
                 preparedStatement.setString(1, customer.getFirstname());
                 preparedStatement.setString(1, customer.getLastname());
                 preparedStatement.executeUpdate();
      
    	 } catch (SQLException e) {
		
    	e.printStackTrace();
	}
     }
     public boolean updateUser(User customer)throws SQLException{
    	 boolean rowUpdated;
    	 try(Connection connection=getConnection();
    			 PreparedStatement statement=connection.prepareStatement(UPDATE);){
    				 statement.setString(1, customer.getFirstname());
    				 statement.setString(1, customer.getLastname());
    				 statement.setInt(1, customer.getId());
    				 rowUpdated = statement.executeUpdate()>0;
    			 }
		return rowUpdated;
     }
     public User selectUser(int id) {
    	 User customer=null;
    	 
    	 try(Connection connection=getConnection();
    			 PreparedStatement preparedstatement= connection.prepareStatement(SELECTBYID);){
    				 preparedstatement.setInt(1,id);
    				 System.out.println(preparedstatement);
    				 ResultSet rs=preparedstatement.executeQuery();
    				 while(rs.next()) {
    					 String firstname=rs.getString("firstname");
    					 String lastname=rs.getString("lastname");
    					 customer=new User(id,firstname,lastname);
    				 }
    			 } catch (SQLException e) {
					
					e.printStackTrace();
				}
		return customer;
    	 
     }
     public List<User> selectall() {
    	List<User> customer=new ArrayList<>();
    	 try(Connection connection=getConnection();
    			 PreparedStatement preparedstatement= connection.prepareStatement(SELECTALL);){
    				
    				 System.out.println(preparedstatement);
    				 ResultSet rs=preparedstatement.executeQuery();
    				 while(rs.next()) {
    					 int id=rs.getInt("id");
    					 String firstname=rs.getString("firstname");
    					 String lastname=rs.getString("lastname");
    					 
    					 customer.add(new User(id,firstname,lastname));
    				 }
    			 } catch (SQLException e) {
					
					e.printStackTrace();
				}
		return  customer;
    	 
     }
     public boolean deleteUser(int id) throws SQLException {
    	 boolean rowDeleted = false;
    	 
    	 try(Connection connection=getConnection();
    			 PreparedStatement preparedstatement= connection.prepareStatement(DELETE);){
    		preparedstatement.setInt(1,id);
    				 rowDeleted =preparedstatement.executeUpdate()>0;
				}
		return rowDeleted;
    	 
     }


 }*/

