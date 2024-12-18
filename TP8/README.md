# Java Network Programming Lab

## Description
This lab contains a collection of Java networking exercises that demonstrate fundamental concepts of socket programming and client-server architecture. The exercises focus on implementing practical applications using Java's networking capabilities, including a number guessing game and a file server system.

## Table of Contents
1. [Magic Number Game](#magic-number-game)
   - [Overview](#overview)
   - [Server Implementation](#server-implementation)
   - [Server Code Analysis](#server-code-analysis)
   - [Client Implementation](#client-implementation)
   - [Client Code Analysis](#client-code-analysis)
   - [Game Output Analysis](#game-output-analysis)

2. [File Server System](#file-server-system)
   - [Overview](#overview-1)
   - [Server Implementation](#server-implementation-1)
   - [Server Code Analysis](#server-code-analysis-1)
   - [Client Handler Implementation](#client-handler-implementation)
   - [Client Handler Analysis](#client-handler-analysis)
   - [Client Implementation](#client-implementation-1)
   - [Client Code Analysis](#client-code-analysis-1)
   - [System Output Analysis](#system-output-analysis)

3. [Project Structure](#project-structure)
4. [Conclusion](#conclusion)

## Magic Number Game

### Overview
A client-server implementation of a number guessing game where the server generates a random number, and the client attempts to guess it through socket communication.

### Server Implementation

1. **Server Socket Creation and Configuration**
```java
try (ServerSocket serverSocket = new ServerSocket(1227)) {
    System.out.println("Game server is running...");
```
This block represents the initial server setup. The code establishes a server socket on port 1227 utilizing Java's try-with-resources pattern to ensure proper resource management. Upon successful initialization, the server displays a startup message to confirm its operational status.

2. **Game Initialization**
```java
int magicNumber = (int) (Math.random() * 101);
System.out.println("The magic number is: " + magicNumber);
Socket socket = serverSocket.accept();
System.out.println("A player start the game...");
```
In this section, the server generates a random number between 0 and 100 using Java's Math.random() function. The generated number is stored and displayed for verification purposes. The server then enters a waiting state using serverSocket.accept() until a client connection is established. Upon successful connection, it announces the start of the game.

3. **Stream Setup**
```java
try (
    InputStream reader = socket.getInputStream();
    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
) {
```
This code segment initializes the communication channels between server and client. An InputStream is created to receive client guesses, while a PrintWriter is established for sending responses. The try-with-resources pattern ensures proper closure of these streams when they are no longer needed.

4. **Game Loop**
```java
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
```
This section implements the core game logic. The server continuously reads player guesses through the input stream and compares them with the magic number. For each guess, it provides appropriate feedback: either congratulating the player on a correct guess or indicating whether the guess was too high or too low. The loop continues until the correct number is guessed.

### Server Code Analysis
The server implementation demonstrates a clear structure, starting with the initialization of the server socket and the generation of the magic number. The game loop is the core component, where the server continuously receives and processes client guesses, providing feedback until the correct number is guessed.

### Client Implementation

1. **Socket and Scanner Setup**
```java
try (
    Socket socket = new Socket("localhost", 1227);
    Scanner scanner = new Scanner(System.in)
) {
```
This code establishes a connection to the server and initializes a Scanner for user input.

2. **Stream Initialization**
```java
try (
    OutputStream writer = socket.getOutputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
) {
```
This section initializes the output stream to send guesses to the server and the input stream to receive feedback.

3. **Input Validation Loop**
```java
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
```
This block prompts the user for input, validates the guess to ensure it is within the range of 0 to 100, and handles invalid input.

4. **Server Communication**
```java
writer.write(playerChoice);
writer.flush();

String response = reader.readLine();
System.out.println(response);
if (response.startsWith("Bingo")) {
    isGuessed = true;
}
```
This code sends the guess to the server, reads the server's response, and checks if the guess was correct.

### Client Code Analysis
The client implementation is structured around the communication with the server. It initializes the connection, sets up the input/output streams, and enters a loop where it continuously prompts the user for guesses, sends them to the server, and displays the feedback received.

### Game Output Analysis
**Server Output:**
```
Game server is running...
The magic number is: 45
A player start the game...
Wrong guess: 61
Wrong guess: 11
Wrong guess: 44
The player guessed the secret code!
```
This output shows the server's initialization, the generation of the magic number, and the tracking of the player's guesses until the correct number is guessed.

**Client Output:**
```
Enter your guess for the secret code: 61
Your guess is higher than the secret code
Enter your guess for the secret code: 101
The secret code is between 0 and 100! Try again!
Enter your guess for the secret code: -6
The secret code is between 0 and 100! Try again!
Enter your guess for the secret code: 11
Your guess is lower than the secret code
Enter your guess for the secret code: 44
Your guess is lower than the secret code
Enter your guess for the secret code: 45
Bingo !!!
```
This output demonstrates the client's interaction with the user, prompting for guesses, displaying feedback from the server, and handling invalid input.

## File Server System

### Overview
A multi-threaded file server implementation that allows clients to request and receive files from the server. The system demonstrates file handling, multi-threading, and socket programming concepts.

### Server Implementation

1. **Server Socket Setup**
```java
try (ServerSocket serverSocket = new ServerSocket(1337)) {
    System.out.println("File server is running...");
```
This code establishes a server socket on port 1337 and displays a startup message.

2. **Client Connection Loop**
```java
while (true) {
    Socket socket = serverSocket.accept();
    System.out.println("New client connected.");
    ClientHandler client = new ClientHandler(socket);
    new Thread(client).start();
}
```
This block continuously accepts client connections, creates a ClientHandler for each connection, and starts a new thread for concurrent handling.

### Server Code Analysis
The server implementation demonstrates a clear structure, starting with the initialization of the server socket and the continuous acceptance of client connections. Each client is handled in a separate thread, enabling concurrent file requests.

### Client Handler Implementation

1. **Handler Initialization**
```java
public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }
```
This code initializes the ClientHandler with the client socket.

2. **Stream Setup**
```java
try (
    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
) {
```
This section initializes the input/output streams for communication with the client.

3. **File Processing**
```java
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
```
This block reads the requested filename, checks if the file exists, and streams the file content to the client if found.

4. **Resource Cleanup**
```java
finally {
    try {
        clientSocket.close();
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
```
This code ensures the client socket is closed after handling the request.

### Client Handler Analysis
The ClientHandler implementation demonstrates a clear structure, starting with the initialization of the input/output streams and the processing of the client's file request. The handler checks if the file exists and streams the content to the client if found.

### Client Implementation

1. **Connection Setup**
```java
try (
    Socket socket = new Socket("localhost", 1337);
    Scanner scanner = new Scanner(System.in);
    PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
) {
```
This code establishes a connection to the server and initializes the input/output streams.

2. **File Request**
```java
System.out.print("Enter the file name: ");
String fileName = scanner.nextLine().trim();

writer.println(fileName);
```
This section prompts the user for the filename and sends the request to the server.

3. **Response Processing**
```java
String response = reader.lines().collect(Collectors.joining("\n"));
System.out.println("Server Response:");
System.out.println(response);
```
This block reads the server's response and displays it to the user.

### Client Code Analysis
The client implementation demonstrates a clear structure, starting with the establishment of the connection to the server and the initialization of the input/output streams. The client prompts the user for the filename, sends the request to the server, and displays the response.

### System Output Analysis
**Server Output:**
```
File server is running...
New client connected.
Client requested file: file.txt
File not found: file.txt
New client connected.
Client requested file: test.txt
```
This output shows the server's initialization, the tracking of client connections, and the processing of file requests.

**Client 1 Output (File Not Found):**
```
Enter the file name: file.txt
Server Response:
The requested file does not exist!
```
This output demonstrates the client's interaction with the user, prompting for the filename and displaying the server's response.

**Client 2 Output (Successful Request):**
```
Enter the file name: test.txt
Server Response:
[File contents displayed...]
```
This output shows the client's successful request and the display of the file content.

## Project Structure
```
TP8
│   .gitignore
│   README.md
│       
├───Exercice1
│   ├───Client
│   │       Player.java
│   │
│   └───Server
│           MagicNumberGame.java
│
└───Exercice2
    ├───Client
    │       Client.java
    │
    └───Server
        │   ClientHandler.java
        │   FileServer.java
        │
        └───Files
            test.txt
```

## Conclusion
This lab successfully demonstrates key concepts in Java network programming through two practical implementations. The Magic Number Game showcases basic client-server communication with user interaction, while the File Server System illustrates advanced concepts such as multi-threading and file handling. Both implementations emphasize proper resource management, error handling, and clean code organization. Through these exercises, we've explored fundamental networking concepts while maintaining good programming practices, providing a solid foundation for building distributed applications.