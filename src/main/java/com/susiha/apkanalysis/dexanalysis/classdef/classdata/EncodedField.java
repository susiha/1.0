package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

/**
 * struct DexField {
 *   uleb128 fieldIdx;    // index to a field_id_item
 *   uleb128 accessFlags;
 *   };
 */
public class EncodedField {

    /**
     * 索引偏移量 针对上一个元素的偏移量 通常在列表中出现
     * 列表的第一个元素正常的索引值 后面的就是依次对前一个索引值的偏移量
     */
    private int fieldIdxDiff;
    private int accessFlags;

    public int getFieldIdxDiff() {
        return fieldIdxDiff;
    }

    public void setFieldIdxDiff(int fieldIdxDiff) {
        this.fieldIdxDiff = fieldIdxDiff;
    }
    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }



}
