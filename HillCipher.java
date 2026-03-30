import java.util.*;

public class HillCipher {

    static int[][] key = {
        {2, 1, 1},
        {1, 3, 2},
        {1, 0, 0}
    };

    // Convert char to number
    static int getNum(char c) {
        return c - 'a';
    }

    // Convert number to char
    static char getChar(int n) {
        return (char)(n + 'a');
    }

    // Multiply matrix
    static int[] multiply(int[][] m, int[] v) {
        int[] res = new int[3];
        for (int i = 0; i < 3; i++) {
            res[i] = (m[i][0]*v[0] + m[i][1]*v[1] + m[i][2]*v[2]) % 26;
        }
        return res;
    }

    // Determinant
    static int determinant(int[][] m) {
        return m[0][0]*(m[1][1]*m[2][2] - m[1][2]*m[2][1])
             - m[0][1]*(m[1][0]*m[2][2] - m[1][2]*m[2][0])
             + m[0][2]*(m[1][0]*m[2][1] - m[1][1]*m[2][0]);
    }

    // Inverse matrix (float for display)
    static float[][] inverseMatrix(int[][] m) {
        float[][] inv = new float[3][3];

        int det = determinant(m);

        inv[0][0] =  (m[1][1]*m[2][2] - m[1][2]*m[2][1]);
        inv[0][1] = -(m[0][1]*m[2][2] - m[0][2]*m[2][1]);
        inv[0][2] =  (m[0][1]*m[1][2] - m[0][2]*m[1][1]);

        inv[1][0] = -(m[1][0]*m[2][2] - m[1][2]*m[2][0]);
        inv[1][1] =  (m[0][0]*m[2][2] - m[0][2]*m[2][0]);
        inv[1][2] = -(m[0][0]*m[1][2] - m[0][2]*m[1][0]);

        inv[2][0] =  (m[1][0]*m[2][1] - m[1][1]*m[2][0]);
        inv[2][1] = -(m[0][0]*m[2][1] - m[0][1]*m[2][0]);
        inv[2][2] =  (m[0][0]*m[1][1] - m[0][1]*m[1][0]);

        // transpose and divide by determinant
        float[][] adjT = new float[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adjT[i][j] = inv[j][i] / det;
            }
        }

        return adjT;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a 3 letter string: ");
        String text = sc.next().toLowerCase();

        int[] vec = new int[3];
        for (int i = 0; i < 3; i++) {
            vec[i] = getNum(text.charAt(i));
        }

        // Encryption
        int[] enc = multiply(key, vec);

        String cipher = "";
        for (int i = 0; i < 3; i++) {
            cipher += getChar((enc[i] + 26) % 26);
        }

        System.out.println("Encrypted string is: " + cipher);

        // Inverse matrix (for display)
        float[][] inv = inverseMatrix(key);

        System.out.println("Inverse Matrix is:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(inv[i][j] + " ");
            }
            System.out.println();
        }

        // Decryption using same inverse (approx)
        float[] decVec = new float[3];
        int[] cipherVec = new int[3];

        for (int i = 0; i < 3; i++) {
            cipherVec[i] = getNum(cipher.charAt(i));
        }

        for (int i = 0; i < 3; i++) {
            decVec[i] = inv[i][0]*cipherVec[0] +
                        inv[i][1]*cipherVec[1] +
                        inv[i][2]*cipherVec[2];
        }

        String decrypted = "";
        for (int i = 0; i < 3; i++) {
            int val = Math.round(decVec[i]) % 26;
            if (val < 0) val += 26;
            decrypted += getChar(val);
        }

        System.out.println("Decrypted string is: " + decrypted);

        sc.close();
    }
}