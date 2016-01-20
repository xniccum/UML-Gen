package asm.DataObjectVisitors;

import asm.StorageApi.*;
import asm.StorageApi.MethodStorage.IMethodPart;
import asm.StorageApi.MethodStorage.IMethodInternalCall;
import asm.asmVisitor.DesignVisitors.SingletonClassVisitor;
import asm.impl.Argument;
import asm.impl2.DesignParts.SingletonDesign;
import asm.impl2.StandardDataObjects.Interphace;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;
import asm.visitorApi.VisitType;
import asm.visitorApi.Visitor;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;

/**
 * Created by Steven on 1/12/2016.
 */
public class UmlOutputStream extends FilterOutputStream {
    private final IVisitor visitor;
    private String className;

    /**
     * Depends on Klass being the very base object
     *
     * @param out
     * @throws IOException
     */
    public UmlOutputStream(OutputStream out) throws IOException {
        super(out);
        this.visitor = new Visitor();
        setupPostVisitSuperKlass();
        setupFieldVisitField();
        setupMethodVisitMethod();
        setupPostVisitInterphase();
        setupNameVistKlass();
        setupMethodVisitKlass();
        setupFieldVisitKlass();
        setupPostVisitKlass();
        setupPostVisitField();
        setupPostVisitMethod();
        setupPostVisitMethodUsedKlass();
        setupNameVisitDesignType();
        setupPostVisitSingletonClass();
    }

