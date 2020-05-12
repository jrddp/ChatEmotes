package me.kokeria.chatemotes.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FileHandler {

    public static final File KEYFILE = new File("config" + File.separator + "chatemotes.txt");

    private static final Map<String, String> EMOTES = new LinkedHashMap<>();

    public static Map<String, String> getEMOTES() {
        return EMOTES;
    }

    private static final Map<String, String> DEFAULT_KEYSET = new LinkedHashMap<>();
    static {
        DEFAULT_KEYSET.put("<3", "❤");
        DEFAULT_KEYSET.put("o/", "( ﾟ◡ﾟ)/");
        DEFAULT_KEYSET.put(":)", "◕‿◕");
        DEFAULT_KEYSET.put("(:", "ಠ⌣ಠ");
        DEFAULT_KEYSET.put(":(", "◉‸◉");
        DEFAULT_KEYSET.put(":star:", "✮");
        DEFAULT_KEYSET.put(":yes:", "✔");
        DEFAULT_KEYSET.put(":no:", "✖");
        DEFAULT_KEYSET.put(":java:", "☕");
        DEFAULT_KEYSET.put(":arrow:", "➜");
        DEFAULT_KEYSET.put(":pvp:", "⚔");
        DEFAULT_KEYSET.put(":peace:", "✌");
        DEFAULT_KEYSET.put(":flower:", "✿");
        DEFAULT_KEYSET.put(":typing:", "✎...");
        DEFAULT_KEYSET.put(":maths:", "√(π+x)=L");
        DEFAULT_KEYSET.put(":shrug:", "¯\\_(ツ)_/¯");
        DEFAULT_KEYSET.put(":tableflip:", "(╯°□°）╯︵ ┻━┻");
        DEFAULT_KEYSET.put(":totem:", "☉_☉");
        DEFAULT_KEYSET.put(":gimme:", "༼つ◕_◕༽つ");
        DEFAULT_KEYSET.put(":wizard:", "('-')⊃━☆ﾟ.*･｡ﾟ");
        DEFAULT_KEYSET.put(":run:", "ᕕ(՞ᗜ՞)ᕗ");
        DEFAULT_KEYSET.put(":bear:", "ʕ ᓀ ᴥ ᓂ ʔ");
        DEFAULT_KEYSET.put(":rly:", "ಠ_ಠ");
        DEFAULT_KEYSET.put(":cry:","ಥ_ಥ");
        DEFAULT_KEYSET.put(":owo:", "◉﹏◉");
        DEFAULT_KEYSET.put(":dance:", "♪ ┏(^o^)┛ ♪");
        DEFAULT_KEYSET.put(":danse:","♪ ┗(^o^)┓ ♪");
    }

    public static void genDefaultKeyfile() {
        if (!KEYFILE.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(KEYFILE), StandardCharsets.UTF_8));

                String comment = "# You can add a custom emote by using the format \"key:emote\".";
                comment+="\n# You can then type the key in chat, surrounded by colons (:key:) and hit tab";
                comment+="\n# To add a key that isn't surrounded by colons, put it in quotes (such as \"<3\":❤)\n" + System.getProperty("line.separator");

                writer.write(comment);

                Iterator<String> iterator = DEFAULT_KEYSET.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    if (key.length() > 2 && key.startsWith(":") && key.endsWith(":")) {
                        String line = key.substring(1, key.length() - 1) + ':' + DEFAULT_KEYSET.get(key);
                        writer.write(line);
                        if (iterator.hasNext()) writer.write(System.getProperty("line.separator"));
                    } else {
                        String line = "\"" + key + "\":" + DEFAULT_KEYSET.get(key);
                        writer.write(line);
                        if (iterator.hasNext()) writer.write(System.getProperty("line.separator"));
                    }
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setEmotesFromFile() {

        if (!KEYFILE.exists()) {
            genDefaultKeyfile();
        }

        if (KEYFILE.exists()) {
            try {
                Scanner reader = new Scanner(KEYFILE, "UTF-8");
                EMOTES.clear();
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    if (line.trim().startsWith("#") || !line.contains(":")) continue;

                    String key;
                    String repl;

                    if (line.startsWith("\"") && line.substring(1).contains("\":")) {
                        int iRepl = line.substring(1).indexOf("\":") + 3;
                        key = line.substring(1, iRepl - 2);
                        repl = line.substring(iRepl);
                    } else if (line.startsWith("'") && line.substring(1).contains("':")) {
                        int iRepl = line.substring(1).indexOf("':") + 3;
                        key = line.substring(1, iRepl - 2);
                        repl = line.substring(iRepl);
                    } else {
                        int splitIndex = line.indexOf(':');
                        key = ":" + line.substring(0, splitIndex) + ":";
                        repl = line.substring(splitIndex + 1);
                    }

                    EMOTES.put(key, repl);
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
