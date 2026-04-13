package modul_2.demo.task1;

import java.util.ArrayList;

public class PassengerManager {
    private ArrayList<Passenger> passengers;

    public PassengerManager() {
        this.passengers = new ArrayList<>();
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public Passenger getPassenger(int index) {
        if (index >= 0 && index < passengers.size()) {
            return passengers.get(index);
        }
        return null;
    }

    public Passenger searchPassengerByName(String name) {
        for (Passenger p : passengers) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public boolean updatePassenger(int index, String name, String email, String phone, int age) {
        if (index >= 0 && index < passengers.size()) {
            Passenger p = passengers.get(index);
            p.setName(name);
            p.setEmail(email);
            p.setPhone(phone);
            p.setAge(age);
            return true;
        }
        return false;
    }

    public boolean removeByIndex(int index) {
        if (index >= 0 && index < passengers.size()) {
            passengers.remove(index);
            return true;
        }
        return false;
    }

    public boolean removeById(int id) {
        for (int i = 0; i < passengers.size(); i++) {
            if (passengers.get(i).getPassengerId() == id) {
                passengers.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayAll() {
        if (passengers.isEmpty()) {
            System.out.println("Data penumpang masih kosong.");
            return;
        }
        System.out.println();
        for (Passenger p : passengers) {
            p.displayInfo();
        }
        System.out.println();
    }
}