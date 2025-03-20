package com.example;

// Exercícios

// 1. Crie uma representação gráfica de um SkipList com alguns elementos e faça a atualização da representação gráfica de acordo com as operações abaixo.

// a) Adicione um novo elemento apenas no nível inferior.

// b) Adicione um novo elemento  no nível inferior e nos superiores.

// c) Remova um elemento.

// d) Indique com setas o processo de busca de um elemento no skip list.
// https://blog.reachsumit.com/posts/2020/07/skip-list/

import java.util.Random;

public class SkipLists {
    private static final int MAX_LEVEL = 4;
    private final Node head = new Node(Integer.MIN_VALUE, MAX_LEVEL);
    private final Random random = new Random();

    private static class Node {
        int value;
        Node[] forward;

        Node(int value, int level) {
            this.value = value;
            this.forward = new Node[level + 1];
        }
    }

    public void insert(int value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        int level = randomLevel();
        Node newNode = new Node(value, level);

        for (int i = 0; i <= level; i++) {
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public void delete(int value) {
        Node[] update = new Node[MAX_LEVEL + 1];
        Node current = head;

        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        if (current != null && current.value == value) {
            for (int i = 0; i <= MAX_LEVEL; i++) {
                if (update[i].forward[i] != current) break;
                update[i].forward[i] = current.forward[i];
            }
        }
    }

    public boolean search(int value) {
        Node current = head;

        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value < value) {
                current = current.forward[i];
            }
        }

        current = current.forward[0];

        return current != null && current.value == value;
    }

    private int randomLevel() {
        int level = 0;
        while (random.nextInt(2) == 0 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void printList() {
        for (int i = MAX_LEVEL; i >= 0; i--) {
            Node current = head.forward[i];
            System.out.print("Level " + i + ": ");
            while (current != null) {
                System.out.print(current.value + " ");
                current = current.forward[i];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipLists skipList = new SkipLists();
        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(9);
        skipList.insert(12);
        skipList.insert(19);
        skipList.insert(17);
        skipList.insert(26);
        skipList.insert(21);
        skipList.insert(25);

        System.out.println("Skip List:");
        skipList.printList();

        System.out.println("Search for 19: " + skipList.search(19));
        System.out.println("Search for 15: " + skipList.search(15));

        skipList.delete(19);
        System.out.println("After deleting 19:");
        skipList.printList();
    }
}
