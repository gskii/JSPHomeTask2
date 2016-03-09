package ru.ncedu.jsphometask.utils;

import ru.ncedu.jsphometask.accounts.Account;
import ru.ncedu.jsphometask.accounts.AccountReader;
import ru.ncedu.jsphometask.accounts.FileAccountReader;
import ru.ncedu.jsphometask.accounts.FileAccountWriter;
import ru.ncedu.jsphometask.servlets.AuthenticationServlet;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Gorbatovskiy on 08.03.2016.
 */
public enum AccountManager {
    INSTANCE;

    private File baseFile;
    private HashMap<String, String> accounts;

    private AccountManager() {
        baseFile = new File(AuthenticationServlet.class.getResource("/users.csv").getPath());
        accounts = new HashMap<>();
    }

    /**
     * Обновляет программно-доступную базу данных.
     * Используйте перед вызовом {@link #isExists(String) isExists} и {@link #validate(Account account) validate},
     * чтобы удостовериться в актуальности данных.
     *
     * @throws IOException если файл БД не существует или возникли проблемы с его считыванием.
     */
    public void rebase() throws IOException {
        try (AccountReader reader = new FileAccountReader(baseFile)) {
            accounts.clear();
            while (reader.hasNext()) {
                Account account = reader.next();
                accounts.put(account.getLogin(), account.getPassword());
            }
        }
    }

    public boolean isExists(String account) throws IOException {
        if (accounts.containsKey(account)) {
            return true;
        } else {
            rebase();
            return accounts.containsKey(account);
        }
    }

    public boolean create(Account account) throws IOException {
        if (accounts.containsKey(account.getLogin())) {
            return false;
        }
        try (FileAccountWriter writer = new FileAccountWriter(baseFile)) {
            writer.write(account);
            return true;
        }
    }

    public boolean validate(String login, String password) {
        return accounts.get(login).equals(password);
    }

    public boolean validate(Account account) {
        return accounts.get(account.getLogin()).equals(account.getPassword());
    }


    public static void main(String[] args) {
        System.out.println("weeee");
    }
}
