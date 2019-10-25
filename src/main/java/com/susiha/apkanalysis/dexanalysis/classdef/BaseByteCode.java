package com.susiha.apkanalysis.dexanalysis.classdef;

import com.susiha.apkanalysis.dexanalysis.Utils;
import okio.BufferedSource;

import java.io.IOException;
import java.util.ArrayList;

public class BaseByteCode {
    /**
     * 获取Int值
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static int getIntLet(BufferedSource bufferedSource) throws IOException{
        byte[] idx  = bufferedSource.readByteArray(4);
        int idxValue = Utils.reverseInt(idx);
        return idxValue;
    }

    /**
     * 获取short 值
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static short getShortLet(BufferedSource bufferedSource) throws IOException{
        byte[] idx  = bufferedSource.readByteArray(2);
        short idxValue = (short) Utils.reverseInt(idx);
        return idxValue;
    }


    /**
     * 获取Uleb128的数值
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static int getULebanese128(BufferedSource bufferedSource) throws IOException{
      ArrayList<Byte> bytes = Utils.readLeb128(bufferedSource);
      int result = Utils.decodeULeb128(bytes);
      return result;
    }


    /**
     * 获取SLeb128的数值
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static int getSLebanese128(BufferedSource bufferedSource) throws IOException{
        ArrayList<Byte> bytes = Utils.readLeb128(bufferedSource);
        int result = Utils.decodeSLeb128(bytes);
        return result;
    }


    /**
     * 获取Uleb128P1的数值
     * @param bufferedSource
     * @return
     * @throws IOException
     */
    public static int getLebanese128P1(BufferedSource bufferedSource) throws IOException{
        ArrayList<Byte> bytes = Utils.readLeb128(bufferedSource);
        int result = Utils.decodeULeb128P1(bytes);
        return result;
    }



}
