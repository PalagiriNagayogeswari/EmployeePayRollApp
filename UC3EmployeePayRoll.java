import java.util.*;

class Employe{
    private String username;
    private String EmpId;
    public Employe(String username,String EmpId){
        this.username=username;
        this.EmpId=EmpId;
 }

    public String getUsername() {
        return username;
    }

    public String getEmpId() {
        return EmpId;
    }
}

class SalaryComponents{
    double basicSalary;
    double hra;
    double da;
    double allowances;
    double pf;
    double tax;
    double netPay;

    public SalaryComponents(double basicSalary,double hra,double da,double allowances){
        this.basicSalary=basicSalary;
        this.hra=hra;
        this.da=da;
        this.allowances=allowances;
    }
}

class PaySlip{
    private Employe employee;
    private SalaryComponents components;
    String month;
    public PaySlip(Employe employee,SalaryComponents components,String month){
        this.employee=employee;
        this.components=components;
        this.month=month;
    }
    @Override
    public String toString() {

        return "\n=========== PAYSLIP ===========\n"
                + "Month          : " + month + "\n"
                + "Employee ID    : " + employee.getEmpId() + "\n"
                + "Employee Name  : " + employee.getUsername() + "\n\n"

                + "------ Earnings ------\n"
                + "Basic Salary   : " + components.basicSalary + "\n"
                + "HRA            : " + components.hra + "\n"
                + "DA             : " + components.da + "\n"
                + "Allowances     : " + components.allowances + "\n\n"

                + "------ Deductions ------\n"
                + "PF             : " + components.pf + "\n"
                + "Tax            : " + components.tax + "\n\n"

                + "Net Pay        : " + components.netPay + "\n"
                + "===============================\n";
    }
}

class PayRollService{
    public static PaySlip generatePaySlip(Employe employee,String month,double basicSalary,double hra,
                                   double da,double allowances){
        SalaryComponents sc=new SalaryComponents(basicSalary,hra,da,allowances);
        double gross=basicSalary+hra+da+allowances;
        sc.pf=basicSalary*0.12;
        sc.tax=gross*0.10;
        sc.netPay=gross-(sc.pf+sc.tax);
        return new PaySlip(employee,sc,month);
    }
}

public class UC3EmployeePayRoll {
    public static void main(String[] args){
        System.out.println("===Use case 3:Employee PaySlip Generation===");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter username: ");
        String username=sc.next();
        System.out.println("Enter userId: ");
        String userId=sc.next();
        sc.nextLine();
        Employe employee=new Employe(username,userId);
        System.out.println("Enter month: ");
        String month=sc.nextLine();
        //sc.nextLine();
        System.out.println("Enter Basic Salary: ");
        double bs=sc.nextDouble();
        System.out.println("Enter HRA: ");
        double hra=sc.nextDouble();
        System.out.println("Enter DA: ");
        double da=sc.nextDouble();
        System.out.println("Enter allowances: ");
        double allowances=sc.nextDouble();
       // PayRollService.generatePaySlip(employee,month,bs,hra,da,allowances);
       // SalaryComponents components=new SalaryComponents(bs,hra,da,allowances);
        PaySlip ps = PayRollService.generatePaySlip(
                employee,
                month,
                bs,
                hra,
                da,
                allowances
        );
        System.out.println(ps);


    }
}