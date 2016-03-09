package ru.ncedu.jsphometask.utils;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import ru.ncedu.jsphometask.accounts.*;
import ru.ncedu.jsphometask.servlets.AuthenticationServlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Gorbatovskiy on 09.03.2016.
 */
public class AccountManager {
    public static final AccountManager INSTANCE = new AccountManager();

    private File baseFile;
    private HashMap<String, Account> accounts;

    private AccountManager() {
        baseFile = new File(AuthenticationServlet.class.getResource("/users.csv").getPath());
        accounts = new HashMap<>();
    }

    /**
     * Обновляет текущую БД из удаленной БД.
     * Используйте перед вызовом {@link #isExists(String) isExists}, {@link #validate(Account account) validate}
     * и {@link #create(Account) create}, чтобы удостовериться в актуальности данных.
     *
     * @throws IOException если удаленный файл БД не существует или возникли проблемы при взаимодействии с ним.
     */
    public void rebase() throws IOException {
        try (AccountReader reader = new FileAccountReader(baseFile)) {
            accounts.clear();
            while (reader.hasNext()) {
                Account account = reader.next();
                accounts.put(account.getLogin(), account);
            }
        }
    }

    /**
     * Проверяет наличие аккаунта в БД.
     *
     * @param account - проверяемый аккаунт
     * @return false в противном случае
     */
    public boolean isExists(Account account) throws IOException {
        if (!accounts.containsKey(account.getLogin())) {
            rebase();
        }
        return accounts.containsKey(account.getLogin());
    }

    /**
     * Добавляет в текущую и удаленную БД новый аккаунт.
     *
     * @param account добавляемый аккаунт.
     * @return false если аккаунт с таким именем уже существует
     * @throws IOException если файл БД не существует или возникли проблемы при взаимодействии с ним
     */
    public boolean create(Account account) throws IOException {
        if (accounts.containsKey(account.getLogin())) {
            return false;
        }
        try (AccountWriter writer = new FileAccountWriter(baseFile)) {
            writer.write(account);
            accounts.put(account.getLogin(), account);
            return true;
        }
    }

    /**
     * Аутентификация пользователя.
     *
     * @param checking проверяемый аккаунт.
     * @return false в противном случае
     */
    public boolean validate(Account checking) {
        Account account = accounts.get(checking.getLogin());
        if (account != null && account.equals(checking)) {
            return true;
        } else return false;
    }
}
