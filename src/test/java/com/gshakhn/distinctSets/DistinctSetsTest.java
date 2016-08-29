package com.gshakhn.distinctSets;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DistinctSetsTest {

    @Test
    public void emptySets() {
        assertDistinctSets(
                Lists.newArrayList(),
                Lists.newArrayList()
        );
    }

    @Test
    public void singleSetOfOneElement() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"))
        );
    }

    @Test
    public void twoSetsOfSameElement() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"),
                        Sets.newHashSet("A")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"))
        );
    }

    @Test
    public void twoSetsOfDifferentElement() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"),
                        Sets.newHashSet("B")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"),
                        Sets.newHashSet("B"))
        );
    }

    @Test
    public void threeSetsOfSameElement() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"),
                        Sets.newHashSet("A"),
                        Sets.newHashSet("A")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"))
        );
    }

    @Test
    public void threeSetsWhereLastTwoHaveSameElement() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"),
                        Sets.newHashSet("B"),
                        Sets.newHashSet("B")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A"),
                        Sets.newHashSet("B"))
        );
    }

    @Test
    public void example1() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("G", "J", "N"),
                        Sets.newHashSet("D", "F", "G", "K"),
                        Sets.newHashSet("E", "H"),
                        Sets.newHashSet("B", "C", "J", "L", "Q"),
                        Sets.newHashSet("C", "M")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("B", "C", "D", "F", "G", "J", "K", "L", "M", "N", "Q"),
                        Sets.newHashSet("E", "H"))
        );
    }

    @Test
    public void example2() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A", "C", "E", "G", "H"),
                        Sets.newHashSet("B", "I", "M"),
                        Sets.newHashSet("E", "M", "O")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A", "B", "C", "E", "G", "H", "I", "M", "O"))
        );
    }

    @Test
    public void example3() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("D", "E", "J", "L"),
                        Sets.newHashSet("F", "K"),
                        Sets.newHashSet("L", "M"),
                        Sets.newHashSet("I", "K"),
                        Sets.newHashSet("I", "K")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("D", "E", "J", "L", "M"),
                        Sets.newHashSet("F", "I", "K"))
        );
    }

    @Test
    public void example4() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("B", "E", "L", "M"),
                        Sets.newHashSet("B", "I", "L", "O", "P"),
                        Sets.newHashSet("A", "J", "O", "P"),
                        Sets.newHashSet("A", "D", "F", "L")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A", "B", "D", "E", "F", "I", "J", "L", "M", "O", "P"))
        );
    }

    @Test
    public void example5() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("E", "G", "K"),
                        Sets.newHashSet("A", "C", "I", "J", "N"),
                        Sets.newHashSet("C", "J", "M", "N")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("E", "G", "K"),
                        Sets.newHashSet("A", "C", "I", "J", "M", "N"))
        );
    }

    @Test
    public void example6() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A", "D", "E", "H"),
                        Sets.newHashSet("D", "N", "P"),
                        Sets.newHashSet("D", "I", "L", "P")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A", "D", "E", "H", "I", "L", "N", "P"))
        );
    }

    @Test
    public void example7() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("E", "F", "K", "N", "O"),
                        Sets.newHashSet("A", "B", "C", "J", "P")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("E", "F", "K", "N", "O"),
                        Sets.newHashSet("A", "B", "C", "J", "P"))
        );
    }

    @Test
    public void example8() {
        assertDistinctSets(
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("C", "H", "M"),
                        Sets.newHashSet("D", "F", "L"),
                        Sets.newHashSet("A", "E", "J", "O"),
                        Sets.newHashSet("C", "H"),
                        Sets.newHashSet("J", "K", "M"),
                        Sets.newHashSet("A", "N", "Q", "T")),
                Lists.<Set<String>>newArrayList(
                        Sets.newHashSet("A", "C", "E", "H", "J", "K", "M", "N", "O", "Q", "T"),
                        Sets.newHashSet("D", "F", "L"))
        );
    }

    private void assertDistinctSets(List<Set<String>> originalSets, List<Set<String>> expectedResult) {
        DistinctSets distinctSets = new DistinctSets();
        assertThat(distinctSets.distinctSets(originalSets), Matchers.containsInAnyOrder(expectedResult.toArray()));
    }
}
