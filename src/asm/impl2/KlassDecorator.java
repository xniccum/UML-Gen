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

    @Override
    public void accept(IVisitor v) {
       // v.preVisit((ITraverser)baseKlass);
        v.preVisit(this);
        //v.nameVisit((ITraverser)baseKlass);
        v.nameVisit(this);
        //v.fieldVisit((ITraverser)baseKlass);
        v.fieldVisit(this);
       // v.methodVisit((ITraverser)baseKlass);
        v.methodVisit(this);
        //v.postVisit((ITraverser)baseKlass);
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
        return startParse > -1 ? type.substring(startParse, type.indexOf('>')) :type;
    }

    //endregion


}
