package com.susiha.apkanalysis.dexanalysis.classdef.annotation;


/**
 * struct DexAnnotationOffItem {
 *     u4  annotation_off;
 *     AnnotationItem  item;              // data in encoded_annotation format
 *   };
 */
public class AnnotationOffItem {
    //表示偏移量 占四个字节
    private int annotationOff;
    private AnnotationItem annotationItem;
    public int getAnnotationOff() {
        return annotationOff;
    }

    public void setAnnotationOff(int annotationOff) {
        this.annotationOff = annotationOff;
    }

    public AnnotationItem getAnnotationItem() {
        return annotationItem;
    }

    public void setAnnotationItem(AnnotationItem annotationItem) {
        this.annotationItem = annotationItem;
    }

}
