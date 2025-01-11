package SportStore.Model;

public class Produkt {
    private String name;
    private int price;
    private String season;

    public Produkt(String name, int price, String season) {
        this.name = name;
        this.price = price;
        this.season = season;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
