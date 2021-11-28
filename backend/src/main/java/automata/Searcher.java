package automata;

import java.util.ArrayList;
import java.util.List;

public class Searcher {
    /**
     * Return a list of index pairs where the substring of that index pair is accepted by the fsa.
     * @param fsa the given fsa
     * @param text the given text
     * @return a list of index pairs representing the substring of text accepted by the fsa
     */
    public static List<List<Integer>> search(FSA fsa, String text){
        List<List<Integer>> ret = new ArrayList<>();

        // loop for every char in text as the starting char
        for(int start = 0; start < text.length(); start++){
            int end = start + 1;

            // if match is found, get the longest match and append index pair
            if (fsa.accept(text.substring(start, end))){
                //get the longest substring
                while(fsa.accept(text.substring(start, end + 1))){
                    end += 1;
                }

                //append index pair
                List<Integer> indexPair = new ArrayList<>();
                indexPair.add(start);
                indexPair.add(end);
                ret.add(indexPair);
            }
        }

        return ret;
    }

}
