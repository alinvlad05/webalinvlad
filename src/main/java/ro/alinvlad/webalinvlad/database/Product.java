package ro.alinvlad.webalinvlad.database;



public class Product {

    private int id;
    private String model;
    private double price;
    private String about_product;
    private String picture;
    private String url;
    private int quantity;
    private String operating_system;
    private String battery;
    private int dxomark_score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAbout_product() {
        return about_product;
    }

    public void setAbout_product(String about_product) {
        this.about_product = about_product;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getOperating_system() {
        return operating_system;
    }

    public void setOperating_system(String operating_system) {
        this.operating_system = operating_system;
    }

    public String getBattery() {
        return battery;
    }

    public void setBattery(String battery) {
        this.battery = battery;
    }

    public int getDxomark_score() {
        return dxomark_score;
    }

    public void setDxomark_score(int dxomark_score) {
        this.dxomark_score = dxomark_score;
    }
}
