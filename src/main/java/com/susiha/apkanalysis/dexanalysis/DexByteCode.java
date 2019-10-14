package com.susiha.apkanalysis.dexanalysis;

import com.susiha.apkanalysis.dexanalysis.header.HeaderByteCode;
import com.susiha.apkanalysis.dexanalysis.header.HeaderItem;

import java.io.IOException;

public class DexByteCode {

    public static  void main(String args[]){
        try {
            for(int i = 0; i< HeaderByteCode.getHeaderStruct().size(); i++){

                String areaName = HeaderByteCode.getHeaderStruct().get(i).getAreaName();

                Utils.Logger("********"+areaName,"********");

                HeaderByteCode.getStoreByteStringByName(areaName);

                //以下四种情况不计算对应的值
                if(areaName != HeaderItem.CHECKSUM&&areaName != HeaderItem.MAGICNUM&&
                        areaName != HeaderItem.SIGNATURE &&areaName != HeaderItem.ENDIANTAG){

                    HeaderByteCode.realSizeByName(areaName);
                }

            }
            Utils.Logger("*******特殊","情况*******");
            HeaderByteCode.magicNumString();
            HeaderByteCode.realEndianTag();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }











}
