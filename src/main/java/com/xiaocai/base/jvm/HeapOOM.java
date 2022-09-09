package com.xiaocai.base.jvm;

/**
 * @description:
 * @author: xiaocai
 * @time: 2022/2/9 10:21
 */

import java.util.ArrayList;
import java.util.List;

/*** VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError * @author zzm */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }

    }
}


