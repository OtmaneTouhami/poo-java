import java.util.Arrays;

public class StringManipulator {
    public static void display(String message) {
        System.out.println(message);
    }

    public static String reverse(String message) {
        StringBuilder reversedMessage = new StringBuilder();
        for (int i = message.length() - 1; i >= 0; i--) {
            reversedMessage.append(message.charAt(i));
        }
        return reversedMessage.toString();
    }

    public static int wordNumber(String message) {
        String trimmedMessage = message.trim();
        String[] words = trimmedMessage.split("\\s+");
        return words.length;
    }
}
