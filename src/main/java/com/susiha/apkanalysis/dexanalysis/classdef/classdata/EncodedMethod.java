package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

/**
 * struct DexMethod {
 *    uleb128 methodIdx;    // index to a method_id_item
 *    uleb128 accessFlags;
 *    uleb128 codeOff;      // file offset to a code_item
 *  };
 */
public class EncodedMethod {

    private int methodIdx;
    private int accessFlags;
    private int codeOff;
    private CodeItem codeItem;

    public int getMethodIdx() {
        return methodIdx;
    }

    public void setMethodIdx(int methodIdx) {
        this.methodIdx = methodIdx;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getCodeOff() {
        return codeOff;
    }

    public void setCodeOff(int codeOff) {
        this.codeOff = codeOff;
    }

    public CodeItem getCodeItem() {
        return codeItem;
    }

    public void setCodeItem(CodeItem codeItem) {
        this.codeItem = codeItem;
    }






}
