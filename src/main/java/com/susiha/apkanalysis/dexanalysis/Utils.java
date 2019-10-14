package com.susiha.apkanalysis.dexanalysis;

import com.susiha.apkanalysis.dexanalysis.header.HeaderItem;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Utils {

    public static  boolean showLog = true;

    public static final File DEX = new File("/Users/yrd/Desktop/010EditorTemple/MyApplication1/classes.dex");

    /**
     * 读取Dex文件
     * @return
     * @throws IOException
     */
    public static BufferedSource getDexBufferedSource() throws IOException {
        return Okio.buffer(Okio.source(Utils.DEX));
    }
    /**
     * 插入排序算法
     */
    public static void insertion(ArrayList<HeaderItem> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j > 0 && list.get(j).getIndex() < list.get(j - 1).getIndex(); j--) {
                Collections.swap(list, j, j - 1);
            }
        }
    }


    public static void Logger(String tag,String msg){
        if(showLog){
            System.out.println(tag+" = "+msg);
        }
    }
    public static void Logger(String tag,int msg){
        if(showLog){
            System.out.println(tag+" = "+msg);
        }
    }

    /**
     * 反转ByteString
     * @param byteString
     * @return
     */
    public static ByteString reverseByteString(ByteString byteString) {
        byte[] bytes =new byte[byteString.size()];
        //反转ByteString
        for(int i = 0;i<bytes.length;i++){
            bytes[i] =byteString.getByte(byteString.size()-1-i);
        }
        return new ByteString(bytes);
    }

    /**
     * Dex 是小端存储，如果涉及到值的问题，需要进行反转 再求值
     * @param byteString
     * @return
     *
     */
    public static int reverseInt(ByteString byteString) {

        ByteString reverseByteString = reverseByteString(byteString);
        byte[] bytes = reverseByteString.toByteArray();
        /**
         * Byte数组转Int值
         */
        int value =0;
        for (int i = 0; i < bytes.length; i++) {
            int shift = (bytes.length - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

}
