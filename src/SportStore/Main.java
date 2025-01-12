package SportStore;

import SportStore.Model.Kunde;
import SportStore.Model.Produkt;
import SportStore.Repo.InMemoryRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Produkt> produktRepo = new InMemoryRepository<Produkt>();
        InMemoryRepository<Kunde> clientRepo = new InMemoryRepository<Kunde>();

        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller(produktRepo, clientRepo);
        ConsoleView consoleView = new ConsoleView(controller);
        consoleView.start();
        scanner.close();
    }
}
