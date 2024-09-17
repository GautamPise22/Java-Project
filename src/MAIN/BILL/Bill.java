package MAIN.BILL;

import MAIN.HOTEL.Hotel;
import MAIN.ROOM.Room;

public class Bill extends Hotel {
    protected double numberOfDays;
    public Bill(String name, String identity, String phoneNumber, String roomType, double roomChargePerDay,
                double numberOfDays) {
        super(name, identity, phoneNumber, roomType, roomChargePerDay);
        this.numberOfDays = numberOfDays;
    }
    public double calculateBill(double numberOfDays) {
        double roomCharge = roomChargePerDay * numberOfDays;
        double gst = 0.18;
        double totalBill = roomCharge + (roomCharge * gst);
        return totalBill;
    }
    public void printBill() {
        double totalBill = calculateBill(numberOfDays);
        System.out.println("Customer Name: " + name);
        System.out.println("Customer Aadhar Card Number: " + identity);
        System.out.println("Customer Phone Number: " + phoneNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Room Number: " + Room.RoomNumber());
        System.out.println("Room Charge Per Day: ₹" + roomChargePerDay);
        System.out.println("Number of Days: " + numberOfDays);
        System.out.println("Room Charge: ₹" + (roomChargePerDay * numberOfDays));
        System.out.println("GST (18%): ₹" + ((roomChargePerDay * numberOfDays) * 0.18));
        System.out.println("Total Bill (including GST): ₹" + totalBill);
    }
}
