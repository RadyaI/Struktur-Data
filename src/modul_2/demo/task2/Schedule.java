package modul_2.demo.task2;

public class Schedule {
    private static int idCounter = 1;
    private int scheduleId;
    private String trainCode;
    private String trainName;
    private String origin;
    private String destination;
    private String departureTime;
    private double baseFare;

    public Schedule(String trainCode, String trainName, String origin, String destination, String departureTime, double baseFare) {
        this.scheduleId = idCounter++;
        this.trainCode = trainCode;
        this.trainName = trainName;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.baseFare = baseFare;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public void displayInfo() {
        System.out.printf("ID: %-3d | Kode: %-8s | Kereta: %-15s | Rute: %-10s -> %-10s | Berangkat: %-6s | Harga: Rp%,.2f%n",
                scheduleId, trainCode, trainName, origin, destination, departureTime, baseFare);
    }
}