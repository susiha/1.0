package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

import java.util.ArrayList;

/**
 * struct DexAnnotationsDirectoryItem {
 *   u4  classAnnotationsOff;  // offset to DexAnnotationSetItem
 *   u4  fieldsSize;           // count of DexFieldAnnotationsItem
 *   u4  methodsSize;          // count of DexMethodAnnotationsItem
 *   u4  parametersSize;       / count of DexParameterAnnotationsItem
 *   //  followed by DexFieldAnnotationsItem[fieldsSize]
 *   //  followed by DexMethodAnnotationsItem[methodsSize]
 *   //  followed by DexParameterAnnotationsItem[parametersSize]
 *};
 */
public class AnnotationDirectoryItem {
    private int classAnnotationsOff;
    private int fieldsSize;
    private int methodsSize;
    private int paramtersSize;

    private AnnotationSetItem classAnnotation;
    private ArrayList<FieldAnnotationItem> fieldAnnotationItems;
    private ArrayList<MethodAnnotationItem> methodAnnotationItems;
    private ArrayList<ParameterAnnotation> parameterAnnotationItems;


    public int getClassAnnotationsOff() {
        return classAnnotationsOff;
    }

    public void setClassAnnotationsOff(int classAnnotationsOff) {
        this.classAnnotationsOff = classAnnotationsOff;
    }

    public int getFieldsSize() {
        return fieldsSize;
    }

    public void setFieldsSize(int fieldsSize) {
        this.fieldsSize = fieldsSize;
    }

    public int getMethodsSize() {
        return methodsSize;
    }

    public void setMethodsSize(int methodsSize) {
        this.methodsSize = methodsSize;
    }

    public int getParamtersSize() {
        return paramtersSize;
    }

    public void setParamtersSize(int paramtersSize) {
        this.paramtersSize = paramtersSize;
    }

    public AnnotationSetItem getClassAnnotation() {
        return classAnnotation;
    }

    public void setClassAnnotation(AnnotationSetItem classAnnotation) {
        this.classAnnotation = classAnnotation;
    }

    public ArrayList<FieldAnnotationItem> getFieldAnnotationItems() {
        return fieldAnnotationItems;
    }

    public void setFieldAnnotationItems(ArrayList<FieldAnnotationItem> fieldAnnotationItems) {
        this.fieldAnnotationItems = fieldAnnotationItems;
    }

    public ArrayList<MethodAnnotationItem> getMethodAnnotationItems() {
        return methodAnnotationItems;
    }

    public void setMethodAnnotationItems(ArrayList<MethodAnnotationItem> methodAnnotationItems) {
        this.methodAnnotationItems = methodAnnotationItems;
    }

    public ArrayList<ParameterAnnotation> getParameterAnnotationItems() {
        return parameterAnnotationItems;
    }

    public void setParameterAnnotationItems(ArrayList<ParameterAnnotation> parameterAnnotationItems) {
        this.parameterAnnotationItems = parameterAnnotationItems;
    }



}
