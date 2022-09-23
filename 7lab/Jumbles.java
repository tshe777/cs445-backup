import java.io.*;
import java.util.*;

//tas242 jumbles lab

public class Jumbles {
    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java Jumbles dictionary.txt jumbles.txt");
            System.exit(0);
        }

        ArrayList<String> dictionary = new ArrayList<String>();
        BufferedReader dInput = new BufferedReader(new FileReader(args[0]));
        while (dInput.ready()) {
            dictionary.add(dInput.readLine());
        }
        Collections.sort(dictionary);
        dInput.close();

        HashMap<String, String> dMap = new HashMap<String, String>();
        for (String dictWord : dictionary) {
            String dCanon = toCanonical(dictWord);
            if (dMap.containsKey(dCanon) == false)
                dMap.put(dCanon, dictWord);
            else
                dMap.put(dCanon, dMap.get(dCanon) + " " + dictWord);
        }
        ArrayList<String> jumbles = new ArrayList<String>();

        BufferedReader jInput = new BufferedReader(new FileReader(args[1]));

        while (jInput.ready()) {
            jumbles.add(jInput.readLine());
        }
        Collections.sort(jumbles);
        jInput.close();

        for (String jWord : jumbles) {
            String jCanon = toCanonical(jWord);
            if (dMap.containsKey(jCanon)) {
                System.out.println(jWord + " " + dMap.get(jCanon));
            } else {
                System.out.println(jWord);
            }
        }

    }

    static String toCanonical(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }

}
