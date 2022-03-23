package Task_01;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hotel {

        static Scanner input = new Scanner(System.in);
        private static  String[] hotel = new String[8];
        //Room maintenance
        static String partname = "C:\\Users\\chami\\IdeaProjects\\Course work\\src\\Task_01\\text_files\\StoreData.txt";

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

                if (choice.equals("Q") || choice.equals("q")){ // When user select quit option
                    System.out.println("\nThank you! Have a nice day.....");break;}

                menu(choice);// Returning the choice

                /* Reference :
                    Availability: https://stackoverflow.com/questions/15446689/what-is-the-use-of-system-in-read#:~:text=Up%20vote%201-,System.,to%20courses%20that%20teach%20programming.
                */

                System.out.println("\nPress \"ENTER\" to Go to the menu else print \"n\"");
                int x = System.in.read(); // abstract method//Decimal of the ASCII values

                if (x==10){ // When user press "Enter"
                    loop=-1;
                }
                else if (x==110){ // When user input "n" .
                    System.out.println("\nThank you! Have a nice day.....");break;}

                else{ // Wrong choice
                    System.out.println("Invalid choice\n");
                    loop=-1; }
            }
        }

        public static int menu(String choice){

            choice=choice.toUpperCase();// Converting choice to uppercase

            switch (choice){

                case "A":{ // Add a customer to a room
                    Hotel check = new Hotel(); //creating a default constructor
                    hotel = check.readCustomerData(hotel);
                    addCustomer(hotel);
                    return -1;}

                case "V":{ // To view all rooms
                    Hotel check = new Hotel();
                    hotel = check.readCustomerData(hotel);
                    allRooms(hotel);
                    return -1;}

                case "E":{ // To view Empty rooms
                    Hotel check = new Hotel();
                    hotel = check.readCustomerData(hotel);
                    empty(hotel);
                    return -1;}

                case "D":{ // To delete customer from room
                    Hotel check = new Hotel();
                    hotel = check.readCustomerData(hotel);
                    delete(hotel);
                    return -1;}

                case "F":{ // To find room from customer name
                    Hotel check = new Hotel();
                    hotel = check.readCustomerData(hotel);
                    find(hotel);
                    return -1;}

                case "S":{ // To store data into file
                    String [] loadData = readCustomer();// Loading data
                    if (hotel[0]!=null){
                    storeCustomerData(hotel);}
                    else {
                        storeCustomerData(loadData);
                    }
                    return -1;}

                case "L":{ // To load data from file
                    loadCustomerData();
                    return -1;}

                case "O":{ // View guests Ordered alphabetically by name
                    Hotel check = new Hotel();
                    hotel = check.readCustomerData(hotel);
                    alphabeticalOrder(hotel);
                    return -1;}

                default:
                    System.out.println("\nSorry invalid choice");
                    return -1;}
        }

        public static void allRooms(String[] hotel) {

            System.out.println(" ");
            for (int i=0;i< hotel.length;i++) {

                if (hotel[i].equals("e : e : e : e")){ // Checking empty rooms
                    System.out.println(i +" room is empty");

                }else if (!hotel[i].equals("e : e : e : e")){ // Checking reserved rooms
                    String data = hotel[i]; // Room availability
                    String[] arrOfStr = data.split(" : "); // Getting the name and the surname into the array
                    System.out.println("Room "+i+" is reserved by "+arrOfStr[0]+" "+arrOfStr[1]);}
            }
        }

        /**Deleting customer from room */
        public static void delete (String [] hotel){

            System.out.print("\nEnter the First Name : ");
            String firstname= input.next();

            if (checkingValidName(firstname)){ // Check the validity of the First name and getting the Surname
                System.out.print("Enter the Surname : ");
                String surname= input.next();

                if (checkingValidName(surname)){ // Check the validity of the Surname
                    System.out.print("Enter the Room number : ");// name of the customer in that particular room
                    String roomNo = input.next();
                    if (isNumber(roomNo)){
                    for(int x = 0; x < hotel.length; x++ ){ // Checking if the customer has a room

                        //finding whether the customer is having a room or not
                        String data = hotel[x];
                        //Getting the first and the second element in to an array
                        String[] arrOfStr = data.split(" : ");

                        // converting to capitalize
                        firstname= firstname.substring(0,1).toUpperCase()+firstname.substring(1).toLowerCase();
                        surname = surname.substring(0,1).toUpperCase()+surname.substring(1).toLowerCase();

                        /*Cancellation of customer reservation*/
                        if(arrOfStr[0].equals(firstname)&& arrOfStr[1].equals(surname)&&x==Integer.parseInt(roomNo)){
                            System.out.print("\nAre you sure you want to delete \nEnter \"y\" to delete else Enter \"c\" to cancel : ");
                            String yes = input.next();
                            yes = yes.toLowerCase();// Changing uppercase to lowercase as required.
                            if (yes.equals("y")){
                                hotel[x]="e : e : e : e";
                                storeCustomerData(hotel);// Deleting customer from the text file
                                break;}
                            else if (yes.equals("c")){break;}
                            else{
                                System.out.println("Sorry wrong input");
                                break;
                            }
                        }


                        /*If the customer does not have a reservation*/
                        else if(x==(hotel.length)-1&&!arrOfStr[0].equals(firstname)&&arrOfStr[1].equals(surname)){
                            System.out.println(firstname+" "+surname+" has no reservations.");

                        }
                        //return the array to the main program
                    }
                }else {
                        System.out.println("Invalid number");
                    }
                }else {
                    System.out.println("Invalid input");
                }
            }else {
                System.out.println("Invalid input");
            }
        }
