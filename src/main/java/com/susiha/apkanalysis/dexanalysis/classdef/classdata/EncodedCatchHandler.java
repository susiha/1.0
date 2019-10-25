package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

import java.util.ArrayList;

/**
 * 基本结构
 *  struct encoded_catch_handler{
 *      sleb128 size;
 *      encoded_type_addr_pair[abs(size)] handlers;
 *      uleb128 catch_all_addr
 *  }
 *
 */
public class EncodedCatchHandler {

    /**此表中是捕获类型的数量，如果是非正数，则该值是捕获类型数量的负数，
     *  捕获数量后跟一个"全部捕获"处理程序
     *  例如size == 0 表示捕获类型为"全部捕获"，而没有明确类型的捕获
     *  size == 2 表示有两个明确类型的捕获，但没有"全部捕获"类型的捕获
     *  size == -1 表示有一个明确类型的捕获和一个"全部捕获"类型的捕获
     */

    private int size;

    private ArrayList<EncodedTypeAddrPair> handlers;
    /**
     * "全部捕获"处理程序的字节码地址，只有当size为非正数时，此 元素才会存在
     */
    private int catch_all_addr;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<EncodedTypeAddrPair> getHandlers() {
        return handlers;
    }

    public void setHandlers(ArrayList<EncodedTypeAddrPair> handlers) {
        this.handlers = handlers;
    }

    public int getCatch_all_addr() {
        return catch_all_addr;
    }

    public void setCatch_all_addr(int catch_all_addr) {
        this.catch_all_addr = catch_all_addr;
    }
}
