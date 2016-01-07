package asm;

import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/7/2016.
 */
public class ClassFieldVisitorTest {
    KlassStorage storage;
    ClassVisitor fieldVisitor;
    ClassReader reader;

    @Before
    public void setUp() throws Exception
    {
        storage = new KlassStorage();
        fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, storage);
    }

    @Test
    public void DeclarationVisitTest() throws IOException {
        reader=new ClassReader("asm.KlassStorage");
        reader.accept(fieldVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("null- currentPart: asm.api.IKlassPart \\l",storage.getCurrentPart().printFieldBlock());

        reader=new ClassReader("asm.ClassDeclarationVisitor");
        reader.accept(fieldVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("null- currentPart: asm.api.IKlassPart \\l- klass: asm.KlassStorage \\l",
                storage.getCurrentPart().printFieldBlock());
    }

}