package timetable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFile {
    String path;

    public ReadFile(String pathName) {
        this.path = pathName;
    }

    public ExternalDataEntity readFileByChars() {
        ExternalDataEntity ede = new ExternalDataEntity();
        String line = "";
        Reader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(path));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (((char) tempchar) != '\r') {
                    line += (char) tempchar + "";
                }
            }
            String[] spl = line.split("#");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < spl.length; i++) {
                if (i == 0) {
                    String holiday = spl[i].trim();
                    String[] split = holiday.split("\n");
                    list = new ArrayList<>();
                    for (String a : split) {
                        list.add(a);
                    }
                    ede.setHlist(list);
                } else if (i == 1) {
                    String workday = spl[i].trim();
                    String[] split = workday.split("\n");
                    list = new ArrayList<>();
                    for (String a : split) {
                        list.add(a);
                    }
                    ede.setWlist(list);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return ede;
    }
}
