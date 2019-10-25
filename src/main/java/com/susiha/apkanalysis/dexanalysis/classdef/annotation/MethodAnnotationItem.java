package com.susiha.apkanalysis.dexanalysis.classdef.annotation;
/**
 * struct DexFieldAnnotationsItem {
 *   u4  methodIdx;
 *   u4  annotationsOff;             // offset to DexAnnotationSetItem
 * };
 */
public class MethodAnnotationItem {

    private int  methodIdx;
    private int annotationsOff;
    private AnnotationSetItem methodAnnotationsItem;

    public int getMethodIdx() {
        return methodIdx;
    }

    public void setMethodIdx(int methodIdx) {
        this.methodIdx = methodIdx;
    }

    public int getAnnotationsOff() {
        return annotationsOff;
    }

    public void setAnnotationsOff(int annotationsOff) {
        this.annotationsOff = annotationsOff;
    }

    public AnnotationSetItem getMethodAnnotationsItem() {
        return methodAnnotationsItem;
    }

    public void setMethodAnnotationsItem(AnnotationSetItem methodAnnotationsItem) {
        this.methodAnnotationsItem = methodAnnotationsItem;
    }

}
