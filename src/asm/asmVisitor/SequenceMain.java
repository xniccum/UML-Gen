package asm.asmVisitor;

import asm.SequenceOutputStream;
import asm.SequenceRunner;
import asm.StorageApi.MethodStorage.IMethodInternalCall;
import asm.impl2.Method;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Steven on 1/14/2016.
 */
public class SequenceMain {
    public static void main(String[] args) throws IOException {
        OutputStream sdOut = new FileOutputStream("inputOutput/seqOutput.sd");
        SequenceOutputStream seqOut = new SequenceOutputStream(sdOut);
        String methodSignature = args[0];
        int maxCallDepth = args.length < 2 ? 5 : Integer.parseInt(args[1]);
        if(maxCallDepth == 0)
            return;


        String className = "asm.asmVisitor.ClassMethodVisitor"; //TODO actual input
        String methodName = "visitMethod";
//        maxCallDepth = 2;

//        String methodSignature = "asm.asmVisitor.ClassMethodVisitor.visitMethod(String arg1, int arg2)";
        String[] split = methodSignature.split("[.]");
        methodName = split[split.length-1].split("[(]")[0];
//        System.out.println(split[split.length-2]);
//        System.out.println(methodSignature.substring(0, methodSignature.charAt('.')));
        className=split[0];
        for(int i=1; i<split.length-1; i++) {
            className+="."+split[i];
        }
        System.out.println(className);

        Method topMethod = SequenceRunner.run(className,methodName,maxCallDepth);

        seqOut.write(topMethod);


    }
}
