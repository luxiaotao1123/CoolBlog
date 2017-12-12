package cn.blog.utils;

/**
 * Created by liuwen on 2017/12/12.
 */
public class R2<K,V> {
    private V k;
    private V v;
    public R2() {
    }

    public R2(V k, V v) {
        this.k = k;
        this.v = v;
    }

    public V getK() {
        return k;
    }

    public void setK(V k) {
        this.k = k;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }
}
