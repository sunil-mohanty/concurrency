package com.ski.jobs;

import com.ski.threadpooldemo.ThreadPool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PrintingJobs {

    private static ThreadPool threadPool = new ThreadPool(4, 5);

    public static  void  main(String args[]){
        try(Stream<String> stream = Files.lines(Paths.get("Quotes"))){
            stream.forEach(PrintingJobs::startPrintingInUpperCase);
            threadPool.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void startPrintingInUpperCase(String quote) {
        try {
            threadPool.execute(new BannerPrinter(quote));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class BannerPrinter implements Runnable {

    private String quote = null;

    BannerPrinter(String quote){
        this.quote = quote;
    }

    public void run() {
        System.out.println(quote.toUpperCase());
    }
}