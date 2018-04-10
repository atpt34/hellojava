package com.oleksii.model.entity;

abstract class AbstractEntity<T> {

    final T id;

    AbstractEntity(T id) {
        this.id = id;
    }
}
