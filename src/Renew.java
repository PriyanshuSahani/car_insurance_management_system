import java.sql.*;

public class Renew {
    public static void main(String[] args) {
        return;
    }

    public static void renewPolicy( Connection conn ) {
        try {
            String policyNoString = System.console().readLine("Enter Policy No. : ");
            int policyNo = Integer.parseInt(policyNoString);

            String query = "SELECT * FROM insurance WHERE policy_no = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, policyNo);

            ResultSet rs = stmt.executeQuery();

            if ( !rs.next() ) {
                System.out.println("Policy has expired....\nCreating new policy");
                NewRecord.addPolicy(conn, "_", "_");
            }

            query = "UPDATE insurance SET renew = 1 WHERE policy_no = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, policyNo);
            int rowsUpdated = stmt.executeUpdate();
            if ( rowsUpdated > 0 ) {
                System.out.println("Policy will be automatically renewd when it expires.");
            } else {
                System.out.println("Policy couldn't be renewd.");
            }
            

            
        } catch ( SQLException e ) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println(e);
        }
    }

}
