package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

import java.util.ArrayList;

public class AnnotationSetRefList {
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<AnnotationSetRefItem> getAnnotationSetRefItems() {
        return annotationSetRefItems;
    }

    public void setAnnotationSetRefItems(ArrayList<AnnotationSetRefItem> annotationSetRefItems) {
        this.annotationSetRefItems = annotationSetRefItems;
    }

    private ArrayList<AnnotationSetRefItem> annotationSetRefItems;


}
