package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import View.View;

public class Connect {
    private Connection con;
    private ResultSet rs;
    private Statement stmt;
    private View v;
     
    public Connect(){
        String connString = "jdbc:mysql://localhost/students?user=root&password=chadholness";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connString);
            stmt = con.createStatement();
        }catch(SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }     
    }
     
    public void insert(String fName, String lName){
        String insertQuery = "INSERT INTO students ( firstName, lastName) VALUES ('"+fName+"',"
                + "'"+lName+"');";
        try {
            stmt.executeUpdate(insertQuery);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void update(){
    	System.out.println("Enter idNumber");
    	Scanner input =  new Scanner(System.in);
    	int id =  input.nextInt();
    	
        String updateQuery = "UPDATE students SET lastName = 'lol' WHERE idNumber = "+id+";";
        try {
            stmt.executeUpdate(updateQuery);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    public void delete(){
    	System.out.println("Enter idNumber");
    	Scanner input =  new Scanner(System.in);
    	int id =  input.nextInt();
    	
        String deleteQuery = "DELETE FROM students WHERE idNumber = "+id+";";
        try {
            stmt.executeUpdate(deleteQuery);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
     
    public void read(){
        String selectQuery = "SELECT * FROM students;";
        try {
            rs = stmt.executeQuery(selectQuery);
            while(rs.next()) {
                System.out.println(rs.getString("firstName") + ' ' + rs.getString("lastName"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
             
    }
     
    public void close(){
        try {
            con.close();
            stmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) {
        Connect conc = new Connect();
        //conc.insert();
        conc.read();
        View v = new View(conc);
        //conc.update();
        //conc.read();
        //conc.delete();
        conc.read();
        conc.close();
        
    }*/

}
