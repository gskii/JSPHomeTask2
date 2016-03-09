package ru.ncedu.jsphometask.accounts;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Gorbatovskiy on 08.03.2016.
 */
public class FileAccountReader implements AccountReader, Closeable {
    private Scanner scanner;

    public FileAccountReader(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    @Override
    public Account next() {
        Account account = null;
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] tokens = line.split(";");
            if (tokens.length >= 2) {
                account = new Account(tokens[0], tokens[1]);
            }
        }
        return account;
    }

    @Override
    public void close() throws IOException {
        scanner.close();
    }
}