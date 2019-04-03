package com.example.admin.myapplication;

public class Abc {
    public static int count=0;
    public static void main(String args[]){
        (new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countCar();
                }
            }
        }).start();

        (new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    printCat();
                }
            }
        }).start();
    }

    synchronized static void printCat(){
        if (count!=0) {
            System.out.println("过去的车辆数=>" + count);
            count=0;
        }
    }

    synchronized static void countCar(){
        count++;
        System.out.println("又过了一辆车");
    }
}
