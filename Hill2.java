import java.util.Scanner;

public class Hill2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] key = new int[2][2];
        int[][] msg = new int[2][1];
        int[][] enc = new int[2][1];
        int[][] dec = new int[2][1];

        // Input key matrix
        System.out.println("Enter 2x2 key matrix:");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                key[i][j] = sc.nextInt();

        // Input message
        System.out.print("Enter 2-letter message: ");
        String text = sc.next().toUpperCase();

        // Convert message to numbers
        for (int i = 0; i < 2; i++)
            msg[i][0] = text.charAt(i) - 'A';

        // -------- Encryption --------
        for (int i = 0; i < 2; i++) {
            enc[i][0] = 0;
            for (int j = 0; j < 2; j++)
                enc[i][0] += key[i][j] * msg[j][0];
            enc[i][0] = ((enc[i][0] % 26) + 26) % 26;
        }

        System.out.print("Encrypted Text: ");
        for (int i = 0; i < 2; i++)
            System.out.print((char)(enc[i][0] + 'A'));

        // -------- Decryption --------
        int[][] inv = inverse(key);

        for (int i = 0; i < 2; i++) {
            dec[i][0] = 0;
            for (int j = 0; j < 2; j++)
                dec[i][0] += inv[i][j] * enc[j][0];
            dec[i][0] = ((dec[i][0] % 26) + 26) % 26;
        }

        System.out.print("\nDecrypted Text: ");
        for (int i = 0; i < 2; i++)
            System.out.print((char)(dec[i][0] + 'A'));
    }

    // -------- Inverse of 2x2 matrix --------
    static int[][] inverse(int[][] m) {

        int det = (m[0][0]*m[1][1] - m[0][1]*m[1][0]) % 26;
        det = (det + 26) % 26;

        int detInv = modInverse(det, 26);

        int[][] inv = new int[2][2];

        inv[0][0] =  m[1][1];
        inv[0][1] = -m[0][1];
        inv[1][0] = -m[1][0];
        inv[1][1] =  m[0][0];

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                inv[i][j] = ((inv[i][j] * detInv) % 26 + 26) % 26;

        return inv;
    }

    // -------- Modular inverse --------
    static int modInverse(int det, int mod) {
        for (int i = 1; i < mod; i++)
            if ((det * i) % mod == 1)
                return i;
        return -1;
    }
}