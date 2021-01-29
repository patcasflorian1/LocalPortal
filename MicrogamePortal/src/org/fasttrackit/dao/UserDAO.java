package org.fasttrackit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.fasttrackit.helper.DBHelper;
import org.fasttrackit.pojo.Roles;
import org.fasttrackit.pojo.State;
import org.fasttrackit.pojo.User;


public class UserDAO {
	
	//Create table
public void createTableUser() throws SQLException {
		
	    
		DBHelper helper = new DBHelper();
		Connection con = helper.getConnectionSocietate();
		String insertUser = "CREATE TABLE IF NOT EXISTS user"+ 
				" (id INTEGER not NULL auto_increment primary key,full_name VARCHAR(50) NULL,phone_number VARCHAR(15) NULL,"
				+ "company_name VARCHAR(50) NULL,vat_number VARCHAR(15) NULL,nr_onrc VARCHAR(15) NULL,company_adress VARCHAR(200) NULL,"
				+ "email VARCHAR(50) NULL,role VARCHAR(15) NULL,"
				+ "salt VARCHAR(30) NULL,password VARCHAR(100) NULL,statute VARCHAR(30) NULL)";
				
	      Statement stmt = con.createStatement();
	      
	       stmt.execute(insertUser);
	       helper.closeConnection(con);
	       
	}
//display table
//afisare lista tabele 
	public String displayTable() throws SQLException {
		String result = null;
		DBHelper helper = new DBHelper();
		Connection con = helper.getConnectionSocietate();
		String updateClient = "Show tables";
	      Statement stmt = con.createStatement();
	      ResultSet rs = stmt.executeQuery(updateClient);
	      while(rs.next()) {
	        result = rs.getString(1);
	      }
	        helper.closeConnection(con);
	        return result;
	        }
	
	
//verify if table User is Empty
public int verifyTableUser() throws SQLException {
	
	DBHelper helper = new DBHelper();
	Connection con = helper.getConnectionSocietate();
	String selectUser = "SELECT * FROM user";
	int count = 0;	
      Statement stmt = (PreparedStatement) con.prepareStatement(selectUser);
      ResultSet rst = stmt.executeQuery(selectUser);
          try {
               while(rst.next()){
                  count++;
               }
              
          } catch (SQLException ex) {
                  
           }
    
       helper.closeConnection(con);
       return count;
}

//afisare continut tabela user

	public ArrayList<User> getUser() throws SQLException{
		ArrayList<User> listUser=new ArrayList<User>();
		User user = null;
		DBHelper helper = new DBHelper();
		Connection con = helper.getConnectionSocietate();
		String getLocatie = "select*from user";
	      Statement stmt = con.createStatement();
	      ResultSet rst = stmt.executeQuery(getLocatie);
	      while(rst.next()) {
	    	  
	    	     int id = rst.getInt("id");
	    		 String fullName = rst.getString("full_name");
	    		 String phoneNumber = rst.getString("phone_number");
	    		 String companyName = rst.getString("company_name");
	    		 String vatNumber = rst.getString("vat_number");
	    		 String nrOnrc = rst.getString("nr_onrc");
	    		 String companyAdress = rst.getString("company_adress");
	    		 String email = rst.getString("email");
	    		 String salt = rst.getString("salt");
	    	     String password = rst.getString("password");
	    	     String role1 = rst.getString("role");
	    	     String statute1 = rst.getString("statute");
	    	 user = new User(id,fullName,phoneNumber,companyName,vatNumber,nrOnrc
	    			  ,companyAdress,email,salt,password,role1,statute1);
	    	  listUser.add(user);
	      }
	      
	     helper.closeConnection(con);
		return listUser;
	}

	//selectare user dupa fullName din tabela
	public User getOneUser(String fullNam) throws SQLException{
		User user =null;
		DBHelper helper = new DBHelper();
		Connection con = helper.getConnectionSocietate();
		String getUser = "SELECT*FROM user where full_name ='"+fullNam+"'";
	      Statement stmt = con.createStatement();
	      ResultSet rst = stmt.executeQuery(getUser);
	      while(rst.next()) {
	    	  int id = rst.getInt("id");
	    		 String fullName = rst.getString("full_name");
	    		 String phoneNumber = rst.getString("phone_number");
	    		 String companyName = rst.getString("company_name");
	    		 String vatNumber = rst.getString("vat_number");
	    		 String nrOnrc = rst.getString("nr_onrc");
	    		 String companyAdress = rst.getString("company_adress");
	    		 String email = rst.getString("email");
	    		 String salt = rst.getString("salt");
	    	     String password = rst.getString("password");
	    	     String role1 = rst.getString("role");
	    	     String statute1 = rst.getString("statute");
	    	 user = new User(id,fullName,phoneNumber,companyName,vatNumber,nrOnrc
	    			  ,companyAdress,email,salt,password,role1,statute1);
		    	  }
		
		return user;
		
	}
//Insert  into table user
public void insertUser(User user) throws SQLException {
	
	
	DBHelper helper = new DBHelper();
	Connection con = helper.getConnectionSocietate();
	String insertUser = "insert into user"+ 
			"( full_name,phone_number,company_name,vat_number,nr_onrc,company_adress,email,role,salt,password,statute)"
			  +" value(?,?,?,?,?,?,?,?,?,?,?)";
	
	PreparedStatement ps = con.prepareStatement(insertUser);
	ps.setString(1, user.getFullName());
	ps.setString(2,user.getPhoneNumber());
	ps.setString(3, user.getCompanyName());
	ps.setString(4, user.getVatNumber());
	ps.setString(5, user.getNrOnrc());
	ps.setString(6, user.getCompanyAdress());
	ps.setString(7, user.getEmail());
	ps.setString(8, user.getRole1());
	ps.setString(9, user.getSalt());
	ps.setString(10, user.getPassword());
	ps.setString(11, user.getStatute1());
	ps.executeUpdate();
	
	 helper.closeConnection(con);
      }
//update user
public void updateUser(User user,String fullNam) throws SQLException {
	DBHelper helper = new DBHelper();
	Connection con = helper.getConnectionSocietate();
	String insertUser = "UPDATE user set"+ 
			" id=?,full_name=?,phone_number=?,company_name=?,vat_number=?,nr_onrc=?,company_adress=?,email=?,"
			+ "role=?,salt=?,password=?,statute=? where full_name ='"+fullNam+"'";
	
	PreparedStatement ps = con.prepareStatement(insertUser);
	ps.setInt(1, user.getId());
	ps.setString(2, user.getFullName());
	ps.setString(3,user.getPhoneNumber());
	ps.setString(4, user.getCompanyName());
	ps.setString(5, user.getVatNumber());
	ps.setString(6, user.getNrOnrc());
	ps.setString(7, user.getCompanyAdress());
	ps.setString(8, user.getEmail());
	ps.setString(9, user.getRole1());
	ps.setString(10, user.getSalt());
	ps.setString(11, user.getPassword());
	ps.setString(12, user.getStatute1());
	
	
	ps.executeUpdate();
	
	 helper.closeConnection(con);
        }


public void deleteUser(int id) throws SQLException {
	DBHelper helper = new DBHelper();
	Connection con = helper.getConnectionSocietate();
	String deleteRow = "DELETE FROM user WHERE id=?";
	PreparedStatement ps = con.prepareStatement(deleteRow);
	ps.setInt(1, id);
	ps.executeUpdate();
	helper.closeConnection(con);
}

}

