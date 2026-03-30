import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;

public class Blowfish {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the string:");
            String inputString = sc.nextLine();

            String secretKey = "custom-key-123";

            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "Blowfish");

            // Encryption
            Cipher encryptCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encryptedBytes = encryptCipher.doFinal(inputString.getBytes());
            String encryptedValue = Base64.getEncoder().encodeToString(encryptedBytes);

            // Decryption
            Cipher decryptCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
            String decryptedValue = new String(decryptedBytes);

            // Output
            System.out.println("String  : " + inputString);
            System.out.println("Encrypt : " + encryptedValue);
            System.out.println("Decrypt : " + decryptedValue);

            sc.close();

        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}