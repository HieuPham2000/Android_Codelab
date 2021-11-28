package com.example.android.convertradix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Lớp cung cấp 1 số phương thức hỗ trợ
 */
public class Utils {
    public static final String BASE_10 = "Hệ thập phân";
    public static final String BASE_2 = "Hệ nhị phân";
    public static final String BASE_8 = "Hệ 8";
    public static final String BASE_16 = "Hệ 16";
    public static final Map<String, Integer> MAP_RADIX;
    
    static {
        MAP_RADIX = new HashMap<>();
        MAP_RADIX.put(Utils.BASE_10, 10);
        MAP_RADIX.put(Utils.BASE_2, 2);
        MAP_RADIX.put(Utils.BASE_8, 8);
        MAP_RADIX.put(Utils.BASE_16, 16);
    }

    /**
     * Phương thức chuyển đổi xâu giá trị từ hệ cơ số fromBase => hệ cơ số toBase
     * @param str xâu giá trị trong hệ cơ số fromBase
     * @param fromBase hệ cơ số ban đầu
     * @param toBase hệ cơ số muốn chuyển đổi sang
     * @return xâu giá trị trong hệ cơ số toBase
     * Created By: PTHIEU 27.11.2021
     */
    public static String convertFromBaseToBase(String str, int fromBase, int toBase) {
        try {
            return Integer.toString(Integer.parseInt(str, fromBase), toBase);
        } catch (NumberFormatException ex) {
            throw ex;
        }
    }
    
}
