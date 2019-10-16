package com.susiha.apkanalysis.dexanalysis.protoids;

import com.susiha.apkanalysis.dexanalysis.Utils;

import java.util.ArrayList;

/**
 * 参数的结构Item
 */
public class ProtoParameterItem {
    //参数的个数 4个字节表示
    private byte[] parameterSize;

    public byte[] getParameterSize() {
        return parameterSize;
    }

    public void setParameterSize(byte[] parameterSize) {
        this.parameterSize = parameterSize;
    }

    public ArrayList<ParameterIds> getParameterIds() {
        return parameterIds;
    }

    public void setParameterIds(ArrayList<ParameterIds> parameterIds) {
        this.parameterIds = parameterIds;
    }

    //参数的索引，每连个字节表示一个索引,指向的是TypeIds
    ArrayList<ParameterIds> parameterIds;

    public int getRealParameterSize(){
        return Utils.reverseInt(parameterSize);
    }


}



