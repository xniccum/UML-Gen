package asm.impl2;

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
        this.baseKlassName = baseKlass == null? "" : baseKlass.getBaseName();
    }

    @Override
    public String getBaseName() {
        return baseKlass == null? "" : this.baseKlassName;
    }

    //region Print Overrides
    @Override
    public String printBefore() {
        return baseKlass == null? "" : baseKlass.printBefore();
    }

    @Override
    public String printFieldBlock() {
        return baseKlass == null? "" : baseKlass.printFieldBlock();
    }

    @Override
    public String printMethodBlock() {
        return baseKlass == null? "" : baseKlass.printMethodBlock();
    }

    @Override
    public String printNameBlock() {
        return baseKlass == null? "" : baseKlass.printNameBlock();
    }

    @Override
    public String printEnd() {
        return baseKlass == null ? "" : baseKlass.printEnd();
    }

    //endregion

    //region Helper Methods

    public String getAccessStringLevel(String accessLevel){
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

    public String getAccessStringLevel(int accessLevel){
        return this.getAccessStringLevel(this.getAccessLevelString(accessLevel));
    }

    public String getAccessLevelString(int access){
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

    public String stripFilePath(String s){
        String[] sArray = s.split("/");
        return sArray[sArray.length -1];
    }

    public String stripClassPath(String s){
        String[] strArr = s.split("[./]");
        return strArr[strArr.length-1];
    }

    public String stripCollection(String type){
        int startParse = type.indexOf('<');
        return startParse > -1 ? type.substring(startParse, type.indexOf('>')) :type;
    }

    //endregion


}
