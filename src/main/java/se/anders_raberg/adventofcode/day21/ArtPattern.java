package se.anders_raberg.adventofcode.day21;

import java.util.Arrays;

public class ArtPattern {
    private final String[][] _pattern;

    public ArtPattern(String[][] pattern) {
        _pattern = pattern;
    }

    public String[][] getPattern() {
        return _pattern;
    }

    public ArtPattern getRotated() {
        int size = _pattern.length;
        String[][] result = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = _pattern[size - j - 1][i];
            }
        }
        return new ArtPattern(result);
    }

    public ArtPattern getVerticallyFlipped() {
        int size = _pattern.length;
        String[][] result = new String[size][size];
        for (int i = 0; i < size ; i++) {
            result[size - i - 1] = Arrays.copyOf(_pattern[i], size);
            result[i] = Arrays.copyOf(_pattern[size - i - 1], size);
        }
        return new ArtPattern(result);
    }

    public ArtPattern getHorzontallyFlipped() {
        int size = _pattern.length;
        String[][] result = new String[size][size];
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = _pattern[i][size -1 - j];
            }
        }
        return new ArtPattern(result);        
    }
    
    public ArtPattern[][] split() {
        ArtPattern[][] result;
        int size;
        int numberOfPieces;
        if (_pattern.length == 2 || _pattern.length == 3) {
            result = new ArtPattern[1][1];
            size = _pattern.length;
        } else if (_pattern.length % 2 == 0) {
            numberOfPieces = _pattern.length /2;
            result = new ArtPattern[numberOfPieces][numberOfPieces];            
            size = _pattern.length / numberOfPieces;
        } else if (_pattern.length % 3 == 0) {
            numberOfPieces = _pattern.length / 3;
            result = new ArtPattern[numberOfPieces][numberOfPieces];
            size = _pattern.length / numberOfPieces;
        } else {
            throw new IllegalStateException();
        }

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = subPattern(i * size, j * size, size, size);
            }
        }
        
        return result;
    }
    
    
    public ArtPattern subPattern(int x, int y, int dx, int dy) {
        String[][] subPatt = new String[dx][dy];

        for (int i = 0; i < dx; i++) {
            for (int j = 0; j < dy; j++) {
                subPatt[i][j] = _pattern[x + i][y + j];
            }
        }
        return new ArtPattern(subPatt);
    }

    public static ArtPattern joinPatterns(ArtPattern[][] patterns) {
        int partDimension = patterns[0][0]._pattern[0].length;
        int joinedDimension = patterns.length * partDimension;
        String[][] joined = new String[joinedDimension][joinedDimension];
        for (int i = 0; i < patterns.length; i++) {
            for (int j = 0; j < patterns.length; j++) {
                for (int i2 = 0; i2 < partDimension; i2++) {
                    for (int j2 = 0; j2 < partDimension; j2++) {
                        joined[i * partDimension + i2][j * partDimension + j2] 
                                = patterns[i][j]._pattern[i2][j2];
                    }
                }
            }
        }
        return new ArtPattern(joined);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(_pattern);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ArtPattern other = (ArtPattern) obj;
        if (!Arrays.deepEquals(_pattern, other._pattern)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int size = _pattern.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(_pattern[i][j]);
            }
            sb.append("/");
        }
        return sb.substring(0, sb.length() -1);
    }

}
