package asm.impl2;

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

/** BROKEN M@ Stuff
    @Override
    public String printEnd() {
        StringBuilder strBuild = new StringBuilder();
        strBuild.append(super.printEnd());

        //fieldSignature is empty: cat the \\ off field type and add to builder
        if(fieldSignature == null || fieldSignature.equals(""))
           return strBuild.append(String.format("\n edge [ \n  style=\"solid\", arrowhead= \"vee\" \n ] \n %s -> %s \n",
                    super.stripFilePath(super.getBaseName()),
                    super.stripClassPath(fieldType))).toString();
        //type is inside of a collection or outer object. Format of style ///<>
        String carrotedString = fieldSignature.substring(fieldSignature.indexOf('<'), fieldSignature.indexOf('>'));
        //Look for multiple params broken by semi-colon
        String[] strArry = carrotedString.split("[;,:]");

        for(String s : strArry){
            strBuild.append(String.format("\n edge [ \n  style=\"solid\", arrowhead= \"vee\" \n ] \n %s -> %s \n",
                    super.stripFilePath(super.getBaseName()),
                    super.stripClassPath(s)));
        }
        
        return strBuild.toString();
    }**/
}