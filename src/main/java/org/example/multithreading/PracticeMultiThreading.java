package org.example.multithreading;

public class PracticeMultiThreading {
    static void main() {
        Thread thread = new Thread( () -> {
            System.out.println("I am the running thread");
        });
        thread.setDaemon(true);
        System.out.println(thread.getState());
        thread.start();
    }
}
