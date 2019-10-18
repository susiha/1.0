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
    public static final int DEXTYPE_IDX_SIZE =2;
    //指向TypeIds的索引 占两个字节
    private byte[] typeIdx;

    public byte[] getTypeIdx() {
        return typeIdx;
    }

    public void setTypeIdx(byte[] typeIdx) {
        this.typeIdx = typeIdx;
    }
    //获取真实的int值
    public int getRealTypeIdx(){

        return Utils.reverseInt(typeIdx);
    }
}
