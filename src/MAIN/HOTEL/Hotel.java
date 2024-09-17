package MAIN.HOTEL;
import MAIN.CUSTOMER.Customer;

public class Hotel extends Customer {
    protected String roomType;
    public double roomChargePerDay;
    public Hotel(String name, String identity, String phoneNumber, String roomType, double roomChargePerDay) {
        super(name, identity, phoneNumber);
        this.roomType = roomType;
        this.roomChargePerDay = roomChargePerDay;
    }
}
