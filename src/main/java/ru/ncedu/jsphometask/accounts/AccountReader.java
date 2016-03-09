package ru.ncedu.jsphometask.accounts;

import java.io.Closeable;

/**
 * Created by Gorbatovskiy on 09.03.2016.
 */
public interface AccountReader extends Closeable {
    boolean hasNext();
    Account next();
}
