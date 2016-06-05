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

        FileOutputStream fos = null;
        try {

            File file = new File("TelephoneNumber.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream("TelephoneNumber.txt");
            try {

                URL url = new URL("http://ktozvonit.com.ua/operators/067/15/0000000-0009999");
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    Pattern pattern = Pattern.compile("\\(\\d{3}\\)\\s*\\d{3}\\-\\d{2}\\-\\d{2}");
                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find()) {
                        String group = matcher.group();
                        //System.out.println(group);
                        StringBuilder sb = new StringBuilder(group);
                        sb.deleteCharAt(1);
                        sb.insert(0, '+');
                        sb.insert(1, '3');
                        sb.insert(2, '8');
                        sb.insert(3, '0');
                        sb.insert(4, ' ');
                        String newGroup = sb.toString();
                        //System.out.println(newGroup);
                        byte[] bytes = newGroup.getBytes();
                        fos.write(bytes);
                        fos.write('\n');

                    }

                }
                reader.close();
                //fos.close();

            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
