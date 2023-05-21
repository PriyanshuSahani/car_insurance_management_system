import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class NewRecord {

    public static void addPerson(Connection conn, String pid) {
        try {
            String fname = System.console().readLine("Enter first name : ");
            String mname = System.console().readLine("Enter middle name : ");
            String lname = System.console().readLine("Enter last name : ");
            String dob = System.console().readLine("Enter date of birth (YYYY-MM-DD) : ");
            String phone1 = System.console().readLine("Enter primary phone number : ");
            String phone2 = System.console().readLine("Enter secondary phone number (optional) : ");

            String query = "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pid);
            stmt.setString(2, fname);
            stmt.setString(3, mname);
            stmt.setString(4, lname);
            stmt.setString(5, dob); 
            stmt.setString(6, phone1);
            stmt.setString(7, phone2);
            
            int rowsInserted = stmt.executeUpdate();

            if ( rowsInserted > 0 ) {
                System.out.println("Person added successfully");
            } else {
                System.out.println("Person not added.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding Person: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public static void addCar(Connection conn, String cid) {
        try {
            // Prompt the user for the car details
            String ownerIdStr = System.console().readLine("Enter owner's ID: ");
            int ownerId = Integer.parseInt(ownerIdStr);
            String brand = System.console().readLine("Enter brand: ");
            String model = System.console().readLine("Enter model: ");
            String makeYear = System.console().readLine("Enter make year (yyyy): ");
            String engineNoStr = System.console().readLine("Enter engine number: ");
            int engineNo = Integer.parseInt(engineNoStr);
            String chassisNoStr = System.console().readLine("Enter chassis number: ");
            int chassisNo = Integer.parseInt(chassisNoStr);
    
            // Prepare the SQL statement
            String sql = "INSERT INTO car VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cid);
            statement.setInt(2, ownerId);
            statement.setString(3, brand);
            statement.setString(4, model);
            statement.setString(5, makeYear);
            statement.setInt(6, engineNo);
            statement.setInt(7, chassisNo);
    
            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Car added successfully!");
            } else {
                System.out.println("Car not added.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding car: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
    

    public static void addWorkshop(Connection conn) {
        try {
            // Prompt the user for the workshop details
            String idString = System.console().readLine("Enter workshop id : ");  
            String name = System.console().readLine("Enter workshop name : ");
            String plotNo = System.console().readLine("Enter plot number: ");
            String street = System.console().readLine("Enter street name : ");
            String city = System.console().readLine("Enter city : ");
    
            // Prepare the SQL statement
            String sql = "INSERT INTO workshop VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idString);
            statement.setString(2, name);
            statement.setString(3, plotNo);
            statement.setString(4, street);
            statement.setString(5, city);
    
            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Workshop added successfully!");
            } else {
                System.out.println("Workshop not added.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding workshop: " + e.getMessage());
        }
    }
    
    public static void addPolicy(Connection conn, String cid, String oid ) {
        try {
            if ( oid == "_" ) {
                oid = System.console().readLine("Enter Owner id :");
                if ( !CheckExists.isPersonRegistered(conn, "person", Integer.parseInt(oid)) ) {
                    NewRecord.addPerson(conn, oid);
                }

            }
            
            if ( cid == "_" ) {
                cid = System.console().readLine("Enter Car id : ");
                NewRecord.addCar(conn, cid);
            }

            java.util.Date date = new java.util.Date();
            Calendar today = Calendar.getInstance();
            today.setTime(date);
            today.add(Calendar.YEAR, 1);

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date endDate = today.getTime();

            String query = "INSERT INTO insurance (c_id, owner_id, end_date) VALUES (?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cid);
            stmt.setString(2, oid);
            stmt.setString(3, ft.format(endDate));

            int rowsInserted = stmt.executeUpdate();

            if ( rowsInserted > 0 ) {
                System.out.print("Policy created successfully \nPolicy No : ");
                System.out.println(RetrieveRecord.getGeneratedPolicyNo(conn, Integer.parseInt(cid)));
            } else {
                System.out.println("There was an error creating the policy");
            }


        } catch(SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addDriver(Connection conn, String id) {
        try {
            // String id = System.console().readLine("Enter Person id : ");
            String name = System.console().readLine("Enter Name of the Person : ");
            String phone = System.console().readLine("Enter Phone no. : ");

            String query = "INSERT INTO driver VALUES( ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, phone);

            int rowsInserted = stmt.executeUpdate();
            if ( rowsInserted > 0 ) {
                System.out.println("Person Added Successfully");
            } else {
                System.out.println("Person not added");
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println( e );
        }
    }

    public static void addClaim(Connection conn) {
        try {
            String wasOwnerDriven = "-1";

            while ( !wasOwnerDriven.equalsIgnoreCase("y") && !wasOwnerDriven.equalsIgnoreCase("n") ) {
                wasOwnerDriven = System.console().readLine("Was the car driven by its owner at the time of accident?(y/n) : ");
            }

            String cid = System.console().readLine("Car Id : ");
            if ( !CheckExists.isCarInsured(conn, cid) ) {
                System.out.println("The car isn't insured.");
                return;
            }
            String accDate = System.console().readLine("Accident Date : ");
            String cost = System.console().readLine("Repair Cost : ");
            String wsid = System.console().readLine("Workshop Id : ");
            
            String query;
            String driverId = "";
            String driverAge = "";

            if ( wasOwnerDriven.equalsIgnoreCase("n") ) {
                driverId = System.console().readLine("Driver Id : ");
                if ( !CheckExists.isPersonRegistered(conn, "driver", Integer.parseInt(driverId)) ) {
                    NewRecord.addDriver(conn, driverId);
                }
                driverAge = System.console().readLine("Driver Age : ");
                
                
            } else {
                driverId = RetrieveRecord.getCarOwnerId(conn, cid);
                driverAge = System.console().readLine("Enter your age : ");
            }

            query = "INSERT INTO accident (c_id, owner_driven, accident_date, repair_cost, ws_id, driver_id, driver_age) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, cid);
            stmt.setString(3, accDate);
            stmt.setString(4, cost);
            stmt.setString(5, wsid);
            stmt.setString(6, driverId);
            stmt.setString(7, driverAge);

            if ( wasOwnerDriven.equalsIgnoreCase("y") ) {
                stmt.setInt(2, 1);
            } else {
                stmt.setInt(2, 0);
            }

            int rowsInserted = stmt.executeUpdate();
            if ( rowsInserted > 0 ) {
                System.out.println("Claim added successfully.");
            } else {
                System.out.println("Claim not added!");
            }

        } catch ( SQLException e ) {
            System.out.println(e);
        } catch ( Exception e ) {
            System.out.println(e);
        }
    }
}
