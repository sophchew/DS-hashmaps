package com.company;

import java.util.LinkedList;

public class MyHashMap<K, V> {
    LinkedList<MyNode>[] hashmap;
    int capacity;
    double loadFactor; // not indexes, actual items in list

    private class MyNode {
        K key;
        V value;

        public MyNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public MyHashMap() {
        capacity = 16;
        loadFactor = 0.75;
        hashmap = new LinkedList[capacity];
        for(int i=0; i<hashmap.length; i++) {
            hashmap[i] = new LinkedList<>();
        }
    }

    public MyHashMap(int initialCapacity) {
        capacity = initialCapacity;
        loadFactor = 0.75;
        hashmap = new LinkedList[capacity];
        for(int i=0; i<hashmap.length; i++) {
            hashmap[i] = new LinkedList<>();
        }
    }

    public MyHashMap(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        this.loadFactor = loadFactor;
        hashmap = new LinkedList[capacity];
        for(int i=0; i<hashmap.length; i++) {
            hashmap[i] = new LinkedList<>();
        }
    }


    public void empty() { //Empties the HashMap of all entries
        hashmap = new LinkedList[capacity];
    }

    public boolean isEmpty() { //I mean....ya know.
        for(int i = 0; i<capacity; i++) {
            if(!hashmap[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void put(K key, V value) { //The HashMap equivalent of add()
        MyNode newNode = new MyNode(key, value);
        int index = calculateIndex(key);

        for(MyNode node: hashmap[index]) {
            if(key == null) {
                if(node.key == null) {
                    node.value = value;
                    return;
                }
            } else {
                if (node.key !=null && node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
        }
        hashmap[index].add(newNode);
        if(size() == (loadFactor*capacity)) {
            resize();
        }
    }

    public void putIfAbsent(K key, V value) { //Only places in HashMap if no current entry exists. Does NOT overwrite.
        MyNode newNode = new MyNode(key, value);
        int index = calculateIndex(key);

        for(MyNode node: hashmap[index]) {
            if(key == null) {
                if(node.key == null){
                    return;
                }
            } else {
                if (node.key.equals(key)) {
                    return;
                }
            }
        }
        hashmap[index].add(newNode);
        if(size() == (loadFactor*capacity)) {
            resize();
        }
    }

    public V remove(K key) { // Returns the item being removed

        MyNode toRemove;

        int index = calculateIndex(key);
        for (int i = 0; i < hashmap[index].size(); i++) {
            if(key == null) {
                if(hashmap[index].get(i).key == null) {
                    toRemove = hashmap[0].remove(i);
                    return toRemove.value;
                }
            } else {
                if (hashmap[index].get(i).key.equals(key)) {
                    toRemove = hashmap[index].remove(i);
                    return toRemove.value;
                }
            }

        }

        return null;

    }

    public boolean containsValue(V value) { //Will need to search all buckets - think about why.
        for(LinkedList<MyNode> ll: hashmap) {
            for(MyNode node: ll) {
                if(node.value == null && value == null) {
                    return true;
                } else if(node.value != null && node.value.equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsKey(K key) { //Will search only one bucket.
        int index = calculateIndex(key);
        for (MyNode node : hashmap[index]) {
            if(node.key == null && key == null) {
                return true;
            } else if (node.key != null && node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) { // Only searches one bucket.
        int index = calculateIndex(key);
        for(MyNode node: hashmap[index]) {
            if(node.key == null && key == null) {
                return node.value;
            } else if(node.key != null && node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    public V getOrDefault(K key, V value) { //Searches for key and returns respective value if found, otherwise returns 2nd parameter
        int index = calculateIndex(key);
        for(MyNode node: hashmap[index]) {
            if(node.key == null && key == null) {
                return node.value;
            } else if(node.key != null && node.key.equals(key)) {
                return node.value;
            }
        }

        return value;
    }

    public int size() { //Returns num of items in total HashMap.
        int count = 0;
        for(LinkedList<MyNode> ll: hashmap) {
            for(MyNode node : ll) {
                count++;
            }
        }
        return count;
    }

    public V replace(K key, V value) { //Overwrites existing key/value. Return is old object. If no object exists, should output a message indicating so and do nothing.
        MyNode newNode = new MyNode(key, value);
        V toReturn = null;

        int index = calculateIndex(key);
        for (MyNode node : hashmap[index]) {
            if(node.key == null && key == null) {
                toReturn = node.value;
                node.value = value;
                return toReturn;
            } else if (node.key != null && node.key.equals(key)) {
                toReturn = node.value;
                node.value = value;
                return toReturn;
            }
        }

        System.out.println("No key found to replace.");
        return null;
    }

    public void display() { //Output entire HashMap. Should indicate which bucket each element belongs to and, if multiple per bucket, starts at head and goes to tail.
        int bucket = 0;
        for(LinkedList<MyNode> ll: hashmap) {
            System.out.print(bucket + ": [");
            for(MyNode node: ll) {
                System.out.print(node.key + "=" + node.value + ", ");
            }
            System.out.println("]");
            bucket++;
        }
    }

    public int calculateIndex(K key) { //You'll need this for determining which bucket to go to when given a key.
        if(key == null) {
            return 0;
        } else {
            return key.hashCode() & (capacity-1);
        }

        //Formula is: index = hashCode(key) & (n-1).
    }

    public void resize() {

            LinkedList<MyNode>[] newHashmap =  new LinkedList[capacity*2];
            for(int i=0; i<newHashmap.length; i++) {
                newHashmap[i] = new LinkedList<>();
            }
            capacity = capacity*2;
            for(LinkedList<MyNode> ll: hashmap) {
                for(MyNode node: ll) {
                   int index = calculateIndex(node.key);
                   newHashmap[index].add(node);
                }
            }

            hashmap = newHashmap;


    }

}
