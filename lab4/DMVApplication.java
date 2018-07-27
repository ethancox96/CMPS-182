import java.sql.*;
import java.util.*;

/**
 * The class implements methods of the DMV database interface.
 *
 * All methods of the class receive a Connection object through which all
 * communication to the database should be performed. Note: the
 * Connection object should not be closed by any method.
 *
 * Also, no method should throw any exceptions. In particular, in case
 * that an error occurs in the database, then the method should print an
 * error message and call System.exit(-1);
 */
public class DMVApplication {

    private Connection connection;
    
    /*
     * Constructor
     */
    public DMVApplication(Connection connection) {
        this.connection = connection;
    }
    
    public Connection getConnection()
    {
        return connection;
    }
    
     /**
     * Return list of DriverID values for drivers who own at least numberOfVehicles vehicles.
     */
    public List<Integer> getDriversWithManyVehicles(int numberOfVehicles)
    {
        List<Integer> result = new ArrayList<Integer>();
        // your code here
        PreparedStatement stat = null;
	ResultSet rs = null;
	try {
	    Connection conn = getConnection();
            stat = connection.prepareStatement("SELECT d.LicenseID FROM Drivers d, Tickets t WHERE d.LicenseID = t.LicenseID GROUP BY d.LicenseID HAVING COUNT(*) >= ?");
	    stat.setInt(1, numberOfVehicles);
            rs = stat.executeQuery();
            while (rs.next()){
                result.add(rs.getInt(1));
            }
    
            rs.close();
            stat.close();
	}catch(SQLException e){
	    System.out.println("Exception: " + e);
	    e.printStackTrace();
	} finally {
	    if (stat != null) try { stat.close(); } catch (SQLException e) {}
	    if (rs != null){
		try {
		    rs.close();
		} catch (SQLException e) {
		    System.out.println("Exception closing ResultSet: " + e);
		    e.printStackTrace();
		}
	    }
	}
        // end of your code
        return result;
    }
    
    
    /**
     * Takes as input a name and address, and a TicketID, and changes the DriverID on the ticket to the DriverId 
     * of the driver who has the specified name and address.  Since (name, address) is UNIQUE in Drivers, 
     * there can’t be multiple Drivers with that name.  
     * If there are no people with that name and address,setTicketedDriver should do nothing. 
     * If there is no ticket with the specified TicketId, setTicketedDriver should also do nothing.
     * setTicketedDriver should be performed as a single SQL statement.
     */
    public void setTicketedDriver(String name, String address, int ticketID) {
        // your code here
        PreparedStatement stat = null;
        try {
            Connection conn = getConnection();
            stat = connection.prepareStatement("UPDATE TICKETS SET LicenseID = (SELECT LicenseID FROM DRIVERS WHERE name = ? AND address = ?) WHERE TicketID = 3000011");
	    stat.setString(1, name);
	    stat.setString(2, address);
            stat.executeUpdate();
            stat.close();
        }catch(SQLException e){
            System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            if (stat != null){
                try {
                    stat.close();
                } catch (SQLException e) {
                    System.out.println("Exception closing ResultSet: " + e);
                    e.printStackTrace();
                }
            }
        }
        

        // end of your code
    }
    
    
    /**
     * The getSomeTicketFees method takes as input an integer, stopTotal. getSomeTicketFees should
     * iterate through all the tickets whose Fee isn’t NULL in ascending TicketDate order, gathering their
     * TicketID.  getSomeTicketFees should total the fees on those tickets as it goes.  When
     * the total of the fees is more than stopTotal, then the method is finished; it should not 
     * look at or gather any more tickets.  If the total of the fees isn’t more than stopTotal 
     * but there are no more tickets to gather, then the method also should finish.
     * getSomeTicketFees should return all the TicketID values that it found,     *
     * If stopTotal is not positive, then getSomeTicketFees should do nothing.  
     * Note that the Fee on a ticket can be NULL, but TicketDate can’t be NULL.
     */
    public List<Integer> getSomeTicketFees(int stopTotal)
    {
    List<Integer> result = new ArrayList<Integer>();
        // your code here
        if (stopTotal >= 0){
	    PreparedStatement stat = null;
	    ResultSet rs = null;
            try {
                Connection conn = getConnection();
                stat = connection.prepareStatement("SELECT TicketID, Fee FROM Tickets WHERE Fee IS NOT NULL ORDER BY TicketDate ASC");
                rs = stat.executeQuery();
	        int sum = 0;
                while (rs.next() && sum <= stopTotal){
		    sum += rs.getInt(2);
                    result.add(rs.getInt(1));
                }

                rs.close();
                stat.close();
            }catch(SQLException e){
                System.out.println("Exception: " + e);
            e.printStackTrace();
        } finally {
            if (stat != null) try { stat.close(); } catch (SQLException e) {}
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception closing ResultSet: " + e);
                    e.printStackTrace();
                }
            }
        }
            return result;
	}
	else{
	    return result;
	}
        // end of your code       
    }

};
