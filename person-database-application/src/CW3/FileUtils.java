/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CW3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andrew.abel
 */
public class FileUtils {
       
    public static List<Person> readFile(String filename){
        ArrayList<Person> personList = new ArrayList<>();
        Path path = Paths.get(filename);
        try{
            BufferedReader reader = Files.newBufferedReader(path);
            String content = reader.readLine();
            while(content != null) {
                boolean check = false;
                String[] personInfo = content.split(",");
                if (personInfo.length != 9) {
                    content = reader.readLine();
                    continue;
                }
                else {
                    for (int i = 0; i < personInfo.length; i++) {
                        if (personInfo[i].equals("")){
                            content = reader.readLine();
                            check = true;
                            break;
                        } 
                    }
                    if (check)
                        continue;
                }      
                try {
                    int testParse = Integer.parseInt(personInfo[3].trim());
                    
                }
                catch(NumberFormatException e) {
                    content = reader.readLine();
                    continue;
                }
                System.out.println(content);
                Person person = new Person(content);
                personList.add(person);
                content = reader.readLine();
            }
            reader.close();
        }
        catch(IOException e) {
            System.out.println("");
            System.out.println(e);
        } 
        return personList;
    }
    
    
}


        // Receive a filename String, and read a file, storing all person 
        // information in an arraylist, handling any file reading errors and
        // returning an array list
        
        // Complete this method
        
        // temporary return line, change this