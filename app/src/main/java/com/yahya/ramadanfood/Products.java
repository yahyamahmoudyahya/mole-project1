package com.yahya.ramadanfood;

public class Products {
    private String title ;
    private String image ;
    private String des ;

    public Products() {
    }

    public Products(String title, String image, String des) {
        this.title = title;
        this.image = image;
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
