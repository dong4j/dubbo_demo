package com.dong4j.provider.common;

/**
 * Created by Code.Ai on 16/7/23.
 * Description:循环链表实现
 */
public class KeyRecycleLink {
    class Element {
        public  Object  value = null;
        private Element next  = null;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    private Element header = null;//头结点

    public KeyRecycleLink() {
        initList();
    }

    public KeyRecycleLink(Object o) {
        initList(o);
    }

    public Element getHeader() {
        return header;
    }

    public Object getHeaderData() {
        return header.value;
    }

    public void setHeader(Element header) {
        this.header = header;
    }

    /**
     * 初始化链表
     */
    public void initList() {
        header = new Element();
        header.value = null;
        header.next = header;
    }

    public void initList(Object o) {
        header = new Element();
        header.value = o;
        header.next = header;
    }

    /**
     * 向表尾插入新节点
     */
    public synchronized void insertList(Object o) {
        Element e = new Element();
        e.value = o;
        //第一次插入元素
        if (header.next == header) {
            if (header.value != o) {
                header.next = e;
                e.next = header;
            }
        } else {
            //temp引用在栈中，temp和header引用都指向堆中的initList()中new的Element对象
            Element temp = header;
            while (temp.next != header) {
                temp = temp.next;
            }
            temp.next = e;
            e.next = header;//新插入的最后一个节点指向头结点
        }
    }

    /**
     * 删除链表中第i个元素
     */
    public synchronized void deletelist(Object o) {
        if (this.size() == 1) {
            if (header.value.equals(o)) {
                header.value = null;
            }
            return;
        }
        Element p1 = header;
        Element p2 = header.next;
        if (p1.value.equals(o)) {
            while (p2.next != p1) {
                p2 = p2.next;
            }
            p2.next = p1.next;
            header = p1.next;
            p1.next = null;
        } else {
            while (p2.next != header) {
                if (p2.value.equals(o)) {
                    p1.next = p2.next;
                    p2.next = null;
                    return;
                }
                p1 = p2;
                p2 = p2.next;
            }
        }
    }

    /**
     * 获取链表的第i个位置的元素
     */
    public Element getElement(int i) {
        if (i < 0 || i > size()) {
            System.out.println("获取链表的位置有误！返回null");
            return null;
        } else {
            int     count   = 0;
            Element element = new Element();
            Element temp    = header;
            if (i == 0) {
                return temp;
            }
            while (temp.next != header) {
                count++;
                if (count == i) {
                    element.value = temp.next.value;
                }
                temp = temp.next;
            }
            return element;
        }
    }

    /**
     * 链表长度
     */
    public int size() {
        Element temp = header;
        int     size = 1;
        if (temp.next == header) {
            return size;
        }
        while (temp.next != header) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 判断链表中是否存在某元素
     */
    public Boolean isContain(Object o) {
        Element temp = header;
        while (temp.next != header) {
            if (temp.next.value.equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * 打印链表
     */
    public void print() {
        System.out.print("打印链表：");
        Element temp = header;
        System.out.print(header.value + "\t");
        if (temp.next != null) {
            while (temp.next != header) {
                System.out.print(temp.next.value + "\t");
                temp = temp.next;
            }
        }
        System.out.println();
    }
}
