package com.susiha.apkanalysis.dexanalysis.common;
import java.util.ArrayList;

/**
 * 就是EncodedValue的列表
 * 基本结构
 * EncodedArray{
 *     uleb128 size;
 *     encoded_value[size]	values
 * }
 */
public class EncodedArray {

   private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<EncodedValue> getValues() {
        return values;
    }

    public void setValues(ArrayList<EncodedValue> values) {
        this.values = values;
    }

    private ArrayList<EncodedValue> values;

}
