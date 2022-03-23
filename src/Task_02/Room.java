package Task_02;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Room {

     static Scanner input = new Scanner(System.in);
     //Room maintenance
     static String filepath = "C:\\Users\\chami\\IdeaProjects\\Course work\\src\\Task_02\\My project\\StoreData.txt";
     //Waiting list maintenance
     static String path="C:\\Users\\chami\\IdeaProjects\\Course work\\src\\Task_02\\My project\\WaitingList.txt";


    /* Reference :
     Author: w3schools.com ;
     Availability: https://www.w3schools.com/java/java_files_create.asp
     */

    /*Store Room Details
     * (Overwriting the data to the StoreData.txt file)
     *  */
     public static void storeCustomerData(String[] hotel) {

        try {
            FileWriter myWriter = new FileWriter(filepath);

            for (String i:hotel){
                myWriter.write(i+"\n");
            } myWriter.close();
            System.out.println("Successfully Updated");
            System.out.println("Thank you!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /* Reference :
    Author: Professor Saad ;
     Availability: https://www.youtube.com/watch?v=epDEG6YstSU
     */

    public static void storeCustomerData(ArrayList<String>hotel) {

        FileWriter file = null;
        try {
            for (int i=8;i< hotel.size();i++){
            file = new FileWriter(path,true);
            BufferedWriter b = new BufferedWriter(file);
            b.write(hotel.get(i));
            b.newLine();
            b.close();}
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void empty (String[]hotel){
         //Displaying the empty Rooms
        for (int i=0;i< hotel.length;i++){
            if (hotel[i].equals("e : e : e : e")){
                System.out.println(i+" is Empty");
            }
        }
        // //All rooms are full
        for (int i=0;i< hotel.length;i++){
            if (hotel[i].equals("e : e : e : e")){
                break; }
            else if (i== hotel.length-1&&!hotel[i].equals("e : e : e : e")){
                System.out.println("No Empty Room");
            }
        }
    }
    /* Loading the Room Details before update StoreData.txt file
    * (Reading StoreData.txt file)
    * */
    public static String [] readCustomer() {

        String[] hotel = new String[8];
        int i=0;

        //Reading the file
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);

            // Reading data line by line
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                //Appending data to the array
                hotel[i]=data;
                i++;
            }
            // When the text file is empty
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

    public static void allRooms(String[] hotel) {
        for (int i=0;i< hotel.length;i++) {
            //for empty rooms
            if (hotel[i].equals("e : e : e : e")){
                System.out.println(i +" room is empty");
            }//for reserved rooms
            else if (!hotel[i].equals("e : e : e : e")){
                // finding whether the customer is having a room or not
                String data = hotel[i];
                //Getting the first and the second element in to an array
                String[] arrOfStr = data.split(" : ");
                System.out.println("Room "+i+" is reserved by "+arrOfStr[0]+" "+arrOfStr[1]);}
        }
    }

    /* Reference :
    Author: McMillen,Hunter ;
     Availability: https://stackoverflow.com/questions/17575840/better-way-to-generate-array-of-all-letters-in-the-alphabet
     */

    public static boolean checkingValidName(String name){
        try {

        String names = name.toLowerCase();
        // Regex to check valid name.
        char [] numletter = names.toCharArray();
        char [] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (char validity : numletter){
            for (int i=0;i<letters.length;i++){
                if ((validity==letters[i])){

                    if(i!=letters.length-1){
                        break;
                    }
                } else if (i == letters.length - 1) {
                    return false;
                }
            }
        }}catch (Exception e){
            System.out.println("Invalid");
        }
        return true;
    }
  /* Reference :
    Author: AutomationLabs, Naveen ;
     Availability: https://www.youtube.com/watch?v=z_Cys4W3OOk
     */
    //Checking validation numbers

    public static boolean isNumber (CharSequence cs){
        int len = cs.length();
        for (int i=0; i<len;i++){
            if (!Character.isDigit(cs.charAt(i))){
                return false;
            }}return true;
    }

    /*Customer Data*/

    public static String data(){
        String x = null;
        boolean tr =true;
    while (tr) {
        System.out.print("\nEnter the first name : ");// name of the customer in that particular room
        String firstName = input.next();
        if (checkingValidName(firstName)) {

            System.out.print("\nEnter the Surname : ");// name of the customer in that particular room
            String surname = input.next();
            if (checkingValidName(surname)){
                System.out.print("\nNumber of guests in a room : ");// name of the customer in that particular room
                String NoofGusts = input.next();

                if (isNumber(NoofGusts)&&Integer.parseInt(NoofGusts)>=1){
                System.out.print("\nEnter the Card number : ");// name of the customer in that particular room
                String cardNumber = input.next();
                    if (isNumber(cardNumber)){
                // return true for valid first name
                firstName= firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();
                surname = surname.substring(0,1).toUpperCase()+surname.substring(1).toLowerCase();

                x = (firstName + " : " + surname + " : " + NoofGusts + " : " + cardNumber);
                tr = false;
            }else
                System.out.println("Invalid number");

                }else
                    System.out.println("Invalid number, the guest number should grater than 1");
            }else {
                System.out.println("Invalid input");


            }
        }else {
            System.out.println("Invalid input");

             }}
        return  x;
    }

public static String addcus(String [] hotel){
    int i=0;

    while (!hotel[i].equals("e : e : e : e")){

        if (i== hotel.length-1){
            System.out.print("All rooms are full \nNeed to put to waiting list \"y\" for Yes \"n\" for No : ");
            String yes = input.next();
            yes = yes.toLowerCase();
            if (yes.equals("y")){
                return data();

            }else {
                return null;
            }
        }i++;}return null;
}

      /* Room reservation*/

    public static ArrayList<String> addCustomer (String[] hotel) {
        Scanner input = new Scanner(System.in);
        int roomNum=0;
        boolean y = true;
        ArrayList<String> waitingList = new ArrayList<>();// waiting list array

        //Appending Room  reserves details to the array list
        Collections.addAll(waitingList, hotel);
    while (y){
        for (int a=0;a< 8;a++){
            if (hotel[a].equals("e : e : e : e")){
                // Displaying available rooms

                    System.out.println("\nAvailable Room numbers ");
                    for (int roomDetails=0;roomDetails< hotel.length;roomDetails++){
                        if (hotel[roomDetails].equals("e : e : e : e")){
                            System.out.println(" Room No : "+roomDetails);
                        }}

                    /* Adding customer to the room*/

                    System.out.print("\nEnter room number (0-7) or 8 to Stop adding Customers : ");
                    try {

                        roomNum = input.nextInt();
                        //Checking Room availability
                        if (roomNum >= 0 && roomNum < 8 && hotel[roomNum].equals("e : e : e : e")) {

                            String f = data();
                            hotel[roomNum]=f;
                            waitingList.set(roomNum,f);


                        }
                        else if (roomNum == 8) {
                            // when stop adding customers
                            System.out.print("\nNeed to confirm the reservation\nEnter \"y\" for yes else \"n\" : ");
                            String conformation=input.next();

                            if (conformation.equals("y")){
                                System.out.println("\nEnter \"S\" in the menu to confirm the reservation");
                                y=false;
                                break;


                            }else {
                                System.out.println("You canceled the reservation");
                            }
                            y=false;
                            break;
                        }// For reserved rooms
                        else if (!hotel[roomNum].equals("e : e : e : e")) {
                            System.out.println("This room has already reserved by someone");
                        }
                        // For invalid room numbers
                        else {
                            System.out.println("No room.Room numbers are between 0-7\nPlease try again\n");

                        }
                    }
                    // Invalid data
                    catch (Exception e) {
                        System.out.println(e + " : Invalid input");
                        y=false;
                        break;
                    }

                // Adding to the waiting list
            } else if (a== hotel.length-1){
                String dataString = "";
                while (dataString!=null){
                    dataString = addcus(hotel);
                    if (dataString!=null){
                        waitingList.add(dataString);}}

                System.out.print("\nNeed to confirm the reservation\nEnter \"y\" for yes else \"n\" : ");
                String conformation=input.next();

                if (conformation.equals("y")){
                    System.out.println("Enter \"S\" in the menu to confirm the reservation");
                    y=false;
                    break;

                }else {
                    System.out.println("You canceled the reservation");
                }

                y=false;
                break;
            }
        }}

        //return the array to the main program
        return waitingList;
    }



    /**Deleting customer from room */

    public static String[] delete (String [] hotel){
        //Getting first name, Surname and the room number to delete the customer
        System.out.print("Enter the First Name : ");
        String firstname= input.next();
        //Validation of first name
        if (checkingValidName(firstname)){

            System.out.print("Enter the Surname : ");
            String surname= input.next();
        //Validation of second name
            if (checkingValidName(surname)){

            System.out.print("Enter the Room number : ");
            String roomNo = input.next();
            if (isNumber(roomNo)){
              // Checking whether the customer has a room
                for(int x = 0; x < hotel.length; x++ ){
                    String data = hotel[x];
                    //Getting the first and the second element of the String
                    String[] arrOfStr = data.split(" : ");

                    // Capitalization of the first name and second name
                    firstname= firstname.substring(0,1).toUpperCase()+firstname.substring(1).toLowerCase();
                    surname = surname.substring(0,1).toUpperCase()+surname.substring(1).toLowerCase();

                    /*Cancellation of customer reservation*/
                    if(arrOfStr[0].equals(firstname)&& arrOfStr[1].equals(surname)&&Integer.parseInt(roomNo)==x){

                        System.out.println("Are you sure you want to delete \nEnter \"y\" to delete else Enter \"c\" to cancel");
                        String yes = input.next();
                        yes = yes.toLowerCase();
                        if (yes.equals("y")){

                            // Room is reserving to the new customer in the waiting list

                            String cus = Person.main();
                            if (cus!=null){
                                hotel[x]=cus;
                            Room.storeCustomerData(hotel);
                                return hotel;// Updating the Room maintenance text file (StoreData.txt file)
                            }

                            //When waiting list is empty
                            else {
                                hotel[x]="e : e : e : e";
                                Room.storeCustomerData(hotel);
                                return hotel; }
                            }

                        else if (yes.equals("c")){break;}
                        else{
                            System.out.println("Sorry wrong input");
                            break;
                        }
                    }
                    //If the customer does not have a reservation
                    else if(x == hotel.length - 1){
                        System.out.println("No match found!");break; }
                }
            }else {
                System.out.println("Invalid number");
            }} else {
                System.out.println("Invalid input");}

        }else {
            System.out.println("Invalid input"); }

        return null;
    }

}
