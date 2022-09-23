import java.io.*;
import java.util.*;

public class Potus {
    public static void main(String[] args) throws Exception {
        // 1
        BufferedReader state2PresidentsFile = new BufferedReader(new FileReader("state2Presidents.txt"));
        TreeMap<String, ArrayList<String>> state2PresMap = new TreeMap<String, ArrayList<String>>();
        TreeMap<String, String> president2StatesMap = new TreeMap<String, String>();
        while (state2PresidentsFile.ready()) {
            String[] tokens = state2PresidentsFile.readLine().split("\\s+");
            ArrayList<String> thisStatesPresidents = new ArrayList<String>(Arrays.asList(tokens));
            thisStatesPresidents.remove(0);
            Collections.sort(thisStatesPresidents);
            state2PresMap.put(tokens[0], thisStatesPresidents);
        }
        state2PresidentsFile.close();
        for (String state : state2PresMap.keySet()) {
            System.out.print(state + " ");
            for (String president : state2PresMap.get(state)) {
                System.out.print(president + " ");
                president2StatesMap.put(president, state);
            }
            System.out.println();
        }
        System.out.println();
        // 2
        for (String president : president2StatesMap.keySet()) {
            System.out.println(president + " " + president2StatesMap.get(president));
        }
        System.out.println();

        // 3
        BufferedReader allPresidentsFile = new BufferedReader(new FileReader("allPresidents.txt"));
        TreeSet<String> allPresidentsSet = new TreeSet<String>();
        while (allPresidentsFile.ready()) {
            allPresidentsSet.add(allPresidentsFile.readLine());
        }
        allPresidentsFile.close();
        for (String president : allPresidentsSet) {
            if (!president2StatesMap.keySet().contains(president)) {
                System.out.println(president);
            }
        }
        System.out.println();
        // 4
        BufferedReader allStatesFile = new BufferedReader(new FileReader("allStates.txt"));
        TreeSet<String> allStatesSet = new TreeSet<String>();
        while (allStatesFile.ready()) {
            allStatesSet.add(allStatesFile.readLine());
        }
        allStatesFile.close();
        for (String state : allStatesSet) {
            if (!state2PresMap.keySet().contains(state))
                System.out.println(state);
        }
    }
}