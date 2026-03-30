import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class DESAlgorithm {

    public static void main(String[] args) {
        try {
            // Generate a DES key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56); // DES uses a 56-bit key
            SecretKey secretKey = keyGenerator.generateKey();

            // Create a Cipher object for DES
            Cipher cipher = Cipher.getInstance("DES");

            // Take user input
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter text to encrypt: ");
            String plainText = scanner.nextLine();

            // Encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("\nEncrypted Text: " + encryptedText);

            // Decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes =
                    cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);

            System.out.println("Decrypted Text: " + decryptedText);

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}