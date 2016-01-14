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


        String className;
        String methodName;

        Method topMethod = SequenceRunner.run(className,methodName,maxCallDepth);
        seqOut.write(topMethod);


    }
}
