package Exercice1.Client;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 1227);
                Scanner scanner = new Scanner(System.in)
        ) {
            try (
                    OutputStream writer = socket.getOutputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
            ) {
                boolean isGuessed = false;

                while (!isGuessed) {
                    int playerChoice = -1;
                    while (playerChoice < 0 || playerChoice > 100) {
                        try {
                            System.out.print("Enter your guess for the secret code: ");
                            playerChoice = scanner.nextInt();
                            scanner.nextLine();
                            if (playerChoice < 0 || playerChoice > 100) {
                                System.out.println("The secret code is between 0 and 100! Try again!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid character!");
                            scanner.nextLine();
                        }
                    }

                    writer.write(playerChoice);
                    writer.flush();

                    String response = reader.readLine();
                    System.out.println(response);
                    if (response.startsWith("Bingo")) {
                        isGuessed = true;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}