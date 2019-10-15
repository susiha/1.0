package com.susiha.apkanalysis.dexanalysis.stringids;

import java.util.ArrayList;

public class StringItemData {

    //表示String的字符个数
    private int size;
    //表示原始的字节列表
    private ArrayList<Byte> originalByteArray;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Byte> getOriginalByteArray() {
        return originalByteArray;
    }

    public void setOriginalByteArray(ArrayList<Byte> originalByteArray) {
        this.originalByteArray = originalByteArray;
    }

    public ArrayList<Byte> getStringData() {
        return stringData;
    }

    public void setStringData(ArrayList<Byte> stringData) {
        this.stringData = stringData;
    }

    /**
     * String 数据 是一个Utf-8编码的字符串 这里存储的是字节数 上面的size 表示的是
     * String 数据包含的字符个数并不是字节个数，因为有时候一个字符可能占用多个字节,字符以
     * "/0"结束也就是字节的0x00
     */
    private ArrayList<Byte> stringData;

}
