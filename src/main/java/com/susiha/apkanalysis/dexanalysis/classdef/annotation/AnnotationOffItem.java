package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

import com.susiha.apkanalysis.dexanalysis.Utils;

/**
 * struct DexAnnotationOffItem {
 *     u4  annotation_off;
 *     AnnotationItem  item;              // data in encoded_annotation format
 *   };
 */
public class AnnotationOffItem {
    public static final int ANNOTATION_OFF_SIZE = 4;
    //表示偏移量 占四个字节
    private byte[] annotationOff;
    private AnnotationItem annotationItem;
    public byte[] getAnnotationOff() {
        return annotationOff;
    }

    public void setAnnotationOff(byte[] annotationOff) {
        this.annotationOff = annotationOff;
    }

    public AnnotationItem getAnnotationItem() {
        return annotationItem;
    }

    public void setAnnotationItem(AnnotationItem annotationItem) {
        this.annotationItem = annotationItem;
    }

    //获取真实的偏移量值
    public int getRealAnnotationOffValue(){
        return Utils.reverseInt(annotationOff);
    }





}
