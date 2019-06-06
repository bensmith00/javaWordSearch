package wordsearch;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PointTest {

	@SuppressWarnings("static-access")
	@Test
	void test() {
		Matrix test = new Matrix();
		List<Integer> intList = new ArrayList<Integer>();
		String word = "works";
		Map<String, Integer> myMap = new HashMap<String, Integer>(); 
		
		for(int x = 0; x < 5; x++) {
			intList.add(0);
			intList.add(x);
		}
		
		test.point(intList, word, myMap);
		
	    for (String key : myMap.keySet()) {
			assertEquals(5, myMap.get(key));
	    }
	}

}
