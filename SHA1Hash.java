import java.security.MessageDigest;
import java.util.Scanner;

public class SHA1Hash {

    public static void main(String[] args) {
        try {
            // Read input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter text to hash using SHA-1: ");
            String input = scanner.nextLine();
            scanner.close();

            // Get SHA-1 message digest instance
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // Calculate digest
            md.update(input.getBytes());
            byte[] digest = md.digest();

            // Convert to hex format
            String hash = bytesToHex(digest);

            // Print result
            System.out.println("SHA-1 hash of \"" + input + "\" is:");
            System.out.println(hash);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Utility method to convert byte array to hex string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }

        return hexString.toString();
    }
}