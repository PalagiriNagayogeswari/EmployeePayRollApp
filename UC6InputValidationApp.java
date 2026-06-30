import java.util.*;
class ValidationExceptions extends Exception{
    public ValidationExceptions(String message){
        super(message);
    }
}


class EmailValidationException extends ValidationExceptions{
    public EmailValidationException(String message) {
        super(message);
    }
}
class phoneValidationException extends ValidationExceptions{
    public phoneValidationException(String message) {
        super(message);
    }
}

class passwordValidationException extends ValidationExceptions{
    public passwordValidationException(String message) {
        super(message);
    }
}

class EmployeeIdValidationException extends ValidationExceptions{
    public EmployeeIdValidationException(String message) {
        super(message);
    }
}

class ValidationService {

    // Removes leading and trailing spaces
    private static String sanitize(String input) {

        if (input == null) {
            return "";
        }

        return input.trim();
    }

    // Validate Email
    public static void validateEmail(String email)
            throws EmailValidationException {

        email = sanitize(email);

        String regex = "^[A-Za-z0-9][A-Za-z0-9+_.-]*@[A-Za-z0-9-]+\\.[A-Za-z]{2,}$";

        if (!email.matches(regex)) {
            throw new EmailValidationException("Invalid Email Address.");
        }
    }

    // Validate Phone Number
    public static void validatePhone(String phone)
            throws phoneValidationException {

        phone = sanitize(phone);

        String regex = "^[6-9][0-9]{9}$";

        if (!phone.matches(regex)) {
            throw new phoneValidationException(
                    "Invalid Phone Number. Must be 10 digits starting with 6-9."
            );
        }
    }

    // Validate Password
    public static void validatePassword(String password)
            throws passwordValidationException {

        password = sanitize(password);

        String regex =
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        if (!password.matches(regex)) {
            throw new passwordValidationException(
                    "Password must contain uppercase, lowercase, digit, special character and minimum 8 characters."
            );
        }
    }

    // Validate Employee ID
    public static void validateEmployeeId(String empId)
            throws EmployeeIdValidationException {

        empId = sanitize(empId);

        String regex = "^EMP-[0-9]{4}$";

        if (!empId.matches(regex)) {
            throw new EmployeeIdValidationException(
                    "Employee ID must be in EMP-XXXX format."
            );
        }
    }
}

public class UC6InputValidationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("===Use Case 6:Input Validation App===");
        try{
            System.out.println("Enter EmpID ");
            String empID=sc.next();
            ValidationService.validateEmployeeId(empID);

            System.out.println("Enter email Id: ");
            String email=sc.next();
            ValidationService.validateEmail(email);

            System.out.println("Enter phonenumber: ");
            String phonenum=sc.next();
            ValidationService.validatePhone(phonenum);

            System.out.println("Enter Password: ");
            String password=sc.next();
            ValidationService.validatePassword(password);

            System.out.println("validation succeeded.Login,Register can Proceed");
        }catch(ValidationExceptions e){
            System.out.println("Validation Failed");
            System.out.println(e.getMessage());
        }
    }
}
