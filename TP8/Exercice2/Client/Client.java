package Exercice2.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 1337);
                Scanner scanner = new Scanner(System.in);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.print("Enter the file name: ");
            String fileName = scanner.nextLine().trim();

            writer.println(fileName);

            String response = reader.lines().collect(Collectors.joining("\n"));
            System.out.println("Server Response:");
            System.out.println(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
