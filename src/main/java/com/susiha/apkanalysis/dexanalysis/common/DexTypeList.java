package com.susiha.apkanalysis.dexanalysis.common;

import com.susiha.apkanalysis.dexanalysis.Utils;
import java.util.ArrayList;

/**
 * 表示类型的的列表
 * 基本结构
 *  struct DexTypeList {
 *    u4  size;
 *    DexTypeItem list[1];
 *   };
 */
public class DexTypeList {
    //表示Type的个数 4个字节
    private int size;
    private ArrayList<DexTypeItem> dexTypeLists;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<DexTypeItem> getDexTypeLists() {
        return dexTypeLists;
    }

    public void setDexTypeLists(ArrayList<DexTypeItem> dexTypeLists) {
        this.dexTypeLists = dexTypeLists;
    }
}
