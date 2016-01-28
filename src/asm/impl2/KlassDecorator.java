package asm.impl2;

import asm.StorageApi.IKlassPart;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;
//import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.Opcodes;

/**
 * Created by Steven on 1/4/2016.
 */
public abstract class KlassDecorator implements IKlassPart{

    public void accept(IVisitor v) {
        v.preVisit(this);
        v.nameVisit(this);
        v.fieldVisit(this);
        v.methodVisit(this);
        v.postVisit(this);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

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
        if(s.charAt(s.length()-1)=='/')
            return "";
        String[] sArray = s.split("/");
        return sArray[sArray.length -1];
    }

    public static String stripClassPath(String s){
        if(s.charAt(s.length()-1)=='/' || s.charAt(s.length()-1)=='.')
            return "";
        String[] strArr = s.split("[./]");
        return strArr[strArr.length-1];
    }

    public static String stripCollection(String type){
        int startParse = type.indexOf('<');
        int endParse =  type.indexOf('>');
        return startParse > -1 && endParse > -1 ?type.substring(startParse+1,endParse):type;
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
        if(s.contains("java.") || s.trim().equals(""))
            return false;
        s = KlassDecorator.fullStripClean(s);
        String[] string = {"","String","int","FilterOutputStream", "Object","StringBuilder", "HashSet", "PrintStream", "LinkedHashSet", "HashMap", "Map","boolean","Arrays", "RuntimeException", "Collection","Collections","List","ArrayList"};
        for(String s1: string){
            if(s1.equals(s))
                return false;
        }
        return true;
    }
    //endregion


}
