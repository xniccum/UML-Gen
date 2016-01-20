package asm.asmVisitor;

import asm.SequenceOutputStream;
import asm.SequenceRunner;
import asm.impl2.StandardDataObjects.Method;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Steven on 1/14/2016.
 */
public class SequenceMain {
    public static void main(String[] args) throws Exception {
        OutputStream sdOut = new FileOutputStream("inputOutput/seqOutput.sd");
        SequenceOutputStream seqOut = new SequenceOutputStream(sdOut);
        String methodSignature = args[0];
        int maxCallDepth = args.length < 2 ? 5 : Integer.parseInt(args[1]);
        if(maxCallDepth == 0)
            return;


        String className;
        String methodName;

        String[] split = methodSignature.split("[.]");
        methodName = split[split.length-1].split("[(]")[0];

        className=split[0];
        for(int i=1; i<split.length-1; i++) {
            className+="."+split[i];
        }
        System.out.println(className);

        Method topMethod = SequenceRunner.run(className,methodName,maxCallDepth);

        seqOut.write(topMethod);

    }


}
