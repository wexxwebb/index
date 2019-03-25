package pro.kretov.repository.search.service;

import java.util.concurrent.Future;

/**
 * @author Aleksandr Kretov
 * @date 26.02.2019
 */

public class FutureIndexer {

    private Future future;
    private String name;

    public FutureIndexer(Future future, String name) {
        this.future = future;
        this.name = name;
    }

    public Future getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
