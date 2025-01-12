package SportStore.Model;

public class Produkt implements HasID {
    private String name;
    private int price;
    private String season;
    private Integer ID;

    public Produkt(String name, int price, String season, Integer ID) {
        this.name = name;
        this.price = price;
        this.season = season;
        this.ID = ID;
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

    @Override
    public Integer getId() {
        return ID;
    }


    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Produkt" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", season='" + season + '\'' +
                ", ID=" + ID ;
    }
}
