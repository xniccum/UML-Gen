package asm;

import asm.StorageApi.IKlassPart;
import asm.impl2.KlassDecorator;
import asm.visitorApi.ITraverser;
import asm.visitorApi.IVisitor;
import asm.visitorApi.Visitor;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Steven on 1/12/2016.
 */
public class SequenceOutputStream extends FilterOutputStream {
    private final IVisitor visitor;
    private String className;
    //private final String className;

    /**
     * Depends on Klass being the very base object
     *
     * @param out
     * @throws IOException
     */
    public SequenceOutputStream(OutputStream out) throws IOException {
        super(out);
        this.visitor = new Visitor();

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

    // listners
}
