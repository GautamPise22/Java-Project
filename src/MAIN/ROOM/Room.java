package MAIN.ROOM;
import java.io.*;
import java.util.*;
import MAIN.BILL.Bill;
class AadharException extends Exception {
    AadharException(String s) {
        super(s);
    }
}
class RoomException extends Exception {
    RoomException(String s) {
        super(s);
    }
}
class PhoneException extends Exception {
    PhoneException(String s) {
        super(s);
    }
}
public class Room {
    static int[] roomnumber = new int[50];
    static String roomType;
    public Room (int val) {
        Arrays.fill(roomnumber, val);
    }
    public static void takeRoom() {
        try( BufferedWriter bWriter = new BufferedWriter (new FileWriter("D:\\Projects\\Java\\Java\\src\\records.txt", true))) {
            Scanner s = new Scanner(System.in);
            System.out.print("Enter Customer Name: ");
            String name = s.nextLine();
            System.out.print("Enter Customer Identity Number(Aadhar Card): ");
            String identity = s.next();
            if (!length(identity, 16)) throw new AadharException("Enter valid Aadhar Card Number.");
            System.out.print("Enter Customer Phone Number: ");
            String phoneNumber = s.next();
            if (!length(phoneNumber, 10)) throw new PhoneException("Enter valid Phone Number.");
            System.out.print("Enter Room Type (AC/Non-AC): ");
            roomType = s.next();
            double roomChargePerDay = 0;
            if (roomType.equalsIgnoreCase("AC")) roomChargePerDay = 2000.0;
            else if (roomType.equalsIgnoreCase("Non-AC")) roomChargePerDay = 1000.0;
            else throw new RoomException("Enter valid Room Type.");

            System.out.print("Enter Number of Days to Stay: ");
            double numberOfDays = s.nextInt();

            bWriter.write(" " +name);
            bWriter.write(" " +identity);
            bWriter.write(" " +phoneNumber);
            bWriter.write(" " +roomType);
            bWriter.write(" " +String.valueOf(numberOfDays));
            bWriter.newLine();

            Bill bill = new Bill(name, identity, phoneNumber, roomType, roomChargePerDay, numberOfDays);System.out.println("\n** Bill Details **");
            bill.printBill();
        } catch(AadharException ex1) {
            System.out.println("Error: " + ex1.getMessage());
        } catch(RoomException ex2) {
            System.out.println("Error: " + ex2.getMessage());
        } catch (PhoneException ex3) {
            System.out.println("Error: " +ex3.getMessage());
        } catch (IOException ex4) {
            System.out.println(ex4.getMessage());
        }
    }
    public static void leaveRoom() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Room Number to Leave: ");
        int room = s.nextInt();
        if (roomnumber[room] == -1) {
            System.out.println("Room " + room + " is already vacant! Please try valid Room Number");
        }else {
            System.out.println("Room " + room + " has been vacant. Thank you!");
            roomnumber[room]=-1;
        }
    }

    public static int RoomNumber() {
        if(roomType.equalsIgnoreCase("AC"))
        { for (int i = 1; i <=25; i++) {
            if (roomnumber[i] == -1) {
                roomnumber[i] = i;
                return i;
            }
        }
        } else if (roomType.equalsIgnoreCase("Non-AC")) {
            for (int i = 26; i <=50; i++) {
                if (roomnumber[i] == -1) {
                    roomnumber[i] = i;
                    return i;
                }
            }
        }
        return -1;
    }
    public static boolean length(String str, int k) {
        int c;
        for(int j=0;j<str.length();j++) {
            c=str.charAt(j);
            if(c>=48 && c<= 57 && str.length()==k){
                return true;
            }
        }
        return false;
    }
}