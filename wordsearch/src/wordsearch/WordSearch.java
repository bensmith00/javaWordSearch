package wordsearch; 

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SuppressWarnings("rawtypes")
class Node implements Comparable
{
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Node node = (Node)obj;
		return x == node.x && y == node.y;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String toString() {
		return ( "" + x + " " + y + " ");
	}
	
	
};


public class WordSearch {
	
	// Below arrays details all 8 possible movements from a cell
	private static final int[] row = {-1, 0, 0, 1};
	private static final int[] col = {0, -1, 1, 0};

	// Function to check if it is possible to go to position next from current position.
	public static boolean isValid(Node next, List<Node> path, int M, int N) 
	{
		return (next.x >= 0) && (next.x < M) &&
					   (next.y >= 0) && (next.y < N) &&
					   (!path.contains(next));
	}

	// Recursive Function 
	public static void DFS(char mat[][], String word, Node next, List<Node> path, int index, Map<String, Integer> topTen) {
		
		int i = next.x;
		int j = next.y;
		int n = word.length();
		String temp = "";

		// return if characters don"t match
		if (mat[i][j] != word.charAt(index)) {
			return;
		}

		// if all words are matched, print the result and return
		if (index == n - 1) {
			
			for (int k = 0; k < path.size(); k++) {
				temp += ("" + path.get(k) + "");	
			}
			temp += (""+ new Node(i, j));
	        
	        List<String> matchList = new ArrayList<String>();
	        List<Integer> matchList2 = new ArrayList<Integer>();
	        Pattern regex = Pattern.compile("([\\d]+)");
	        Matcher regexMatcher = regex.matcher(temp);
	        
	        while (regexMatcher.find()) {
	            matchList.add(regexMatcher.group());   
	        } 
	        for(String number : matchList) {
	        	matchList2.add(Integer.parseInt(number)); 	
	        }
	        Matrix.point(matchList2, word, topTen);
			return;
		}

		// include current cell in the path
		path.add(new Node(i, j));

		// check all 8 possible movements from current cell
		// and recur for each valid movement
		for (int k = 0; k < 4; k++)
		{
			// calculate next position
			next = new Node(i + row[k], j + col[k]);

			// check if it is possible to go to next position
			// from current position
			if (isValid(next, path, mat.length, mat[0].length)) {
				DFS(mat, word, next, path, index + 1, topTen);
			}
		}
		// include current cell in the path
		path.remove(path.size() - 1);
	}

	static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
	
			List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

			Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() {
				
		        @Override
		        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
		            return e1.getKey().toString().compareTo(e2.getKey().toString());
		        }
		    });
			
			Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() {
				
		        @Override
		        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
		            return e2.getValue().compareTo(e1.getValue());
		        }
		    });
	
	return sortedEntries;
	}
	
	public static void main(String[] args) throws IOException {
		
    	// Read in word search .txt
    	File x = new File("../wordsearch/20x20-word-search.txt");
    	// Read in dictionary .txt
        File y = new File("../wordsearch/dictionary.txt");
        // Create 2D array from word search File
        char[][] letterMatrix = Matrix.create(x);
        // Create 2D array from dictionary File
        char[][] dictionary = Matrix.create(y);

    	String word = "";
    	Map<String, Integer> topTen = new HashMap<>();
        // Loops through every word in dictionary
	  	for ( int wordCount = 0; wordCount < dictionary.length; wordCount++) {
	  		
	  		// Returns single word
	  	    word = Word.selector(dictionary, wordCount);
	  	    
    	    // Ignore word If it is smaller than last place
    	    if (word.length() < 5 ) {
    	    	continue;
    	    }

		List<Node> path = new ArrayList<>();
		
			for (int i = 0; i < letterMatrix.length; ++i) {
				for (int j = 0; j < letterMatrix[0].length; ++j) {
					DFS(letterMatrix, word, new Node(i, j), path, 0, topTen);
				}
			}
	  	}
	  	List<Entry<String, Integer>> countList = entriesSortedByValues(topTen);
	  	PrintWriter writer = new PrintWriter("output.txt", "UTF-8");


	  	for (int count = 0; count < 10; count ++) {
		  	writer.print(countList.get(count).getKey());
		  	writer.println("," + countList.get(count).getValue());
	  	}
	  	writer.close();
	  	
	  	String content;

	  	content = new String(Files.readAllBytes(Paths.get("output.txt")));
	  	System.out.println(content);
	}
}
