package asm;

import asm.DataObjectVisitors.UmlOutputStream;
import asm.asmVisitor.StandardVisitors.ClassDeclarationVisitor;
import asm.asmVisitor.StandardVisitors.ClassFieldVisitor;
import asm.asmVisitor.StandardVisitors.ClassMethodVisitor;
import asm.impl2.Klass;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by Austin on 1/14/2016.
 */
public class UmlOutputStreamTest {
    private FileInputStream fileInputStream;

    @Test
    public void testWrite() throws Exception {
        UmlOutputStream stream = new UmlOutputStream(new FileOutputStream("inputOutput/test.dot"));
        Klass klass = new Klass();
        ClassReader reader = new ClassReader("asm.asmVisitor.FirstASM");
        ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, klass);
        ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, klass);
        ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, klass);
        reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
        stream.setClassName(klass.getName());
        stream.write(klass);
        stream.close();

        //begin Test
        StringBuilder stringBuilder = new StringBuilder();
        fileInputStream = new FileInputStream("inputOutput/test.dot");

        // Always wrap FileReader in BufferedReader.
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader("inputOutput/test.dot"));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader("inputOutput/UMLOutputStreamTest.dot"));


        String line1;
        String line2;
        while (((line1 = bufferedReader1.readLine()) != null) && ((line2 = bufferedReader2.readLine()) != null)) {
            assertEquals(line1.trim(),line2.trim());
        }
        assertTrue(bufferedReader1.readLine() == null && bufferedReader2.readLine() == null);
    }
}