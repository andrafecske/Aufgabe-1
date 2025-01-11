package SportStore;

import SportStore.Model.Kunde;
import SportStore.Model.Produkt;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private final List<Produkt> products = new ArrayList<Produkt>();
    private final List<Kunde> clients = new ArrayList<Kunde>();

    public void addProduct(String name, int price, String season) {
        products.add(new Produkt(name, price, season));
    }

    public List<Produkt> getProducts() {
        return products;
    }

    public boolean updateProduct(String name, int price, String season) {
        for(Produkt p : products) {
            if(p.getName().equals(name)) {
                p.setName(name);
                p.setPrice(price);
                p.setSeason(season);
                return true;
            }
        }
        return false;
    }
    public boolean deleteProduct(String name) {
        return products.removeIf(p -> p.getName().equals(name));
    }

    public Produkt getProduct(String name) {
        for(Produkt p : products) {
            if(p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }


}
