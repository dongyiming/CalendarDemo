package cn.readsense.com.calendardemo.service;

import java.util.List;

/**
 * @description:
 * @author: dongyiming
 * @date: 2018/9/28 22:44
 * @version: v1.0
 */
public interface BindActivityCallBack<T> {

    /**
     * <TODO>
     *
     * @param:ref：0为左边时间
     * @author: dongyiming
     * @date: 2018/9/25 0:13
     * @version: v1.0
     */
    void call(List<T> list, int ref);
}
