package asm;

import asm.StorageApi.IKlassPart;
import asm.StorageApi.IMethod;
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
public class SequenceOutputStream extends FilterOutputStream {
    private final IVisitor visitor;

    /**
     * Depends on Klass being the very base object
     *
     * @param out
     * @throws IOException
     */
    public SequenceOutputStream(OutputStream out) throws IOException {
        super(out);
        this.visitor = new Visitor();
        setupMethodVisitMethods();

    }

    private void write(String m) {
        try {
            super.write(m.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(IMethod method) {
        ITraverser t = (ITraverser) method;
        t.accept(this.visitor);
    }

    private void setupMethodVisitMethods(){
        this.visitor.addVisit(VisitType.MethodVisit, IMethod.class, (ITraverser t)->{
            IMethod m = (IMethod)t;
            String s = String.format("");
            this.write(s);
        });
    }
}
