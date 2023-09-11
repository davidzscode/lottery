package com.qihang.tenant;

/**
 * @author: qihang
 * @description:
 * @time: 2022-11-12 23:24
 */
public class TenantContextHolder {
    /**
     * 是否忽略租户
     */
    private static final ThreadLocal<Boolean> IGNORE = new ThreadLocal<>();

    /**
     * 当前是否忽略租户
     *
     * @return 是否忽略
     */
    public static boolean isIgnore() {
        return Boolean.TRUE.equals(IGNORE.get());
    }

    public static void setIgnore(Boolean ignore) {
        IGNORE.set(ignore);
    }
}
