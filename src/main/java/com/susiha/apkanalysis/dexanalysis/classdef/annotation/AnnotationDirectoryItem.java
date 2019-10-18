package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

/**
 * struct DexAnnotationsDirectoryItem {
 *   u4  classAnnotationsOff;  // offset to DexAnnotationSetItem
 *   u4  fieldsSize;           // count of DexFieldAnnotationsItem
 *   u4  methodsSize;          // count of DexMethodAnnotationsItem
 *   u4  parametersSize;       / count of DexParameterAnnotationsItem
 *   //  followed by DexFieldAnnotationsItem[fieldsSize]
 *   //  followed by DexMethodAnnotationsItem[methodsSize]
 *   //  followed by DexParameterAnnotationsItem[parametersSize]
 *};
 */
public class AnnotationDirectoryItem {
}
