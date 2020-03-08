package com.bing.stumanage.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO相关工具类
 * @author zhaobing
 * @date 2020/1/26
 */
public class IOUtils {

	/**
	 * 关闭对象，连接
	 * @param closeable
	 */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}
