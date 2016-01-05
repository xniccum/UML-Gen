package asm.impl;

import java.lang.reflect.Type;

/**
 * Created by Steven on 1/4/2016.
 */
class Argument {
    private String name;
    private Type type;

    public Argument(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
