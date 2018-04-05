package returnvsfields;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    class ReturnStyle {
        /**
         * Given a recipe, create a fruit salad.
         *
         * @param recipe Describe what does in this fruit salad.
         * @return A list of the salad components (mix well!).
         */
        public List<Fruit> makeFruitSalad(Recipe recipe) {
            // Stuff to determines which fruits go in the salad.
            List<Fruit> fruitSalad = new ArrayList<>();
            for (Fruit fruit : recipe) {
                Optional<Fruit> prepared = prepareFruitForSalad(fruit);
                if (prepared.isPresent()) {
                    fruitSalad.add(prepared.get());
                } else {
                    System.out.println("skipped fruit: " + fruit);
                }
            }
            // There's more stuff here, dressings and such.
            return fruitSalad;
        }

        /**
         * Prepare a fruit.
         *
         * @return The fruit if successful, empty if not.
         */
        private Optional<Fruit> prepareFruitForSalad(Fruit fruit) {
            // Perhaps clone the fruit.
            fruit.wash();
            fruit.cut();
            // Let's pretend there's enough stuff here to make a method.
            // And something can go wrong that returns Optional.empty().
            return Optional.of(fruit);
        }

        public static void main(String[] args) {
            ReturnStyle saladMaker = new ReturnStyle();
            List<Fruit> salad = saladMaker.makeFruitSalad(new Recipe(/* ... */));
        }
    }
