package com.susiha.apkanalysis.dexanalysis.classdef;

import com.susiha.apkanalysis.dexanalysis.common.DexTypeList;

/**
 * 基本结构
 * struct DexClassDef {
 *    u4 classIdx;        描述具体的class类型，指向TypeIds,不能是基本类型或者是数组类型
 *    u4 accessFlags;     访问类型
 *    u4 superclassIdx;   父类类型，约束同class_idx
 *    u4 interfacesOff;   接口的偏移量 如果为0  表示没有实现的接口
 *    u4 sourceFileIdx;   表示源代码文件的信息，指向StringIds,0xFFFFFFFF 表示没有找到索引
 *    u4 annotationsOff;  注解的偏移量
 *    u4 classDataOff;    classData 的偏移量
 *    u4 staticValuesOff; 静态数据的偏移量
 * };
 */
public class ClassDefItem {
    public static final int CLASS_IDX_SIZE         = 4;
    public static final int ACCESS_FLAGS_SIZE      = 4;
    public static final int SUPERCLASS_IDX_SIZE    = 4;
    public static final int INTERFACES_OFF_SIZE    = 4;
    public static final int SOURCEFILE_IDX_SIZE    = 4;
    public static final int ANNOTATIONS_OFF_SIZE   = 4;
    public static final int CLASS_DATA_OFF_SIZE    = 4;
    public static final int STATIC_VALUES_OFF_SIZE = 4;

    private byte[] classIdx;
    private byte[] accessFlags;
    private byte[] superclassIdx;
    private byte[] interfacesOff;
    private byte[] sourceFileIdx;
    private byte[] annotationsOff;
    private byte[] classDataOff;
    private byte[] staticValuesOff;
    private DexTypeList interfaceList;









}
