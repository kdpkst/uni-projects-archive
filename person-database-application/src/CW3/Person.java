/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import javax.imageio.ImageIO;

/**
 *
 * @author andrew.abel
 */
public class Person {

    // Instance variables
    private String firstName;
    private String familyName;
    private String nickname;
    private int reward;
    private String nationality;
    private String idCode;
    private String crimes;
    private LocalDate dob;
    private Image photo;

    public Person(String[] info) {                                        //have no idea whether this method is correct or not, check it later.
        // Constructor to take a String array and assign it to instance variables
        try{    
            this.firstName = info[0];
            this.familyName = info[1];
            this.nickname = info[2];
            this.reward = Integer.parseInt(info[3]);
            this.nationality = info[4];
            this.idCode = info[5];
            this.crimes = info[6];
            this.dob = convertDateOfBirth(info[7]);
            this.photo = readImage(info[8]);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("the length of input array should be 9!");
        }
        // Complete this method
    }

    public Person(String line) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        // Constructor to take a single string, the entire line of a file, and assign
        // it to instance variables
        
        String[] personArray = line.split(",");
        for (int i = 0; i < personArray.length; i++) {
            personArray[i] = personArray[i].trim();             
        }
        
        this.firstName = personArray[0]; 
        this.familyName = personArray[1];
        this.nickname = personArray[2];
        this.reward = Integer.parseInt(personArray[3]);
        this.nationality = personArray[4];
        this.idCode = personArray[5];
        this.crimes = personArray[6];
        this.dob = convertDateOfBirth(personArray[7]);
        this.photo = readImage(personArray[8]);            
    
        // Complete this method
    }

    public BufferedImage readImage(String filename) {
        // Read an image from a file and return a Buffered image
        BufferedImage image = null;
        try{
            image = ImageIO.read(new File(filename));
        }
        catch(IOException e) {
            System.out.println(e);
        }
        // Complete this method
        // Return temporary value, change this
        return image;
    }

    public LocalDate convertDateOfBirth(String inputDate) {
        // Take a date input String and convert to a local date
        // If the date is an incorrect value, assign a default value of 1/1/1991
        LocalDate dt = LocalDate.of(1991, 1, 1);       
        LocalDate current = LocalDate.now();
        int yearNow = current.getYear();
        try{
            String[] dateArray = inputDate.split("/");
            int day = Integer.parseInt(dateArray[0]);
            int month = Integer.parseInt(dateArray[1]);
            int year = Integer.parseInt(dateArray[2]);    
            dt = LocalDate.of(year, month, day);
        }
        catch(DateTimeException|NumberFormatException|ArrayIndexOutOfBoundsException e) {
            dt = LocalDate.of(1991, 1, 1);
        }
                                             
        // Complete this method
        // Return temporary value, change this
        return dt;
    }

    public int getAgeinYears() {
        // return the age of the person in years
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public String toString() {
        // Output method as String

        // Do not change!
        String out = "***\n" + getIdCode() + ", " + getFirstName() + " "
                + getFamilyName() + ", Reward: " + getReward() + ", " + getNationality()
                + ", " + getDob().toString() + ", " + getNickname() + ", " + getCrimes();

        return out;
    }

    // All getters are completed, do not change
    public String getFirstName() {

        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public int getReward() {
        return reward;
    }

    public String getNationality() {
        return nationality;
    }

    public String getIdCode() {
        return idCode;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Image getPhoto() {
        return photo;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCrimes() {
        return crimes;
    }
    
    
    


}
