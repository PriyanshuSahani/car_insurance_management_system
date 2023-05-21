import java.sql.*;

public class RetrieveRecord {
    public static int getGeneratedPolicyNo( Connection conn, int cid ) {
        try {
            String queryString = "SELECT policy_no FROM insurance WHERE c_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setInt(1, cid);
            ResultSet rSet = query.executeQuery();

            if (rSet.next()) {
                return rSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println(e);
        }

        return 0;
    }

    public static String getCarOwnerId( Connection conn, String cid ) {
        try {
            String queryString = "SELECT owner_id from car WHERE c_id = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setInt(1, Integer.parseInt(cid));

            ResultSet rs = query.executeQuery();

            if ( rs.next() )  {
                return rs.getString(1);
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        }

        return "";
    }
}
