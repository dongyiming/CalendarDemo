package cn.readsense.com.calendardemo


/**
 * @description: <fragment->activity>
 * @author: dongyiming
 * @date: 2018/9/24 23:44
 * @version: v1.0
 */
interface BindActivityCallBack<in T> {

    /**
     * <TODO>
     * @param:ref：0为左边时间
     * @author: dongyiming
     * @date: 2018/9/25 0:13
     * @version: v1.0
     */
    fun call(list: List<T>, ref: Int)
}