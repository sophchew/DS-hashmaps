package com.company;

public class Main {

    public static void main(String[] args) {
        MyHashMap<String, String> hashMap = new MyHashMap<>();
        System.out.println(hashMap.isEmpty());
        hashMap.put(null,"blah");

        for(int i =0; i < 20; i++) {
            hashMap.put(i+"",i+"");
        }

        System.out.println(hashMap.size());
    }
}
