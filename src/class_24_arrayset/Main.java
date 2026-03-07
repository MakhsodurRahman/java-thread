package class_24_arrayset;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Main {
    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> value = new CopyOnWriteArraySet<>();
        value.add(44);

        ConcurrentSkipListMap<Integer,String> value2 = new ConcurrentSkipListMap<>();

        value2.put(33,"adsf");
    }
}
