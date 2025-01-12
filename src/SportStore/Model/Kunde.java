package SportStore.Model;

import java.util.ArrayList;
import java.util.List;

public class Kunde implements HasID {
    private int id;
    private String name;
    private String adresse;
    private List<Produkt> produktList;


    public Kunde(int id, String name, String adresse) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        produktList = new ArrayList<Produkt>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Produkt> getProduktList() {
        return produktList;
    }

    public void setProduktList(List<Produkt> produktList) {
        this.produktList = produktList;
    }

    @Override
    public String toString() {
        return "Kunde" +
                "name='" + name + '\'' +
                ", ID=" + id +
                ", produktList=" + produktList +
                ", adresse='" + adresse +  '\'';
    }
}
