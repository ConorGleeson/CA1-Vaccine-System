package com.example.ca1;

import java.util.Iterator;

public class MyIterator<E> implements Iterator<E> {
    private MyNode<E> pos;

    public MyIterator(MyNode<E> fnode) {
        pos = fnode;
    }

    @Override
    public boolean hasNext() {
        return pos != null;
    }

    public E next() {
        MyNode<E> temp = pos;
        pos = pos.next;
        return temp.getContents();
    }
}
