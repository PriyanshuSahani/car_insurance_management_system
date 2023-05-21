import java.sql.*;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/carinsurance";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static void main(String[] args) {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            int choice = -1;

            while (choice != 0) {
                System.out.println("\nChoose an option : ");
                System.out.println("1. Add a new insurance policy");
                System.out.println("2. Add a new workshop");
                System.out.println("3. Add a new accident claim");
                System.out.println("4. Renew insurance policy");
                System.out.println("5. Delete/Renew expiring policies");
                System.out.println("6. Show Details");
                System.out.println("7. View claims");
                System.out.println("8. Release Claim");
                System.out.println("0. Exit");

                choice = Integer.parseInt(System.console().readLine("Choice : "));
                switch (choice) {
                    case 1:
                        NewRecord.addPolicy(conn, "_", "_");
                        break;
                    case 2:
                        NewRecord.addWorkshop(conn);
                        break;
                    case 3:
                        NewRecord.addClaim(conn);
                        break;
                    case 4:
                        Renew.renewPolicy(conn);
                        break;
                    case 5:
                        DailyOp.handleExpiringPolicy(conn);
                        break;
                    case 6:
                        ShowData.main(conn);
                        break;
                    case 7:
                        Claim.viewActiveClaims(conn);
                        break;
                    case 8:
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch ( ClassNotFoundException e ) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
