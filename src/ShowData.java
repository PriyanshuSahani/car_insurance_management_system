import java.sql.*;

public class ShowData {
    public static void displayPolicyByCarId(Connection conn) {
        try {
            String cidString = System.console().readLine("Enter Car id : ");
            Integer cid = Integer.parseInt(cidString);

            String queryString = "SELECT * FROM insurance WHERE c_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setInt(1, cid);
            ResultSet rSet = query.executeQuery();

            if ( !rSet.next() ) {
                System.out.println("Car not found.");
                return;
            }

            System.out.println("Policy No. : " + rSet.getString(1));
            System.out.println("Car Id : " + rSet.getString(2));
            System.out.println("Owner Id : " + rSet.getString(3));
            System.out.println("End Date : " + rSet.getString(4));
            System.out.println("Renew " + rSet.getString(5));

        
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void displayCarDetails(Connection conn, String cid) {
        try {
            if ( cid == "_" ) {
                cid = System.console().readLine("Enter Car id : ");
            }
            String queryString = "SELECT * FROM car WHERE c_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setInt(1, Integer.parseInt(cid));

            ResultSet rSet = query.executeQuery();

            if (!rSet.next()) {
                System.out.println("Car not found");
                return;
            }

            System.out.println("Car Id : " + rSet.getString(1));
            System.out.println("Owner Id : " + rSet.getString(2));
            System.out.println("Brand : " + rSet.getString(3));
            System.out.println("Model : " + rSet.getString(4));
            System.out.println("Make year : " + rSet.getString(5));
            System.out.println("Engine No. : " + rSet.getString(6));
            System.out.println("Chassis No. : " + rSet.getString(7));

        } catch ( SQLException e ) {
            System.out.println(e);
        }
    }

    public static void displayPersonDetails(Connection conn, String pidString) {
        try {
            // String oidString = RetrieveRecord.getCarOwnerId(conn, cid);
            if ( pidString == "_" ) {
                pidString = System.console().readLine("Enter Person Id");
            }
            String queryString = "SELECT * FROM person WHERE p_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setInt(1, Integer.parseInt(pidString));
            ResultSet rs = query.executeQuery();

            if (!rs.next()) {
                System.out.println("Person not found.");
                return;
            }

            System.out.println("Person Id : " + rs.getString(1));
            System.out.println("First Name : " + rs.getString(2));
            System.out.println("Middle Name : " + rs.getString(3));
            System.out.println("Last Name : " + rs.getString(4));
            System.out.println("Date of Birth : " + rs.getString(5));
            System.out.println("Phone 1 : " + rs.getString(6));
            System.out.println("Phone 2 : " + rs.getString(7));
        } catch ( SQLException e ) {
            System.out.println(e);
        }
    }
    public static void displayOwnerDetails(Connection conn, String cidString) {
        if (cidString == "_") {
            cidString = System.console().readLine("Enter Car Id : ");
        }
        String oidString = RetrieveRecord.getCarOwnerId(conn, cidString);
        displayPersonDetails(conn, oidString);
    }
    public static void displayClaimDetails(Connection conn) {
        try {
            String id = System.console().readLine("Enter Claim no. : ");
            String queryString = "SELECT * FROM accident WHERE claim_no = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setString(1, id);

            ResultSet rs = query.executeQuery();

            if ( !rs.next() ) {
                System.out.println("Claim not found!");
                return;
            }

            System.out.println("Claim No. : " + rs.getString(1));
            System.out.println("Car Id : " + rs.getString(2));
            System.out.println("Owner Driven : " + rs.getString(3));
            System.out.println("Driver Id : " + rs.getString(4));
            System.out.println("Driver Age : " + rs.getString(5));
            System.out.println("Accident Date : " + rs.getString(6));
            System.out.println("Repair Cost : " + rs.getString(7));
            System.out.println("Workshop Id " + rs.getString(8));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void displayWorkshopDetails(Connection conn, String wsid) {
        try {
            if ( wsid == "_" ) {
                wsid = System.console().readLine("Enter Workshop id : ");
            }
            String queryString = "SELECT * FROM workshop WHERE ws_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setInt(1, Integer.parseInt(wsid));

            ResultSet rSet = query.executeQuery();

            if (!rSet.next()) {
                System.out.println("Workshop not found");
                return;
            }

            System.out.println("Workshop Id : " + rSet.getString(1));
            System.out.println("Workshop Name : " + rSet.getString(2));
            System.out.println("Plot No. : " + rSet.getString(3));
            System.out.println("Street : " + rSet.getString(4));
            System.out.println("City : " + rSet.getString(5));

        } catch ( SQLException e ) {
            System.out.println(e);
        }
    }

    public static void displayDriverDetails(Connection conn, String pid) {
        try {
            String queryString = "SELECT * FROM driver WHERE p_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setString(1, pid);
            ResultSet rs = query.executeQuery();
            rs.next();
            System.out.println("Driver Name : " + rs.getString(2));
            System.out.println("Driver Phone no. : " + rs.getString(3));

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(Connection conn) {
        int choice = -1;
        while (choice != 0) {
            // Thread.sleep(2000);
            // System.out.print("\033[H\033[2J");  
            // System.out.flush();

            System.out.println("\nChoose an option:");
            System.out.println("1. Display Policy details of a car ");
            System.out.println("2. Display Car details ");
            System.out.println("3. Display Person Details");
            System.out.println("4. Display Owner Details");
            System.out.println("5. Display Claim details");
            System.out.println("6. Display Workshop details");
            System.out.println("0. Exit");

            choice = Integer.parseInt(System.console().readLine("Choice : "));
            switch (choice) {
                case 1:
                    ShowData.displayPolicyByCarId(conn);
                    break;
                case 2:
                    ShowData.displayCarDetails(conn, "_");
                    break;
                case 3:
                    ShowData.displayPersonDetails(conn, "_");
                    break;
                case 4:
                    ShowData.displayOwnerDetails(conn, "_");
                    break;
                case 5:
                    ShowData.displayClaimDetails(conn);
                    break;
                case 6:
                    ShowData.displayWorkshopDetails(conn, "_");
                    break;
                case 0:
                    System.out.println("Returning to main...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    } 

}