    private void write(String m) {
        try {
            super.write(m.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(IKlassPart part) {
        ITraverser t = (ITraverser) part;
        t.accept(this.visitor);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className =  KlassDecorator.stripFilePath(className);
    }

    //region Setup Methods
    private void setupPostVisitSuperKlass() {
        this.visitor.addVisit(VisitType.PostVisit, ISuperKlass.class, (ITraverser t) -> {
                    ISuperKlass sk = (ISuperKlass) t;
                    String superName = KlassDecorator.stripFilePath(sk.getSuperKlass());
                    if(!superName.equals("Object")) {
                        String line = String.format("\n edge [ \n  style=\"solid\", arrowhead = \"normal\" \n ] \n %s -> %s \n",
                                this.className, superName);
                        this.write(line);
                    }
                }
        );
    }

    private void setupFieldVisitField() {
        this.visitor.addVisit(VisitType.FieldVisit, IField.class, (ITraverser t) -> {
            IField f = (IField) t;
            String line = String.format("%s %s: %s \\l ", f.getAccessLevel(), f.getFieldName(), KlassDecorator.stripClassPath(f.getFieldType()));
            this.write(line);
        });
    }

    private void setupMethodVisitMethod() {
        this.visitor.addVisit(VisitType.MethodVisit, IMethod.class, (ITraverser t) -> {
            IMethod m = (IMethod) t;
            Argument[] args = m.getArguments();
            StringBuilder returnString = new StringBuilder();
            returnString.append(String.format("%s %s ( ", m.getAccessLevel(), m.getMethodName()));
            if (args.length != 0) {
                returnString.append(String.format("%s : %s", args[0].getName(), KlassDecorator.stripClassPath(args[0].getType().toString())));
            }

            for (int i = 1; i < args.length; i++) {
                returnString.append(String.format(", %s : %s", args[i].getName(), KlassDecorator.stripClassPath(args[i].getType().toString())));
            }
            returnString.append(String.format("): %s \\l ", KlassDecorator.stripClassPath(m.getReturnType())));
            this.write(returnString.toString());
        });
    }

    private void setupPostVisitInterphase() {
        this.visitor.addVisit(VisitType.PostVisit, IInterphace.class, (ITraverser t) -> {
            IInterphace phace = (Interphace) t;

            StringBuilder outString = new StringBuilder();
            outString.append(" edge [\n style=\"solid\", arrowhead = \"empty\"\n]\n");
            for (String interphaceName : phace.getInterphase()) {
                outString.append(String.format("%s -> %s \n", this.className, KlassDecorator.stripFilePath(interphaceName)));
            }
            this.write(outString.toString());
        });
    }

    private void setupNameVistKlass() {
        this.visitor.addVisit(VisitType.NameVisit, IKlass.class, (ITraverser t) -> {
            IKlass k = (IKlass) t;
            String nameString = KlassDecorator.stripFilePath(k.getName());
            this.write(String.format("%s [ \n label = \" { %s", nameString, nameString));
        });
    }

    private void setupFieldVisitKlass() {
        this.visitor.addVisit(VisitType.FieldVisit, IKlass.class, (ITraverser t) -> {
            IKlass k = (IKlass) t;
            this.write("|");
        });
    }

    private void setupMethodVisitKlass() {
        this.visitor.addVisit(VisitType.MethodVisit, IKlass.class, (ITraverser t) -> {
            IKlass k = (IKlass) t;
            this.write("|");
        });
    }

    private void setupPostVisitKlass() {
        this.visitor.addVisit(VisitType.PostVisit, IKlass.class, (ITraverser t) -> {
            IKlass k = (IKlass) t;
            this.write(String.format(" \n } \" \n ]"));
        });
    }

    private void setupPostVisitField(){
        this.visitor.addVisit(VisitType.PostVisit, IField.class, (ITraverser t) -> {
            IField f = (IField) t;

            StringBuilder strBuild = new StringBuilder();
            String fieldSignature = f.getfieldSignature();
            //fieldSignature is empty: cat the \\ off field type and add to builder
            strBuild.append("\n" +
                    " edge [ \n" +
                    "  style=\"solid\", arrowhead= \"vee\" \n" +
                    " ] \n");
            if(fieldSignature == null || fieldSignature.equals("")) {
                if(className =="")
                    return;
                fieldSignature = className;

            }

                //type is inside of a collection or outer object. Format of style ///<>
                //String carrotedString = KlassDecorator.stripCollection(fieldSignature);
                //Look for multiple params broken by semi-colon
                String[] strArry = fieldSignature.split("[;,:]");

                for (String str : strArry) {
                    String s = KlassDecorator.fullStripClean(str);
                    if(KlassDecorator.isDesirableObject(s))
                        strBuild.append(String.format("%s -> %s \n",className,s));
                }

            this.write(strBuild.toString());
        });
    }

    private void setupPostVisitMethod() {
        this.visitor.addVisit(VisitType.PostVisit, IMethod.class, (ITraverser t) -> {
            IMethod m = (IMethod) t;
            HashSet<String> set = new HashSet<String>();
            if (m.getReturnType() != "void")
                set.add(m.getReturnType());

            for (Argument arg : m.getArguments()) {
                set.add(KlassDecorator.stripCollection(arg.getType()));
            }

            StringBuilder strBuild = new StringBuilder();
            strBuild.append("\n" +
                    " edge [ \n" +
                    "  style=\"dashed\", arrowhead= \"vee\" \n" +
                    " ] \n");
            for (String str : set) {
                String s = KlassDecorator.fullStripClean(str);
                if (KlassDecorator.isDesirableObject(s))
                    strBuild.append(String.format("%s -> %s \n", className, s));
            }
            this.write(strBuild.toString());
            for(IMethodPart part : m.getMethodParts()){
                visitor.postVisit((ITraverser)part);
            }
        });
    }

    private void setupPostVisitMethodUsedKlass(){
        this.visitor.addVisit(VisitType.PostVisit, IMethodInternalCall.class, (ITraverser t) -> {
            IMethodInternalCall m = (IMethodInternalCall) t;

            StringBuilder strBuild = new StringBuilder();
            strBuild.append("\n" +
                    " edge [ \n" +
                    "  style=\"dashed\", arrowhead= \"vee\" \n" +
                    " ] \n");

                String s = KlassDecorator.fullStripClean(m.getClassName());
                if(KlassDecorator.isDesirableObject(s))
                    strBuild.append(String.format("%s -> %s \n",className, s));

            this.write(strBuild.toString());
        });
    }

    //SingletonPatten
    private void setupNameVisitDesignType(){
        this.visitor.addVisit(VisitType.NameVisit, DesignType.class, (ITraverser t) -> {
            DesignType des = (DesignType) t;
            this.write(String.format("\\l\\<\\<%s\\>\\>", des.getDesignName()));
        });
    }

    private void setupPostVisitSingletonClass(){
        this.visitor.addVisit(VisitType.PostVisit, SingletonDesign.class, (ITraverser t) -> {
            SingletonDesign s = (SingletonDesign) t;

            String str = String.format("\n edge [ \n  style=\"solid\", arrowhead = \"normal\" \n ] \n %s -> %s \n",
                    this.className, this.className);
            this.write(str);
        });
    }

    //endregion

}
