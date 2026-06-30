import java.io.FileWriter;
import java.io.IOException;

// ================= Immutable Payslip =================
final class Payslip implements Cloneable {

    private final String empId;
    private final String empName;
    private final String month;
    private final double netPay;

    public Payslip(String empId, String empName, String month, double netPay) {
        this.empId = empId;
        this.empName = empName;
        this.month = month;
        this.netPay = netPay;
    }

    public String getEmpId() { return empId; }
    public String getEmpName() { return empName; }
    public String getMonth() { return month; }
    public double getNetPay() { return netPay; }

    @Override
    public Object clone() {
        return new Payslip(empId, empName, month, netPay);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Payslip)) return false;
        Payslip p = (Payslip)obj;
        return empId.equals(p.empId) && month.equals(p.month);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + empId.hashCode();
        result = 31 * result + month.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "=========== PAYSLIP ===========\n"
                + "Employee ID   : " + empId + "\n"
                + "Employee Name : " + empName + "\n"
                + "Month         : " + month + "\n"
                + "Net Pay       : " + netPay + "\n"
                + "===============================\n";
    }
}

// ================= Download Token =================
class DownloadToken {

    private long createdTime;
    private long expiryMillis;

    public DownloadToken() {
        createdTime = System.currentTimeMillis();
        expiryMillis = 60 * 1000;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - createdTime > expiryMillis;
    }
}

// ================= File Service =================
class FileService {

    public String savePayslipAsText(Payslip payslip) throws IOException {

        String fileName = "Payslip_" + payslip.getEmpId() + "_"
                + System.currentTimeMillis() + ".txt";

        FileWriter fw = new FileWriter(fileName);
        fw.write(payslip.toString());
        fw.close();

        return fileName;
    }

    public String savePayslipAsPdf(Payslip payslip) throws IOException {

        String fileName = "Payslip_" + payslip.getEmpId() + "_"
                + System.currentTimeMillis() + ".pdf";

        FileWriter fw = new FileWriter(fileName);
        fw.write(payslip.toString()); // demo only
        fw.close();

        return fileName;
    }
}

// ================= Main App =================
public class UC4PayslipDownloadApp {

    public static void main(String[] args) {

        System.out.println("==== USE CASE 4 : PAYSLIP DOWNLOAD ====");

        Payslip original = new Payslip(
                "EMP-1010",
                "John David",
                "January 2026",
                48500);

        System.out.println("\nOriginal Payslip");
        System.out.println(original);

        Payslip copy = (Payslip) original.clone();

        System.out.println("Clone Created Successfully.");

        System.out.println("Equals : " + original.equals(copy));
        System.out.println("Hash Original : " + original.hashCode());
        System.out.println("Hash Clone    : " + copy.hashCode());

        DownloadToken token = new DownloadToken();

        if (token.isExpired()) {
            System.out.println("Download Token Expired.");
            return;
        }

        FileService service = new FileService();

        try {

            String txt = service.savePayslipAsText(copy);
            String pdf = service.savePayslipAsPdf(copy);

            System.out.println("\nPayslip Saved Successfully.");
            System.out.println("Text File : " + txt);
            System.out.println("PDF File  : " + pdf);

        } catch (IOException e) {
            System.out.println("File Error : " + e.getMessage());
        }

        System.out.println("\nDownloaded Payslip");
        System.out.println(copy);

        System.out.println("Original object unchanged.");
    }
}
