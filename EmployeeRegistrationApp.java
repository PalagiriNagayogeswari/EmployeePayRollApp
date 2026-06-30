import java.io.IOException;
import java.util.*;
import java.io.FileWriter;


class ValidationException extends Exception{
    public ValidationException(String message){
        super(message);
    }
}
class Validator{

        // Validate Email
        public static void validateEmail(String email) throws ValidationException {

            if (!email.matches("^[A-Za-z0-9][A-Za-z0-9+_.-]*@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$")) {
                throw new ValidationException("Invalid Email Format");
            }
        }

        // Validate Phone Number
        public static void validatePhone(String phone) throws ValidationException {

            if (!phone.matches("[6-9][0-9]{9}")) {
                throw new ValidationException("Invalid Phone Number");
            }
        }

        // Validate Employee ID
        public static void validateEmpId(String empId) throws ValidationException {

            if (!empId.matches("EMP-[0-9]{4}")) {
                throw new ValidationException("Invalid Employee ID");
            }
        }
}


class UserAccount{
    private String username;
    private String password;

    public UserAccount(String username,String password){
        this.username=username;
        this.password=password;
    }

    public String getUsername(){
        return username;
    }
}


class Employee{
    private String username;
    private String empId;
    private String email;
    private String phone;
    private UserAccount account;

    public Employee(String username, String empId, String phone, String email, UserAccount account){
        this.username=username;
        this.email=email;
        this.empId=empId;
        this.phone=phone;
        this.account=account;
    }

    public String getUsername() {
        return username;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserAccount getAccount() {
        return account;
    }

    @Override
    public String toString() {

        return "Employee ID : " +getEmpId() +
                "\nName : " + getUsername()+
                "\nEmail : " + getEmail() +
                "\nPhone : " + getPhone() +
                "\nUsername : " + account.getUsername();
    }

    public void persist() throws IOException {

        FileWriter fw =
                new FileWriter("employee.txt", true);

        fw.write(toString());

        fw.write("\n-----------------\n");

        fw.close();
    }

}



public class EmployeeRegistrationApp {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("=== USE CASE 1: EMPLOYEE REGISTRATION ===");

            System.out.print("Enter Employee ID (EMP-XXXX): ");
            String empId = sc.nextLine();

            Validator.validateEmpId(empId);

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            Validator.validateEmail(email);

            System.out.print("Enter Phone (10 digits starting 6-9): ");
            String phone = sc.nextLine();

            Validator.validatePhone(phone);

            System.out.print("Create Username: ");
            String username = sc.nextLine();


            System.out.print("Create Password: ");
            String password = sc.nextLine();

            // Object creation happens ONLY AFTER validation
            UserAccount account = new UserAccount(username, password);

            Employee employee = new Employee(
                    empId,
                    name,
                    email,
                    phone,
                    account);

            employee.persist();

            System.out.println("\nEmployee Registered Successfully");
           // System.out.println(employee);
            System.out.println(employee.toString());

        }
        catch (ValidationException e) {

            System.out.println("Validation Error : " + e.getMessage());

        }
        catch (IOException e){

            System.out.println("File Error : " + e.getMessage());

        }
    }
}
