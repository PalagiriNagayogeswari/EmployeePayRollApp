import java.util.Scanner;

// Abstract class
abstract class User {

    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Abstract Method
    public abstract boolean authenticate(String username, String password);
}

// Employee Class
class Employeee extends User {

    public Employeee(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean authenticate(String username, String password) {

        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("\nLogin Successful");
            System.out.println("Role : EMPLOYEE");
            return true;
        }

        System.out.println("Invalid Credentials");
        return false;
    }
}

// Manager Class
class Manager extends User {

    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean authenticate(String username, String password) {

        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("\nLogin Successful");
            System.out.println("Role : MANAGER");
            return true;
        }

        System.out.println("Invalid Credentials");
        return false;
    }
}

// Session Class
class Session {

    private String username;
    private long loginTime;
    private long timeoutMillis;

    public Session(String username) {

        this.username = username;
        this.loginTime = System.currentTimeMillis();

        // Session timeout = 30 seconds
        this.timeoutMillis = 30000;
    }

    // Check whether session is expired
    public boolean isExpired() {

        long currentTime = System.currentTimeMillis();

        return (currentTime - loginTime) > timeoutMillis;
    }

    @Override
    public String toString() {
        return "Session Active for User : " + username;
    }
}

public class UC2EmployeePayroll {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Dummy Users
        Employeee employee = new Employeee("yogi", "yogi123");
        Manager manager = new Manager("bridgelabz", "bridgelabz123");

        System.out.println("===== EMPLOYEE PAYROLL LOGIN =====");

        System.out.println("1. Employee Login");
        System.out.println("2. Manager Login");

        System.out.print("Enter Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();

        User user = null;

        if(choice == 1){
            user = employee;
        }
        else if(choice == 2){
            user = manager;
        }
        else{
            System.out.println("Invalid Choice");
            return;
        }

        int attempts = 3;

        while(attempts > 0){

            System.out.print("\nEnter Username : ");
            String username = sc.nextLine();

            System.out.print("Enter Password : ");
            String password = sc.nextLine();

            if(user.authenticate(username,password)){

                // Create Session
                Session session = new Session(username);

                System.out.println(session);

                System.out.println("Welcome " + username);

                if(user instanceof Employeee){

                    System.out.println("\n===== EMPLOYEE DASHBOARD =====");
                    System.out.println("1. View Payslip");
                    System.out.println("2. Update Profile");
                    System.out.println("3. Logout");
                }
                else{

                    System.out.println("\n===== MANAGER DASHBOARD =====");
                    System.out.println("1. View Employee Details");
                    System.out.println("2. Generate Payroll");
                    System.out.println("3. Logout");
                }

                // Demonstration of session checking
                System.out.println("\nChecking Session Status...");

                try{
                    Thread.sleep(5000);      // Wait for 5 seconds
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }

                if(session.isExpired()){
                    System.out.println("Session Expired.");
                }
                else{
                    System.out.println("Session Still Active.");
                }

                break;
            }

            attempts--;

            if(attempts > 0){
                System.out.println("Attempts Left : " + attempts);
            }
            else{
                System.out.println("\nMaximum Login Attempts Reached.");
                System.out.println("Login Failed.");
            }
        }

    }
}