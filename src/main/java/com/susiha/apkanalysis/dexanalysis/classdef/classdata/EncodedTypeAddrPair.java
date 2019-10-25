package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

/**
 * 基本结构
 * struct encoded_type_addr_pair{
 *     uleb128 type_idx;
 *     uleb128 addr;
 * }
 *
 */
public class EncodedTypeAddrPair {

    public int getTypeIdx() {
        return typeIdx;
    }

    public void setTypeIdx(int typeIdx) {
        this.typeIdx = typeIdx;
    }

    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    private int typeIdx; //要捕获的异常类型在TypeIds列表中的索引
    private int addr; //关联的处理异常程序的字节码地址
}
