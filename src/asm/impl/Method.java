package asm.impl;

import asm.api.IKlassPart;
import asm.api.IMethod;

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
        this.methodName = methodName;
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


}