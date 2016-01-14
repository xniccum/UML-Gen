package asm.impl2;

import asm.StorageApi.IKlassPart;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Created by Steven on 1/4/2016.
 */
public abstract class KlassDecorator implements IKlassPart, ITraverser{
    public KlassDecorator() {

    }


    public void accept(IVisitor v) {
        v.preVisit(this);
        v.nameVisit(this);
        v.fieldVisit(this);
        v.methodVisit(this);
        v.postVisit(this);
    }

    //region Print Overrides
    /**@Override
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
    }**/

    //endregion

    //region Helper Methods

    public static String getAccessStringLevel(String accessLevel){
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

    public static String getAccessStringLevel(int accessLevel){
        return getAccessStringLevel(getAccessLevelString(accessLevel));
    }

    public static String getAccessLevelString(int access){
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

    public static String stripFilePath(String s){
        String[] sArray = s.split("/");
        return sArray[sArray.length -1];
    }

    public static String stripClassPath(String s){
        String[] strArr = s.split("[./]");
        return strArr[strArr.length-1];
    }

    public static String stripCollection(String type){
        int startParse = type.indexOf('<');
        int endParse =  type.indexOf('>');
        return startParse > -1 && endParse > -1 ?type.substring(startParse,endParse):type;
    }

    public static String stripSymbols(String s){
       return s.replaceAll("[!@#$%^&*():\"<>;\"'/\\\\+--_.\\[\\]]","");
    }

    public static String fullStripClean(String s){
        s = stripCollection(s);
        s = stripFilePath(s);
        s = stripClassPath(s);
        return stripSymbols(s);
    }

    public static boolean isDesirableObject(String s){
        String[] string = {"String", "Object","boolean","","Collection","Collections","List","ArrayList"};
        for(String s1: string){
            if(s1.equals(s))
                return false;
        }
        return true;
    }
    //endregion


}
