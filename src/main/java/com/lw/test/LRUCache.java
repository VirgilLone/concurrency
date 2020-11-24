package com.lw.test;

import java.util.HashMap;

public class LRUCache<K,V>  {

    private final int MAX_CACHE_SIZE;
    private Entry first; //头元素
    private Entry last;//尾元素

    private HashMap<K,Entry<K, V>> hashMap;

    /**
     * 初始化 缓存大小和对象
     * @param cacheSize
     */
    public LRUCache(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new HashMap<K, Entry<K, V>>();
    }

    public static void main(String[] args) {
        LRUCache<Integer,Integer> lruCache1 = new LRUCache<>(4);
        lruCache1.put(9,9);
        lruCache1.put(7,7);
        lruCache1.put(5,5);
        lruCache1.put(3,3);
//        lruCache1.get(5);
        lruCache1.put(2,2);
        System.out.println(lruCache1.toString());

    }

    /**
     * 存入k,v
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        //1首先判断是否存在该值
        Entry entry = getEntry(key);
        //如果不存在
        if (entry == null) {
            if (hashMap.size() >= MAX_CACHE_SIZE) {
                // 节点数据达到最大容量时
                hashMap.remove(last.key);
                removeLast();
            }
            entry = new Entry();
            entry.key = key;
        }
        entry.value = value;
        //然后把该节点移动到头
        moveToFirst(entry);
        hashMap.put(key, entry);
    }
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) return null;
        moveToFirst(entry);
        return entry.value;
    }

    public void remove(K key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            // 下面两行把当前Entry在链表中移除
            if (entry.pre != null) entry.pre.next = entry.next;
            if (entry.next != null) entry.next.pre = entry.pre;

            // 如果当前Entry是头节点，则把下一个节点赋为头节点
            if (entry == first) first = entry.next;
            // 如果当前Entry是尾节点，则把上一个节点赋为尾节点
            if (entry == last) last = entry.pre;
        }
        hashMap.remove(key);
    }

    /**
     * 将元素移到first
     * @param entry
     */
    private void moveToFirst(Entry entry) {
        if (entry == first) return;// 元素已经在first位置了
        if (entry.pre != null) // 如果该节点前面有节点，则把它前一个节点的next指向它后面的一个节点
            entry.pre.next = entry.next;
        if (entry.next != null) // 如果该节点后面有节点，则把它后一个节点的pre指向它前面的一个节点
            entry.next.pre = entry.pre;
        if (entry == last) last = last.pre;//元素在last位置

        if (first == null || last == null) {// 只有当前一个元素
            first = last = entry;
            return;
        }

        // entry对象成为链表新的头
        entry.next = first;
        first.pre = entry;

        first = entry;// 赋值到first成员变量，并指定pre为null
        entry.pre = null;
    }

    /**
     * 删除最后一个元素
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre; // last元素被删除,last的前一个元素作为最后一个元素
            if (last == null)  // 如果只有一个元素的情况下，(此时first=last)
                first = null;
            else last.next = null;//last后继为null
        }
    }

    /**
     * 判断map是否存在该key,value
     * @param key
     * @return
     */
    private Entry<K, V> getEntry(K key) {
        return hashMap.get(key);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Entry entry = first;
        while (entry != null) {
            sb.append(String.format("%s=%s ", entry.key, entry.value));
            entry = entry.next;
        }
        return sb.toString();
    }





    class Entry<K, V> {
        Entry pre;
        Entry next;
        K key;
        V value;
    }

}
