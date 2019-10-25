package com.susiha.apkanalysis.dexanalysis.common;

import com.susiha.apkanalysis.dexanalysis.Utils;
/**
 * 指向TypeIds的索引
 * 基本结构
 * struct DexTypeItem {
 *   u2  typeIdx;
 *  };
 */
public class DexTypeItem {
    //指向TypeIds的索引 占两个字节
    private short typeIdx;
    public short getTypeIdx() {
        return typeIdx;
    }
    public void setTypeIdx(short typeIdx) {
        this.typeIdx = typeIdx;
    }
}
