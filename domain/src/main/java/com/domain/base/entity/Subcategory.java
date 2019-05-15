package com.domain.base.entity;
import java.util.Date;

/*
 * Created by dendy-prtha on 15/05/2019.
 * Subcategory Entity
 */

public class Subcategory {
    public int id;

    public String name;

    public String target;

    public int priority;

    public boolean isActive;

    public Date created;

    public  Category category;
}
