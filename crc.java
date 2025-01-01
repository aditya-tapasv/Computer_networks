import java.util.Scanner;

public class crc
{
    // Method to perform CRC division
    public static String divide(String dividend, String divisor)
    {
        int n = divisor.length();
        String temp = dividend.substring(0, n);

        for (int i = 0; i < dividend.length() - n + 1; i++)
        {
            if (temp.charAt(0) == '1')
            {
                temp = xor(temp, divisor) + (i + n < dividend.length() ? dividend.charAt(n + i) : '0');
            }
            else
            {
                temp = xor(temp, "0".repeat(n)) + (i + n < dividend.length() ? dividend.charAt(n + i) : '0');
            }
            temp = temp.substring(1);
        }
        return temp;
    }

    // XOR operation between two binary strings
    public static String xor(String a, String b)
    {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++)
        {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter data bits:");
        String data = scanner.nextLine();

        System.out.println("Enter generator bits:");
        String generator = scanner.nextLine();

        String appendedData = data + "0".repeat(generator.length() - 1);

        String remainder = divide(appendedData, generator);

        String codeword = data + remainder;
        System.out.println("Transmitted codeword: " + codeword);

        System.out.println("Enter received codeword:");
        String received = scanner.nextLine();

        String checkRemainder = divide(received, generator);
        if (checkRemainder.contains("1"))
        {
            System.out.println("Error detected in received codeword.");
        }
        else
        {
            System.out.println("No error detected in received codeword.");
        }

        scanner.close();
    }
}
