package com.susiha.apkanalysis.dexanalysis.classdef.annotation;


import java.util.ArrayList;

/**
 * struct DexAnnotationSetItem {
 *    u4  size;
 *    u4  entries[1];                 // offset to DexAnnotationItem
 *    };
 */
public class AnnotationSetItem {
    private int size;
    private ArrayList<AnnotationOffItem> items;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<AnnotationOffItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<AnnotationOffItem> items) {
        this.items = items;
    }



}
