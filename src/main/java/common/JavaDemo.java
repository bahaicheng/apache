package common;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JavaDemo {
    public static void main(String[] args) {

    }

    public static void week() {
        System.out.println("EnumTest.FRI 的 value = " + Week.FRI.getValue());

    }

    public static void array() {
        String[] arr = new String[3];
        String[] arr1 ={"","",""};
        String[] arr2 = new String[]{"",""};

    }

    public static void bytelenth() {
        String a = "{\"browser\":{\"browser_lang\":\"zh\",\"browser_name\":\"Chrome Mobile\",\"browser_version\":\"49.0.2623.105\"},\"client\":{\"imei\":\"869963021220883\",\"ip\":\"192.168.253.6\",\"mac\":\"02:00:00:00:00:00\",\"os\":\"Android6.0\"},\"event\":\"click\",\"event_context\":{\"menu_name\":\"基金\",\"menu_url\":\"appNew/JJLC/JJSY.jsp\",\"menu_code\":\"MB0034\"},\"event_object\":\"MB0034\",\"session_id\":\"5E0C29AEBD63BC9FE11560733DED4FAA\",\"time\":1473042439014,\"url\":\"http://10.118.60.87:8080/pmclient/appNew/CFSY/menuwealth.jsp\",\"user\":{\"master_id\":\"1314957560\"},\"view\":\"\"}";
        byte[] bytes = a.getBytes();
        System.out.println(bytes.length);
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

        String a = "";

//        Set<String> set = new HashSet<>();
//        set.add("bourne");
//        Iterator<String> iterator = set.iterator();
    }
}
