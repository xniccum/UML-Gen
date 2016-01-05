package asm.impl;

import asm.api.IField;
import asm.api.IKlassPart;

/**
 * Created by Steven on 1/5/2016.
 */
public class Field extends KlassDecorator implements IField{
    private IKlassPart baseKlass;

    private String accessLevel;
    private String fieldName;
    private String fieldType;

    public Field(IKlassPart baseKlass, int accessLevel, String fieldName, String fieldType) {
        super(baseKlass);
        this.baseKlass = baseKlass;
        this.accessLevel = super.getAccessStringLevel(accessLevel);
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public String getFieldType() {
        return this.fieldType;
    }

    @Override
    public String getAccessLevel() {
        return this.fieldName;
    }

    @Override
    public String printFieldBlock() {
        return super.printFieldBlock() + String.format("%s %s: %s \\1 ", this.accessLevel, this.fieldName, this.fieldType);
    }
}
