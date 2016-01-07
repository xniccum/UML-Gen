package asm.impl;

import asm.api.IKlass;
import asm.api.IKlassPart;
import org.objectweb.asm.Opcodes;

/**
 * Created by Steven on 1/4/2016.
 */
public abstract class KlassDecorator implements IKlassPart{// extends IKlass implements IKlassPart{
    private IKlassPart baseKlass;
    private String baseKlassName;

    public KlassDecorator(IKlassPart baseKlass) {
        this.baseKlass = baseKlass;
        this.baseKlassName = baseKlass.getBaseName();
    }

    @Override
    public String getBaseName() {
        return this.baseKlassName;
    }

    String getAccessStringLevel(String accessLevel){
        switch (accessLevel){
            case "public":
                return "+";
            case "protected":
                return "#";
            case "private":
                return "-";
            default:
                return "";
        }
    }

    String getAccessStringLevel(int accessLevel){
        return this.getAccessStringLevel(this.getAccessLevelString(accessLevel));
    }

    String getAccessLevelString(int access){
        if((access& Opcodes.ACC_PUBLIC)!=0){
            return "public";
        }else if((access&Opcodes.ACC_PROTECTED)!=0){
           return "protected";
        }else if((access&Opcodes.ACC_PRIVATE)!=0){
            return "private";
        }else{
            return "default";
        }
    }

    String stripFilePath(String s){
        String[] sArray = s.split("/");
        return sArray[sArray.length -1];
    }

    String stripClassPath(String s){
        String[] strArr = s.split("[.]");
        return strArr[strArr.length-1];
    }

    @Override
    public String printBefore() {
        return baseKlass.printBefore();
    }

    @Override
    public String printFieldBlock() {
        return baseKlass.printFieldBlock();
    }

    @Override
    public String printMethodBlock() {
        return baseKlass.printMethodBlock();
    }

    @Override
    public String printNameBlock() {
        return baseKlass.printNameBlock();
    }

    @Override
    public String printEnd() {
        return baseKlass.printEnd();
    }
}
