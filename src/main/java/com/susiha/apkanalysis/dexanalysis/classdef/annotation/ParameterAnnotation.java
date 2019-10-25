package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

public class ParameterAnnotation {
    private int methodIdx;
    private int annotationOff;
    private AnnotationSetRefList annotationSetRefList;

    public int getMethodIdx() {
        return methodIdx;
    }

    public void setMethodIdx(int methodIdx) {
        this.methodIdx = methodIdx;
    }

    public int getAnnotationOff() {
        return annotationOff;
    }

    public void setAnnotationOff(int annotationOff) {
        this.annotationOff = annotationOff;
    }

    public AnnotationSetRefList getAnnotationSetRefList() {
        return annotationSetRefList;
    }

    public void setAnnotationSetRefList(AnnotationSetRefList annotationSetRefList) {
        this.annotationSetRefList = annotationSetRefList;
    }


}
