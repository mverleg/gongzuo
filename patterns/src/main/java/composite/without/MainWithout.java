package src.main.java.composite.without;

import src.main.java.composite.util.IO;

public class MainWithout {
    public static void main(String[] args) {
        System.out.println("Which 'Rick and Morty' character are you?");
        Question badQuiz = new Question("Are you smart?",
                new Question("Are you male?",
                        new Answer("You are RICK"),
                        new Answer("You are BETH")),
                new Question("Like, really stupid?",
                        new Answer("You are JERRY"),
                        new Question("Are you male?",
                                new Answer("You are MORTY"),
                                new Answer("You are SUMMER"))));
        badQuiz.perform(new IO());
    }
}
