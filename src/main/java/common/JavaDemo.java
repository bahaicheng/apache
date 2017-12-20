package common;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JavaDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
    }

    public static void list() {
        Map<String, String> map = new HashMap<>();
        Map<String, String> map1 = new ConcurrentHashMap<>();
        Set<Map.Entry<String, String>> entries = map.entrySet();

        for (Map.Entry<String, String> e : entries) {
            String key = e.getKey();
            String value = e.getValue();
        }
        Set<String> keys = map.keySet();

        List<String> list = new ArrayList<>();
        String[] arr = {"aa", "bb", "cc"};
        List<String> strings = Arrays.asList(arr);

        Set<String> set = new HashSet<>();
        set.add("bourne");
        Iterator<String> iterator = set.iterator();
    }
}
