package asm.impl2;

import asm.StorageApi.IKlassPart;
import asm.StorageApi.IMethod;
import asm.impl.Argument;
import asm.impl2.KlassDecorator;

import java.util.HashSet;

/**
 * Created by Steven on 1/4/2016.
 */
public class Method extends KlassDecorator implements IMethod {

    private String accessLevel;
    private String methodName;
    private String returnType;
    private Argument[] arguments;
    private String[] exceptions;

    public Method(int accessLevel, String methodName, String returnType, Argument[] arguments, String[] exceptions) {
        super();
        this.accessLevel = super.getAccessStringLevel(accessLevel);
        this.methodName = methodName.replaceAll("[\\<\\>\\\"\\'\\|\\;\\\\\\/]","");
        this.returnType = returnType;
        this.arguments = arguments;
        this.exceptions = exceptions;
    }

    @Override
    public String getAccessLevel() {
        return this.accessLevel;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }

    @Override
    public String getReturnType() {
        return this.returnType;
    }

    @Override
    public Argument[] getArguments() {
        return this.arguments;
    }

    public String[] getExceptions() {
        return exceptions;
    }


    /**@Override
    public String printEnd() {
        //remove duplicate using types for each method
        HashSet<String> set = new HashSet<>();
        if(returnType != "void")
            set.add(returnType);

        for(Argument arg : arguments)
        {
            set.add(super.stripCollection(arg.getType()));
        }

        StringBuilder strBuild = new StringBuilder();
        strBuild.append(super.printEnd());

        for(String str: set){
            strBuild.append(String.format("\n edge [ \n  style=\"dashed\", arrowhead= \"vee\" \n ] \n %s -> %s \n",
                    super.stripFilePath(super.getBaseName()), super.stripClassPath(str)));
        }

        return strBuild.toString();
    }**/
}