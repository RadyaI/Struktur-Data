package modul_4.Demo.Task2;

/**
 * Data model representing a railway employee.
 * Implements Comparable<Employee> so BST can sort/compare by employeeId.
 */
public class Employee implements Comparable<Employee> {

    private int    employeeId;
    private String name;
    private String department;
    private String position;      // e.g. "Masinis", "Kondektur", "Teknisi"
    private String email;

    // ─── Constructor ───────────────────────────────────────────────────────────

    public Employee(int employeeId, String name, String department, String position, String email) {
        this.employeeId = employeeId;
        this.name       = name;
        this.department = department;
        this.position   = position;
        this.email      = email;
    }

    // Minimal constructor (id + name + department)
    public Employee(int employeeId, String name, String department) {
        this(employeeId, name, department, "-", "-");
    }

    // ─── Comparable ────────────────────────────────────────────────────────────

    /**
     * Compare based strictly on employeeId.
     * Negative  → this < other
     * Zero      → this == other
     * Positive  → this > other
     */
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.employeeId, other.employeeId);
    }

    // ─── Getters & Setters ─────────────────────────────────────────────────────

    public int    getEmployeeId()  { return employeeId; }
    public String getName()        { return name; }
    public String getDepartment()  { return department; }
    public String getPosition()    { return position; }
    public String getEmail()       { return email; }

    public void setName(String name)             { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setPosition(String position)     { this.position = position; }
    public void setEmail(String email)           { this.email = email; }

    // ─── toString ──────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return String.format("Employee[id=%-4d | %-20s | dept=%-15s | pos=%-12s | email=%s]",
                employeeId, name, department, position, email);
    }
}