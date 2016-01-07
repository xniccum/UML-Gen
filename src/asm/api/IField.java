package asm.api;

import org.objectweb.asm.Type;

/**
 * Created by Steven on 1/5/2016.
 */
public interface IField extends IKlassPart{
    public String getFieldName();
    public String getFieldType();
    public String getAccessLevel();
    public String getfieldSignature();
}
