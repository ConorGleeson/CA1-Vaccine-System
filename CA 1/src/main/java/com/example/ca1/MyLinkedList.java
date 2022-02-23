package com.example.ca1;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {
    public MyNode<E> head = null;

    public void addElement(E e) {
        MyNode<E> mn = new MyNode<>();
        mn.setContents(e);
        mn.next = head;
        head = mn;

    }


    public void clearList() {
        head = null;
    }

    public int size() {
        MyNode temp = head;
        int counter = 0;

        while (temp != null) {
            temp = temp.next;
            ++counter;

        }
        return counter;

    }

    public boolean isEmpty() {
        return size() == 0;
    }


    public E get(int index) {
        MyNode temp = head;

        for (int i = 0; temp != null && i <= index; i++) {
            if (i == index) {
                return (E) temp.getContents();
            }
            temp = temp.next;
        }
        return null;
    }






    public void deleteElement(int index) { // take in index use for loop to see if i = index then when match delete
        if (size() == 1) {
            clearList();
            return;
        }
        if (index < 0) {
            return;
        }
        MyNode<E> temp = head;


        if (index == 0) {
            head = head.next;
            return;
        }

        for (int i = 0; i <= index && temp != null; i++) {
            if (i == index - 1) {
                temp.next = temp.next.next;
                return;

            }
        }
    }


    public Iterator<E> iterator() {
        return new MyIterator<E>(head);
    }

}
