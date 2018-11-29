package com.wdm.blogcode.utils;

/**
 * @author wdmyong
 */
public class FormatUtil {

    public static String format20FixedLength(long value) {
        return String.format("%020d", value);
    }

    public static String format16FixedLength(long value) {
        return String.format("%016d", value);
    }
}
