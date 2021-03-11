package com.lw.test;

/**
 * 使用单链表简单实现队列
 */
public class LinkedQueue {

    public static void main(String[] args) {
        System.out.println(1%8);
        LinkedQueue queue = new LinkedQueue();
        queue.offer(2);
        queue.offer(5);
        queue.offer(8);
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
    }

    Node head;//理解为虚拟节点
    Node tail;//理解为虚拟节点

    void offer(Integer value) {
        if (value == null) {
            throw new NullPointerException();
        }
        Node node = new Node(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;   //1。
            tail = node;        //2。2必须在1之后
        }
    }

    Integer poll() {
        Node first = head;//先把head节点存到本地变量first
        if (first != null) {
            head = first.next;
            first.next = null;
            return first.value;
        } else {
            return null;
        }
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }


}
