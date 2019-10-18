package com.susiha.apkanalysis.dexanalysis.classdef;


/**
 * 访问修饰符
 */
public class AccessFlag {
    public static final int ACC_PUBLIC       = 0x00000001;      // class, field, method, ic
    public static final int ACC_PRIVATE      = 0x00000002;      // field, method, ic
    public static final int ACC_PROTECTED    = 0x00000004;       // field, method, ic
    public static final int ACC_STATIC       = 0x00000008;       // field, method, ic
    public static final int ACC_FINAL        = 0x00000010;       // class, field, method, ic
    public static final int ACC_SYNCHRONIZED = 0x00000020;       // method (only allowed on natives)
    public static final int ACC_SUPER        = 0x00000020;       // class (not used in Dalvik)
    public static final int ACC_VOLATILE     = 0x00000040;       // field
    public static final int ACC_BRIDGE       = 0x00000040;       // method (1.5)
    public static final int ACC_TRANSIENT    = 0x00000080;       // field
    public static final int ACC_VARARGS      = 0x00000080;       // method (1.5)
    public static final int ACC_NATIVE       = 0x00000100;       // method
    public static final int ACC_INTERFACE    = 0x00000200;       // class, ic
    public static final int ACC_ABSTRACT     = 0x00000400;       // class, method, ic
    public static final int ACC_STRICT       = 0x00000800;       // method
    public static final int ACC_SYNTHETIC    = 0x00001000;       // field, method, ic
    public static final int ACC_ANNOTATION   = 0x00002000;       // class, ic (1.5)
    public static final int ACC_ENUM         = 0x00004000;       // class, field, ic (1.5)
    public static final int ACC_CONSTRUCTOR  = 0x00010000;       // method (Dalvik only)
    public static final int ACC_DECLARED_SYNCHRONIZED = 0x00020000;      // method (Dalvik only)
    public static final int ACC_CLASS_MASK = (ACC_PUBLIC | ACC_FINAL | ACC_INTERFACE | ACC_ABSTRACT
                    | ACC_SYNTHETIC | ACC_ANNOTATION | ACC_ENUM);
    public static final int ACC_INNER_CLASS_MASK = (ACC_CLASS_MASK | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC);
    public static final int ACC_FIELD_MASK = (ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL
                    | ACC_VOLATILE | ACC_TRANSIENT | ACC_SYNTHETIC | ACC_ENUM);
    public static final int ACC_METHOD_MASK = (ACC_PUBLIC | ACC_PRIVATE | ACC_PROTECTED | ACC_STATIC | ACC_FINAL
                    | ACC_SYNCHRONIZED | ACC_BRIDGE | ACC_VARARGS | ACC_NATIVE
                    | ACC_ABSTRACT | ACC_STRICT | ACC_SYNTHETIC | ACC_CONSTRUCTOR
                    | ACC_DECLARED_SYNCHRONIZED);

    /**
     *
     * @param accessFlags
     * @return
     */
     public static String getAccessFlagDesc(int accessFlags){
         return "";
     }
}
