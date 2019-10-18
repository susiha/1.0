package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

/**
 * 注解的常量解析
 */
public class AnnotationConstants {

    public static final int kDexVisibilityBuild = 0x00;     /* annotation visibility */
    public static final int kDexVisibilityRuntime = 0x01;
    public static final int kDexVisibilitySystem = 0x02;

    public static final int kDexAnnotationByte = 0x00;
    public static final int kDexAnnotationShort = 0x02;
    public static final int kDexAnnotationChar = 0x03;
    public static final int kDexAnnotationInt = 0x04;
    public static final int kDexAnnotationLong = 0x06;
    public static final int kDexAnnotationFloat = 0x10;
    public static final int kDexAnnotationDouble = 0x11;
    public static final int kDexAnnotationMethodType = 0x15;
    public static final int kDexAnnotationMethodHandle = 0x16;
    public static final int kDexAnnotationString = 0x17;
    public static final int kDexAnnotationType = 0x18;
    public static final int kDexAnnotationField = 0x19;
    public static final int kDexAnnotationMethod = 0x1a;
    public static final int kDexAnnotationEnum = 0x1b;
    public static final int kDexAnnotationArray = 0x1c;
    public static final int kDexAnnotationAnnotation = 0x1d;
    public static final int kDexAnnotationNull = 0x1e;
    public static final int kDexAnnotationBoolean = 0x1f;

    public static final int kDexAnnotationValueTypeMask = 0x1f;     /* low 5 bits */
    public static final int kDexAnnotationValueArgShift = 5;

    /**
     * 获取真实的TypeValue，只要低5位的值
     * @param typeValue
     * @return
     */
    public static int getRealAnnotationTypeValue(int typeValue){
        if(typeValue>kDexAnnotationValueTypeMask){
            return  typeValue&kDexAnnotationValueTypeMask;
        }
        return typeValue;
    }


    /**
     * 获取注解的可见性 也就是运行在哪里
     * @param typeValue
     * @return
     */
    public static String getAnnotationVilibleByTypeValue(int typeValue){

        switch (typeValue){
            case kDexVisibilityBuild:
                return "Visibility_Build";
            case kDexVisibilityRuntime:
                return "Visibility_Runtime";
            case kDexVisibilitySystem:
                return "Visibility_System";

        }
        return "Visibility_UnKnow";
    }

    /**
     * 获取注解的类型字符串描述
     * @param typeValue
     * @return
     */
    public static String getAnnotationTypeDescByValue(int typeValue){

        switch (typeValue){
            case kDexAnnotationByte:
                return "byte";
            case kDexAnnotationShort:
                return "short";
            case kDexAnnotationChar:
                return "char";
            case kDexAnnotationInt:
                return "int";
            case kDexAnnotationLong:
                return "long";
            case kDexAnnotationFloat:
                return "float";
            case kDexAnnotationDouble:
                return "double";
            case kDexAnnotationMethodType:
                return "method_type";
            case kDexAnnotationMethodHandle:
                return "method_handle";
            case kDexAnnotationString:
                return "String";
            case kDexAnnotationType:
                return "type";
            case kDexAnnotationField:
                return "field";
            case kDexAnnotationMethod:
                return "method";
            case kDexAnnotationEnum:
                return "enum";
            case kDexAnnotationArray:
                return "array";
            case kDexAnnotationAnnotation:
                return "annotation";
            case kDexAnnotationNull:
                return "null";
            case kDexAnnotationBoolean:
                return "boolean";
        }
        return "Type_UnKnow";
    }










}
