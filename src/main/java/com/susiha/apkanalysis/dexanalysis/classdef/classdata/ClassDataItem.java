package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

import java.util.ArrayList;

/**
 * struct DexClassDataHeader {
 *     uleb128 staticFieldsSize;
 *     uleb128 instanceFieldsSize;
 *     uleb128 directMethodsSize;
 *     uleb128 virtualMethodsSize;
 * };
 */
public class ClassDataItem {
    private int staticFieldsSize;
    private int instanceFieldsSize;
    private int directMethodsSize;
    private int virtualMethodsSize;
    private ArrayList<EncodedField> staticFields;
    private ArrayList<EncodedField> instanceFields;
    private ArrayList<EncodedMethod> directMethods;
    private ArrayList<EncodedMethod> virtualMethods;

    public int getStaticFieldsSize() {
        return staticFieldsSize;
    }

    public void setStaticFieldsSize(int staticFieldsSize) {
        this.staticFieldsSize = staticFieldsSize;
    }

    public int getInstanceFieldsSize() {
        return instanceFieldsSize;
    }

    public void setInstanceFieldsSize(int instanceFieldsSize) {
        this.instanceFieldsSize = instanceFieldsSize;
    }

    public int getDirectMethodsSize() {
        return directMethodsSize;
    }

    public void setDirectMethodsSize(int directMethodsSize) {
        this.directMethodsSize = directMethodsSize;
    }

    public int getVirtualMethodsSize() {
        return virtualMethodsSize;
    }

    public void setVirtualMethodsSize(int virtualMethodsSize) {
        this.virtualMethodsSize = virtualMethodsSize;
    }

    public ArrayList<EncodedField> getStaticFields() {
        return staticFields;
    }

    public void setStaticFields(ArrayList<EncodedField> staticFields) {
        this.staticFields = staticFields;
    }

    public ArrayList<EncodedField> getInstanceFields() {
        return instanceFields;
    }

    public void setInstanceFields(ArrayList<EncodedField> instanceFields) {
        this.instanceFields = instanceFields;
    }

    public ArrayList<EncodedMethod> getDirectMethods() {
        return directMethods;
    }

    public void setDirectMethods(ArrayList<EncodedMethod> directMethods) {
        this.directMethods = directMethods;
    }

    public ArrayList<EncodedMethod> getVirtualMethods() {
        return virtualMethods;
    }

    public void setVirtualMethods(ArrayList<EncodedMethod> virtualMethods) {
        this.virtualMethods = virtualMethods;
    }




}
