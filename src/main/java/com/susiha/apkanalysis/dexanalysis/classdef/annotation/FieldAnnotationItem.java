package com.susiha.apkanalysis.dexanalysis.classdef.annotation;
/**
 * struct DexFieldAnnotationsItem {
 *   u4  fieldIdx;
 *   u4  annotationsOff;             // offset to DexAnnotationSetItem
 * };
 */
public class FieldAnnotationItem {

    public int getFieldIds() {
        return fieldIds;
    }

    public void setFieldIds(int fieldIds) {
        this.fieldIds = fieldIds;
    }

    public int getAnnotationsOff() {
        return annotationsOff;
    }

    public void setAnnotationsOff(int annotationsOff) {
        this.annotationsOff = annotationsOff;
    }

    public AnnotationSetItem getFileAnnotationsItem() {
        return fileAnnotationsItem;
    }

    public void setFileAnnotationsItem(AnnotationSetItem fileAnnotationsItem) {
        this.fileAnnotationsItem = fileAnnotationsItem;
    }

    private int fieldIds;
    private int annotationsOff;
    private AnnotationSetItem fileAnnotationsItem;

}
