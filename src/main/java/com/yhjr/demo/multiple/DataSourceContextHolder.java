package com.yhjr.demo.multiple;

/**
 * 本地线程全局变量，用来存放当前操作是读还是写
 * 
 * @Author  LiuBao
 * @Version 2.0
 *   2017年4月27日
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读可能是多个库
     */
    public static void read() {
        local.set(DataSourceType.READ.getType());
    }

    /**
     * 写只有一个库
     */
    public static void write() {
        local.set(DataSourceType.WRITE.getType());
    }

    /**
     * 这里需要注意的时，每次我们返回当前数据源的值得时候都需要移除ThreadLocal的值,
     *  这是为了避免同一线程上一次方法调用对之后调用的影响
     */
    public static void remove() {
        local.remove();
    }
    
    public static String getJdbcType() {
        return local.get();
    }
}
