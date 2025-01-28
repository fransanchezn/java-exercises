package org.fransanchez.usecases.retryer;

import java.util.concurrent.Callable;

public interface Retryer {
    <T> T execute(Callable<T> function);
}
