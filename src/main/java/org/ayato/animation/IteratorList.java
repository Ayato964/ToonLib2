package org.ayato.animation;

import org.ayato.animation.text.properties.IProperty;

public class IteratorList<T> {
    IteratorList<T> next;
    IteratorList<T> back;
    T node;
    public IteratorList(T t){
        node = t;
    }
    private IteratorList(T n, IteratorList<T> i){
        this(n);
        back = i;
    }
    public T get(){
        return node;
    }
    public void add(T t){
        if(next == null)
            next = new IteratorList<>(t, this);
        else
            next.add(t);
    }
    public IteratorList<T> next(){
        return next;
    }
    public IteratorList<T> back(){
        return back;
    }
    public boolean hasNext(){
        return next != null;
    }

}
