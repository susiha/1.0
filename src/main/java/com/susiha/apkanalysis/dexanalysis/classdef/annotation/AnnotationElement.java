package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

import com.susiha.apkanalysis.dexanalysis.common.EncodedValue;

/**
 * struct AnnotationElement {
 *     uleb128  name_idx; 名字索引
 *     encode_value
 *   };
 */
public class AnnotationElement {
    private int nameIdx;
    private EncodedValue encodeValue;

    public int getNameIdx() {
        return nameIdx;
    }

    public void setNameIdx(int nameIdx) {
        this.nameIdx = nameIdx;
    }

    public EncodedValue getEncodeValue() {
        return encodeValue;
    }

    public void setEncodeValue(EncodedValue encodeValue) {
        this.encodeValue = encodeValue;
    }
}
