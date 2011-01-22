package org.cleartk.evaluation.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CountCollectionTest {

	@Test
	public void test1() {
		List<String> list = new ArrayList<String>();
		list.add("asdf");
		list.add("qwerty");
		list.add("asdf");
		list.add("fdsa");
		list.add("fdsa");
		list.add("qwerty");
		list.add("lkjh");
		list.add("uiop");
		list.add("rtyu");
		list.add("qwerty");
		list.add("asdf");
		list.add("asdf");
		list.add("qwerty");
		list.add("sdf");
		list.add("sdf");
		list.add("asdf");
		list.add("xcvb");
		list.add("asdf");
		list.add("asdf");
		list.add("cvbn");
		list.add("vbnm");
		list.add("ascxc");
		CountCollection<String> countCollection = new CountCollection<String>();
		countCollection.addAll(list);
		countCollection.add("qwerty");
		countCollection.add("asdf");
		countCollection.add("fdsa");
		countCollection.add("lkjh");
		countCollection.add("sdf");
		countCollection.add("sdf");
		countCollection.add("sdf");
		countCollection.add("cvbn");
		countCollection.add("1234");
		List<CountObject<String>> counts = countCollection.sortCounts(false, true);
		assertEquals(12, counts.size());
		test(counts, 0, "asdf", 8);
    test(counts, 1, "qwerty", 5);
    test(counts, 2, "sdf", 5);
    test(counts, 3, "fdsa", 3);
    test(counts, 4, "cvbn", 2);
    test(counts, 5, "lkjh", 2);
    test(counts, 6, "1234", 1);
    test(counts, 7, "ascxc", 1);
    test(counts, 8, "rtyu", 1);
    test(counts, 9, "uiop", 1);
    test(counts, 10, "vbnm", 1);
    test(counts, 11, "xcvb", 1);

	}
	
	private <T> void test(List<CountObject<T>> counts, int index, T expectedObject, int expectedCount) {
    assertEquals(expectedObject, counts.get(index).getObject());
    assertEquals(expectedCount, counts.get(index).getCount());
  }


	
  @Test
	public void testInts() {
		CountCollection<Integer> countCollection = new CountCollection<Integer>();
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(5);
		countCollection.add(5);
		countCollection.add(5);
		countCollection.add(7);
		countCollection.add(7);
		countCollection.add(9);
		
		List<CountObject<Integer>> counts = countCollection.sortCounts(false, true);
		assertEquals(5, counts.size());
    test(counts, 0, 1, 5);
    test(counts, 1, 3, 4);
    test(counts, 2, 5, 3);
    test(counts, 3, 7, 2);
    test(counts, 4, 9, 1);
    assertEquals(3.6666666666666665d, CountCollection.weightedAverage(countCollection), 0.001d);

		countCollection = new CountCollection<Integer>();
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(3);
		counts = countCollection.sortCounts(false, true);
		assertEquals(2, counts.size());
		test(counts, 0, 1, 5);
		test(counts, 1, 3, 5);
		assertEquals(2.0d, CountCollection.weightedAverage(countCollection), 0.001d);

		
	}
	
	@Test
	public void test3() {
		CountCollection<Integer> countCollection = new CountCollection<Integer>();
		countCollection.add(0);
		countCollection.add(1);
		countCollection.add(1);
		countCollection.add(2);
		countCollection.add(2);
		countCollection.add(3);
		countCollection.add(3);
		countCollection.add(3);

		List<CountObject<Integer>> counts = countCollection.sortCounts(false, true);
		assertEquals(4, counts.size());
		test(counts, 0, 3, 3);
		test(counts, 1, 1, 2);
		test(counts, 2, 2, 2);
		test(counts, 3, 0, 1);
    
    counts = countCollection.sortCounts(false, false);
    test(counts, 0, 3, 3);
    test(counts, 1, 2, 2);
    test(counts, 2, 1, 2);
    test(counts, 3, 0, 1);

    counts = countCollection.sortCounts(true, true);
    test(counts, 0, 0, 1);
    test(counts, 1, 1, 2);
    test(counts, 2, 2, 2);
    test(counts, 3, 3, 3);

    counts = countCollection.sortCounts(true, false);
    test(counts, 0, 0, 1);
    test(counts, 1, 2, 2);
    test(counts, 2, 1, 2);
    test(counts, 3, 3, 3);

    counts = countCollection.sortObjects(true);
    test(counts, 0, 0, 1);
    test(counts, 1, 1, 2);
    test(counts, 2, 2, 2);
    test(counts, 3, 3, 3);

    counts = countCollection.sortObjects(false);
    test(counts, 0, 3, 3);
    test(counts, 1, 2, 2);
    test(counts, 2, 1, 2);
    test(counts, 3, 0, 1);

	}
	
	@Test
	public void test4() {
		CountCollection<Integer> countCollection = new CountCollection<Integer>();
		countCollection.add(1, 5);
		countCollection.add(2, 7);
		countCollection.add(3, 2);
		//1*5 + 2*7 + 3*2 / (5+7+2) =  25 / 14 = 1.78571
		double weightedAverage = CountCollection.weightedAverage(countCollection);
		assertEquals(1.7857143f, weightedAverage, 0.001f);

		countCollection = new CountCollection<Integer>();
		countCollection.add(5, 1);
		countCollection.add(7, 2);
		countCollection.add(2, 3);
		//5*1 + 7*2 + 2*3 / (1+2+3) =  25 / 6 = 4.1666666666666666666666666666667
		weightedAverage = CountCollection.weightedAverage(countCollection);
		assertEquals(4.16666667f, weightedAverage, 0.001f);

	}
	
	@Test
  public void basicTests() throws Exception {
    List<String> list = new ArrayList<String>();
    list.add("a");
    list.add("a");
    list.add("a");
    list.add("a");
    list.add("b");
    CountCollection<String> countCollection = new CountCollection<String>(list);
    
    Set<String> countedObjects = countCollection.getCountedObjects();
    assertEquals(2, countedObjects.size());
    
    countCollection.add("a", 10);
    assertEquals(14, countCollection.getCount("a"));
    
    Collection<CountObject<String>> counts = countCollection.getCounts();
    assertEquals(2, counts.size());
    assertEquals(2, countCollection.size());
    
    assertEquals(0, countCollection.getCount("c"));
    assertEquals(15, countCollection.total());
    
    
  }
}
