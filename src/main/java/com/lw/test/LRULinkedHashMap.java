package com.lw.test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap extends LinkedHashMap {
    //定义缓存的容量
    private int capacity;

    private static final long serialVersionUID = 1L;

    protected LRULinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    //实现LRU的关键方法，如果map里面的元素个数大于了缓存最大容量，则删除链表的顶端元素
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
//        return super.removeEldestEntry(eldest);
        return size() > capacity;
    }

    public static void main(String[] args) {
//        test();

        Map<Integer, Integer> map = new LRULinkedHashMap(4);
        map.put(9, 3);
        map.put(7, 4);
        map.put(5, 9);
        map.put(3, 4);
        map.get(9);
        map.put(2, 2);
        //总共操作了6个元素，超过了指定的缓存最大容量，会淘汰最久远的元素
        //遍历结果
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            System.out.println(integerIntegerEntry);
        }
    }

    public static void test() {
        Map<Integer, Integer> map = new
                LinkedHashMap<>(10, 0.75f, true);
        map.put(9, 3);
        map.put(7, 4);
        map.put(5, 9);
        map.put(3, 4);
        //现在遍历的话顺序肯定是9,7,5,3
        //下面访问了一下9,3这个键值对，输出顺序就变喽~
        map.get(9);
        System.out.println(map);
//        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
//            System.out.println(integerIntegerEntry.getKey());
//        }

    }

}

