/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author ANDREW.ABEL
 */
public class Menu {

    // ArrayList for id
    List<Person> ids = new ArrayList<>();
    
    public void mainMenu(String fileName) {
        
        // Display a welcome message, get option and input
        
        // Do not Change
        
        System.out.println("\nCriminal Database Menu \n");
        System.out.println("Choose an option: ");
        System.out.println("1. Load Crime File");
        System.out.println("2. List all Criminals");
        System.out.println("3. Search for a Criminal");
        System.out.println("4. Generate a Wanted Poster");
        System.out.println("5. Generate Stats");
        System.out.println("6. Check Criminal for ID Fraud");
        System.out.println("7. Exit");

        // Get input from method
        int command = getIntInput();
        System.out.println("You entered " + command);
        switch (command) {
            case 1:
                System.out.println("Load Database");
                ids = loadFiles(fileName, ids);
                break;
            case 2:
                System.out.println("Listing all Criminals");
                listPeople(ids);
                break;
            case 3:
                System.out.println("Search for a Criminal with partial match");
                String input = getStringInput("Please input a partial match for the ID file");      
                searchIDs(ids, input);
                break;
            case 4:
                System.out.println("Generate a Wanted Poster");
                input = getStringInput("Please input a exact match for the ID code"); 
                
                generatePoster(ids, input);
                break;
            case 5:
                System.out.println("\nGenerate Stats");
                generateStats(ids);
                break;
            case 6:
                System.out.println("Check for Fraud");
                input = getStringInput("Please input a exact match for the ID code");                
                // Get a boolean of whether valid
                boolean valid = checkFraud(ids, input);
                if(valid){
                    System.out.println("Valid ID");
                } else {
                    System.out.println("Invalid ID or not found in system");
                }
                
                break;
            case 7:

                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice, please re-enter between 1 and 6");
            //  showMenu();
        }
        // Loop through menus again
        mainMenu(fileName);
    }
    
    
    private List<Person> loadFiles(String fileName, List<Person> ids) {
        // Method to handle loading of file and writing into array
        // Completed method, do not change
        
        System.out.println("Loading file " + fileName);
        ids = FileUtils.readFile(fileName);
        return ids;
    }
    
    // Generate Stats
    // This method is pre-completed
    private void generateStats(List<Person> ids) {
        // Method to generate stats
        // Completed method, do not change
        
        System.out.println("Number of criminals in System: " + statsIdNumber(ids));
        System.out.println("Number of Nationalities in System: " + statsNatNumber(ids));
        System.out.println("Average age of people: " + statsAvgAge(ids));
        System.out.println("Average reward level: " + avgReward(ids));
    }
    

    
    public static int getIntInput() {
        // Method to check for input
        // Completed method, do not change
        
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();

        int cmd = 6;
        try {
            cmd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // If not a number
            System.out.println("Invalid number option chosen");
            cmd = getIntInput();
        }

        return cmd;
    }
    
    public static String getStringInput(String inputMessage) {
        // Method to receive a message to display, and get a string input
        // from keyboard
        // Completed method, do not change
        
        Scanner kb = new Scanner(System.in);
        
        System.out.println(inputMessage);
        String input = kb.nextLine();
        return input;
    }
   
    

    
    private void listPeople(List<Person> ids) {
        // Display list of Peoople using toString method
        for (Person elem : ids) {
            System.out.println(elem.toString());
        }
        
        // Complete this method
        
    }

    
    private void searchIDs(List<Person> ids, String input) {
        // Method to search for people and display using toString
        // Search by full or partial match of first name, family name, nationality,
        // id or nickname, not case sensitive                        
        String match;                                     
        int matchNumbers = 0;                             
        
        for (int i = 0; i < ids.size(); i++) {
            match = ids.get(i).getFirstName()+","+ids.get(i).getFamilyName()+","+
                    ids.get(i).getNationality()+","+ids.get(i).getIdCode()+","+
                    ids.get(i).getNickname();
            if (match.toLowerCase().contains(input.toLowerCase()) && !input.equals("")) {
                System.out.println(ids.get(i).toString());
                matchNumbers++;
            }
  
        }   
        
        if (matchNumbers == 0) {
            System.out.println("");
            System.out.println("no matching results");
        }
//        else{
//            System.out.println("");
//            System.out.println("number of matches found is: " + matchNumbers);
//        }
               
        // Complete this method
    }

