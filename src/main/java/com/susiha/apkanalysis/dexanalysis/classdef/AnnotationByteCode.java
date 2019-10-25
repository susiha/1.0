package com.susiha.apkanalysis.dexanalysis.classdef;

import com.susiha.apkanalysis.dexanalysis.Utils;
import com.susiha.apkanalysis.dexanalysis.classdef.annotation.*;
import com.susiha.apkanalysis.dexanalysis.common.EncodedArray;
import com.susiha.apkanalysis.dexanalysis.common.EncodedValue;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class AnnotationByteCode extends BaseByteCode {
    /**
     * 获取AnnotationDirectItem Annotation一级结构
     * @param annotationOff
     * @return
     * @throws IOException
     */
    public static AnnotationDirectoryItem getAnnotationDirectItem(int annotationOff) throws IOException{
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(annotationOff);
        AnnotationDirectoryItem annotationDirectoryItem = new AnnotationDirectoryItem();
        //读取类注解的偏移量
        int classAnnotationsOff = getIntLet(bufferedSource);
        //读取有注解的变量的个数
        int fieldsSize = getIntLet(bufferedSource);
        //读取有注解的方法的个数
        int methodsSize = getIntLet(bufferedSource);
        //读取有注解的参数的个数
        int paramtersSize = getIntLet(bufferedSource);
        annotationDirectoryItem.setClassAnnotationsOff(classAnnotationsOff);
        annotationDirectoryItem.setFieldsSize(fieldsSize);
        annotationDirectoryItem.setMethodsSize(methodsSize);
        annotationDirectoryItem.setParamtersSize(paramtersSize);
        //该类有注解
        if(classAnnotationsOff!=0){
            annotationDirectoryItem.setClassAnnotation(getAnnotationSetItem(classAnnotationsOff));
        }
        if(fieldsSize!=0){

            ArrayList<FieldAnnotationItem> fieldAnnotationItems = new ArrayList<>();
            for(int i=0;i<fieldsSize;i++){
                fieldAnnotationItems.add(getFieldAnnotationItem(bufferedSource));
            }
            annotationDirectoryItem.setFieldAnnotationItems(fieldAnnotationItems);
        }

        if(methodsSize!=0){

            ArrayList<MethodAnnotationItem> methodAnnotationItems = new ArrayList<>();

            for(int i=0;i<methodsSize;i++){
                methodAnnotationItems.add(getMethodAnnotationItem(bufferedSource));
            }
            annotationDirectoryItem.setMethodAnnotationItems(methodAnnotationItems);
        }

        if(paramtersSize!=0){
            ArrayList<ParameterAnnotation> parameterItems = new ArrayList<>();
            for(int i = 0;i< paramtersSize;i++){
                parameterItems.add(getParameterAnnotation(bufferedSource));
            }

            annotationDirectoryItem.setParameterAnnotationItems(parameterItems);
        }


        return annotationDirectoryItem;
    }

    /**
     * 根据偏移量直接返回偏移后的数据源
     * @param offSize
     * @return
     * @throws IOException
     */
    private static BufferedSource getSkipBufferedSourceBySize(int offSize)throws IOException{
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(offSize);
        return bufferedSource;
    }

    /**
     * 获取字符的注解
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static FieldAnnotationItem getFieldAnnotationItem( BufferedSource bufferedSource) throws IOException {
        FieldAnnotationItem fieldItem = new FieldAnnotationItem();
        //字段的索引
        int filedIdx = getIntLet(bufferedSource);
        //偏移量
        int offSet = getIntLet(bufferedSource);
        fieldItem.setFieldIds(filedIdx);
        fieldItem.setAnnotationsOff(offSet);
        if(offSet!=0){
            fieldItem.setFileAnnotationsItem(getAnnotationSetItem(offSet));
        }
        return fieldItem;
    }


    public static MethodAnnotationItem getMethodAnnotationItem( BufferedSource bufferedSource) throws IOException {
        MethodAnnotationItem methodItem = new MethodAnnotationItem();
        //方法的索引
        int methodIdx = getIntLet(bufferedSource);
        //偏移量
        int offSet = getIntLet(bufferedSource);
        methodItem.setMethodIdx(methodIdx);
        methodItem.setAnnotationsOff(offSet);
        if(offSet!=0){
            methodItem.setMethodAnnotationsItem(getAnnotationSetItem(offSet));
        }
        return methodItem;
    }

    public static ParameterAnnotation getParameterAnnotation( BufferedSource bufferedSource) throws IOException {
        ParameterAnnotation parameterAnnotation = new ParameterAnnotation();
        //方法的索引
        int methodIdx = getIntLet(bufferedSource);
        //偏移量
        int offSet = getIntLet(bufferedSource);
        parameterAnnotation.setMethodIdx(methodIdx);
        parameterAnnotation.setAnnotationOff(offSet);
        if(offSet!=0){
            parameterAnnotation.setAnnotationSetRefList(getAnnotationSetRefList(offSet));
        }
        return parameterAnnotation;
    }




    public static AnnotationSetRefList getAnnotationSetRefList(int annotationOff) throws IOException {
        BufferedSource bufferedSource = getSkipBufferedSourceBySize(annotationOff);
        AnnotationSetRefList parameterItem = new AnnotationSetRefList();
        int size = getIntLet(bufferedSource);
        parameterItem.setSize(size);
        if(size>0){

            ArrayList<AnnotationSetRefItem> refItems = new ArrayList<>();
            for(int i = 0;i<size;i++){
                AnnotationSetRefItem item = new AnnotationSetRefItem();
                int offSet = getIntLet(bufferedSource);
                item.setAnnotationsOff(offSet);
                item.setParameterItem(getAnnotationSetItem(offSet));
                refItems.add(item);

            }
            parameterItem.setAnnotationSetRefItems(refItems);
        }
        return parameterItem;
    }




    /**
     * 获取AnnotationSetItem Annotation二级结构
     * @param annotationOff
     * @return
     * @throws IOException
     */
    public static AnnotationSetItem getAnnotationSetItem(int annotationOff) throws IOException {
        BufferedSource bufferedSource = getSkipBufferedSourceBySize(annotationOff);
        AnnotationSetItem setItem = new AnnotationSetItem();
        int size = getIntLet(bufferedSource);
        setItem.setSize(size);
        ArrayList<AnnotationOffItem> offItems = new ArrayList<>();
        for(int i =0;i<size;i++){
            AnnotationOffItem  offItem = new AnnotationOffItem();
            int offSize = getIntLet(bufferedSource);
            offItem.setAnnotationOff(offSize);
            if(offSize!=0){
                offItem.setAnnotationItem(getAnnotationItem(offSize));
            }
            offItems.add(offItem);
        }
        setItem.setItems(offItems);
        return setItem;
    }

    /**
     * 获取AnnotationItem Annotation 三级结构
     * @param annotationOff
     * @return
     * @throws IOException
     */
    public static AnnotationItem getAnnotationItem(int annotationOff) throws IOException {
        BufferedSource bufferedSource = getSkipBufferedSourceBySize(annotationOff);
        AnnotationItem setItem = new AnnotationItem();
        //注解的可见性
        byte visibility = bufferedSource.readByte();
        setItem.setVisibility(visibility);
        setItem.setEncodedAnnotation(getEncodedAnnotation(bufferedSource));
        return setItem;
    }

    /**
     * 获取EncodedAnnotation Annotation 四级结构
     * @param bufferedSource
     * @return
     */
    public static EncodedAnnotation getEncodedAnnotation( BufferedSource bufferedSource) throws IOException{
        EncodedAnnotation encodedAnnotation = new EncodedAnnotation();
        //获取注解类型索引
        ArrayList<Byte> typeIdx = Utils.readLeb128(bufferedSource);
        encodedAnnotation.setTypeIdx(Utils.decodeULeb128(typeIdx));
        //注解
        ArrayList<Byte> sizeArray = Utils.readLeb128(bufferedSource);
        int size = Utils.decodeULeb128(sizeArray);
        encodedAnnotation.setSize(size);
        if(size >0){
            ArrayList<AnnotationElement> elements = new ArrayList<>();
            for(int i  = 0;i<size;i++){
                elements.add(getAnnotationElement(bufferedSource));
            }
            encodedAnnotation.setElements(elements);
        }


        return  encodedAnnotation;
    }


    /**
     * 获取AnnotationElement Annotation五级结构
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static AnnotationElement getAnnotationElement( BufferedSource bufferedSource)throws IOException{
        AnnotationElement element = new AnnotationElement();
        //获取注解类型
        ArrayList<Byte> nameIdx = Utils.readLeb128(bufferedSource);
        element.setNameIdx(Utils.decodeULeb128(nameIdx));
        element.setEncodeValue(getEncodedValue(bufferedSource));
        return element;
    }


    /**
     * 注解的值 下面EncodeArray一样
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static EncodedValue getEncodedValue(BufferedSource bufferedSource) throws IOException{
        EncodedValue encodedValue = new EncodedValue();
        byte valueTypeArgs =bufferedSource.readByte();
        encodedValue.setValue_type_args(valueTypeArgs);
        //如果类型是数组的话
        if(encodedValue.getRealValueType()==EncodedValue.EncodedValueArray){
            encodedValue.setEncodedArrays(getEncodedArray(bufferedSource));
        }else {
            byte[] values = new byte[encodedValue.getValueSize()];

            for(int i = 0;i<values.length;i++){
                values[i] = bufferedSource.readByte();
            }
            encodedValue.setValues(values);
        }
        return encodedValue;
    }

    public static EncodedArray getEncodedArray(BufferedSource bufferedSource) throws IOException{
        EncodedArray array = new EncodedArray();
        ArrayList<Byte> sizeArray =Utils.readLeb128(bufferedSource);
        int size = Utils.decodeSLeb128(sizeArray);
        if(size>0){

            ArrayList<EncodedValue> encodedValues = new ArrayList<>();

            for(int i =0;i<size;i++){
                encodedValues .add(getEncodedValue(bufferedSource));
            }
            array.setValues(encodedValues);
        }
        return array;
    }



}
