package com.oleksii.model.entity;

import java.util.Objects;

abstract class AbstractEntity<T> {

    private T id;

    AbstractEntity(T id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AbstractEntity<?>) {
            AbstractEntity<T> that = (AbstractEntity<T>) obj;
            return id.equals(that.id);
        }
        return false;
    }

}
