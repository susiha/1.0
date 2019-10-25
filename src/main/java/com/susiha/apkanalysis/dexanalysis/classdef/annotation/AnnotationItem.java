package com.susiha.apkanalysis.dexanalysis.classdef.annotation;

/**
 * struct DexAnnotationItem {
 *     u1  visibility;
 *     u1  annotation[1];              // data in encoded_annotation format
 *   };
 */
public class AnnotationItem {

    private byte visibility;

    public byte getVisibility() {
        return visibility;
    }

    public void setVisibility(byte visibility) {
        this.visibility = visibility;
    }

    public EncodedAnnotation getEncodedAnnotation() {
        return encodedAnnotation;
    }

    public void setEncodedAnnotation(EncodedAnnotation encodedAnnotation) {
        this.encodedAnnotation = encodedAnnotation;
    }

    private EncodedAnnotation encodedAnnotation;

}
