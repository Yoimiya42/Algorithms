/*
 * Exercise 2 – Triplicates

    Given 3 arrays of n strings each, design a guaranteed linearithmic (i.e., O(nlogn)) algorithm to determine if there is any string that is common to all three. Return such string.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SP4_2_Triplicates {

    public static void main(String[] args){
        String strs1[] = {"legacy", "tally", "exhaust", "propagate"};
        String strs2[] = {"forgery", "exhaust", "controversial", "propagate"};
        String strs3[] = {"exhaust", "propagate", "propagate", "catalogue"};

        
        Triplicates tri = new Triplicates();
        HashSet<String> result = tri.isTriplicates(strs1, strs2, strs3);
        System.out.println("Common strings: " + result);
    }

}

class Triplicates{
    public HashSet<String> isTriplicates(String strs1[], String strs2[], String strs3[]){

        Map <String, Integer> map = new HashMap<>();
        
        for(String str1: strs1){
            map.put(str1,1);
        }

        for(String str2: strs2){
            if(map.containsKey(str2)){
                map.put(str2, 2);
            }
        }

        HashSet <String> common = new HashSet<>();
        for(String str3: strs3){
            if(map.containsKey(str3) && map.get(str3) == 2){
                common.add(str3);
            }
        }

        return common;
    }
}


// Solution2: mergeSort 3 arrays and binary search for common strings