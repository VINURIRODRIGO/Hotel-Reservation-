package Task_02;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

     static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int loop =-1;

        while(loop ==-1){
            System.out.println("\n========== Welcome to Marriott Room Reservation System ==========");
            System.out.println("A: Add a customer to a room");
            System.out.println("V: View all rooms");
            System.out.println("E: Display Empty rooms");
            System.out.println("D: Delete customer from room");
            System.out.println("F: Find room from customer name");
            System.out.println("S: Store program data into file");
            System.out.println("L: Load program data from file");
            System.out.println("O: View guests names alphabetical Order");
            System.out.println("Q: Quit the program");
            System.out.print("\nEnter your choice : ");
            String choice = input.next();

            if (choice.equals("Q") || choice.equals("q")){// When user select quit option

                System.out.println(" ");
                System.out.println("Thank you! Have a nice day.....");
                break;}

            Hotel.menu(choice); // Returning the choice

            /* Reference :
                    Availability: https://stackoverflow.com/questions/15446689/what-is-the-use-of-system-in-read#:~:text=Up%20vote%201-,System.,to%20courses%20that%20teach%20programming.
                */

            System.out.println("\nPress \"ENTER\" to Go to the menu else print \"n\"");
            int x = System.in.read();// abstract method//Decimal of the ASCII values

            if (x==10){ // When user press "Enter"
                loop=-1;
            }
            else if (x==110){// When user input "n"
                System.out.println(" ");
                System.out.println("Thank you! Have a nice day.....");break;}

            else{ // Wrong choice
                System.out.println("Invalid choice\n");
                loop=-1; }

        }
    }
}
