package com.gshakhn.distinctSets;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

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

    private class SetMerger {
        Set<String> processedStrings;
        Multimap<String, Set<String>> setsByString;

        public SetMerger(Multimap<String, Set<String>> setsByString) {
            this.setsByString = setsByString;
            this.processedStrings = Sets.newHashSet();
        }

        public List<Set<String>> merge() {
            List<Set<String>> result = Lists.newArrayList();
            for (String string : setsByString.keys()) {
                Optional<Set<String>> strings = mergeString(string);
                if (strings.isPresent()) {
                    result.add(strings.get());
                }
            }
            return result;
        }

        private Optional<Set<String>> mergeString(String stringToMerge) {
            Collection<Set<String>> setsThatHaveStringToMerge = setsByString.get(stringToMerge);
            if (processedStrings.contains(stringToMerge)) {
                return Optional.empty();
            }

            processedStrings.add(stringToMerge);
            Set<String> result = Sets.newHashSet();

            for (Set<String> otherSets : setsThatHaveStringToMerge) {
                for (String otherString : otherSets) {
                    result.add(otherString);
                    Optional<Set<String>> otherSetsWithThisString = mergeString(otherString);
                    if (otherSetsWithThisString.isPresent()) {
                        result.addAll(otherSetsWithThisString.get());
                    }
                }
            }

            return Optional.of(result);
        }
    }


}
