package com.zzp.learn.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Desc
 * Created by zzp
 * on 2016/9/18.11:33
 */
public class ManualDiceRolls {

    public static final int N = 100000000;

    private final int numberOfThread;
    private final ExecutorService executor;
    private final Map<Integer, Double> results;
    private final int workPerThread;
    private final double fraction;

    private ManualDiceRolls() {
        numberOfThread = Runtime.getRuntime().availableProcessors();
        fraction = 1.0 / N;
        workPerThread = N / numberOfThread;
        executor = Executors.newFixedThreadPool(numberOfThread);
        results = new ConcurrentHashMap<>();
    }

    private void simulateDiceRolls() {
        List<Future> futures = submitJobs();
        awaitCompletion(futures);
        printResult();
    }

    private void printResult() {
        results.entrySet().forEach(System.out::println);
    }

    public List<Future> submitJobs() {
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < numberOfThread; i++) {
            futures.add(executor.submit(myJob()));
        }
        return futures;
    }


    private Runnable myJob() {
        return () -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < workPerThread; i++) {
                int entry = twoDiceThrows(random);
                accumulateResult(entry);
            }
        };
    }

    private void accumulateResult(int entry) {
        results.compute(entry, (key, previous) -> previous == null ? fraction : previous + fraction);
    }

    public int twoDiceThrows(ThreadLocalRandom random) {
        return random.nextInt(1, 7) + random.nextInt(1, 7);
    }


    public void awaitCompletion(List<Future> futures) {
        futures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }
    public static void main(String[] args) {
        ManualDiceRolls rolls = new ManualDiceRolls();
        rolls.simulateDiceRolls();
    }
}
