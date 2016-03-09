package ru.ncedu.jsphometask.accounts;

import java.io.*;

/**
 * Created by Gorbatovskiy on 09.03.2016.
 */
public class FileAccountWriter implements AccountWriter, Closeable {
    private PrintWriter writer;

    public FileAccountWriter(File file) throws IOException {
        writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
    }

    @Override
    public void write(Account account) {
        writer.println(account.getLogin() + ";" + account.getPassword() + ";");
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
