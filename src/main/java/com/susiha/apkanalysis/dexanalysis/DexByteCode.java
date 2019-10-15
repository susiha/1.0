package com.susiha.apkanalysis.dexanalysis;

import com.susiha.apkanalysis.dexanalysis.header.HeaderByteCode;
import com.susiha.apkanalysis.dexanalysis.header.HeaderItem;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsByteCode;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;

import java.io.IOException;
import java.util.ArrayList;

public class DexByteCode {

    public static  void main(String args[]){
        try {
//            readHeader();
            readStringIdsItem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void readHeader() throws IOException{
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

    }



    private static void readStringIdsItem() throws IOException{

        int stringIdsSize = HeaderByteCode.realSizeByName(HeaderItem.STRINGIDSSIZE);
        int stringIdsOff = HeaderByteCode.realSizeByName(HeaderItem.STRINGIDSOFF);
        long startTime = System.currentTimeMillis();
//        int count = 0;
//        BufferedSource bufferedSource = Utils.getDexBufferedSource();
//        for(int i= 0;i<stringIdsSize;i++){
//            StringIdsItem idsItem = new StringIdsItem();
//            idsItem.setIndex(i);
//            byte[] bytes =bufferedSource.readByteArray(4);
//            int stringDataoff =Utils.reverseInt(bytes);
//            idsItem.setStringDataOff(stringDataoff);
//
//        }


        ArrayList<StringIdsItem> idsItems = StringIdsByteCode.decodeStringIds(stringIdsSize,stringIdsOff);
        Utils.Logger("总共花费时间 ",(System.currentTimeMillis()-startTime)+"");
        Utils.Logger("StringIdsItemSize ",idsItems.size());
        Utils.Logger("*******分割","一下*******");

        for(StringIdsItem item:idsItems){
            Utils.Logger("********",item.toString());
        }


    }


}
