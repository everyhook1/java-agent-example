package org.example;

public class DeadLoopMain {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("dead loop");
        while (true) {
            Thread.sleep(300_100);
        }
    }
}
