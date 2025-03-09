package com.epam.rd.autocode.assessment.basics.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvStorageImpl implements CsvStorage {

    private String encoding;
    private String quoteCharacter;
    private String valuesDelimiter;
    private boolean headerLine;

    public CsvStorageImpl() {
        this.encoding = "UTF-8";
        this.quoteCharacter = "\"";
        this.valuesDelimiter = ",";
        this.headerLine = true;
    }

    /**
     * @param props configuration properties.<br>
     *              {@code String encoding} default: {@code 'UTF-8'} - The encoding to use when reading the CSV files; must be a valid charset.<br>
     *              {@code String quoteCharacter} default: {@code '"'} - The quote character to use for <em>quoted strings</em>. <br>
     *              {@code String valuesDelimiter} default: {@code ','} - The column delimiter character to use when reading the CSV file.<br>
     *              {@code boolean headerLine} default: {@code 'true'} - The first line considered as headers and should be ignored
     */
    public CsvStorageImpl(Map<String, String> props) {
        this.encoding = props.get("encoding");
        this.quoteCharacter = props.get("quoteCharacter");
        this.valuesDelimiter = props.get("valuesDelimiter");
        this.headerLine = props.get("headerLine").equals("true");
    }

    @Override
    public <T> List<T> read(InputStream source, Function<String[], T> mapper) throws IOException {
        List<T> resList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(source, encoding))) {
            String string;
            if (headerLine) {
                string = bufferedReader.readLine();
            }
            while ((string = bufferedReader.readLine()) != null) {
                String[] split = splitRespectingQuotes(string, valuesDelimiter, quoteCharacter);
//                for (int i = 0; i < split.length; i++) {
//                    if (split[i].equals(quoteCharacter + quoteCharacter)) {
//                        split[i] = "\"\"";
//                    }
//                }
                resList.add(mapper.apply(split));
            }
        }
        return resList;
    }

    private String[] splitRespectingQuotes(String input, String delimiter, String quoteCharacter) {
        String regex = quoteCharacter+"[^"+quoteCharacter+"]*"+quoteCharacter+"|[^"+delimiter+"]+|(?<="+delimiter+")(?="+delimiter+")";
        input = delimiter+input+delimiter;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group().isEmpty() ? "" : matcher.group());
        }
        for (int i = 0; i < matches.size(); i++) {
            if(matches.get(i).equals(quoteCharacter+quoteCharacter)) {
                matches.set(i, "\"\"");
            } else if(matches.get(i).contains(quoteCharacter)) {
                matches.set(i, matches.get(i).replaceAll(quoteCharacter,""));
            }
        }
        return matches.toArray(new String[0]);
    }

    @Override
    public <T> void write(OutputStream dest, List<T> values, Function<T, String[]> mapper) throws IOException {
        // place your code here
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(dest, encoding))) {
            for (T value : values) {
                String[] apply = mapper.apply(value);
                for (int i = 0; i < apply.length; i++) {
                    if (apply[i] != null && apply[i].contains(valuesDelimiter)) {
                        apply[i] = quoteCharacter+apply[i]+quoteCharacter;
                    }
                    if(apply[i] == null) {
                        apply[i] = "";
                    } else
                    if (apply[i].isEmpty()) {
                        apply[i] = quoteCharacter+quoteCharacter;
                    }
                }
                String join = String.join(valuesDelimiter, apply);
                bufferedWriter.write(join+System.lineSeparator());
            }
        }
    }


}
