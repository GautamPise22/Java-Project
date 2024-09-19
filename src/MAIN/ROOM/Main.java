package MAIN.ROOM;
import java.util.*;
import MAIN.ROOM.Room;

public class Main {
    static int i = 1;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Room room = new Room(-1);
        Room[] r= new Room[50];

        while (true) {
            System.out.println("\n** Hotel Management System **");
            System.out.println("1. Take a Room");
            System.out.println("2. Leave a Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");    
            int ch = s.nextInt();

            switch (ch) {
                case 1:
                    r[i].takeRoom();
                    i++;
                    break;
                case 2:
                    r[i].leaveRoom();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

}
