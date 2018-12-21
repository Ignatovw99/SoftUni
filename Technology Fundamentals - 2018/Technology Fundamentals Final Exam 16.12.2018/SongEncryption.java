package technologyFundamentalsFinalExam;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongEncryption {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = "\\b([A-Z][a-z ']+):([A-Z ]+)\\b";
        Pattern pattern = Pattern.compile(regex);
        String cmd = scanner.nextLine();

        while(!cmd.equalsIgnoreCase("end")){
            Matcher matcher = pattern.matcher(cmd);

            if (matcher.matches()){
                String encryption = encryptMessage(cmd);
                System.out.println(String.format("Successful encryption: %s", encryption));
            } else {
                System.out.println("Invalid input!");
            }
            cmd = scanner.nextLine();
        }
    }

    public static String encryptMessage(String decryptedMessage){
        String[] tokens = decryptedMessage.split(":");
        String artist = tokens[0];
        String song = tokens[1];
        int encryptionKey = artist.length();
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < decryptedMessage.length(); i++) {
            char currLetter = decryptedMessage.charAt(i);

            if (Character.isLetter(currLetter)){
                if (Character.isUpperCase(currLetter)){
                    currLetter += encryptionKey;
                    if (currLetter > 'Z'){
                        currLetter -= 26;
                    }
                } else if (Character.isLowerCase(currLetter)){
                    currLetter += encryptionKey;
                    if (currLetter > 'z'){
                        currLetter -= 26;
                    }
                }
            }
            encrypted.append(currLetter);
        }
        int index = encrypted.indexOf(":");
        return encrypted.replace(index, index+1, "@").toString();
    }
}
