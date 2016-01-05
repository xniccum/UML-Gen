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
    private String[] arguments;
    private String[] exceptions;

    public Method(IKlassPart baseClass, String accessLevel, String methodName, String returnType, String[] arguments, String[] exceptions) {
        super(baseClass);
        this.baseKlass = baseClass;
        this.accessLevel = accessLevel;
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
    public String[] getArguments() {
        return this.arguments;
    }

    public String[] getExceptions() {
        return exceptions;
    }

    @Override
    public String getBaseName() {
        return super.getBaseName();
    }

    @Override
    public String printBefore() {
        return "";
    }

    @Override
    public String printMiddle() {
        return String.format("");
    }

    @Override
    public String printEnd() {
        return null;
    }

}