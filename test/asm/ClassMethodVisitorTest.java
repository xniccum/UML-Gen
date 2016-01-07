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
public class ClassMethodVisitorTest {
    KlassStorage storage;
    ClassVisitor methodVisitor;
    ClassReader reader;

    @Before
    public void setUp() throws Exception
    {
        storage = new KlassStorage();
        methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, storage);
    }

    @Test
    public void MethodVisitTest() throws IOException {
        reader=new ClassReader("asm.KlassStorage");
        reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("+ init ( ): void \\l+ " +
                "getCurrentPart ( ): asm.api.IKlassPart \\l+ " +
                "setCurrentPart ( arg0 : asm.api.IKlassPart): void \\l+ " +
                "toString ( ): java.lang.String \\l",
                storage.getCurrentPart().printMethodBlock());

        reader=new ClassReader("asm.ClassDeclarationVisitor");
        reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
        assertEquals("+ init ( ): void \\l+ " +
                "getCurrentPart ( ): asm.api.IKlassPart \\l+ " +
                "setCurrentPart ( arg0 : asm.api.IKlassPart): void \\l+ " +
                "toString ( ): java.lang.String \\l+ init ( arg0 : int, arg1 : asm.KlassStorage): void \\l+ " +
                "visit ( arg0 : int, arg1 : int, arg2 : java.lang.String, arg3 : java.lang.String, arg4 : java.lang.String, arg5 : java.lang.String[]): void \\l",
                storage.getCurrentPart().printMethodBlock());
    }

}