/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectstudentmanagmentsystam;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;



/**
 *
 * @author wazeem
 */
public class ProjectStudentManagmentSystam11 {
    private static DbConfiq dbconfiq = DbConfiq.getInstance();
    static String [][] studentList= new String[50][6];
    static String[][]tempList= new String[50][6];
    private static Object cs;


    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
    
         dashbord();
        
        
    }
    
    
    public static void dashbord() throws Exception{
         Scanner s = new Scanner(System.in);

       
        System.out.println("1.Edit Student");
        System.out.println("2.View all students");
        System.out.println("3. Search student");
        System.out.println("4. Remove student");
        System.out.println("5. Logout\n");

        System.out.println("Enter Your Choice:");
        int choice = s.nextInt();
        
        switch (choice) {
            case 1:
               insertStudent();
            
                break;
            case 2:
                 getAllStudent();
            
            break;
            case 3:
                getSearchStudent();
             
                break;
            case 4:
                deleteStudent();
                break;
            case 5:
                System.exit(0);
                break;
                
            default:
                System.out.println("Invalid choice, please try again!");
                dashbord();
                break;
        }
    }
    
    public static void getAllStudent()throws Exception {
        
        Scanner s = new Scanner(System.in);
        System.out.println("\033[H\033[2J"); 
        System.out.println("----------------------------------------");
        System.out.println("|Welcome to Students Management System|");
        System.out.println("---------------------------------------");

        System.out.println("-----------------");
        System.out.println("|View all student|");
        System.out.println("------------------");
        
        String sql ="SELECT * FROM Students";
        
        try(Connection con = dbconfiq.dbConnction()){
            var st = con.createStatement();
            var rs = st.executeQuery(sql);

           while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String nic = rs.getString("nic");
                String gender = rs.getString("gender");
                
                System.out.println(id + " " + name + " " + nic + " " + gender);
           }
           
    System.out.print("\n Press any letter to go dashbord \n");
    String selction =s.nextLine();
    if(selction != null){
        System.out.println("\033[H\033[2J");
        dashbord();
    }

        }
      
        }
   
   
    public static void deleteStudent() throws Exception{
        Scanner s = new Scanner(System.in);
        System.out.println("\033[H\033[2J"); 
        System.out.println("----------------------------------------");
        System.out.println("|Welcome to Students Management System|");
        System.out.println("---------------------------------------");

        System.out.println("-----------------");
        System.out.println("|Remove  student|");
        System.out.println("------------------");

        System.out.println("Enter Student NIC Number:");
        String nic = s.nextLine();

        Connection con = dbconfiq.dbConnction();
        String sql = "DELETE FROM Students WHERE nic = ?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, nic);
        int row = st.executeUpdate();

        System.out.println("Number of rows affected: " + row);

        
        System.out.print("\n Press any letter to go dashbord \n");
    String selction =s.nextLine();
    if(selction != null){
        System.out.println("\033[H\033[2J");
        dashbord();
    }

        
    }
 
    
    public static void insertStudent() throws Exception {
    System.out.println("\033[H\033[2J");
    System.out.println("----------------------------------------");
    System.out.println("| Welcome to Students Management System |");
    System.out.println("----------------------------------------");

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter name: ");
    String name = scanner.nextLine();

    System.out.print("Enter age: ");
    int age = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter NIC: ");
    String nic = scanner.nextLine();

    System.out.print("Enter gender: ");
    String gender = scanner.nextLine();

    System.out.print("Enter date of birth (yyyy-mm-dd): ");
    String dob = scanner.nextLine();

    String sql = "INSERT INTO Students (name, age, nic, gender, dob) VALUES (?, ?, ?, ?, ?)";

    try (Connection con = dbconfiq.dbConnction()) {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, nic);
        ps.setString(4, gender);
        ps.setString(5, dob);
        int row = ps.executeUpdate();
        System.out.println(row);
    } catch (Exception e) {
        System.out.println(e);
    }

    System.out.println("\n Press any letter to go dashboard \n");
    String selection = scanner.nextLine();
    if (selection != null) {
        System.out.println("\033[H\033[2J");
        dashbord();
    }
}

    
    
    public static void getSearchStudent() throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("\033[H\033[2J");
        System.out.println("----------------------------------------");
        System.out.println("| Welcome to Students Management System |");
        System.out.println("----------------------------------------");

        System.out.println("Enter search criteria (id, name, age, gender, dob): ");
        String criteria = s.nextLine().trim();

        if (!criteria.matches("id|name|age|gender|dob")) {
            System.out.println("Invalid search Please try again.");
            getSearchStudent();
            return;
        }

        System.out.println("Enter search value: ");
        String value = s.nextLine().trim();

        if (value.isEmpty()) {
            System.out.println("Search value cannot be empty. Please try again.");
           getSearchStudent();
            return;
        }

        String sql = "SELECT * FROM Students WHERE " + criteria + " LIKE ?";
        try (Connection con = dbconfiq.dbConnction()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,  value);
            var rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String nic = rs.getString("nic");
                String gender = rs.getString("gender");
                String dob = rs.getString("dob");

                System.out.println(id + " " + name + " " + age + " " + nic + " " + gender + " " + dob);
            }

            System.out.print("\n Press any letter to go dashboard \n");
            String selction = s.nextLine();
            if (selction != null) {
                System.out.println("\033[H\033[2J");
                dashbord();
            }
        }
    }
   
    
   
    
}
