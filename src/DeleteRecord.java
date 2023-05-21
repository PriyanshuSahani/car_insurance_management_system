import java.sql.*;

public class DeleteRecord {
    public static void deleteExpiredPolicy(Connection conn, String policyNo) {
        try {
            String queryString = "DELETE FROM insurance WHERE policy_no = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setString(1, policyNo);
            int numRows = query.executeUpdate();

            if (numRows > 0) {
                System.out.println( "Policy " + policyNo + " deleted successfully!" );
            } else {
                System.out.println("Policy not deleted.");
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println(e);
        }
    }
    public static void releaseClaim(Connection conn, String claimNo ) {
        try {
            String queryString = "DELETE FROM accident WHERE claim_no = ?";
            PreparedStatement query = conn.prepareStatement(queryString);
            query.setString(1, claimNo);

            int numRows = query.executeUpdate();
            if (numRows > 0) {
                System.out.println("Policy deleted successfully!");
            } else {
                System.out.println("Policy not deleted.");
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println(e);
        }
    }
}
