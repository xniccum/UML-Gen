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
public class ClassDeclarationVisitorTest {
    KlassStorage storage;
    ClassVisitor decVisitor;
    ClassReader reader;

    @Before
    public void setUp() throws Exception
    {
        storage = new KlassStorage();
        decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, storage);
    }

    @Test
    public void DeclarationVisitTest() throws IOException {
        reader=new ClassReader("asm.KlassStorage");
        reader.accept(decVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("asm/KlassStorage",storage.getCurrentPart().getBaseName());
        assertEquals("KlassStorage [ \n" + " label = \" { KlassStorage",storage.getCurrentPart().printNameBlock());

        reader=new ClassReader("asm.ClassDeclarationVisitor");
        reader.accept(decVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("asm/ClassDeclarationVisitor",storage.getCurrentPart().getBaseName());
        assertEquals("ClassDeclarationVisitor [ \n" + " label = \" { ClassDeclarationVisitor",
                storage.getCurrentPart().printNameBlock());
    }

}