/* Reading Room Details
* (Reading StoreData.txt file for Hotel Room maintenance)
* */
        public String [] readCustomerData(String[] hotel) {

            int i=0;// first index of the array
            //Reading the file
            try {
                File myObj = new File(partname);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    //Appending the data to the array
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
        public static void find(String[]hotel) {


            System.out.print("\nEnter the First Name : ");
            String firstname = input.next();

            if (checkingValidName(firstname)) { // Checking the validity of the First name and getting the Surname
                System.out.print("Enter the Surname : ");
                String surname = input.next();

                if (checkingValidName(surname)) { // Checking the validity of the Surname

                    for (int x = 0; x < hotel.length; x++) {
                        // finding whether the customer is having a room or not
                        String data = hotel[x];
                        //Getting the first and the second element in to an array
                        String[] arrOfStr = data.split(" : ");

                        // converting Capitalize

                        firstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1).toLowerCase();
                        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();

                        // Finding the customer using the First name and the Surname
                        if (arrOfStr[0].equals(firstname) && arrOfStr[1].equals(surname)) {

                            System.out.println(firstname + " " + surname + " has booked room number " + x);
                            break;


                        } // customer is not reserve any room
                        else if (x == (hotel.length) - 1 && !arrOfStr[0].equals(firstname) && !arrOfStr[1].equals(surname)) {

                            System.out.println(firstname + " " + surname + " is not having any reservation");
                        }
                    }
                } else {
                    System.out.println("Invalid input"); // Invalid Second name
                }
            } else {
                System.out.println("Invalid input"); // Invalid First name
            }
        }

/* Loading data to the text file
* (Reading data from the StoreData.txt file)
* */

        public static void loadCustomerData() {
            try {
                File myObj = new File(partname);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    myReader.nextLine();

                }System.out.println("Successfully loaded");

                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

    /*
    Customers in alphabetical order
    */
    static boolean lessThan(String s1, String s2) {
        boolean flag = true;
        int i, lim;

        String shorter;

        // converting strings to lowercase
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
        // Comparing the length if one name is contained in another (Harini Rodrigo and Harini Rodrigos)
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
        // converting uppercase to lowercase
        System.out.println(" ");
        for (int i = 0; i < hotel.length; i++){
            String data = hotel[i];
            //Getting the first and the second element in to an array
            String[] arrOfStr = data.split(" : ");
            hotel[i] = (arrOfStr[0]+" "+arrOfStr[1]);}
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

       /* Reference :
    Author: McMillen,Hunter ;
     Availability: https://stackoverflow.com/questions/17575840/better-way-to-generate-array-of-all-letters-in-the-alphabet
     */

         /*Validation of a customer name*/

        public static boolean checkingValidName(String name) { // checking valid name

            String names = name.toLowerCase();
            char [] numletter = names.toCharArray(); // Separating name into characters

            char [] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
                    'n','o','p','q','r','s','t','u','v','w','x','y','z'};

            for (char validity : numletter){ // Check whether it's an alphabetic character or not

                for (int i=0;i<letters.length;i++){

                    if ((validity==letters[i])){
                        if(i!=letters.length-1){
                            break;
                        }
                    } else if (i == letters.length - 1) {
                        return false;
                    }
                }
            }
            return true;
        }

       /* Reference :
    Author: AutomationLabs, Naveen ;
     Availability: https://www.youtube.com/watch?v=z_Cys4W3OOk
     */

    // Checking for empty rooms
        public static boolean availability(String[] hotel){
            boolean emptyRooms = true;
            for (int roomDetails=0;roomDetails< hotel.length;roomDetails++){
                if (hotel[roomDetails].equals("e : e : e : e")){
                    emptyRooms= true;
                    break;
                }else if (roomDetails== hotel.length-1&&!hotel[roomDetails].equals("e : e : e : e")){
                    emptyRooms= false;}
            }return emptyRooms;
        }

    // Checking validation numbers
    public static boolean isNumber (CharSequence cs){
        int len = cs.length();
        for (int i=0; i<len;i++){
            if (!Character.isDigit(cs.charAt(i))){
                return false;
            }}return true;
    }

        /**
         * Add a customer to a room
         */
        public static void addCustomer (String[] hotel) {

            Scanner input = new Scanner(System.in);
            boolean emptyRooms = availability(hotel);
            if (!emptyRooms){
                System.out.println("All rooms are booked");
                 }
            int roomNum = 0;
            while (emptyRooms) {

                    for (int roomDetails=0;roomDetails< hotel.length;roomDetails++){
                        if (hotel[roomDetails].equals("e : e : e : e")){
                            System.out.println("Room No "+roomDetails+" is empty");
                        }}
                System.out.print("\nEnter room number (0-7) or 8 to Stop adding Customers : ");
                try {
                    roomNum = input.nextInt();
                    if (roomNum >= 0 && roomNum < 8 && hotel[roomNum].equals("e : e : e : e")) {
                        // Customer Details
                        System.out.print("Enter the first name : ");
                        String firstName = input.next();
                        if (checkingValidName(firstName)) {
                        System.out.print("Enter the Surname : ");
                        String surname = input.next();
                        if (checkingValidName(surname)){
                        System.out.print("Number of guests in a room : ");
                        String NoofGusts = input.next();
                        if (isNumber(NoofGusts)&&Integer.parseInt(NoofGusts)==1){
                        System.out.print("Enter the Card number : ");
                        String cardNumber = input.next();
                        if (isNumber(cardNumber)){
                        
                                firstName= firstName.substring(0,1).toUpperCase()+firstName.substring(1).toLowerCase();
                                surname = surname.substring(0,1).toUpperCase()+surname.substring(1).toLowerCase();
                                hotel[roomNum] = (firstName+" : "+surname+" : "+NoofGusts+" : "+cardNumber);
                            }else {
                            System.out.println("Invalid number");
                        }}else {
                            System.out.println("Invalid number, the guest number should grater than 1");
                        }
                        }else {
                            System.out.println("Invalid input");
                            break;
                        }
                        }
                        // for Invalid customer name
                        else {
                            System.out.println("Invalid input");
                            break;
                        }
                    } // when user want to Quit the program
                    else if (roomNum == 8) {
                        System.out.print("Need to confirm the reservation\nEnter \"y\" for yes else \"n\" : ");
                        String conformation=input.next();
                        if (conformation.equals("y")){
                            System.out.println("Enter \"S\" in the menu to confirm the reservation");
                        }else {
                            System.out.println("You canceled the reservation");
                        }
                        break;
                    }// for the rooms that are already booked
                    else if (!hotel[roomNum].equals("e : e : e : e")) {
                        System.out.println("This room has already reserved by someone");
                    }
                    // to find room numbers more than 5 (for invalid room numbers)
                    else {
                        System.out.println("No room.Room numbers are between 0-8\nPlease try again\n");

                    }
                }
                // Invalid room numbers
                catch (Exception e) {
                    System.out.println(e + " : Invalid input");
                    break;
                }
            }if (!emptyRooms){
                System.out.println("You can no longer add customers");
            }
            //return the array to the main program

        }
    /*Store Room Details
     * (Overwriting the data to the StoreData.txt file)
     *  */
    public static void storeCustomerData(String[] hotel) {

        try {
            FileWriter myWriter = new FileWriter(partname);
            for (String i:hotel){
                myWriter.write(i+"\n");
            }myWriter.close();
            System.out.println("\nSuccessfully Updated");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /* Finding empty rooms */
    public static void empty (String[]hotel){
        int s=0;
        System.out.println(" ");
        for (int i=0;i< hotel.length;i++){
            //Displaying the empty Rooms
            if (hotel[i].equals("e : e : e : e")){
                System.out.println(i+" is Empty");
            }
        }for (int i=0;i< hotel.length;i++){

            if (hotel[i].equals("e : e : e : e")){
                break;
            }
            //All rooms are full
            else if (i== hotel.length-1&&!hotel[i].equals("e : e : e : e")){
                System.out.println("No empty room find");
        }

        }

    }
    /* Loading the Room Details before update StoreData.txt file
     *(Reading StoreData.txt file)
     * */
    public static String [] readCustomer() {

        String[] hotel = new String[8];
        int i=0;

        //Reading the file
        try {
            File myObj = new File(partname);
            Scanner myReader = new Scanner(myObj);

            //Reading data line by line
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
}
