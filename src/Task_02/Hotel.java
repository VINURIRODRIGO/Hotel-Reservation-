package Task_02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Hotel {
    private static  String[] hotel = new String[8];// Appending customer data to a  array
    static String pathname = "C:\\Users\\chami\\IdeaProjects\\Course work\\src\\Task_02\\My project\\StoreData.txt";//Room Details
    static ArrayList<String>data = new ArrayList<>();//Customer waiting list

    public static int menu(String choice){ // Separating choices as user wish

        choice=choice.toUpperCase();

        switch (choice) {

            case "A" -> { // Add customers into rooms or into the waiting list
                Hotel check = new Hotel(); //creating a default constructor
                hotel = check.readCustomerData(hotel);
                data = Room.addCustomer(hotel);
                return -1;
            }
            case "V" -> { // View all rooms
                Hotel check = new Hotel();
                hotel = check.readCustomerData(hotel);

                Room.allRooms(hotel);
                return -1;
            }
            case "E" -> { // View Empty rooms
                Hotel check = new Hotel();
                hotel = check.readCustomerData(hotel);
                Room.empty(hotel);
                return -1;
            }
            case "D" -> { // Delete a customer from the room
                Hotel check = new Hotel();
                hotel = check.readCustomerData(hotel);
                Room.delete(hotel);
                return -1;
            }
            case "F" -> {// Find room from customer name
                Hotel check = new Hotel();
                hotel = check.readCustomerData(hotel);
                find(hotel);
                return -1;
            }
            case "S" -> { // Store data into a file
                String [] loadData = Room.readCustomer();// Loading data
                if (hotel[0]!=null){
                    //Adding Room details(no waiting list)
                    if (data.size()== hotel.length){
                Room.storeCustomerData(hotel); }

                    else {//Appending data the waiting list
                        Room.storeCustomerData(data);
                        Room.storeCustomerData(hotel);
                    }
                } else {
                    // When no new data input
                    Room.storeCustomerData(loadData);
                }
                return -1;
            }
            case "L" -> { // Loading  data from the file
                loadCustomerData();
                return -1;
            }
            case "O" -> { // View guests Ordered alphabetically by name
                Hotel check = new Hotel();
                hotel = check.readCustomerData(hotel);
                alphabeticalOrder(hotel);
                return -1;
            }
            default -> {
                System.out.println("Sorry invalid choice");
                return -1;
            }
        }
    }

    /* Reading Room Details
     * (Reading StoreData.txt file for Hotel Room maintenance)
     * */

    public String [] readCustomerData(String[] hotel) {

        int i=0;// first index of the array
        //Reading the Room maintenance file
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                //Appending data to the array
                hotel[i]=data;
                i++;//increasing Index(next index)
            }
            //When all rooms are null(when the text file is empty)
            if (myObj.length()==0){
                for (int x = i; x<hotel.length;x++){
                    hotel[x]="e : e : e : e"; }
            }
            myReader.close();

        }catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();

        }return hotel;
    }

    public static void find(String[]hotel){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the First Name : ");
        String firstname= input.next();
        if (Room.checkingValidName(firstname)){
        System.out.println("Enter the Surname : ");
        String surname= input.next();

            if (Room.checkingValidName(surname)){
        for(int x = 0; x < hotel.length; x++ ){
            // finding whether the customer is having a room or not

            String data = hotel[x];
            //Getting the first and the second element in to an array
            String[] arrOfStr = data.split(" : ");

            // converting Capitalize

            firstname= firstname.substring(0,1).toUpperCase()+firstname.substring(1).toLowerCase();
            surname = surname.substring(0,1).toUpperCase()+surname.substring(1).toLowerCase();

            if(arrOfStr[0].equals(firstname) && arrOfStr[1].equals(surname)){

                System.out.println(firstname+" "+surname+" has booked room number "+ x); break;
            }
            // customer is not reserve any room

            else if (x==(hotel.length)-1&&!arrOfStr[0].equals(firstname) && !arrOfStr[1].equals(surname)){
                System.out.println(firstname+" "+surname+" is not having any reservation");
            }
        }}else {
                System.out.println("Invalid input");}
        }else {
            System.out.println("Invalid input");
        }
    }

    /* Loading data to the text file
     * (Reading data from the StoreData.txt file)
     * */

    public static void loadCustomerData() {
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                myReader.nextLine();

            }
            System.out.println("Successfully loaded");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

     /* Sorting names in alphabetical order */

    static boolean lessThan(String s1, String s2) {
        boolean flag = true;
        int i, lim;

        String shorter;
        // converting strings to lower case
        String first = s1.toLowerCase(), second = s2.toLowerCase();
        // finding shorter string setting iteration limit to its length
        if (first.length() <= second.length()) {
            shorter = first;
            lim = first.length();
        } else {
            shorter = second;
            lim = second.length();
        }
        // If each character is the same, compare them one by one
        for (i = 0; i < lim; i++)
            if (first.charAt(i) != second.charAt(i)) {
                if (first.charAt(i) < second.charAt(i)){
                    flag = false;}
                // breaks the loop since the characters are not equal
                break;
            }
        // condition if one name is contained in another (Harini Rodrigo and Harini Rodrigos)
        if (i == lim && first.equals(shorter))
            flag = false;
        return flag;
    }

    /* Reference :
     Author: Eldelshell ;
     Availability: https://stackoverflow.com/questions/26789205/alphabetize-strings-without-using-compareto-method
     */

    public static void alphabeticalOrder(String[]hotel) {
        String t;
        // converting uppercase letters to lowercase

        for (int i = 0; i < hotel.length; i++){

            String data = hotel[i];
            //Getting the first and the second element in to an array
            String[] arrOfStr = data.split(" : ");
            hotel[i] = (arrOfStr[0]+" "+arrOfStr[1]);
        }

        for (int i = 0; i < hotel.length; i++)
            for (int j = 0; j < hotel.length - i-1; j++)
                // compares two adjacent strings and exchanges them if not in
                // correct order
                if (lessThan(hotel[j], hotel[j + 1])) {
                    t = hotel[j];
                    hotel[j] = hotel[j + 1];
                    hotel[j + 1] = t;
                }
        // printing the names
        for (String i : hotel) {
            if (!i.equals("e e")){
                System.out.println(i);}
        }


    }

}

