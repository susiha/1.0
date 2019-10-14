package com.susiha.apkanalysis.dexanalysis.header;

import com.susiha.apkanalysis.dexanalysis.Utils;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Adler32;

/**
 *
 *  struct header_item{
 * 		ubyte[8] magic;
 * 		unit checksum;
 * 		ubyte[20] siganature;
 * 		uint file_size;
 * 		uint header_size;
 * 		unit endian_tag;
 * 		uint link_size;
 * 		uint link_off;
 * 		uint map_off;
 * 		uint string_ids_size;
 * 		uint string_ids_off;
 * 		uint type_ids_size;
 * 		uint type_ids_off;
 * 		uint proto_ids_size;
 * 		uint proto_ids_off;
 * 	    uint field_ids_size;
 *      uint field_ids_off;
 * 		uint method_ids_size;
 * 		uint method_ids_off;
 * 		uint class_defs_size;
 * 		uint class_defs_off;
 * 		uint data_size;
 * 		uint data_off;
 *     }
 */
public class HeaderByteCode {
    private static ArrayList<HeaderItem> headerStruct = new ArrayList<>();
    static {
        headerStruct.add(new HeaderItem(0, HeaderItem.MAGICNUM,8));
        headerStruct.add(new HeaderItem(1, HeaderItem.CHECKSUM,4));
        headerStruct.add(new HeaderItem(2, HeaderItem.SIGNATURE,20));
        headerStruct.add(new HeaderItem(3, HeaderItem.FILESIZE,4));
        headerStruct.add(new HeaderItem(4, HeaderItem.HEADERSIZE,4));
        headerStruct.add(new HeaderItem(5, HeaderItem.ENDIANTAG,4));
        headerStruct.add(new HeaderItem(6, HeaderItem.LINKSIZE,4));
        headerStruct.add(new HeaderItem(7, HeaderItem.LINKOFF,4));
        headerStruct.add(new HeaderItem(8, HeaderItem.MAPOFF,4));
        headerStruct.add(new HeaderItem(9, HeaderItem.STRINGIDSSIZE,4));
        headerStruct.add(new HeaderItem(10, HeaderItem.STRINGIDSOFF,4));
        headerStruct.add(new HeaderItem(11, HeaderItem.TYPEIDSSIZE,4));
        headerStruct.add(new HeaderItem(12, HeaderItem.TYPEIDSOFF,4));
        headerStruct.add(new HeaderItem(13, HeaderItem.PROTOIDSSIZE,4));
        headerStruct.add(new HeaderItem(14, HeaderItem.PROTOIDSOFF,4));
        headerStruct.add(new HeaderItem(15, HeaderItem.FIELDIDSSIZE,4));
        headerStruct.add(new HeaderItem(16, HeaderItem.FIELDIDSOFF,4));
        headerStruct.add(new HeaderItem(17, HeaderItem.METHODIDSSIZE,4));
        headerStruct.add(new HeaderItem(18, HeaderItem.METHODIDSOFF,4));
        headerStruct.add(new HeaderItem(19, HeaderItem.CLASSDEFSSIZE,4));
        headerStruct.add(new HeaderItem(20, HeaderItem.CLASSDEFSOFF,4));
        headerStruct.add(new HeaderItem(21, HeaderItem.DATASIZE,4));
        headerStruct.add(new HeaderItem(22, HeaderItem.DATAOFF,4));
        /**
         * 虽然是按照顺序添加的,但是也强排序一下保证DexHeaderStruct的绝对顺序，方便后面解析的时候不
         * 会出错，使用的是插入排序算法，在时间复杂度上保证O(N)
         */
        Utils.insertion(headerStruct);
    }


