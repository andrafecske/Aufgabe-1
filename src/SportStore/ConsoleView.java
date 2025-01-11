package SportStore;

import java.util.Scanner;

public class ConsoleView {
    private Controller controller;

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userChoice;
        System.out.println("Welcome to the SportStore!");

        do{
            System.out.println("\nChoose");
            System.out.println("0. Exit");
            System.out.println("1. Add Product");
            System.out.println("Please enter your choice: ");
            userChoice = scanner.nextLine();
            switch (userChoice) {
                case "0":
                    System.out.println("Das Programm wird beendet.");
                    break;

                case "1":
                    break;

                default:
                    System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Option.");
                    break;

            }

        }while (!userChoice.equals("0"));
    }

    public static void start(){

    }
}
