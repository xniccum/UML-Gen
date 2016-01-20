package asm;

import asm.asmVisitor.StandardVisitors.ClassDeclarationVisitor;
import asm.asmVisitor.StandardVisitors.ClassFieldVisitor;
import asm.asmVisitor.StandardVisitors.ClassMethodVisitor;
import asm.impl2.Klass;
import asm.impl2.KlassDecorator;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/5/2016.
 */
public class ClassVisitorTest
{
    private Klass klass;
    private ClassReader reader;

    @Before
    public void setUp() throws Exception
    {

//        storage = new KlassStorage();
//        reader=new ClassReader("asm.mainRunners.FirstASM");
        klass = new Klass();

    }

    @Test
    public void VisitTest() throws Exception
    {
        reader = new ClassReader("asm.asmVisitor.FirstASM");
        ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, klass);
        ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, klass);
        ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, klass);
        reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("+", KlassDecorator.getAccessStringLevel(klass.getAccess()));
        assertEquals("asm/asmVisitor/FirstASM",klass.getName());
    }

    @Test(expected=IOException.class)
    public void badClassTest() throws Exception
    {
        reader = new ClassReader("asm.asmVisitor.First");
        fail("IOException Failed ot be thrown");
    }
}
