import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CaesarCipher {

    static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.print("Enter any string: ");
        String str = br.readLine();

        System.out.print("Enter the key: ");
        int key = sc.nextInt();

        String encrypted = encrypt(str, key);
        System.out.println("\nEncrypted string is: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted string is: " + decrypted);
    }

    public static String encrypt(String str, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isUpperCase(c)) {
                c = (char) (c + (key % 26));
                if (c > 'Z')
                    c -= 26;

            } else if (Character.isLowerCase(c)) {
                c = (char) (c + (key % 26));
                if (c > 'z')
                    c -= 26;
            }

            encrypted.append(c);
        }

        return encrypted.toString();
    }

    public static String decrypt(String str, int key) {
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (Character.isUpperCase(c)) {
                c = (char) (c - (key % 26));
                if (c < 'A')
                    c += 26;

            } else if (Character.isLowerCase(c)) {
                c = (char) (c - (key % 26));
                if (c < 'a')
                    c += 26;
            }

            decrypted.append(c);
        }

        return decrypted.toString();
    }
}