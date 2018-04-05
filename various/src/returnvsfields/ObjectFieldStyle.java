package returnvsfields;

import java.util.ArrayList;
import java.util.List;

class ObjectFieldStyle {
    private List<Fruit> fruitSalad;

    /**
     * Given a recipe, create a fruit salad in {@link fruitSalad}.
     *
     * @param recipe Describe what does in this fruit salad.
     */
    public void makeFruitSalad(Recipe recipe) {
        // Stuff to determines which fruits go in the salad.
        fruitSalad = new ArrayList<>();
        for (Fruit fruit : recipe) {
            if (!addFruitToSalad(fruit)) {
                System.out.println("skipped fruit: " + fruit);
            }
        }
        // There's more stuff here, dressings and such.
    }

    /**
     * Prepare a fruit and add it to the salad.
     *
     * @return Return code indicates success/failure.
     */
    private boolean addFruitToSalad(Fruit fruit) {
        fruit.wash();
        fruit.cut();
        // Let's pretend there's enough stuff here to make a method.
        // And something can go wrong that returns false.
        fruitSalad.add(fruit);
        return true;
    }

    public List<Fruit> getSalad() {
        return fruitSalad;
    }

    public static void main(String[] args) {
        ObjectFieldStyle saladMaker = new ObjectFieldStyle();
        saladMaker.makeFruitSalad(new Recipe(/* ... */));
        saladMaker.getSalad();
    }
}
