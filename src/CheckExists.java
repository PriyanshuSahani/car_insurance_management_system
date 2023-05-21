import java.sql.*;

public class CheckExists {

    public static boolean isPersonRegistered(Connection conn, String table, int id) {
        try {
            String query = "SELECT p_id FROM " + table + " WHERE p_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);

            boolean ret = stmt.executeQuery().next();
            return ret;
        } catch ( SQLException e ) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println(e);
        }

        return false;
    }

    public static boolean isCarInsured(Connection conn, String cid ) {
        try {
            if ( cid == "_" ) {
                cid = System.console().readLine("Enter Car id : ");
            }
            String queryString = "SELECT c_id FROM insurance WHERE c_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setString(1, cid);
            ResultSet rs = query.executeQuery();

            if ( rs.next() ) {
                return true;
            } else {
                return false;
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        }
        return false;
    }
    

    public static boolean isWorkshopRegistered(Connection conn, int wsid) {
        try {
            String query = "SELECT ws_id FROM workshop WHERE ws_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, wsid);

            ResultSet rs = stmt.executeQuery();
            
            if ( !rs.next() ) {
                System.out.println("The provided Workshop isn't affiliated with our organization.");
                return false;
            } else {
                return true;
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        }

        return false;
    }
}