    private void generatePoster(List<Person> ids, String input) {
        // Method will receive a String input and a list of persons.  Will
        // Look for an exact match with the id and if a match is found, will
        // Display a wanted poster
        int index = -1;
        boolean check = false;
        for (int i = 0; i < ids.size(); i++) {
            String idInSystem = ids.get(i).getIdCode();
            if(input.equals(idInSystem) && ids.get(i).getPhoto() != null) {
                check = true;
                index = i;
                break;
            }
        }
        if (check == true) {
            JFrame frame = new JFrame();
            frame.setTitle("CRIMINAL");
            frame.setSize(400, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            ImagePanel ip = new ImagePanel(ids.get(index),0,0,400,600);
            frame.add(ip);
     
        }
        else {
            System.out.println("No match found or image read error happened");
        }
        // Complete this method
    }
    
    private int statsIdNumber(List<Person> ids){
        // method to calculate the number of ids in the system
  
        // complete this method
        // Return temporary value, change this
        return ids.size();
    }
    
    private int statsNatNumber(List<Person> ids){
        // Method to return the number of nationalities present in the system
        String nat;
        int repeatSum = 0;
        int[] repeatMarkArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++){
            nat = ids.get(i).getNationality();
            for (int j = i; j < ids.size(); j++) {
                if (nat.equals(ids.get(j).getNationality()) && j != i) {
                    repeatMarkArray[j] = 1;
                }   
            }
        }
        for (int i = 0; i < repeatMarkArray.length; i++){
            repeatSum = repeatMarkArray[i] + repeatSum;   
        }
        int nationalityNumbers = ids.size() - repeatSum;
        // Complete this method
       // Return temporary value, change this
        return nationalityNumbers;
    }
    
    private double statsAvgAge(List<Person> criminals){
        // Calculate age of every person in the system and return as double
        double avg = 0;
        double ageSum = 0;
        double idNumbers = statsIdNumber(criminals);
        for(int i = 0; i < criminals.size(); i++){
            int age = criminals.get(i).getAgeinYears();
            ageSum = ageSum + age;
        }
        avg = ageSum / idNumbers; 
        // Complete this method
        // Return temporary value, change this
        return avg;
    }
    
    private double avgReward(List<Person> ids){
        // Method to calculate average reward amount of all people in system
        double avg = 0;
        double rewardSum = 0;
        double idNumbers = statsIdNumber(ids);
        for (int i = 0; i < ids.size(); i++) {
            rewardSum = rewardSum + ids.get(i).getReward();
        }
        avg = rewardSum / idNumbers;        
        // Complete this method
        // Return temporary value, change this
        return avg;
    }
    
    private boolean checkFraud(List<Person> ids, String input){
        // Method to check if an ID is valid or invalid.  Receives an ID string 
        // and a list of people.  Valid ID if:
        // when the user inputs an ID code, a matching ID is found in the system
        // The ID code is 8 characters in length
        // The code begins with an “A”, “B”, or “C” (case sensitive)
        // The third character matches the last number of their year of birth
        // The final 2 characters are a checksum, and should add up to 7
        String idInSystem;
        boolean returnvalue = true;
        

        if(input.length() != 8) {
//            System.out.println("ID length does not match");
            return false;
        }
        
        char firstLetter = input.charAt(0);
        if (firstLetter != 'A' && firstLetter != 'B' && firstLetter != 'C') {
//            System.out.println("The begin letter of ID does not match");
            return false;
        }
        
        char last = input.charAt(6);
        char lastSecond = input.charAt(7);
        if (!Character.isDigit(last) || !Character.isDigit(lastSecond)) {
//            System.out.println("The type of last two characters does not match (last two characters should both be numbers!)");
            return false;
        }
        int last1 = Integer.parseInt(Character.toString(last));
        int lastSecond1 = Integer.parseInt(Character.toString(lastSecond));
        if (last1 + lastSecond1 != 7) {
//            System.out.println("Checksum does not match");
            return false;
        }
              
        int index = -1;
        for (int i = 0; i < ids.size(); i++) {
            idInSystem = ids.get(i).getIdCode();
            if(input.equals(idInSystem)) {
                returnvalue = true;
                index = i;
//                System.out.println("Match found in the database");
                break;
            } 
            else {
                returnvalue = false;
            }  
        }
        
        char third = input.charAt(2);
        if (!Character.isDigit(third)) {
//            System.out.println("The type of the third character does not match (the third character should be a number!)");
            return false;
        }
    
        try{
            int birthYear = ids.get(index).getDob().getYear();
            int three = birthYear % 1000;
            int two = three % 100;
            int targetNumber = two % 10;
            int third1 = Integer.parseInt(Character.toString(third));
            if (third1 != targetNumber) {
//                System.out.println("Year does not match");
                return false;
            } 
        }
        catch(IndexOutOfBoundsException e) {
//            System.out.println("No match in database");
            returnvalue = false;
        }
    
                

        // Complete this method
        // Return temporary value, change this
        return returnvalue;
    }

}
