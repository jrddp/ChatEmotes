package me.kokeria.chatemotes.util;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class FileHandler {

    private static final File KEYFILE = new File("config" + File.separator + "chatemotes.txt");

    public static HashMap<String, String> getKEYSET() {
        return KEYSET;
    }

    private static final HashMap<String, String> KEYSET = new HashMap<>();

    private static final HashMap<String, String> DEFAULT_KEYSET = new HashMap<>();
    static {
        DEFAULT_KEYSET.put("owo", "OwO");
    }

    public static final void genKeyfile() {
        if (!KEYFILE.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(KEYFILE));
                Iterator<String> iterator = DEFAULT_KEYSET.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    String line = key + ':' + DEFAULT_KEYSET.get(key);
                    writer.write(line);
                    if (iterator.hasNext()) writer.write(System.getProperty("line.separator"));
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final void setKeyset() {

        if (KEYFILE.exists()) {
            Scanner reader = null;
            try {
                reader = new Scanner(KEYFILE);
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (!line.contains(":")) continue;
                    int splitIndex = line.indexOf(':');
                    String key = line.substring(0, splitIndex);
                    String repl = line.substring(splitIndex);
                    KEYSET.put(key, repl);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
