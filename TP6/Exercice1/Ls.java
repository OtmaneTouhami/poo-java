package Exercice1;

import java.io.File;
import java.util.Scanner;

public class Ls {

    public static String permissions(File file) {
        return (file.canRead() ? "r" : "-") +
                (file.canWrite() ? "w" : "-") +
                (file.isHidden() ? "h" : "-");
    }

    public static void directoryInspector(File file, String indent) {
        String type = file.isDirectory() ? "<DIR>" : "<FICH>";
        String line = String.format("%s%s %s %s", indent, file, type, permissions(file));
        System.out.println(line);

        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    directoryInspector(subFile, indent + "----");
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory path: ");
        String path = scanner.nextLine();
        File file = new File(path);
        scanner.close();
        if (!file.exists()) {
            System.out.println("The directory does not exist or an I/O error occurred.");
            return;
        }
        directoryInspector(file, "");
    }
}