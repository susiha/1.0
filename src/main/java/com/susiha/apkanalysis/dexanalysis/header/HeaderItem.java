package com.susiha.apkanalysis.dexanalysis.header;

/**
 * HeaderStruct对应的Item
 * struct DexHeader {
 * u1  magic[8];
 * u4 checksum;
 * u1 signature[kSHA1DigestLen]; kSHA1DigestLen = 20
 * u4 fileSize;
 * u4 headerSize;
 * u4 endianTag;
 * u4 linkSize;
 * u4 linkOff;
 * u4 mapOff;
 * u4 stringIdsSize;
 * u4 stringIdsOff;
 * u4 typeIdsSize;
 * u4 typeIdsOff;
 * u4 protoIdsSize;
 * u4 protoIdsOff;
 * u4 fieldIdsSize;
 * u4 fieldIdsOff;
 * u4 methodIdsSize;
 * u4 methodIdsOff;
 * u4 classDefsSize;
 * u4 classDefsOff;
 * u4 dataSize;
 * u4 dataOff;
 * };
 */
public class HeaderItem {

    /**
     * Dex文件魔数，是个固定值 一般为"dex\n035\0" 占8个字节
     */
    public final static String MAGICNUM = "MagicNum";
    /**
     * CheckSum 使用alder32算法校验文件除去 maigc,checksum 外余下的所有文件区域 占4个字节
     */
    public final static String CHECKSUM = "CheckSum";

    /**
     * Signature 使用 SHA-1 算法hash 除去 magic ,checksum 和 signature 外余下的所有文件区域
     * 占20个字节
     */
    public final static String SIGNATURE = "Signature";

    /**
     * FileSize 标明文件的大小 占四个字节
     */
    public final static String FILESIZE = "FileSize";
    /**
     * HeaderSize 标明是头结构体的大小 占四个字节 一般是固定的值112
     */
    public final static String HEADERSIZE = "HeaderSize";
    /**
     * Endian Tag，大小端标记，值为0x12345678，由于Dex是小端存储，所以Dex文件中保存为0x78563412 占四个字节
     */
    public final static String ENDIANTAG = "EndianTag";

    /**
     * 链接数据的大小 占四个字节
     */
    public final static String LINKSIZE = "LinkSize";
    /**
     * 链接数据的偏移量 占四个字节
     */
    public final static String LINKOFF = "LinkOff";
    /**
     * MapItem 偏移地址占四个字节
     */
    public final static String MAPOFF = "MapOff";
    /**
     * 所有用到字符串的大小和偏移量 各占四个字节
     */
    public final static String STRINGIDSSIZE = "StringIdsSize";
    public final static String STRINGIDSOFF = "StringIdsOff";

    /**
     * Dex中类型数据结构的大小和偏移量 各占四个字节
     */
    public final static String TYPEIDSSIZE = "TypeIdsSize";
    public final static String TYPEIDSOFF = "TypeIdsOff";
    /**
     * Dex中元数据信息数据结构的大小和偏移量 各占四个字节
     */
    public final static String PROTOIDSSIZE = "ProtoIdsSize";
    public final static String PROTOIDSOFF = "ProtoIdsOff";
    /**
     * Dex中字段信息数据结构的大小和偏移量 各占四个字节
     */
    public final static String FIELDIDSSIZE = "FieldIdsSize";
    public final static String FIELDIDSOFF = "FieldIdsOff";
    /**
     * Dex中方法信息数据结构的大小和偏移量 各占四个字节
     */
    public final static String METHODIDSSIZE = "MethodIdsSize";
    public final static String METHODIDSOFF = "MethodIdsOff";

    /**
     * Dex 中类信息数据结构的大小和偏移量 各占四个字节
     */
    public final static String CLASSDEFSSIZE = "ClassDefsSize";
    public final static String CLASSDEFSOFF = "ClassDefsOff";

    /**
     * Dex 中数据区域的大小和偏移量 各占四个字节
     */
    public final static String DATASIZE = "DataSize";
    public final static String DATAOFF = "DataOff";


    public HeaderItem(int index, String areaName, long size) {
        this.index = index;
        this.areaName = areaName;
        this.size = size;
    }

    private int index; //索引值

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private String areaName;//区域名称
    private long size; //占用字节大小
}
