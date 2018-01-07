package se.anders_raberg.adventofcode.day21.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import se.anders_raberg.adventofcode.day21.ArtPattern;

public class ArtPatternTest {

    @Before
    public void setUp() {
    }
    
    @Test
    public void testGetRotated3x3() {
        String[][] startPattern = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected1 = {
                {"G", "D", "A"},
                {"H", "E", "B"},
                {"I", "F", "C"}};

        ArtPattern rotated1 = testee.getRotated();
        assertEquals(new ArtPattern(expected1), rotated1);

        String[][] expected2 = {
                {"I", "H", "G"},
                {"F", "E", "D"},
                {"C", "B", "A"}};

        ArtPattern rotated2 = rotated1.getRotated();
        assertEquals(new ArtPattern(expected2), rotated2);

        String[][] expected3 = {
                {"C", "F", "I"},
                {"B", "E", "H"},
                {"A", "D", "G"}};

        ArtPattern rotated3 = rotated2.getRotated();
        assertEquals(new ArtPattern(expected3), rotated3);

        String[][] expected4 = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}};

        ArtPattern rotated4 = rotated3.getRotated();
        assertEquals(new ArtPattern(expected4), rotated4);
    }

    @Test
    public void testGetRotated2x2() {
        String[][] startPattern = {
                {"X", "Y"},
                {"Z", "W"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected = {
                {"Z", "X"},
                {"W", "Y"}};

        ArtPattern rotated = testee.getRotated();
        assertEquals(new ArtPattern(expected), rotated);
    }

    @Test
    public void testGetRotated4x4() {
        String[][] startPattern = {
                {"0", "1", "2", "3"},
                {"4", "5", "6", "7"},
                {"8", "9", "A", "B"},
                {"C", "D", "E", "F"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected = {
                {"C", "8", "4", "0"},
                {"D", "9", "5", "1"},
                {"E", "A", "6", "2"},
                {"F", "B", "7", "3"}};

        ArtPattern rotated = testee.getRotated();
        assertEquals(new ArtPattern(expected), rotated);
    }

    @Test
    public void testGetVerticallyFlipped2x2() {
        String[][] startPattern = {
                {"X", "Y"},
                {"Z", "W"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected = {
                {"Z", "W"},
                {"X", "Y"}};

        ArtPattern flipped = testee.getVerticallyFlipped();
        assertEquals(new ArtPattern(expected), flipped);
    }

    @Test
    public void testGetVerticallyFlipped3x3() {
        String[][] startPattern = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected = {
                {"G", "H", "I"},
                {"D", "E", "F"},
                {"A", "B", "C"}};

        ArtPattern flipped = testee.getVerticallyFlipped();
        assertEquals(new ArtPattern(expected), flipped);
    }

    @Test
    public void testGetHorzontallyFlipped2x2() {
        String[][] startPattern = {
                {"X", "Y"},
                {"Z", "W"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected = {
                {"Y", "X"},
                {"W", "Z"}};

        ArtPattern flipped = testee.getHorzontallyFlipped();
        assertEquals(new ArtPattern(expected), flipped);
    }

    @Test
    public void testGetHorzontallyFlipped3x3() {
        String[][] startPattern = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}};
        
        ArtPattern testee = new ArtPattern(startPattern);

        String[][] expected = {
                {"C", "B", "A"},
                {"F", "E", "D"},
                {"I", "H", "G"}};

        ArtPattern flipped = testee.getHorzontallyFlipped();
        assertEquals(new ArtPattern(expected), flipped);
    }

    @Test
    public void testSplit2x2() {
        String[][] startPattern = {
                {"X", "Y"},
                {"Z", "W"}};
        
        ArtPattern testee = new ArtPattern(startPattern);
        ArtPattern[][] expectedSplit = {{new ArtPattern(startPattern)}};
        ArtPattern[][] splitPattern = testee.split();
        assertArrayEquals(expectedSplit, splitPattern);
    }

    @Test
    public void testSplit3x3() {
        String[][] startPattern = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I"}};
        
        ArtPattern testee = new ArtPattern(startPattern);
        ArtPattern[][] expectedSplit = {{new ArtPattern(startPattern)}};
        ArtPattern[][] splitPattern = testee.split();
        assertArrayEquals(expectedSplit, splitPattern);
    }

    @Test
    public void testSplitAndJoin4x4() {
        String[][] startPattern = {
                {"0", "1", "2", "3"},
                {"4", "5", "6", "7"},
                {"8", "9", "A", "B"},
                {"C", "D", "E", "F"}};
        
        
        String[][] expect00 = {
                {"0", "1"},
                {"4", "5"}};
        
        String[][] expect01 = {
                {"2", "3"},
                {"6", "7"}};

        String[][] expect10 = {
                {"8", "9"},
                {"C", "D"}};

        String[][] expect11 = {
                {"A", "B"},
                {"E", "F"}};

        ArtPattern testee = new ArtPattern(startPattern);
        ArtPattern[][] expectedSplit = {
                {new ArtPattern(expect00), new ArtPattern(expect01)},
                {new ArtPattern(expect10), new ArtPattern(expect11)}};
        ArtPattern[][] splitPattern = testee.split();
        assertArrayEquals(expectedSplit, splitPattern);
        
        assertEquals(testee, ArtPattern.joinPatterns(expectedSplit));
        
    }

    @Test
    public void testSplitAndJoin6x6() {
        String[][] startPattern = {
                {"0", "1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "A", "B"},
                {"C", "D", "E", "F", "G", "H"},
                {"I", "J", "K", "L", "M", "N"},
                {"O", "P", "Q", "R", "S", "T"},
                {"U", "V", "W", "X", "Y", "Z"}};
        
        
        String[][] expect00 = {
                {"0", "1", "2"},
                {"6", "7", "8"},
                {"C", "D", "E"}};
        
        String[][] expect01 = {
                {"3", "4", "5"},
                {"9", "A", "B"},
                {"F", "G", "H"}};
        
        String[][] expect10 = {
                {"I", "J", "K"},
                {"O", "P", "Q"},
                {"U", "V", "W"}};
        
        String[][] expect11 = {
                {"L", "M", "N"},
                {"R", "S", "T"},
                {"X", "Y", "Z"}};
        

        ArtPattern testee = new ArtPattern(startPattern);
        ArtPattern[][] expectedSplit = {
                {new ArtPattern(expect00), new ArtPattern(expect01)},
                {new ArtPattern(expect10), new ArtPattern(expect11)}};
        ArtPattern[][] splitPattern = testee.split();
        assertArrayEquals(expectedSplit, splitPattern);
        assertEquals(testee, ArtPattern.joinPatterns(expectedSplit));
    }

    @Test
    public void testSubPattern() {
        String[][] startPattern = {
                {"0", "1", "2", "3"},
                {"4", "5", "6", "7"},
                {"8", "9", "A", "B"},
                {"C", "D", "E", "F"}};
        
        ArtPattern testee = new ArtPattern(startPattern);
        String[][] expected1 = {
                {"0"}};

        ArtPattern subPattern1 = testee.subPattern(0, 0, 1, 1);
        assertEquals(new ArtPattern(expected1), subPattern1);

        String[][] expected2 = {
                {"F"}};

        ArtPattern subPattern2 = testee.subPattern(3, 3, 1, 1);
        assertEquals(new ArtPattern(expected2), subPattern2);

        String[][] expected3 = {
                {"5", "6"},
                {"9", "A"}};

        ArtPattern subPattern3 = testee.subPattern(1, 1, 2, 2);
        assertEquals(new ArtPattern(expected3), subPattern3);

        String[][] expected4 = {
                {"1", "2", "3"},
                {"5", "6", "7"},
                {"9", "A", "B"}};

        ArtPattern subPattern4 = testee.subPattern(0, 1, 3, 3);
        assertEquals(new ArtPattern(expected4), subPattern4);

    }

    @Ignore
    @Test
    public void testJoinPatterns() {
        fail("Not yet implemented");
    }

}
