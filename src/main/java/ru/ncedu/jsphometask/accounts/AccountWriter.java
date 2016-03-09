package ru.ncedu.jsphometask.accounts;

import java.io.Closeable;

/**
 * Created by Gorbatovskiy on 09.03.2016.
 */
public interface AccountWriter extends Closeable {
    void write(Account account);
}
