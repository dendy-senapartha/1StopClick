package com;

/*
 * Created by dendy-prtha on 27/02/2019.
 * an Entity Data Factory template
 */
public abstract class AbstractEntityDataFactory<T>{
    public abstract T createData(@DataSource String source);
}
