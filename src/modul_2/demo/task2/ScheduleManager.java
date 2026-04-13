package modul_2.demo.task2;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ScheduleManager {
    private LinkedList<Schedule> schedules;

    public ScheduleManager() {
        this.schedules = new LinkedList<>();
    }

    public void addFirst(Schedule schedule) {
        schedules.addFirst(schedule);
    }

    public void addLast(Schedule schedule) {
        schedules.addLast(schedule);
    }

    public Schedule removeFirst() {
        if (!schedules.isEmpty()) {
            return schedules.removeFirst();
        }
        return null;
    }

    public Schedule removeLast() {
        if (!schedules.isEmpty()) {
            return schedules.removeLast();
        }
        return null;
    }

    public List<Schedule> searchRoutes(String keyword) {
        List<Schedule> results = new ArrayList<>();
        for (Schedule s : schedules) {
            if (s.getOrigin().equalsIgnoreCase(keyword) || s.getDestination().equalsIgnoreCase(keyword)) {
                results.add(s);
            }
        }
        return results;
    }

    public void displayAll() {
        if (schedules.isEmpty()) {
            System.out.println("Tidak ada jadwal kereta saat ini.");
            return;
        }

        System.out.println();
        Iterator<Schedule> iterator = schedules.iterator();
        while (iterator.hasNext()) {
            Schedule s = iterator.next();
            s.displayInfo();
        }
        System.out.println();
    }

    public boolean cleanupSchedule(String trainCodeToRemove) {
        boolean isRemoved = false;
        Iterator<Schedule> iterator = schedules.iterator();

        while (iterator.hasNext()) {
            Schedule currentSchedule = iterator.next();
            if (currentSchedule.getTrainCode().equalsIgnoreCase(trainCodeToRemove)) {
                iterator.remove();
                isRemoved = true;
            }
        }
        return isRemoved;
    }
}