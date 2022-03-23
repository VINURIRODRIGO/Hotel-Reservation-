package Task_02;

import java.io.*;
import java.util.ArrayList;

public class Person {
    static int SIZE = 5; // Size of Circular Queue
    static int front, rear;
    static String[] customerlist = new String[SIZE];
    static String filepart="C:\\Users\\chami\\IdeaProjects\\Course work\\src\\Task_02\\My project\\WaitingList.txt";


    /* Reference :
     Author: programiz
     Availability: https://www.programiz.com/dsa/circular-queue
     */

    Person() {
        front = -1;
        rear = -1;
    }

    // Check if the queue is full
    boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        if (front == rear + 1) {
            return true;
        }
        return false;
    }

    // Check if the queue is empty
    boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    // Adding an element
     void enQueue(String element) {
        if (!isFull())  {
            if (front == -1)
                front = 0;
            rear = (rear + 1) % SIZE;
            customerlist[rear] = element;
        }
    }

    // Removing an element
   String deQueue() {
       String element;
        if (isEmpty()) {
            return (null);
        } else {
            element = customerlist[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } /* Q has only one element, so we reset the queue after deleting it. */
            else {
                front = (front + 1) % SIZE;
            }
            return (element);
        }
    }
        /* Reference :
     Author: McProgramming ;
     Availability: https://www.youtube.com/watch?v=waXvGUEjTTs
     */


    /*Getting data in the WaitingList.txt file*/
    public  ArrayList<String> waitingListData(){
        ArrayList<String> allData = new ArrayList<>();

        BufferedReader reader;
        String line;
        try {
            reader = new BufferedReader(new FileReader(filepart));
            while ((line=reader.readLine())!=null){
                allData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return allData;
    }

    /*Getting the first eight customers in the WaitingList.txt file*/
    public String[] maindata(ArrayList<String> allData){
        String [] maincus = new String[8];
        if (allData.size()!=0){
        if (maincus.length<allData.size()){
        for (int i=0;i<maincus.length;i++){

            maincus[i]=allData.get(i);}
            }else {
            for (int i=0;i<allData.size();i++){
                int difference = maincus.length-allData.size();
                if (i<=difference){
                maincus[i]=allData.get(i);}
                else {
                    maincus[i]="e : e : e : e";
                }
            }
        }

        }else {
            return new String[1];
        }
        return maincus;
    }

// Removing the customer who reserve the room by updating the waiting list
    public static void reRightData(ArrayList<String>data){
        try {
            FileWriter writer = new FileWriter(filepart);
            BufferedWriter reright = new BufferedWriter(writer);
            for (int i =1; i< data.size();i++){
            reright.write(data.get(i)+"\n");
            }reright.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *Appending data to the deleted row in StoreData.txt file
     */
    public static String main(){
        Person x = new Person();

        // Getting all customers in the waiting list
        ArrayList<String> allcusData = x.waitingListData();

        // Getting the first eight customers in the waiting list
        customerlist =  x.maindata(allcusData);

        if (customerlist.length !=0){
        for (String customer : customerlist){
            x.enQueue(customer);
        }}
        // When the waiting list is empty
        else {
            for (int i=0;i< 8;i++){
                x.enQueue("e : e : e : e");
            }
        }
        reRightData(allcusData);
        return x.deQueue();

    }

}
