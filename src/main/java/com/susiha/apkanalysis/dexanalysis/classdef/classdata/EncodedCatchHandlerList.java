package com.susiha.apkanalysis.dexanalysis.classdef.classdata;

import java.util.ArrayList;

/**
 * 基本结构
 *  struct encoded_catch_handler_list{
 *      uleb128 size;
 *      encoded_catch_handler[handler_size] list；
 *  }
 */
public class EncodedCatchHandlerList {
    //列表的大小
    private int size;

    //处理程序列表的实际列表
    private ArrayList<EncodedCatchHandler> encodedCatchHandlerList;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<EncodedCatchHandler> getEncodedCatchHandlerList() {
        return encodedCatchHandlerList;
    }

    public void setEncodedCatchHandlerList(ArrayList<EncodedCatchHandler> encodedCatchHandlerList) {
        this.encodedCatchHandlerList = encodedCatchHandlerList;
    }



}
