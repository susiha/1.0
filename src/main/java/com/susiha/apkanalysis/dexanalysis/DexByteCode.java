package com.susiha.apkanalysis.dexanalysis;

import com.susiha.apkanalysis.dexanalysis.header.HeaderByteCode;
import com.susiha.apkanalysis.dexanalysis.header.HeaderItem;
import com.susiha.apkanalysis.dexanalysis.protoids.ProtoIdsByteCode;
import com.susiha.apkanalysis.dexanalysis.protoids.ProtoIdsItem;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsByteCode;
import com.susiha.apkanalysis.dexanalysis.stringids.StringIdsItem;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsByteCode;
import com.susiha.apkanalysis.dexanalysis.typeIds.TypeIdsItem;

import java.io.IOException;
import java.util.ArrayList;

public class DexByteCode {

    public static  void main(String args[]){
        try {
//            readHeader();
//            readStringIdsItem();
            ArrayList<StringIdsItem> stringIdsItems = readStringIdsItem();
            ArrayList<TypeIdsItem> typeIdsItems =readTypeIdsIttem(stringIdsItems);

            readProtoIdsTem(stringIdsItems,typeIdsItems);

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
        HeaderByteCode.magicNumString();
        HeaderByteCode.realEndianTag();

    }


    /**
     * 这个读起来太耗费时间了
     * @return
     * @throws IOException
     */
    private static ArrayList<StringIdsItem> readStringIdsItem() throws IOException{
        int stringIdsSize = HeaderByteCode.realSizeByName(HeaderItem.STRINGIDSSIZE);
        int stringIdsOff = HeaderByteCode.realSizeByName(HeaderItem.STRINGIDSOFF);
        ArrayList<StringIdsItem> idsItems = StringIdsByteCode.decodeStringIds(stringIdsSize,stringIdsOff);
        return idsItems;
    }



    private static ArrayList<TypeIdsItem> readTypeIdsIttem(ArrayList<StringIdsItem> stringIdsItems) throws IOException{
        int typeIdsSize = HeaderByteCode.realSizeByName(HeaderItem.TYPEIDSSIZE);
        int typeIdsOff = HeaderByteCode.realSizeByName(HeaderItem.TYPEIDSOFF);
        ArrayList<TypeIdsItem> typeIdsItems =TypeIdsByteCode.decodeTypeIds(stringIdsItems,typeIdsSize,typeIdsOff);
        return typeIdsItems;
    }

    private static ArrayList<ProtoIdsItem> readProtoIdsTem(ArrayList<StringIdsItem> stringIdsItems,ArrayList<TypeIdsItem> typeIdsItems) throws IOException{
        int protoIdsSize = HeaderByteCode.realSizeByName(HeaderItem.PROTOIDSSIZE);
        int protoIdsOff = HeaderByteCode.realSizeByName(HeaderItem.PROTOIDSOFF);
        ArrayList<ProtoIdsItem> protoIdsItems = ProtoIdsByteCode.decodeProtoIds(stringIdsItems,typeIdsItems,protoIdsSize,protoIdsOff);
        int count = 0;
        for(ProtoIdsItem idsItem:protoIdsItems){

            Utils.Logger("proto index "+count++,idsItem.toString());
        }


        return protoIdsItems;
    }







}
