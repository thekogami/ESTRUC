package com.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        System.out.println(map.get(1));
        map.put(1, 20);
        System.out.println(map.get(1));
        map.put(2, null);
        System.out.println(map.get(2));

        String texto = "aqui tem um texto com varias letra";
        Map<Character, Integer> freq = new HashMap<>();
        for (var c : texto.toCharArray()) {
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) + 1);
            } else {
                freq.put(c, 1);
            }
        }
        System.out.println(freq);

    }
}