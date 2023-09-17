package edu.pizza.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class yo_la_armo {
    private String name;

    private List<Topping> toppings = new ArrayList<>();

    public yo_la_armo(String name, Topping... toppings) {
        this.name = name;
        for (Topping topping : toppings) {
            this.toppings.add(topping);
        }
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(int index) {
        this.toppings.remove(index);
    }

    public List<Topping> getToppings() {

        return Collections.unmodifiableList(new ArrayList<>(toppings));
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Pizza{" + "name='" + name + '\'' + ", toppings=" + toppings + '}';
    }

}