    /**
     * 根据areaName获取Dex字节码结构对应的数值
     * @param areaName 该结构体对应的名称
     * @return 返回ByteString 调用者根据自身结构特点处理
     * @throws IOException
     */
    private static ByteString getDexByteStringByName(String areaName)throws IOException {
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        if(headerStruct.size() == 0){
            throw new IllegalStateException("headerStruct 没有添加值！");
        }

        byte[] bytes = null;
        for(HeaderItem headerItem:headerStruct){
            if(headerItem.getAreaName().equals(areaName)){
                //设置对应的字节大小
                bytes = bufferedSource.readByteArray(headerItem.getSize());
                bufferedSource.readInt();
                //跳出循环
                break;
            }else{
                //如果暂时没有找到，就逐个设置跳过对应的字节大小，这也是为什么在类的初始化的时候先要对列表
                //进行强排序，保证Header结构顺序的正确性
                bufferedSource.skip(headerItem.getSize());
            }
        }

        if(bytes == null){ //表示没有找到对应的名称
            throw new IllegalArgumentException("没有找到名称"+areaName+"!");
        }
        Buffer buffer = new Buffer();
        buffer.write(bytes);
        ByteString byteString = buffer.readByteString();
        return byteString;
    }



    /**
     * 对于魔数可以返回它对应的真实信息
     * @return
     * @throws IOException
     */
    public static String magicNumString() throws IOException {
        String  info =getStoreByteStringByName(HeaderItem.MAGICNUM).utf8();
        Utils.Logger(HeaderItem.MAGICNUM+" info ",info);
        return info;
    }
    /**
     * 根据名称获取真实的值
     * @param areaName
     * @return
     * @throws IOException
     */
    public static int realSizeByName(String areaName) throws IOException{
        ByteString byteString =getStoreByteStringByName(areaName);
        if(byteString == null){
            throw  new IllegalStateException("no areaName was mattched！");
        }
        int result = Utils.reverseInt(byteString);
        Utils.Logger(areaName+" size ",result);
        return result;
    }

    /**
     * 真实的值 反转
     * @return
     * @throws IOException
     */
    public static ByteString realEndianTag()throws IOException{
        ByteString byteString = getStoreByteStringByName(HeaderItem.ENDIANTAG);
        Utils.Logger(HeaderItem.ENDIANTAG+" RealHex", Utils.reverseByteString(byteString).hex());
        return Utils.reverseByteString(byteString);
    }

    /**
     * 真实的值
     * @return
     * @throws IOException
     */
    public static ByteString realCheckSum()throws IOException{
        ByteString byteString = getStoreByteStringByName(HeaderItem.CHECKSUM);
        Utils.Logger(HeaderItem.CHECKSUM+" RealHex", Utils.reverseByteString(byteString).hex());
        return Utils.reverseByteString(byteString);
    }
    /**
     * 根据名称获取在Dex中存储的二进制信息
     * @param areaName
     * @return
     * @throws IOException
     */
    public static ByteString getStoreByteStringByName(String areaName) throws IOException{
        ByteString byteString = getDexByteStringByName(areaName);
        Utils.Logger(areaName+" Hex",byteString.hex());
        return  byteString;
    }
    /**
     * 对除去MagicNum和CheckSum之外的文件区域进行Alder32校验，验证checkSum的值
     * @throws IOException
     */
    private static void adler32()throws IOException {
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(8); //跳过MagicNum
        bufferedSource.skip(4); //跳过CheckSum
        Adler32 adler32 = new Adler32();
        adler32.update(bufferedSource.readByteArray());
        long value = adler32.getValue();
        Buffer buffer = new Buffer();
        buffer.writeIntLe((int) value);
        ByteString byteString = buffer.readByteString();
        Utils.Logger("alder32 checkSum",byteString.hex());

    }


    /**
     * 验证Signature 跳过自身然后进行SHA-1计算 结果却与实际不一致
     * @throws IOException
     */
    private static void sha_1()throws IOException {
        BufferedSource bufferedSource = Utils.getDexBufferedSource();
        bufferedSource.skip(8); //跳过MagicNum
        bufferedSource.skip(4); //跳过CheckSum
        bufferedSource.skip(20); //跳过Signature
        Buffer buffer = new Buffer();
        buffer.write(bufferedSource.readByteArray());
        ByteString byteString = buffer.sha1();
        Utils.Logger("sha_1 signature",byteString.hex());
    }

    public static ArrayList<HeaderItem> getHeaderStruct(){
        return headerStruct;
    }

}
