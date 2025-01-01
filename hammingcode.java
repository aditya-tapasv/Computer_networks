import java.util.Scanner;

public class hammingcode
{
    public static int[] calculateParityBits(int[] dataBits)
    {
        int[] hammingCode = new int[7];

        hammingCode[2] = dataBits[0];
        hammingCode[4] = dataBits[1];
        hammingCode[5] = dataBits[2];
        hammingCode[6] = dataBits[3];

        hammingCode[0] = hammingCode[2] ^ hammingCode[4] ^ hammingCode[6];
        hammingCode[1] = hammingCode[2] ^ hammingCode[5] ^ hammingCode[6];
        hammingCode[3] = hammingCode[4] ^ hammingCode[5] ^ hammingCode[6];

        return hammingCode;
    }

    public static int detectError(int[] receivedCode)
    {
        int p1 = receivedCode[0] ^ receivedCode[2] ^ receivedCode[4] ^ receivedCode[6];
        int p2 = receivedCode[1] ^ receivedCode[2] ^ receivedCode[5] ^ receivedCode[6];
        int p4 = receivedCode[3] ^ receivedCode[4] ^ receivedCode[5] ^ receivedCode[6];

        return (p4 * 4) + (p2 * 2) + p1;
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 4 data bits (space-separated, e.g., 1 0 1 1):");
        int[] dataBits = new int[4];
        for (int i = 0; i < 4; i++)
        {
            dataBits[i] = scanner.nextInt();
        }

        int[] hammingCode = calculateParityBits(dataBits);
        System.out.print("Generated Hamming Code: ");
        for (int bit : hammingCode)
        {
            System.out.print(bit + " ");
        }
        System.out.println();

        System.out.println("Enter received Hamming Code (7 bits, space-separated):");
        int[] receivedCode = new int[7];
        for (int i = 0; i < 7; i++)
        {
            receivedCode[i] = scanner.nextInt();
        }

        int errorPosition = detectError(receivedCode);
        if (errorPosition == 0)
        {
            System.out.println("No error detected in the received code.");
        }
        else
        {
            System.out.println("Error detected at position: " + errorPosition);
            // Correct the error
            receivedCode[errorPosition - 1] ^= 1;
            System.out.print("Corrected Hamming Code: ");
            for (int bit : receivedCode)
            {
                System.out.print(bit + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
