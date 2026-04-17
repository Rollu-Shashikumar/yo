import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HillCipher {

    static int[][] decrypt = new int[3][1];
    static int[][] a = new int[3][3];
    static int[][] b = new int[3][3];
    static int[][] mes = new int[3][1];
    static int[][] res = new int[3][1];

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        getkeymes();

        // Encrypt message
        multiplyMatrix(a, mes, res);

        System.out.print("\nEncrypted string is: ");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) ((res[i][0] % 26) + 65));
        }

        inverse();

        // Decrypt message
        multiplyMatrix(b, res, decrypt);

        System.out.print("\nDecrypted string is: ");
        for (int i = 0; i < 3; i++) {
            int val = ((decrypt[i][0] % 26) + 26) % 26;
            System.out.print((char) (val + 65));
        }

        System.out.print("\n");
    }

    // Input key and message
    public static void getkeymes() {

        System.out.println("Enter 3x3 matrix for key:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        System.out.print("Enter a 3-letter string: ");
        String msg = sc.next().toUpperCase();

        if (msg.length() != 3) {
            System.out.println("Enter exactly 3 letters!");
            System.exit(0);
        }

        for (int i = 0; i < 3; i++) {
            mes[i][0] = msg.charAt(i) - 65;
        }
    }

    // Matrix multiplication
    public static void multiplyMatrix(
            int[][] matrixA,
            int[][] matrixB,
            int[][] result) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {

                result[i][j] = 0;

                for (int k = 0; k < 3; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }

                result[i][j] = ((result[i][j] % 26) + 26) % 26;
            }
        }
    }

    // Find inverse of key matrix
    public static void inverse() {

        int det = determinant(a);
        int detInverse = modInverse(det, 26);

        if (detInverse == -1) {
            System.out.println(
                    "\nMatrix is not invertible in mod 26!");
            System.exit(0);
        }

        int[][] adj = adjoint(a);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                b[i][j] = ((adj[i][j] * detInverse) % 26 + 26) % 26;
            }
        }

        System.out.println("\nInverse matrix is:");
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                System.out.print(b[i][j] + " ");
            }

            System.out.println();
        }
    }

    // Determinant calculation
    public static int determinant(int[][] matrix) {

        int det = matrix[0][0] *
                (matrix[1][1] * matrix[2][2]
                        - matrix[1][2] * matrix[2][1])
                - matrix[0][1] *
                        (matrix[1][0] * matrix[2][2]
                                - matrix[1][2] * matrix[2][0])
                + matrix[0][2] *
                        (matrix[1][0] * matrix[2][1]
                                - matrix[1][1] * matrix[2][0]);

        return ((det % 26) + 26) % 26;
    }

    // Modular inverse
    public static int modInverse(int det, int mod) {

        det = (det % mod + mod) % mod;

        for (int x = 1; x < mod; x++) {

            if ((det * x) % mod == 1) {
                return x;
            }
        }

        return -1;
    }

    // Adjoint matrix
    public static int[][] adjoint(int[][] m) {

        int[][] adj = new int[3][3];

        adj[0][0] = (m[1][1] * m[2][2] - m[1][2] * m[2][1]);
        adj[0][1] = -(m[1][0] * m[2][2] - m[1][2] * m[2][0]);
        adj[0][2] = (m[1][0] * m[2][1] - m[1][1] * m[2][0]);

        adj[1][0] = -(m[0][1] * m[2][2] - m[0][2] * m[2][1]);
        adj[1][1] = (m[0][0] * m[2][2] - m[0][2] * m[2][0]);
        adj[1][2] = -(m[0][0] * m[2][1] - m[0][1] * m[2][0]);

        adj[2][0] = (m[0][1] * m[1][2] - m[0][2] * m[1][1]);
        adj[2][1] = -(m[0][0] * m[1][2] - m[0][2] * m[1][0]);
        adj[2][2] = (m[0][0] * m[1][1] - m[0][1] * m[1][0]);

        // Now transpose (this is required)
        int[][] adjT = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adjT[i][j] = ((adj[j][i] % 26) + 26) % 26;
            }
        }

        return adjT;
    }
}