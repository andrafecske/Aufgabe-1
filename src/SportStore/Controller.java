package SportStore;

import SportStore.Model.Kunde;
import SportStore.Model.Produkt;
import SportStore.Repo.IRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
    //private final List<Produkt> products = new ArrayList<Produkt>();
    private final IRepository<Produkt> products;
    private final IRepository<Kunde> kunden;

    public Controller(IRepository<Produkt> products, IRepository<Kunde> kunden) {
        this.products = products;
        this.kunden = kunden;
        initializeRepository();
    }

    public void initializeRepository() {
        Kunde client1 = new Kunde(1, "Andra", "Satu Mare");
        Kunde client2 = new Kunde(2, "Victor", "Cluj");
        Kunde client3 = new Kunde(3, "Blanca", "Satu Mare");
        Kunde client4 = new Kunde(4, "Ana", "Cluj");

        Produkt prod1 = new Produkt("Rollerskates", 20, "spring", 1);
        Produkt prod2 = new Produkt("Kayak", 2000, "autumn", 2);
        Produkt prod3 = new Produkt("Hiking boots", 300, "autumn", 3);
        Produkt prod4 = new Produkt("Ski jacket", 400, "winter", 4);
        Produkt prod5 = new Produkt("Hiking sticks", 500, "winter", 5);

        products.create(prod1);
        products.create(prod2);
        products.create(prod3);
        products.create(prod4);
        products.create(prod5);

        kunden.create(client1);
        kunden.create(client2);
        kunden.create(client3);
        kunden.create(client4);

        addProductToClient(1, 3);
        addProductToClient(2, 4);
        addProductToClient(3, 5);
        addProductToClient(4, 3);
        addProductToClient(1, 1);
        addProductToClient(2, 3);
        addProductToClient(1, 2);


    }

    /**
     * Creates a new {@link Produkt}
     *
     */

    public void addProduct(Produkt product) {
        products.create(product);
    }

    public List<Produkt> getProducts() {
        return products.getAll();
    }

    public void updateProduct(Produkt product) {
        products.update(product.getId(), product);
    }
    public void deleteProduct(Produkt product) {
        products.delete(product.getId());
    }

    public Produkt getProductByID(Integer ID) {
        return products.read(ID);
    }

    public void addClient(Kunde client) {
        kunden.create(client);
    }

    public void updateClient(Kunde client) {
        kunden.update(client.getId(), client);
    }
    public void deleteClient(Kunde client) {
        kunden.delete(client.getId());
    }

    public List<Kunde> getClients() {
        return kunden.getAll();
    }

    public Kunde getClientByID(Integer ID) {
        return kunden.read(ID);
    }

    public void showProducts(){
        List<Produkt> produktList = products.getAll();
        for(Produkt produkt : produktList){
            System.out.println(produkt);
        }
    }

    public void showClients(){
        List<Kunde> clientList = kunden.getAll();
        for(Kunde client : clientList){
            System.out.println(client);
        }
    }

    public void addProductToClient(Integer clientID, Integer productID) {
        Kunde client = kunden.read(clientID);
        Produkt product = getProductByID(productID);
        client.getProduktList().add(product);

    }

    public List<Kunde> filterClientsByPlace(String place) {
        List<Kunde> clientList = kunden.getAll();
        return clientList.stream()
                .filter(kunde -> kunde.getAdresse().equals(place))
                .collect(Collectors.toList());

    }

    public List<Kunde> filterClientsByProduct (String season){
        List<Kunde> clientList = kunden.getAll();
        return clientList.stream()
                .filter(kunde-> kunde.getProduktList().stream()
                        .anyMatch(produkt -> produkt.getSeason().equals(season)))
                .collect(Collectors.toList());

    }

    public List<Produkt> sortProductsAsc(Integer clientID){
        Kunde kunde = kunden.read(clientID);
        List<Produkt> produktList= kunde.getProduktList();
        return produktList.stream()
                .sorted(Comparator.comparing(Produkt::getPrice))
                .collect(Collectors.toList());

    }

    public List<Produkt> sortProductsDesc(Integer clientID){
        Kunde kunde = kunden.read(clientID);
        List<Produkt> produktList= kunde.getProduktList();
        return kunde.getProduktList().stream()
                .sorted(Comparator.comparing(Produkt::getPrice).reversed()) // Sort by price in descending order
                .collect(Collectors.toList());

    }





}
