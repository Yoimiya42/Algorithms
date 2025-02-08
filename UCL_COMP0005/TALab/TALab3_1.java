/*
Exercise 1 – Anagrams
    Given a list of words, design and implement an efficient algorithm to group all anagrams together. Words w1 and w2 are said to be anagrams if by rearranging the letters of w1, we can get w2 using all the original letters of w1 exactly once.

    Example:
    Given the list of words [eat, tea, part, ate, trap, pass], your algorithm should output:
    [eat, tea, ate]
    [part, trap]
    [pass]

    Hint: First sort each individual word (rearrange its characters alphabetically), then sort the list of rearranged words.
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TALab3_1
{
    public static void main(String[] args)
    {   
        TALab3_1 lab3_1 = new TALab3_1();
        String[] words = {"eat", "tea", "part", "ate", "trap", "pass"};
        ArrayList<ArrayList<String>> result = lab3_1.groupAnagrams(words);
        result.sort((a, b) -> Integer.compare(b.size(), a.size()));
        for (ArrayList<String> group : result) {
            System.out.println(group);
        }

    }

    public String insertionSort(String arr){
        char[] charArr = arr.toCharArray();
        for(int i = 1; i < charArr.length; i++)
        {
            char key = charArr[i];
            int j = i - 1;
            while(j >= 0 && charArr[j] > key)
                charArr[j+1] = charArr[j--];
            
            charArr[j+1] = key;
        }

        return new String(charArr);
    }

    public ArrayList<ArrayList<String>> groupAnagrams(String[] strs){
        Map <String, ArrayList<String>> hmap = new HashMap<>();

        for(String str:strs){
            String sortedStr = insertionSort(str);
            ArrayList<String> list = hmap.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            hmap.put(sortedStr, list);
        }

        return new ArrayList<>(hmap.values());
    }






}