package SportStore;

import SportStore.Model.Kunde;
import SportStore.Model.Produkt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Controller controller;

    public ConsoleView(Controller controller) {
        this.controller = controller;
    }

    public  void start() {
        Scanner scanner = new Scanner(System.in);
        String userChoice;
        System.out.println("Welcome to the SportStore!");

        do{
            System.out.println("\nChoose");
            System.out.println("0. Exit");
            System.out.println("1. Add Product");
            System.out.println("2. Show All Products");
            System.out.println("3. Add client");
            System.out.println("4. Show all clients");
            System.out.println("5. Add product to client");
            System.out.println("6. Delete client");
            System.out.println("7. Delete product");
            System.out.println("8. Update client");
            System.out.println("9. Update product");
            System.out.println("10. Filter clients by city");
            System.out.println("11. See clients that have purchased items for a season");
            System.out.println("12. Sort products for a client");
            System.out.println("Please enter your choice: ");

            userChoice = scanner.nextLine();
            switch (userChoice) {

                case "0":
                    System.out.println("Das Programm wird beendet.");
                    break;

                case "1":
                    System.out.println("Enter product ID:");
                    int productID = scanner.nextInt();
                    System.out.println("Enter product name:");
                    scanner.nextLine();
                    String productName = scanner.nextLine();
                    System.out.println("Enter product price:");
                    Integer productPrice = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter product season:");
                    String productSeason = scanner.nextLine();
                    Produkt product = new Produkt(productName, productPrice, productSeason, productID);
                    controller.addProduct(product);
                    break;

                case"2":
                    List<Produkt> products = controller.getProducts();
                    products.forEach(System.out::println);
                    break;

                case"3":
                    System.out.println("Enter client ID:");
                    int clientID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter client name:");
                    String clientName = scanner.nextLine();
                    System.out.println("Enter client address (City):");
                    String clientAddress = scanner.nextLine();

                    Kunde client = new Kunde(clientID, clientName, clientAddress);
                    controller.addClient(client);
                    break;

                case"4":
                    List<Kunde> clients = controller.getClients();
                    clients.forEach(System.out::println);
                    break;

                case"5":
                    List<Kunde> clientsToShow = controller.getClients();
                    clientsToShow.forEach(System.out::println);
                    System.out.println("Enter client's ID:");
                    Integer id = scanner.nextInt();
                    scanner.nextLine();
                    controller.showProducts();
                    System.out.println("Enter product ID:");
                    Integer prodId = scanner.nextInt();
                    scanner.nextLine();
                    controller.addProductToClient(id, prodId);
                    break;

                case"6":
                    controller.showClients();
                    System.out.println("Enter client ID:");
                    Integer clientId = scanner.nextInt();
                    scanner.nextLine();
                    Kunde clientToDelete = controller.getClientByID(clientId);
                    controller.deleteClient(clientToDelete);
                    break;

                case"7":
                    controller.showProducts();
                    System.out.println("Enter product ID:");
                    Integer productId = scanner.nextInt();
                    scanner.nextLine();
                    Produkt prodToDelete = controller.getProductByID(productId);
                    controller.deleteProduct(prodToDelete);
                    break;

                case"8":
                    controller.showClients();
                    System.out.println("Enter client ID:");
                    Integer idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Kunde clientToUpdate = controller.getClientByID(idUpdate);
                    System.out.println("Enter new name or press enter to continue");
                    String temp_name = scanner.nextLine();
                    String name;
                    if(temp_name.isEmpty())
                        name = clientToUpdate.getName();
                    else
                        name = temp_name;
                    System.out.println("Enter new  address or press enter to continue");
                    String temp_address = scanner.nextLine();
                    String address;
                    if(temp_address.isEmpty())
                        address=clientToUpdate.getAdresse();
                    else
                        address = temp_address;

                    Kunde updatedClient = new Kunde(clientToUpdate.getId(), name, address);
                    controller.updateClient(updatedClient);
                    break;

                case"9":
                    controller.showProducts();
                    System.out.println("Enter product ID:");
                    Integer updatedID = scanner.nextInt();
                    scanner.nextLine();
                    Produkt prodToUpdate = controller.getProductByID(updatedID);
                    System.out.println("Enter new name or press enter to continue");
                    String temp_name2 = scanner.nextLine();
                    String nameProd;
                    if(temp_name2.isEmpty())
                        nameProd = prodToUpdate.getName();
                    else
                        nameProd = temp_name2;

                    System.out.println("Enter new season or press enter to continue");
                    String temp_season = scanner.nextLine();
                    String season;
                    if(temp_season.isEmpty())
                        season = prodToUpdate.getSeason();
                    else
                        season = temp_season;

                    System.out.println("Enter new price or press enter to continue:");
                    String priceInput = scanner.nextLine();
                    int price;
                    if(priceInput.isEmpty())
                        price = prodToUpdate.getPrice();
                    else
                        price = Integer.parseInt(priceInput);


                    Produkt updatedProd = new Produkt(nameProd,price,season, prodToUpdate.getId());
                    controller.updateProduct(updatedProd);
                    break;

                case"10":
                    System.out.println("Enter city to filter clients by:");
                    String city = scanner.nextLine();
                    List<Kunde> filteredClients = controller.filterClientsByPlace(city);
                    filteredClients.forEach(System.out::println);
                    break;

                case"11":
                    System.out.println("Enter season to filter by");
                    String seasonFilter = scanner.nextLine();
                    List<Kunde> filtereDClientsSeason = controller.filterClientsByProduct(seasonFilter);
                    filtereDClientsSeason.forEach(System.out::println);
                    break;

                case"12":
                    System.out.println("Enter ID of the client to filter products:");
                    Integer clientidd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("How do you want to sort: ASC/DESC");
                    String sortOption = scanner.nextLine();
                    List<Produkt> prodsFiltered = new ArrayList<Produkt>();
                    if(sortOption.equals("ASC"))
                        prodsFiltered = controller.sortProductsAsc(clientidd);
                    else
                        prodsFiltered= controller.sortProductsDesc(clientidd);


                    System.out.println("Sorted products for client" + clientidd);

                    prodsFiltered.forEach(System.out::println);
                    break;



                default:
                    System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Option.");
                    break;

            }

        }while (!userChoice.equals("0"));
    }

}
