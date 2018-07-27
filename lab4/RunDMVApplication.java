import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * A class that connects to PostgreSQL and disconnects.
 * You will need to change your credentials below, to match the username and password of your account
 * in the PostgreSQL server.
 * The name of your database in the server is the same as your username.
 * You are asked to include code that tests the methods of the DMVApplication class
 * in a similar manner to the sample RunStoresApplication.java program.
*/


public class RunDMVApplication
{
	// You will need to fill in your real username
	// and password for your Postgres account
	public static String USERNAME = "etecox";
	public static String PASSWORD = "isolation84result";
	
    public static void main(String[] args) {
    	
    	Connection connection = null;
    	try {
    		//Register the driver
    		Class.forName("org.postgresql.Driver"); 
    		// Make the connection
            connection = DriverManager.getConnection(
					 "jdbc:postgresql://cmps182-db.lt.ucsc.edu/" + RunDMVApplication.USERNAME,
					 RunDMVApplication.USERNAME,
					 RunDMVApplication.PASSWORD);
            
            if (connection != null)
                System.out.println("Connected to the database!");

            /* Include your code below to test the methods of the DMVApplication class
             * The sample code in RunStoresApplication.java should be useful.
             * That code tests other methods for a different database schema. */
            //Your code below: 
            
	    //get a new instance of the DMVApplication
	    DMVApplication app = new DMVApplication(connection);

	    //get the licenseID of all drivers with atleast 3 vehicles
            List<Integer> list1 = app.getDriversWithManyVehicles(3);
	    //print out the licenseID list
            for (Integer list: list1)
                System.out.println(list);

	    /*Output of getDriversWithMantVehicles when numberOfVehicles is 3
	    output here:
		10004
		10003
		10008
	    */

	    //Set the DriverID for the ticket with TicketID 3000011 to be the
	    //DriverID for the person in DRIVERS whose name is 'Chao Xu' and 
	    //address is '1007 Broadway Ave' using the setTicketedDriver method
	    app.setTicketedDriver("Chao Xu", "1007 Broadway Ave", 3000011);

	    System.out.println();
	    //test the getSomeTicketFees method
	    //first test with 2300 as the argument and print out the list
	    System.out.println("Test getSomeTicketFees with argument of 2300:");
	    List<Integer> list2 = app.getSomeTicketFees(2300);
	    for (Integer list: list2)
		System.out.println(list);
	    System.out.println();
	    /* Output with argument of 2300
		Test getSomeTicketFees with argument of 2300:
 	        3000024
		3000000
		3000001
		3000002
		3000003
		3000004
		3000012
		3000005
		3000006
		3000007
		3000008
		3000009
	    */

	    //Now test with -8 as the argument and print out the list
	    System.out.println("Test getSomeTicketFees with argument of -8:");
	    List<Integer> list3 = app.getSomeTicketFees(-8);
	    for (Integer list: list3)
		System.out.println(list);
	    System.out.println();
	    /* Output with argument of -8
		Test getSomeTicketFees with argument of -8:
	    */
            
            //Your code ends here
            
    	}
    	catch (SQLException | ClassNotFoundException e) {
    		System.out.println("Error while connecting to database: " + e);
    		e.printStackTrace();
    	}
    	finally {
    		if (connection != null) {
    			// Closing Connection
    			try {
					connection.close();
				} catch (SQLException e) {
					System.out.println("Failed to close connection: " + e);
					e.printStackTrace();
				}
    		}
    	}
    }
}
