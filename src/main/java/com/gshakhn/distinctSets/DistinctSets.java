package com.gshakhn.distinctSets;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class DistinctSets {
    public List<Set<String>> distinctSets(List<Set<String>> originalSets) {
        Multimap<String, Set<String>> setsByString = groupSetsByString(originalSets);

        SetMerger setMerger = new SetMerger(setsByString);
        return setMerger.merge();
    }

    private Multimap<String, Set<String>> groupSetsByString(List<Set<String>> originalSets) {
        Multimap<String, Set<String>> valueToSet = ArrayListMultimap.create();
        for (Set<String> set : originalSets) {
            for (String string : set) {
                valueToSet.put(string, set);
            }
        }
        return valueToSet;
    }


}
