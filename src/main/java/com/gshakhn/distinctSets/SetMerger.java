package com.gshakhn.distinctSets;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by gshakhnazaryan on 8/28/16.
 */
class SetMerger {
    Set<String> processedStrings;
    Multimap<String, Set<String>> setsByString;

    public SetMerger(Multimap<String, Set<String>> setsByString) {
        this.setsByString = setsByString;
        this.processedStrings = Sets.newHashSet();
    }

    public List<Set<String>> merge() {
        List<Set<String>> result = Lists.newArrayList();
        for (String string : setsByString.keys()) {
            Optional<Set<String>> strings = mergeSetsWithString(string);
            if (strings.isPresent()) {
                result.add(strings.get());
            }
        }
        return result;
    }

    // Will return empty if we've already merged this string
    private Optional<Set<String>> mergeSetsWithString(String stringToMerge) {
        if (processedStrings.contains(stringToMerge)) {
            return Optional.empty();
        }
        processedStrings.add(stringToMerge);


        Set<String> result = Sets.newHashSet();

        Collection<Set<String>> setsThatHaveStringToMerge = setsByString.get(stringToMerge);
        for (Set<String> otherSets : setsThatHaveStringToMerge) {
            for (String otherString : otherSets) {
                result.add(otherString);
                Optional<Set<String>> otherSetsWithThisString = mergeSetsWithString(otherString);
                if (otherSetsWithThisString.isPresent()) {
                    result.addAll(otherSetsWithThisString.get());
                }
            }
        }

        return Optional.of(result);
    }
}
