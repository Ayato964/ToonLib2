package org.ayato.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;

public class LangLoader {
    private static final String[] list = {"ja_jp", "en_us", "zh_cn"};
    public static final String JAPANESE = list[0];
    public static final String ENGLISH = list[1];
    public static final String CHINESE = list[2];
    private JsonNode lang;
    public static String LANGUAGE;
    private final String directoryPath;
    private BufferedReader reader;
    private static LangLoader INSTANCE;

    private LangLoader(String path, String lang){
        directoryPath = path;
        loadFile(lang);
    }
    private void loadFile(String lang){
        LANGUAGE = lang;
        URL filePath = getClass().getClassLoader().getResource(directoryPath + "/" + lang + ".json");
        System.out.println(filePath + "!!!!!!!!!!!");

        if(filePath.toString().indexOf("jar:") != -1) {
            try(InputStream is = ClassLoader.getSystemResourceAsStream(directoryPath + "/" + lang + ".json")) {
                reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                readFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            File file = new File(filePath.getFile());
            readFile(file);
        }

    }
    private void readFile(File f){
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        readFile();
    }
    private void readFile() {
        try {
            lang = new ObjectMapper().readTree(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String get(String[] value, String key){
        JsonNode node = lang.get(key);
        if(node != null)
            return getString(value, node.asText());
        return null;

    }

    private String getString(String[] value, String s) {
        int valueCount = 0;
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '$') {
                if (valueCount < value.length) {
                    newString.append(value[valueCount]);
                    valueCount++;
                } else {
                    newString.append("NaN");
                }
            } else {
                newString.append(s.charAt(i));
            }
        }
        return newString.toString();
    }

    public static void create(String path, String lang){
        if(INSTANCE == null)
            INSTANCE = new LangLoader(path, lang);
    }
    public static void create(String lang){
        if(INSTANCE != null)
            INSTANCE.loadFile(lang);
    }
    public static int langCount(){
        return 3;
    }

    public static LangLoader getInstance() {
        return INSTANCE;
    }
}
