package com.qinxc.threads;


import java.security.spec.ECField;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;



public class CompletableFutureTest {

    public static void testRunAsync(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{

            System.out.println("testRunAsync Thread: " + Thread.currentThread().getName());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                throw new IllegalStateException(e);
            }
        });
        try{
            future.get();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void testSupplyAsync(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{

            System.out.println("testSupplyAsync Thread: " + Thread.currentThread().getName());
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                throw new IllegalStateException(e);
            }
            return "Supply Async Result!";
        });
        try{
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("testSupplyAsync Result: " + future.get());
        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args){

        //1
        //testRunAsync();
        //2
        //testSupplyAsync();


    }
}
