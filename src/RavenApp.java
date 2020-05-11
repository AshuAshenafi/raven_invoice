import java.util.ArrayList;
import java.util.Scanner;

public class RavenApp {
        static RavenService rsrv = new RavenService();
        static Scanner keybd = new Scanner(System.in);
        static String [] userInfo = new String [6];
    public static void main(String[] args) {


        String [] temp = loadUserInfo();


        // variable to store user reply
        String userInput;

        // Scanner reads user input
        System.out.println("Good morning, how are you feeling today? (q/Q to quit)");
        userInput = keybd.nextLine();
        // while user input is not equal to the letter q keep looping
        while (!userInput.equalsIgnoreCase("q")) {

            String tempUserInput = userInput.toLowerCase();

            if (tempUserInput.contains("bye")) {

                rsrv.printBye(userInput);
                break;
            } else {
                rsrv.giveReply(userInput);
                userInput = keybd.nextLine();
            }
        }   // end of while() loop

        // print all conversations
        rsrv.printAllLogs();
        rsrv.copyArrayList();

//        inv.printNumberOfRequest();
//        System.out.println("array length from main method = " + giveArraylength());

        Invoice inv = new Invoice();
//        inv.printNumberOfRequest();
        inv.printNumberOfRequest(temp, giveArraylength());


    }   // end of main() method
    public static int giveArraylength(){
        int leng = rsrv.copyArrayLength();
        return leng;
    }
    public static String[] loadUserInfo(){
        // scanner class instantiating
        Invoice inv = new Invoice();
        System.out.println("     ====================USER INFORMATION====================");
        int index = 0;

        System.out.println("Enter First Name: ");
        String firstName = keybd.nextLine();
        inv.setFname(firstName);
        userInfo[index++] = firstName;

        System.out.println("Enter Last Name: ");
        String lastName = keybd.nextLine();
        inv.setLname(lastName);
        userInfo[index++] = lastName;

        System.out.println("Enter Street Address: ");
        String streetAddress = keybd.nextLine();
        inv.setStreetAddress(streetAddress);
        userInfo[index++] = streetAddress;

        System.out.println("Enter City: ");
        String city = keybd.nextLine();
        inv.setCity(city);
        userInfo[index++] = city;

        System.out.println("Enter State: ");
        String state = keybd.nextLine();
        inv.setState(state);
        userInfo[index++] = state;

        System.out.println("Enter Zip-code: ");
        String zipcode = keybd.nextLine();
        inv.setZipCode(zipcode);
        userInfo[index++] = zipcode;

        Invoice inv1 = new Invoice(firstName, lastName, streetAddress, city, state, zipcode);

        System.out.println("====================HERE STARTS THERAPY SESSION====================");

        return userInfo;
    }

}   // end of Class
