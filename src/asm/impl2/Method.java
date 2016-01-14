package asm.impl2;

import asm.StorageApi.IKlassPart;
import asm.StorageApi.IMethod;
import asm.StorageApi.MethodStorage.IMethodPart;
import asm.impl.Argument;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;

import java.util.ArrayList;
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
    private ArrayList<IMethodPart> parts;
    private ArrayList<IMethod> calls;
    private String className;

    public Method(int accessLevel, String methodName, String returnType, Argument[] arguments, String[] exceptions) {
        super();
        this.accessLevel = KlassDecorator.getAccessStringLevel(accessLevel);
        this.methodName = KlassDecorator.stripSymbols(methodName);
        this.returnType = returnType;
        this.arguments = arguments;
        this.exceptions = exceptions;
        this.parts = new ArrayList<IMethodPart>();
        this.calls = new ArrayList<>();
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

    @Override
    public String[] getExceptions() {
        return exceptions;
    }

    @Override
    public void addMethodPart(IMethodPart part) {
        parts.add(part);
    }

    @Override
    public ArrayList<IMethodPart> getMethodParts() {
        return parts;
    }

    @Override
    public void addSubMethod(IMethod method) {
        this.calls.add(method);
    }

    @Override
    public ArrayList<IMethod> getSubMethods() {
        return this.calls;
    }

    @Override
    public void accept(IVisitor v) {
        v.preVisit(this);
        for(IMethodPart part: parts){
            v.preVisit((ITraverser) part);
        }
        for(IMethod call: calls){
            v.preVisit((ITraverser) call);
        }
        v.nameVisit(this);
        for(IMethodPart part: parts){
            v.nameVisit((ITraverser) part);
        }
        for(IMethod call: calls){
            v.nameVisit((ITraverser) call);
        }
        v.fieldVisit(this);
        for(IMethodPart part: parts){
            v.fieldVisit((ITraverser) part);
        }
        for(IMethod call: calls){
            v.fieldVisit((ITraverser) call);
        }
        v.methodVisit(this);
        for(IMethodPart part: parts){
            v.methodVisit((ITraverser) part);
        }
        for(IMethod call: calls){
            v.methodVisit((ITraverser) call);
        }
        v.postVisit(this);
        for(IMethodPart part: parts){
            v.postVisit((ITraverser) part);
        }
        for(IMethod call: calls){
            v.postVisit((ITraverser) call);
        }
    }

    @Override
    public String getClassName() {
        return className == null? "" :className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }
}