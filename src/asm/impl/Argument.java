package asm.impl;

import java.lang.reflect.Type;

/**
 * Created by Steven on 1/4/2016.
 */
class Argument {
    private String name;
    private String type;

    public Argument(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
