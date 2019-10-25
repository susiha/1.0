package com.susiha.apkanalysis.dexanalysis.common;


/**
 * EncodedValue编码规则
 * 用byte的高3位用来表示value_args 用来表示后面的字节的size
 * value_args的值是size-1 也就是说如果表示为0则后面value需要一个字节 依次类推
 * 低5位表示value_type 参考下面常量定义
 *
 */
public class EncodedValue {
    /**
     * 表示EncodedValue的value_type
     */
    public static final int EncodedValueByte = 0x00;
    public static final int EncodedValueShort = 0x02;
    public static final int EncodedValueChar = 0x03;
    public static final int EncodedValueInt = 0x04;
    public static final int EncodedValueLong = 0x06;
    public static final int EncodedValueFloat = 0x10;
    public static final int EncodedValueDouble = 0x11;
    public static final int EncodedValueMethodType = 0x15;
    public static final int EncodedValueMethodHandle = 0x16;
    public static final int EncodedValueString = 0x17;
    public static final int EncodedValueType = 0x18;
    public static final int EncodedValueField = 0x19;
    public static final int EncodedValueMethod = 0x1a;
    public static final int EncodedValueEnum = 0x1b;
    public static final int EncodedValueArray = 0x1c;
    public static final int EncodedValueAnnotation = 0x1d;
    public static final int EncodedValueNull = 0x1e;
    public static final int EncodedValueBoolean = 0x1f;

    public static final int EncodedValueTypeMask = 0x1f;     /* 低 5 位 */
    public static final int EncodedValueShift = 5;
    public static final int EncodedValueArgsMask = 0xe0;/* 高 3 位 */

    /**
     * 获取真实的Type，只要低5位的值
     * @return
     */
    public  int getRealValueType(){
        if(value_type_args > EncodedValueTypeMask){
            return  value_type_args & EncodedValueTypeMask;
        }
        return value_type_args;
    }

    /**
     * 获取真实的valueArgs
     * @return
     */
    public  int getRealValueArgs(){
        int args =(value_type_args & EncodedValueArgsMask)>>EncodedValueShift;
        return args;
    }

    /**
     * 获取Value的字节个数
     * @return
     */
    public int getValueSize(){
        return getRealValueArgs()+1;
    }



    /**
     * 获取注解的类型字符串描述
     * @param typeValue
     * @return
     */
    public  String getTypeDescByValue(int typeValue){

        switch (typeValue){
            case EncodedValueByte:
                return "byte";
            case EncodedValueShort:
                return "short";
            case EncodedValueChar:
                return "char";
            case EncodedValueInt:
                return "int";
            case EncodedValueLong:
                return "long";
            case EncodedValueFloat:
                return "float";
            case EncodedValueDouble:
                return "double";
            case EncodedValueMethodType:
                return "method_type";
            case EncodedValueMethodHandle:
                return "method_handle";
            case EncodedValueString:
                return "String";
            case EncodedValueType:
                return "type";
            case EncodedValueField:
                return "field";
            case EncodedValueMethod:
                return "method";
            case EncodedValueEnum:
                return "enum";
            case EncodedValueArray:
                return "array";
            case EncodedValueAnnotation:
                return "annotation";
            case EncodedValueNull:
                return "null";
            case EncodedValueBoolean:
                return "boolean";
        }
        return "Type_UnKnow";
    }

    private byte value_type_args;
    private EncodedArray encodedArrays;
    private byte[] values;

    public EncodedArray getEncodedArrays() {
        return encodedArrays;
    }
    public void setEncodedArrays(EncodedArray encodedArrays) {
        this.encodedArrays = encodedArrays;
    }
    public byte getValue_type_args() {
        return value_type_args;
    }
    public void setValue_type_args(byte value_type_args) {
        this.value_type_args = value_type_args;
    }
    public byte[] getValues() {
        return values;
    }
    public void setValues(byte[] values) {
        this.values = values;
    }


}
