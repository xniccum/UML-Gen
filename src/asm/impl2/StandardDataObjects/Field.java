package asm.impl2.StandardDataObjects;

import asm.StorageApi.IField;
import asm.StorageApi.IKlassPart;
import asm.impl2.KlassDecorator;

/**
 * Created by Steven on 1/5/2016.
 */
public class Field extends KlassDecorator implements IField{


    private String accessLevel;
    private String fieldName;
    private String fieldType;
    private String fieldSignature;


    public Field( int accessLevel, String fieldName, String fieldSignature, String fieldType) {
        super();
        this.accessLevel = super.getAccessStringLevel(accessLevel);
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldSignature = fieldSignature;
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
    public String getfieldSignature() {
        return fieldSignature;
    }

}
