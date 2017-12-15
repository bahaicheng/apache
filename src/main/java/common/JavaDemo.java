package common;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JavaDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new ConcurrentHashMap<>();
        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> e : entries) {
            String key = e.getKey();
            String value = e.getValue();
        }
        Set<String> keys = map.keySet();

        List<String> list = new ArrayList<>();

    }
}
