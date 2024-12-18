package Exercice2.Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String fileName = reader.readLine();
            System.out.println("Client requested file: " + fileName);

            File file = new File("Exercice2/Server/Files/" + fileName);

            if (file.exists() && file.isFile()) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    fileReader.lines().forEach(writer::println);
                }
            } else {
                writer.println("The requested file does not exist!");
                System.out.println("File not found: " + fileName);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
