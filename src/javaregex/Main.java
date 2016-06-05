/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaregex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gaffs
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "TelephoneNumbers.txt";
        String remoteurl = "http://ktozvonit.com.ua/operators/067/15/0000000-0009999";

        Pattern phonePattern = Pattern.compile("\\(\\d{3}\\)\\s*\\d{3}\\-\\d{2}\\-\\d{2}");

        File file = new File(filename);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(remoteurl).openStream()))) {
                for (String line; (line = reader.readLine()) != null;) {
                    for (Matcher matcher = phonePattern.matcher(line); matcher.find();) {
                        fos.write(new StringBuilder(matcher.group()).deleteCharAt(1).insert(0, "+380 ")
                                .append("\n").toString().getBytes());
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
