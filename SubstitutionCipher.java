import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SubstitutionCipher {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String original = "abcdefghijklmnopqrstuvwxyz";
    static String substitution = "zyxwvutsrqponmlkjihgfedcba";

    public static void main(String[] args) throws IOException {

        System.out.print("Enter any string: ");
        String str = br.readLine().toLowerCase(); 

        String encrypted = encrypt(str);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }

    public static String encrypt(String str) {
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            int index = original.indexOf(c);

            if (index != -1) {
                result.append(substitution.charAt(index));
            } else {
                result.append(c); 
            }
        }

        return result.toString();
    }

    public static String decrypt(String str) {
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            int index = substitution.indexOf(c);

            if (index != -1) {
                result.append(original.charAt(index));
            } else {
                result.append(c); 
            }
        }

        return result.toString();
    }
}