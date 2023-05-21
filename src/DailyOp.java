import java.sql.*;

public class DailyOp {

    public static void handleExpiringPolicy( Connection conn ) {
        // Should be run once and only one everyday!!
        // Implement by code or user awareness

        try {
            String getQueryString = "SELECT * FROM insurance WHERE end_date = CURDATE()";
            PreparedStatement getQuery = conn.prepareStatement(getQueryString);
            ResultSet policy = getQuery.executeQuery();
            
            String oldPolicyNo, cid, oid;
            int newPolicyNo;
            while ( policy.next() ) {
                oldPolicyNo = policy.getString(1);
                cid = policy.getString(2);
                oid = policy.getString(3);
                DeleteRecord.deleteExpiredPolicy(conn, policy.getString(1));

                if ( policy.getInt(5) == 0 ) {
                    continue;
                }

                NewRecord.addPolicy(conn, cid, oid);
                newPolicyNo = RetrieveRecord.getGeneratedPolicyNo(conn, Integer.parseInt(cid));

                System.out.print( oldPolicyNo + " --> " );
                System.out.println(newPolicyNo);
            }
        
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
