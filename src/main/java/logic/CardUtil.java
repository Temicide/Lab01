package logic;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CardUtil {

    public static boolean isExistsInList(UnitCard card, ArrayList<UnitCard> list) {
        for (UnitCard cardList : list) {
            if (card == cardList) return true;
        }
        return false;

    }

    public static boolean isExistsInList(UnitDeck deck, ArrayList<UnitDeck> list) {
        for (UnitDeck deckList : list) {
            if (deck == deckList) return true;
        }
        return false;

    }

    public static boolean cardExistsInDeckList(ArrayList<UnitDeck> deckList, UnitCard cardToTest) {
        for (UnitDeck deck : deckList) {
            for (CardCounter counterList : deck.getCardsInDeck()) {
                if (counterList.getCard() == cardToTest) return true;
            }
        }
        return false;
    }

    public static ArrayList<UnitCard> getCardsFromFile(String filename) {
        ArrayList<UnitCard> cardsFromFile = new ArrayList<UnitCard>();
        InputStream inputStream = CardUtil.class.getClassLoader().getResourceAsStream(filename);

        if (inputStream == null) {
            System.out.println("Cannot find file!");
            return null;
        }

        Scanner myReader = new Scanner(inputStream);

        boolean isError = false;

        while (myReader.hasNextLine()) {
            String[] splittedString = myReader.nextLine().split(",");

            if (splittedString.length != 5) {
                isError = true;
                break;
            }

            try {
                String name = splittedString[0];
                int bloodCost = Integer.parseInt(splittedString[1]);
                int power = Integer.parseInt(splittedString[2]);
                int health = Integer.parseInt(splittedString[3]);
                String flavorText = splittedString[4];
                UnitCard newCard = new UnitCard(name, bloodCost, power, health, flavorText);
            } catch (NumberFormatException f) {
                isError = true;
                break;
            }
        }

        myReader.close();

        if (isError) {
            System.out.println("File contains\n" + "string with incorrect format!");
            return null;
        }

        return cardsFromFile;
    }


    public static void printCardList(ArrayList<UnitCard> cardList, boolean verbose) {

        for (int i = 0; i < cardList.size(); i++) {
            System.out.println(i + ") " + cardList.get(i));
            if (verbose) {
                System.out.println("Blood Cost: " + cardList.get(i).getBloodCost());
                System.out.println(cardList.get(i).getFlavorText());
                if (i < cardList.size() - 1) System.out.println("-----");
            }
        }
    }

    public static void printDeck(UnitDeck ud) {

        if (ud.getCardsInDeck().size() == 0) {
            System.out.println("EMPTY DECK");
        } else {
            for (CardCounter cc : ud.getCardsInDeck()) {
                System.out.println(cc);
            }
        }

        System.out.println("Total Cards: " + ud.cardCount());
    }

    public static void printDeckList(ArrayList<UnitDeck> deckList) {


        for (int i = 0; i < deckList.size(); i++) {
            System.out.println(i + ") " + deckList.get(i).getDeckName());
            printDeck(deckList.get(i));
            if (i < deckList.size() - 1) System.out.println("-----");
        }
    }
}
