package com.susiha.apkanalysis.dexanalysis.stringids;

import com.susiha.apkanalysis.dexanalysis.Utils;
import okio.Buffer;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class StringIdsByteCode {

    private static long count =0;//记录已经取得和
    /**
     * 解析String索引区
     * @param stringIdsSize String 个数
     * @param stringIdsOff 首个String 索引
     * @return
     * @throws IOException
     */
    public static ArrayList<StringIdsItem> decodeStringIds(int stringIdsSize,int stringIdsOff)throws IOException{
        BufferedSource bufferedSourceOffIndex = Utils.getDexBufferedSource();
        /**
         * 跳过偏移量 也就是跳过Header区
         */
        bufferedSourceOffIndex.skip(stringIdsOff);
        ArrayList<StringIdsItem> arrayList = new ArrayList<>();

        long startTime;
        long wasteTime;

        for(int i = 0;i<stringIdsSize;i++){
            startTime =System.currentTimeMillis();
            StringIdsItem idsItem = new StringIdsItem();
            byte[] bytes= bufferedSourceOffIndex.readByteArray(4);
            idsItem.setIndex(i);
            //该字符串的偏移量
            int stringDataoff =Utils.reverseInt(bytes);
            idsItem.setStringDataOff(stringDataoff);
            idsItem.setStringItemData(decodeStringItemData(stringDataoff));
            arrayList.add(idsItem);
            wasteTime = System.currentTimeMillis()-startTime;
            Utils.Logger("index "+i+"花费的时间",wasteTime+"");
        }
        return arrayList;
    }


    /**
     * 根据偏移量读取String真是的数据
     * @param stringDataOff
     * @return
     * @throws IOException
     */
    private static StringItemData decodeStringItemData(int stringDataOff) throws IOException{
        // TODO: 2019-10-15  目前看来太耗时 主要耗费时间的是这个方法 待优化耗时问题
        StringItemData stringItemData = new StringItemData();
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        //跳到偏移位置
        bufferedSource.skip(stringDataOff);
        //读ULeb128数据
        ArrayList<Byte> byteList =readUleb128(bufferedSource);
        stringItemData.setOriginalByteArray(byteList);
        stringItemData.setSize(Utils.decodeLeb128(byteList));
        //读取StringData
        stringItemData.setStringData(readStringData(bufferedSource));






        return stringItemData;
    }


    /**
     * 读取ULeb128字节数组
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    private static ArrayList<Byte> readUleb128(BufferedSource bufferedSource) throws IOException{
        ArrayList<Byte> byteList = Utils.readUleb128(bufferedSource);
        return byteList;
    }


    /**
     * 读取真实的String数据
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    private static ArrayList<Byte> readStringData(BufferedSource bufferedSource) throws IOException{
        byte[] bytes =bufferedSource.readByteArray();
        ArrayList<Byte> byteList = new ArrayList<>();
        Buffer buffer = new Buffer();
        buffer.write(bytes);
        //读取一个字节
        byte b = buffer.readByte();
        byteList.add(b);
        //字符串以0x00结尾，如果没有读取到就一直读下去
        while(b != 0){
            b = buffer.readByte();
            byteList.add(b);
        }
        return byteList;
    }







}
