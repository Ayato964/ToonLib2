package org.ayato.util;

import java.util.ArrayList;

public class Event {
    private static final ArrayList<EventCard<? extends Inheriting>> cards = new ArrayList<>();

    private Event(){}

    public static <T extends Inheriting> EventCard<T> create(Class<?> my, String id, T t){
        EventCard<T> card = new EventCard<>(my.toString() + id, t);
        cards.add(card);
        return card;
    }

    public static EventCard<?> get(Class<?> my, String i) {
        for(EventCard<?> e : cards){
            if(e.getId().equals(my + i)){
                return e;
            }
        }
        return null;
    }


    public static class EventCard<T extends Inheriting>{
        private final String id;
        private boolean event = false;
        T inheriting_data;
        public EventCard(String ID, T t){
            id = ID;
            inheriting_data = t;
        }

        public T getInheriting_data() {
            return inheriting_data;
        }
        public String getId() {
            return id;
        }
        public void clear(){
            event = true;
        }
        public boolean getEvent(){
            return event;
        }
    }
}
