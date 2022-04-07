package ru.vsu.cs.zhilyaev;

public class Main {

    public static void main(String[] args) throws Exception {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
//        list.addFirst(1);
//        list.addFirst(1);
//        list.addFirst(1);
//        list.addFirst(1);
//        list.addFirst(1);
//        list.addFirst(1);
//        list.addLast(1);
//        list.addLast(2);
//        list.addLast(2);
//        list.addLast(2);

        System.out.println("Начальный список:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
        System.out.println();

        list.removeRepeats();

        System.out.println("Изменненный список:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i > 0 ? ", " : "") + list.get(i));
        }
    }
}
