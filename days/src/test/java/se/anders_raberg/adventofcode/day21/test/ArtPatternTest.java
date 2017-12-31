package se.anders_raberg.adventofcode.day21.test;

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

    @Ignore
    @Test
    public void testGetHorzontallyFlipped() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testSplit() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testSubPattern() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testJoinPatterns() {
        fail("Not yet implemented");
    }

}
