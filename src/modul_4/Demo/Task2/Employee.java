package modul_4.Demo.Task2;

public class Employee implements Comparable<Employee> {

    private int    employeeId;
    private String name;
    private String department;
    private String position;
    private String email;

    public Employee(int employeeId, String name, String department, String position, String email) {
        this.employeeId = employeeId;
        this.name       = name;
        this.department = department;
        this.position   = position;
        this.email      = email;
    }

//    public Employee(int employeeId, String name, String department) {
//        this(employeeId, name, department, "-", "-");
//    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.employeeId, other.employeeId);
    }

    public int    getEmployeeId()  { return employeeId; }
    public String getName()        { return name; }
    public String getDepartment()  { return department; }
    public String getPosition()    { return position; }
    public String getEmail()       { return email; }

    public void setName(String name)             { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setPosition(String position)     { this.position = position; }
    public void setEmail(String email)           { this.email = email; }

    @Override
    public String toString() {
        return String.format("Employee[id=%-4d | %-20s | dept=%-15s | pos=%-12s | email=%s]",
                employeeId, name, department, position, email);
    }
}