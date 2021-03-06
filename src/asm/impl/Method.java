package asm.impl;

import asm.api.IKlassPart;
import asm.api.IMethod;

import java.util.HashSet;

/**
 * Created by Steven on 1/4/2016.
 */
public class Method extends KlassDecorator implements IMethod {
    private IKlassPart baseKlass;

    private String accessLevel;
    private String methodName;
    private String returnType;
    private Argument[] arguments;
    private String[] exceptions;

    public Method(IKlassPart baseClass, int accessLevel, String methodName, String returnType, Argument[] arguments, String[] exceptions) {
        super(baseClass);
        this.baseKlass = baseClass;
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
    public Object[] getArguments() {
        return this.arguments;
    }

    public String[] getExceptions() {
        return exceptions;
    }

    @Override
    public String printMethodBlock() {
        StringBuilder returnString = new StringBuilder();
        String parent=super.printMethodBlock();
        if(parent!=null)
            returnString.append(super.printMethodBlock());
        returnString.append(String.format("%s %s ( ", this.accessLevel, this.methodName));
        if(this.arguments.length !=0){
            returnString.append(String.format("%s : %s", this.arguments[0].getName(), this.arguments[0].getType().toString()));
        }

        for(int i = 1; i<this.arguments.length; i++){
            returnString.append(String.format(", %s : %s", this.arguments[i].getName(), this.arguments[i].getType().toString()));
        }
        returnString.append(String.format("): %s \\l", this.returnType ));
        return returnString.toString();
    }

    @Override
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
            strBuild.append(String.format("\n edge [ \n  style=\"dashed\", arrowhead= \"vee\" \n ] \n %s -> %s \n", super.stripFilePath(super.getBaseName()), super.stripClassPath(str)));
        }

        return strBuild.toString();
    }


}