package com.susiha.apkanalysis.dexanalysis;

import com.susiha.apkanalysis.dexanalysis.classdef.ClassDefByteCode;
import com.susiha.apkanalysis.dexanalysis.classdef.ClassDefItem;
import com.susiha.apkanalysis.dexanalysis.fieldids.FieldIdsItem;
import com.susiha.apkanalysis.dexanalysis.fieldids.FieldIdsByteCode;
import com.susiha.apkanalysis.dexanalysis.header.HeaderByteCode;
import com.susiha.apkanalysis.dexanalysis.header.HeaderItem;
import com.susiha.apkanalysis.dexanalysis.methodids.MethodIdsByteCode;
import com.susiha.apkanalysis.dexanalysis.methodids.MethodIdsItem;
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
            ArrayList<TypeIdsItem> typeIdsItems =readTypeIdsItem(stringIdsItems);

           ArrayList<ProtoIdsItem> protoIdsItem =readProtoIdsItem(stringIdsItems,typeIdsItems);
            ArrayList<FieldIdsItem> fieldIdsItems =readFieldIdsItem(stringIdsItems,typeIdsItems);
            ArrayList<MethodIdsItem> methodIdsItems =readMethodIdsItem(stringIdsItems,typeIdsItems,protoIdsItem);
            readClassDefData(stringIdsItems,typeIdsItems,methodIdsItems,fieldIdsItems);

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



    private static ArrayList<TypeIdsItem> readTypeIdsItem(ArrayList<StringIdsItem> stringIdsItems) throws IOException{
        int typeIdsSize = HeaderByteCode.realSizeByName(HeaderItem.TYPEIDSSIZE);
        int typeIdsOff = HeaderByteCode.realSizeByName(HeaderItem.TYPEIDSOFF);
        ArrayList<TypeIdsItem> typeIdsItems =TypeIdsByteCode.decodeTypeIds(stringIdsItems,typeIdsSize,typeIdsOff);
        return typeIdsItems;
    }

    private static ArrayList<ProtoIdsItem> readProtoIdsItem(ArrayList<StringIdsItem> stringIdsItems,ArrayList<TypeIdsItem> typeIdsItems) throws IOException{
        int protoIdsSize = HeaderByteCode.realSizeByName(HeaderItem.PROTOIDSSIZE);
        int protoIdsOff = HeaderByteCode.realSizeByName(HeaderItem.PROTOIDSOFF);
        ArrayList<ProtoIdsItem> protoIdsItems = ProtoIdsByteCode.decodeProtoIds(stringIdsItems,typeIdsItems,protoIdsSize,protoIdsOff);
        int count = 0;
        for(ProtoIdsItem idsItem:protoIdsItems){

            Utils.Logger("proto index "+count++,idsItem.toString());
        }
        return protoIdsItems;
    }

    private static ArrayList<FieldIdsItem> readFieldIdsItem(ArrayList<StringIdsItem> stringIdsItems, ArrayList<TypeIdsItem> typeIdsItems) throws IOException{
        int fieldIdsSize = HeaderByteCode.realSizeByName(HeaderItem.FIELDIDSSIZE);
        int fieldIdsOff = HeaderByteCode.realSizeByName(HeaderItem.FIELDIDSOFF);
        ArrayList<FieldIdsItem> fieldIdsItems = FieldIdsByteCode.decodeFieldIds(stringIdsItems,typeIdsItems,fieldIdsSize,fieldIdsOff);
        int count = 0;
        for(FieldIdsItem idsItem:fieldIdsItems){

            Utils.Logger("field index "+count++,idsItem.toString());
        }
        return fieldIdsItems;
    }


    private static ArrayList<MethodIdsItem> readMethodIdsItem(ArrayList<StringIdsItem> stringIdsItems, ArrayList<TypeIdsItem> typeIdsItems,ArrayList<ProtoIdsItem> protoIdsItems) throws IOException{
        int methodIdsSize = HeaderByteCode.realSizeByName(HeaderItem.METHODIDSSIZE);
        int methodIdsOff = HeaderByteCode.realSizeByName(HeaderItem.METHODIDSOFF);
        ArrayList<MethodIdsItem> methodIdsItems = MethodIdsByteCode.decodeMethodIdsItem(stringIdsItems,typeIdsItems,protoIdsItems,methodIdsSize,methodIdsOff);
        int count = 0;
        for(MethodIdsItem idsItem:methodIdsItems){

            Utils.Logger("method index "+count++,idsItem.toString());
        }
        return methodIdsItems;
    }


    private static ArrayList<ClassDefItem> readClassDefData(ArrayList<StringIdsItem> stringIdsItems, ArrayList<TypeIdsItem> typeIdsItems,
                                                            ArrayList<MethodIdsItem> methodIdsItems,ArrayList<FieldIdsItem> fieldIdsItems) throws IOException{
        int classDefSize = HeaderByteCode.realSizeByName(HeaderItem.CLASSDEFSSIZE);
        int classDefOff = HeaderByteCode.realSizeByName(HeaderItem.CLASSDEFSOFF);
        ArrayList<ClassDefItem> classDefItems = ClassDefByteCode.decodeClassDef(stringIdsItems,typeIdsItems,methodIdsItems,fieldIdsItems,classDefSize,classDefOff);

        for(int i =0;i<classDefItems.size();i++){
            if(classDefItems.get(i).getStaticValuesOff()!=0){
                Utils.Logger("Index ",i);
                Utils.Logger(classDefItems.get(i).toString());
            }
        }
        return classDefItems;
    }











}
