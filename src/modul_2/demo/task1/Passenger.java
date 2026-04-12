package modul_2.demo.task1;

public class Passenger {
    private static int idCounter = 1;
    private int passengerId;
    private String name;
    private String email;
    private String phone;
    private int age;

    public Passenger(String name, String email, String phone, int age) {
        this.passengerId = idCounter++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayInfo() {
        System.out.printf("ID: %-4d | Nama: %-15s | Umur: %-3d | Email: %-20s | No. HP: %s%n",
                passengerId, name, age, email, phone);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}