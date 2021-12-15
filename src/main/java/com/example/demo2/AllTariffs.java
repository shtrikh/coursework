package com.example.demo2;

public class AllTariffs {
    private String Name;
    private int price;
    private int users;
    private int minutes;
    private int megabytes;

    public AllTariffs(String name, int Price, int users, int minutes, int megabytes) {
        this.Name = name;
        this.price = Price;
        this.users = users;
        this.minutes = minutes;
        this.megabytes = megabytes;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return price;
    }

    public int getUsers() {
        return users;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getMegabytes() {
        return megabytes;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setMegabytes(int megabytes) {
        this.megabytes = megabytes;
    }
}
