package wordsearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Matrix {
	
	// Method create
	public static char[][] create(File in) throws IOException {
		// Read in 20x20-word-search.txt
        @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(in));
        String line = "";
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
    // Create 2D array for lines
        char[][] chars = new char[lines.size()][];
        for (int col = 0; col < lines.size(); col++) {
            for (int row = 0; row < lines.get(col).length(); row++) {
                chars[col] = lines.get(col).toCharArray();
            }
        }
        return(chars);

}

	// Method point
    public static boolean point(List<Integer> matchList2, String word, Map<String, Integer> topTen) {
    	int point = 2;
    	for (int start = 0; start < (matchList2.size() - 4); start+=2) {
    		List<Integer> temp = (matchList2.subList(start, start + 6));
    		int x1 = temp.get(0);
    		int x2 = temp.get(2);
    		int x3 = temp.get(4);
    		int y1 = temp.get(1);
    		int y2 = temp.get(3);
    		int y3 = temp.get(5);

    		if (x1 == x2 && x2 == x3 || y1 == y2 && y2 == y3 ) {
    			point += 1;
    		}
    	}
    	if (point > 3) {
			topTen.put(word, point);

    	}

    	
		return false;
    	
    }
}


