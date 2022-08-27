package HW8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SpellChecker implements SpellCheckerInterface {
    HashSet<String> dictionary = new HashSet<>();

    public SpellChecker(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String[] words = input.nextLine().split(" ");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].toLowerCase(Locale.ROOT);
                words[i] = words[i].replaceAll("[^a-z0-9]", "");
                dictionary.add(words[i]);
            }

        }
    }

    @Override
    public List<String> getIncorrectWords(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner input = new Scanner(file);

        ArrayList<String> wrongWords = new ArrayList();

        while (input.hasNextLine()) {
            String[] words = input.nextLine().split(" ");
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].toLowerCase(Locale.ROOT);
                words[i] = words[i].replaceAll("[^a-z0-9]", "");
                if (!dictionary.contains(words[i])) {
                    wrongWords.add(words[i]);
                }
            }
        }


        return wrongWords;
    }

    private static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Override
    public Set<String> getSuggestions(String word) {
        HashSet<String> suggestions = new HashSet<String>();

        word = word.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");

        //check if is word without numbers
        if (dictionary.contains(word)) {
            suggestions.add(word);
        }

        //add characters
        for (int i = 0; i < word.length()+1; i++) {
            for(int j=0; j< alphabet.length; j++){
                String newWord= word.substring(0,i) +alphabet[j] +word.substring(i);
                if (dictionary.contains(newWord)) {
                    suggestions.add(newWord);
                }
            }
        }

        //remove characters
        for (int i = 0; i < word.length(); i++) {
            String newWord= word.substring(0,i) +word.substring(i+1);
            if (dictionary.contains(newWord)) {
                suggestions.add(newWord);
            }
        }

        //swap characters
        for (int i = 0; i < word.length()-1; i++) {
            String newWord= word.substring(0,i) +word.charAt(i+1) +word.charAt(i)+word.substring(i+2);

            if (dictionary.contains(newWord)) {
                suggestions.add(newWord);
            }
        }

        return suggestions;
    }

    public static void main(String[] args) throws FileNotFoundException {
        SpellChecker checker = new SpellChecker("src/HW8/words.txt");
        ArrayList<String> incWords = (ArrayList<String>) checker.getIncorrectWords("src/HW8/test.txt");
        System.out.println(incWords);
        for(int i=0;i<incWords.size();i++) {
            System.out.println(incWords.get(i) + ": " + checker.getSuggestions(incWords.get(i)));
        }
    }
}