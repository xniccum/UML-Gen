package asm;

import asm.StorageApi.*;
import asm.impl.Argument;
import asm.impl2.Interphace;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;
import asm.visitorApi.VisitType;
import asm.visitorApi.Visitor;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Steven on 1/12/2016.
 */
public class UmlOutputStream extends FilterOutputStream {
    private final IVisitor visitor;
    //private final String className;

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
        setupVisitMethodBlock();
        setupPostVisitInterphase();
        setupNameVistKlass();
        setupMethodVisitKlass();
        setupFieldVisitKlass();
        setupPostVisitKlass();
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

    private void setupPostVisitSuperKlass() {
        this.visitor.addVisit(VisitType.PostVisit, ISuperKlass.class, (ITraverser t) -> {
                    ISuperKlass sk = (ISuperKlass) t;
                    String line = String.format("\n edge [ \n  style=\"solid\", arrowhead = \"normal\" \n ] \n %s -> %s \n",
                            KlassDecorator.stripFilePath(sk.getBaseName()), KlassDecorator.stripFilePath(sk.getSuperKlass()));
                    this.write(line);
                }
        );
    }

    private void setupFieldVisitField() {
        this.visitor.addVisit(VisitType.FieldVisit, IField.class, (ITraverser t) -> {
            IField f = (IField) t;
            String line = String.format("%s %s: %s \\l", f.getAccessLevel(), f.getFieldName(), f.getFieldType());
            this.write(line);
        });
    }

    private void setupVisitMethodBlock() {
        this.visitor.addVisit(VisitType.MethodVisit, IMethod.class, (ITraverser t) -> {
            IMethod m = (IMethod) t;
            Argument[] args = m.getArguments();
            StringBuilder returnString = new StringBuilder();
            returnString.append(String.format("%s %s ( ", m.getAccessLevel(), m.getMethodName()));
            if (args.length != 0) {
                returnString.append(String.format("%s : %s", args[0].getName(), args[0].getType().toString()));
            }

            for (int i = 1; i < args.length; i++) {
                returnString.append(String.format(", %s : %s", args[i].getName(), args[i].getType().toString()));
            }
            returnString.append(String.format("): %s \\l", m.getReturnType()));
            this.write(returnString.toString());
        });
    }

    private void setupPostVisitInterphase() {
        this.visitor.addVisit(VisitType.PostVisit, IInterphace.class, (ITraverser t) -> {
            IInterphace phace = (Interphace) t;

            StringBuilder outString = new StringBuilder();
            outString.append(" edge [ \n style=\"solid\", arrowhead = \"empty\" \n ] \n ");
            for (String interphaceName : phace.getInterphase()) {
                outString.append(String.format(" %s -> %s \n", KlassDecorator.stripFilePath(phace.getBaseName()), KlassDecorator.stripFilePath(interphaceName)));
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

}
