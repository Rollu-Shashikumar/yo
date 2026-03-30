import java.security.MessageDigest;
import java.util.Scanner;

public class MD5Hash {

    public static void main(String[] args) {
        try {
            // Read input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter text to hash using MD5: ");
            String input = scanner.nextLine();
            scanner.close();

            // Create MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Compute hash
            md.update(input.getBytes());
            byte[] digest = md.digest();

            // Convert to hex string
            String hash = bytesToHex(digest);

            // Display result
            System.out.println("MD5 hash of \"" + input + "\" is:");
            System.out.println(hash);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }

        return hexString.toString();
    }
}