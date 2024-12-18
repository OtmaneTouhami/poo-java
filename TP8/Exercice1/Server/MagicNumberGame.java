package Exercice1.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MagicNumberGame {
    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(1227)
        ) {
            System.out.println("Game server is running...");
            int magicNumber = (int) (Math.random() * 101);
            System.out.println("The magic number is: " + magicNumber);
            Socket socket = serverSocket.accept();
            System.out.println("A player start the game...");
            try (
                    InputStream reader = socket.getInputStream();
                    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
            ) {
                boolean isGuessed = false;
                while (!isGuessed) {
                    int playerChoice = reader.read();
                    if (playerChoice == magicNumber) {
                        System.out.println("The player guessed the secret code!");
                        writer.println("Bingo !!!");
                        isGuessed = true;
                    } else {
                        System.out.println("Wrong guess: " + playerChoice);
                        writer.println(
                                playerChoice > magicNumber ?
                                        "Your guess is higher than the secret code" :
                                        "Your guess is lower than the secret code"
                        );
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}