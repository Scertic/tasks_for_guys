package com.epam.rd.autocode.assessment.basics.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CsvStorageImpl implements CsvStorage {

    public CsvStorageImpl() {
    }

    /**
     * @param props configuration properties.<br>
     *              {@code String encoding} default: {@code 'UTF-8'} - The encoding to use when reading the CSV files; must be a valid charset.<br>
     *              {@code String quoteCharacter} default: {@code '"'} - The quote character to use for <em>quoted strings</em>. <br>
     *              {@code String valuesDelimiter} default: {@code ','} - The column delimiter character to use when reading the CSV file.<br>
     *              {@code boolean headerLine} default: {@code 'true'} - The first line considered as headers and should be ignored
     */
    public CsvStorageImpl(Map<String, String> props) {
        // place your code here
    }

    @Override
    public <T> List<T> read(InputStream source, Function<String[], T> mapper) throws IOException {
        // place your code here
        return null;
    }

    @Override
    public <T> void write(OutputStream dest, List<T> values, Function<T, String[]> mapper) throws IOException {
        // place your code here
    }

}
