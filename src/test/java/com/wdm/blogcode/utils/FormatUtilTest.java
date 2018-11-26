package com.wdm.blogcode.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author wdmyong
 */
public class FormatUtilTest {

    @Test
    public void test() {
        assertTrue(StringUtils.equals("00000000000000000001", FormatUtil.format20FixedLength(1L)));
        assertTrue(StringUtils.equals("00000000000000012345", FormatUtil.format20FixedLength(12345L)));
        assertTrue(StringUtils.equals("00000000000012345600", FormatUtil.format20FixedLength(12345600L)));
        assertTrue(StringUtils.equals("00001200000000000000", FormatUtil.format20FixedLength(1200000000000000L)));
        assertTrue(StringUtils.equals("09223372036854775807", FormatUtil.format20FixedLength(9223372036854775807L)));
    }
}
