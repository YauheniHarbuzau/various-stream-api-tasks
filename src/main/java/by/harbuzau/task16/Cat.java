package by.harbuzau.task16;

public class Cat {

    public String name;
    public String color;
    public String ownerName;

    public Cat() {
    }

    public Cat(String name, String color, String ownerName) {
        this.name = name;
        this.color = color;
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", ownerName='" + ownerName + '\'' +
                '}';
    }
}