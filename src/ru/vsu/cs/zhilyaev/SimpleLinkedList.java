package ru.vsu.cs.zhilyaev;

import java.util.Iterator;

public class SimpleLinkedList<T> implements Iterable<T>  {
    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private class SimpleLinkedListNode {
        public T value;
        public SimpleLinkedListNode next;

        public SimpleLinkedListNode(T value, SimpleLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(T value) {
            this(value, null);
        }
    }

    private SimpleLinkedListNode head = null;  // first, top
    private SimpleLinkedListNode tail = null;  // last
    private int size = 0;

    // O(1)
    public void addFirst(T value) {
        head = new SimpleLinkedListNode(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    // O(1)
    public void addLast(T value) {
        if (size == 0) {
            head = tail = new SimpleLinkedListNode(value);
        } else {
            tail.next = new SimpleLinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    private void checkEmptyError() throws SimpleLinkedListException {
        if (size == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    // O(n)
    private SimpleLinkedListNode getNode(int index) {
        SimpleLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    // O(1)
    public void removeFirst() throws SimpleLinkedListException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }

    // O(n)
    public void removeLast() throws SimpleLinkedListException {
        checkEmptyError();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    // O(n)
    public void remove(int index) throws SimpleLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            SimpleLinkedListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            size--;
        }
    }

    // O(1)
    public int size() {
        return size;
    }

    // O(n)
    public T get(int index) throws SimpleLinkedListException {
        checkEmptyError();
        return getNode(index).value;
    }

    // O(1)
    public T getFirst() throws SimpleLinkedListException {
        checkEmptyError();
        return head.value;
    }

    // O(1)
    public T getLast() throws SimpleLinkedListException {
        checkEmptyError();
        return tail.value;
    }

    public void removeRepeat() throws SimpleLinkedListException {
        checkEmptyError();
        for (int i = 1; i < size(); i++) {
            if (get(i) == get(i - 1)) {
                remove(i);
                size--;
            }
        }
    }

    public void removeRepeats() throws SimpleLinkedListException {
        checkEmptyError();
        SimpleLinkedListNode prevEl = head;

        while (prevEl.next != null) {
            if (prevEl.value == prevEl.next.value) {
                prevEl.next = prevEl.next.next;
                size--;
            } else {
                prevEl = prevEl.next;
            }
        }

//        for (int i = 1; i < size(); i++) {
//            if (currEL.value == prevEl.value) {
//                SimpleLinkedListNode prev = getNode(i - 1); // убрать
//                prev.next = prev.next.next;
//                if (prev.next == null) {
//                    tail = prev;
//                }
//                size--;
//                i--;
//                currEL = currEL.next;
//            } else {
//                currEL = currEL.next;
//                prevEl = prevEl.next;
//            }
//        }
    }


    @Override
    public Iterator<T> iterator() {
        class SimpleLinkedListIterator implements Iterator<T> {
            SimpleLinkedListNode curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new SimpleLinkedListIterator();
    }
}