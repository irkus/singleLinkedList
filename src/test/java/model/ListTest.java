package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListTest {

    private static final Map<Integer, String> expectedValues = createExpectedValues();

    private static Map<Integer, String> createExpectedValues() {
        Map<Integer, String> result = new HashMap<Integer, String>();
        result.put(0, "zero");
        result.put(1, "one");
        result.put(2, "");
        result.put(3, null);
        return result;
    }


    //    @Test(expected = ConcurrentModificationException.class)
    @Test
    public void testAdd() throws Exception {
        List<String> list = new List<String>();
        for (Integer key : expectedValues.keySet()) {
            list.add(expectedValues.get(key));
        }
        System.out.println(list.toString());
        Assert.assertEquals(4, list.size());
    }


    @Test
    public void testGet() throws Exception {
        List<String> list = new List<String>();

        for (Integer key : expectedValues.keySet()) {
            list.add(expectedValues.get(key));
        }

        System.out.println(list.toString());

        for (int i = 0; i < list.size(); i ++) {
            Assert.assertEquals(expectedValues.get(i), list.get(i));
        }
    }


    @Test
    public void testGetWithForEach() throws Exception {
        List<String> list = new List<String>();

        for (Integer key : expectedValues.keySet()) {
            list.add(expectedValues.get(key));
        }

        System.out.println(list.toString());

        Iterator i = list.iterator();
        while (i.hasNext()) {
            String data = (String) i.next();
            System.out.println(data);
        }

        for (String node : list) {

        }
    }


    @Test
    public void testRemove() throws Exception {
        List<String> list = new List<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.remove("2");
        Assert.assertEquals(2, list.size());
    }



}