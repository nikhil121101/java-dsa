package javaLab.lab10;

import java.io.*;
import java.util.*;

public class Question {

    static class ProducerThread extends Thread{

        Controller control;

        ProducerThread(Controller control) {
            this.control = control;
        }

        @Override
        public void run() {
            try {
                control.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ConsumerThread extends Thread {
        Controller control;

        ConsumerThread(Controller control) {
            this.control = control;
        }

        @Override
        public void run() {
            try {
                control.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter bufferSize");
        int n = Integer.parseInt(br.readLine());

        Controller controller = new Controller(n);

        Thread producerThread = new ProducerThread(controller);
        Thread consumerThread = new ConsumerThread(controller);

        producerThread.start();
        consumerThread.start();

        //System.out.println(producerThread.getPriority() + " " + consumerThread.getPriority());

        producerThread.join();
        consumerThread.join();


    }

    static class Controller {

        Queue<Integer> buffer = new LinkedList<>();
        int buffer_size;
        int count = 0;

        Controller(int buffer_size) {
            this.buffer_size = buffer_size;
        }

        public void produce() throws InterruptedException {
            //Thread.sleep(1000);
            while(true) {
                synchronized (this) {
                    //System.out.println("Queue - > " + buffer);
                    if(buffer.size() == buffer_size) {
                        wait();
                    }
                    System.out.println("data produced : " + count);
                    buffer.add(count++);
                    notifyAll();
                }
                //Thread.sleep(100);
                if(count >= 100) {
                    break;
                }
            }
        }

        public void consume() throws InterruptedException {
            while(true) {
                synchronized (this) {
                    if(buffer.isEmpty()) {
                        wait();
                    }
                    System.out.println("data consumed : " + buffer.remove());
                    notifyAll();
                    //Thread.sleep(100);
                }
            }
        }
    }

}
