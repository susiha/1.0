package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

/**
 * struct DexAnnotationSetRefItem {
 *    u4  annotationsOff;             // offset to DexAnnotationSetItem
 * };
 */
public class AnnotationSetRefItem {
    private int annotationsOff;

    public int getAnnotationsOff() {
        return annotationsOff;
    }

    public void setAnnotationsOff(int annotationsOff) {
        this.annotationsOff = annotationsOff;
    }

    public AnnotationSetItem getParameterItem() {
        return parameterItem;
    }

    public void setParameterItem(AnnotationSetItem parameterItem) {
        this.parameterItem = parameterItem;
    }

    private AnnotationSetItem parameterItem;


}
