import java.sql.*;

public class Claim {
    public static void viewActiveClaims(Connection conn) {
        try {
            String queryString = "SELECT * FROM accident";
            PreparedStatement stmt = conn.prepareStatement(queryString,
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(queryString);

            while ( rs.next() ) {
                System.out.println("Claim No. : " + rs.getString(1));
                System.out.println("Car Id : " + rs.getString(2));
                System.out.println("Owner Driven : " + rs.getString(3));
                System.out.println("Driver Id : " + rs.getString(4));
                if (rs.getBoolean(3) == false) {
                    ShowData.displayDriverDetails(conn, rs.getString(4));
                }
                System.out.println("Driver Age : " + rs.getString(5));
                System.out.println("Accident Date : " + rs.getString(6));
                System.out.println("Repair Cost : " + rs.getString(7));
                System.out.println("Workshop Id " + rs.getString(8));

                System.out.println("\n-----------------------------\n");

                ShowData.displayCarDetails(conn, rs.getString(2));

                System.out.println("\n-----------------------------\n");

                ShowData.displayOwnerDetails(conn, rs.getString(2));

                System.out.println("\n-----------------------------\n");

                ShowData.displayWorkshopDetails(conn, rs.getString(8));

                String opt = "-1";
                while ( !( opt.equals("y") || opt.equals("n") ) ) {
                    opt = System.console().readLine("Release claim?(y/n) : ");
                    if ( opt.equals("y") ) {
                        rs.deleteRow();
                    }     
                }
            }

        } catch ( SQLException e ) {
            System.out.println(e);
        }  
    }
}
