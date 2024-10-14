package com.company;

public class MyHashMap {


    public void empty() { //Empties the HashMap of all entries

    }

    public boolean isEmpty() { //I mean....ya know.
        return false;
    }

    public void put(Object key, Object value) { //The HashMap equivalent of add()

    }

    public void putIfAbsent(Object key, Object value) { //Only places in HashMap if no current entry exists. Does NOT overwrite.

    }

    public Object remove(Object key, Object value) { //Returns the item being removed
        return null;
    }

    public boolean containsValue(Object value) { //Will need to search all buckets - think about why.
        return false;
    }

    public boolean containsKey(Object key) { //Will search only one bucket.
        return false;
    }

    public Object get(Object key) { //Only searches one bucket.
        return null;
    }

    public Object getOrDefault(Object key, Object value) { //Searches for key and returns respective value if found, otherwise returns 2nd parameter
        return null;
    }

    public int size() { //Returns num of items in total HashMap.
        return 0;
    }

    public Object replace(Object key, Object value) { //Overwrites existing key/value. Return is old object. If no object exists, should output a message indicating so and do nothing.
        return null;
    }

    public void display() { //Output entire HashMap. Should indicate which bucket each element belongs to and, if multiple per bucket, starts at head and goes to tail.

    }

    public int calculateIndex(Object key) { //You'll need this for determining which bucket to go to when given a key.
        return 0;
        //Formula is: index = hashCode(key) & (n-1).
    }



    private class Node {
        //This class will have 4 instance variables
        //Functionally VERY similar to a LinkedList.
    }
}
