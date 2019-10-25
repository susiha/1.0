package com.susiha.apkanalysis.dexanalysis.classdef.annotation;


import java.util.ArrayList;

/**
 * struct EncodedAnnotation {
 *     uleb128  type_idx; 类型索引
 *     uleb128  size; 有多少
 *     entries[]  AnnotationElement列表
 *   };
 */
public class EncodedAnnotation {

    private int typeIdx;
    private int size;
    private ArrayList<AnnotationElement> elements;

    public int getTypeIdx() {
        return typeIdx;
    }

    public void setTypeIdx(int typeIdx) {
        this.typeIdx = typeIdx;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<AnnotationElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<AnnotationElement> elements) {
        this.elements = elements;
    }


}
