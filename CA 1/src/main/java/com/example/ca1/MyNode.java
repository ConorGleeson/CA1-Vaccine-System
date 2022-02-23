package com.example.ca1;

public class MyNode<N> {
    public MyNode<N> next = null;
    private N contents;

    public N getContents() {
        return contents;
    }

    public void setContents(N c) {
        contents = c;
    }
}
