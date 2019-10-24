package com.gmail.atpt34;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Main cpu : i5 7300u
 * 2 cores, 4 threads, 3Mb cache
 *
 * Task:
 * Calculate table of GCD values with lenght N
 * with possible other calc expensive task that steal cpu and memory
 *
 * Conclusions for this task:
 * ExecutorService perf is better than CompletableFuture, but both are acceptable
 * More threads the better! 96 is the best bet!
 * The tasks are distributed between threads uniformly and independent
 * FixedThreadPool also the best and gives predictability
 * Sharing memory between threads is not acceptable
 * ES best for submitting runnables and CF for async chained execution
 * Iterative algorithm is better than recursive
 * There is some golden ratio between cpu waiting for I/O and switching threads
 * #Threads can be calc according to Brian Goetz
 * #Threads = #ThreadsAvailable * CPUutil(0..1) * ( 1 + W / C[wait to computation ratio] ) [max threads is 100 recommended]
 */
public class ManyGcdPerformance {

    private static final int N = 10000;
//    private static final int N = 2000; // 1t = 157, 128, 130 es = 85, 76, 140 cf = 94, 100

    public static void main(String[] args) {
        // 4
//        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());
        int n = N;
        int[][] gcds = new int[n][n];

        long startTime = System.nanoTime();

//        plainCalcMainThread(gcds); // 7342 ms
        //improved:
//        plainCalcMainThread(gcds); // 4293

//        plainCalcMainThread(gcds); // recursive 4354, 4297
//        plainCalcMainThread(gcds); // iterative 3940, 4049


//        calcWithTwoThreads(gcds);  // 4078

//        plainCalcMainThreadMathLib(gcds); // 13071

//        calcWithExecutorService(gcds, 2); // 4152 //cachedThreadPool
//        calcWithExecutorService(gcds, 7); // 2562  //cachedThreadPool

//        calcWithExecutorService(gcds, 2); // 7314 // singleThreadPool

//        calcWithExecutorService(gcds, 2); // 4220 // workStealingPool
//        calcWithExecutorService(gcds, 4); // 2800 // workStealingPool
//        calcWithExecutorService(gcds, 5); // 3559 // workStealingPool
//        calcWithExecutorService(gcds, 6); // 3219 // workStealingPool
//        calcWithExecutorService(gcds, 7); // 2881 // workStealingPool

//        calcWithExecutorService(gcds, 2); // 4147
//        calcWithExecutorService(gcds, 3); // 4118
//        calcWithExecutorService(gcds, 3); // 3079  // fixedThreadPool
//        calcWithExecutorService(gcds, 4); // 2716
//        calcWithExecutorService(gcds, 5); // 2656
//        calcWithExecutorService(gcds, 6); // 2630
//        calcWithExecutorService(gcds, 7); // 2493, 2519, 2452
//        calcWithExecutorService(gcds, 8); // 2791
//        calcWithExecutorService(gcds, 10); // 2570

        // improved version:
//        calcWithExecutorService(gcds, 2); //
//        calcWithExecutorService(gcds, 4); // 1709, 1635
//        calcWithExecutorService(gcds, 8); // 1361, 1455, 1414, 1398
//        calcWithExecutorService(gcds, 16); // 1515, 1352, 1320, 1427
//        calcWithExecutorService(gcds, 24); // 1248
//        calcWithExecutorService(gcds, 32); // 1235, 1303, 1309, 1251, 1268
//        calcWithExecutorService(gcds, 64); // 1291, 1220, 1194, 1221, 1145
//        calcWithExecutorService(gcds, 80); // 1242
//        calcWithExecutorService(gcds, 88); // 1211
//        calcWithExecutorService(gcds, 92); // 1267
        calcWithExecutorService(gcds, 96); // 1199, 1176, 1168, 1193, 1272, 1220, 1131, 1070, 1027
//        calcWithExecutorService(gcds, 98); // 1208, 1193
//        calcWithExecutorService(gcds, 100); // 1179
//        calcWithExecutorService(gcds, 112); // 1195
//        calcWithExecutorService(gcds, 128); // 1304

//        calcWithExecutorService(gcds, 7); // 2781 // ForkJoin.commonPool()

//        calcWithCompletableFutures(gcds, 2); // 4311  // default
//        calcWithCompletableFutures(gcds, 4); // 4197 // default
//        calcWithCompletableFutures(gcds, 7); // 3862 // default
//        calcWithCompletableFutures(gcds, 8); // 3424 // default
//        calcWithCompletableFutures(gcds, 8); // 3424, 2051

//        calcWithCompletableFutures(gcds, 2); // 4108 // fixedThreadPool
//        calcWithCompletableFutures(gcds, 3); // 3136
//        calcWithCompletableFutures(gcds, 4); // 2509
//        calcWithCompletableFutures(gcds, 5); // 2866
//        calcWithCompletableFutures(gcds, 6); // 2619
//        calcWithCompletableFutures(gcds, 7); // 2459, 2495
//        calcWithCompletableFutures(gcds, 8); // 2570
//        calcWithCompletableFutures(gcds, 9); // 2452
//        calcWithCompletableFutures(gcds, 10); // 2511
//        calcWithCompletableFutures(gcds, 12); // 2436
//        calcWithCompletableFutures(gcds, 16); // 2463

        // improvement without caching
        // (the best plain & async solution for independent tasks ):
        // 3 times faster on average then plain one thread solution !!!
//        calcWithCompletableFutures(gcds, 1); // 4301
//        calcWithCompletableFutures(gcds, 2); // 3080
//        calcWithCompletableFutures(gcds, 4); // 1701
//        calcWithCompletableFutures(gcds, 6); // 1444
//        calcWithCompletableFutures(gcds, 8); // 1354, 1339
//        calcWithCompletableFutures(gcds, 12); // 1370
//        calcWithCompletableFutures(gcds, 16); // 1339
//        calcWithCompletableFutures(gcds, 32); // 1286, 1334, 1331
//        calcWithCompletableFutures(gcds, 64); // 1422
//        calcWithCompletableFutures(gcds, 96); // 1395, 1385, 1537, 1397
//        calcWithCompletableFutures(gcds, 128); // 1390
//        calcWithCompletableFutures(gcds, 256); // 1349
//        calcWithCompletableFutures(gcds, 512); // 1550

//        calcWithDynamicProgramming(gcds, 1);  // 3034, 3041
//        calcWithDynamicProgramming(gcds, 2);  // 2361
//        calcWithDynamicProgramming(gcds, 3);  // 2200
//        calcWithDynamicProgramming(gcds, 4);  // 1762, 2060, 2198, 2012
//        calcWithDynamicProgramming(gcds, 5);  // 2077
//        calcWithDynamicProgramming(gcds, 6);  // 1953
//        calcWithDynamicProgramming(gcds, 7);  // 1829, 1969, 2214
//        calcWithDynamicProgramming(gcds, 8);  // 1819, 1827, 1834

        // only one table & improvement trick (best for DP):
//        calcWithDynamicProgramming(gcds, 1);  // 2920
//        calcWithDynamicProgramming(gcds, 2);  // 2224
//        calcWithDynamicProgramming(gcds, 4);  // 1793
//        calcWithDynamicProgramming(gcds, 8);  //  1796, 1671, 1620
//        calcWithDynamicProgramming(gcds, 10);  // 1641
//        calcWithDynamicProgramming(gcds, 12);  // 1661
//        calcWithDynamicProgramming(gcds, 16);  // 1662
//        calcWithDynamicProgramming(gcds, 32);  // 1570
//        calcWithDynamicProgramming(gcds, 64);  // 1450
//        calcWithDynamicProgramming(gcds, 96);  // 1294
//        calcWithDynamicProgramming(gcds, 128);  // 1355
//        calcWithDynamicProgramming(gcds, 256);  // 1439

//        calcWithDynamicProgramming(gcds, 1);  // 6344
//        calcWithDynamicProgramming(gcds, 8);  // 3972
//        calcWithDynamicProgramming(gcds, 16);  //  3208
//        calcWithDynamicProgramming(gcds, 32);  // 2708
//        calcWithDynamicProgramming(gcds, 64);  // 2444
//        calcWithDynamicProgramming(gcds, 100);  // 2085
//        calcWithDynamicProgramming(gcds, 256);  // 1862
//        calcWithDynamicProgramming(gcds, 512);  // 2255
//        calcWithDynamicProgramming(gcds, 1000);  // 2572

        // with improvement
//        calcWithDynamicProgramming(gcds, 1);  // 4918
//        calcWithDynamicProgramming(gcds, 2);  // 4169
//        calcWithDynamicProgramming(gcds, 4);  // 3286
//        calcWithDynamicProgramming(gcds, 8);  // 3076
//        calcWithDynamicProgramming(gcds, 9);  // 2882
//        calcWithDynamicProgramming(gcds, 10);  // 2842
//        calcWithDynamicProgramming(gcds, 12);  // 2773
//        calcWithDynamicProgramming(gcds, 16);  // 2602
//        calcWithDynamicProgramming(gcds, 32);  // 2293
//        calcWithDynamicProgramming(gcds, 64);  // 1999

//        usingParallelStream(gcds, 100); // 1396, 1269, 1526
//        usingParallelStream(gcds, 400); // 1630
//        usingParallelStream(gcds, 96); // 1501

        // commented .parallel()
//        usingParallelStream(gcds, 100); // 3486

        long finishTime = System.nanoTime();
        System.out.println((finishTime - startTime)/1000_000 + " ms ");

    }

    private static void calcWithDynamicProgramming(int[][] gcds, int nThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        CompletableFuture.runAsync(veryExpensiveSideTask());
        List<CompletableFuture<Void>> collect =
                IntStream.range(0, nThreads)
                        .mapToObj(k -> (Runnable) () -> {
                            int n = gcds.length;
                            for (int i = 0; i < n; i++) {
//                                for (int j = k; j < n; j += nThreads) {
                                for (int j = k; j <= i; j += nThreads) {  // improvement!
                                    simpleGcdDP(j, i, gcds);
                                }
                            }
                        })
                        .map(r -> CompletableFuture.runAsync(r, executorService))
                        .collect(Collectors.toList());

        CompletableFuture<Void> all = CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]));
        all.join();
        executorService.shutdown();

        // single main thread impl:
//        int n = gcds.length;
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                gcds[i][j] = simpleGcdDP(i, j);
//            }
//        }
    }

    private static void calcWithCompletableFutures(int[][] gcds, int nThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//        callExpensive();
//        CompletableFuture<Void> cfv = new CompletableFuture<>();  // post trigger trick(works better without it)
        List<CompletableFuture<Void>> collect =
                IntStream.range(0, nThreads)
                    .mapToObj(i -> threadWork(gcds, i, nThreads))
//                    .map(r -> CompletableFuture.runAsync(r))
                    .map(r -> CompletableFuture.runAsync(r, executorService))
//                        .map(r -> cfv.thenRunAsync(r, executorService))
                    .collect(Collectors.toList());
//        cfv.complete(null);
        CompletableFuture<Void> all = CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]));
        all.join();
        executorService.shutdown();
    }

    private static void calcWithExecutorService(int[][] gcds, int nThreads) {
//        ExecutorService executorService = ForkJoinPool.commonPool();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newScheduledThreadPool(nThreads);

//        callExpensive();

        IntStream.range(0, nThreads)
                .mapToObj(i -> threadWork(gcds, i, nThreads))
                .forEach(executorService::submit);
        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void callExpensive() {
        Thread thread = new Thread(veryExpensiveSideTask());
        thread.setDaemon(true);
        thread.start();
    }

    private static void calcWithTwoThreads(int[][] gcds) {
        int n = gcds.length;
        Thread additionalThread = new Thread(threadWork(gcds, 1, 2));

        additionalThread.start();
        threadWork(gcds, 0, 2).run();

        try {
            additionalThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Runnable threadWork(int[][] gcds, int start, int delta) {
        return () -> {
            int n = gcds.length;
            for (int i = 0; i < n; i++) {
//                for (int j = start; j < n; j += delta) { // improvement w/o caching
                for (int j = start; j <= i; j += delta) {
//                    gcds[i][j] = simpleGcd(i, j);
//                    gcds[i][j] = gcds[j][i] = simpleGcd(j, i);
                    gcds[i][j] = gcds[j][i] = iterativeGcd(j, i); // iteration instead of recursion
                }
            }
        };
    }

    static class Pair {
        int a;
        int b;
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private static void usingParallelStream(int[][] gcds, int nThreads) {
//        int n = N;
        IntStream.range(0, nThreads)
                .parallel()
//                .forEach(x -> {  // inefficient!
//                    int a = x % n;
//                    int b = x / n;
//                    gcds[a][b] = gcds[b][a] = iterativeGcd(a, b);
//                });
                .mapToObj(i -> threadWork(gcds, i, nThreads))
                .forEach(Runnable::run);
    }

    // Eventually throws OutOfMemError
    private static Runnable veryExpensiveSideTask() {
        return () -> {
            ArrayList<BigInteger> ints;
            List<Double> averages = new LinkedList<>();
            while (averages.size() < N) {
                int n = ThreadLocalRandom.current().nextInt(499_000_000, 500_000_000);
                ints = new ArrayList<>(n);
                for (int j = 0; j < n; j++) {
                    ints.add(BigInteger.valueOf(Long.MAX_VALUE - n));
                }
                double average = ints
                        .parallelStream()
//                        .stream()
                        .mapToInt(BigInteger::intValue)
                        .summaryStatistics()
                        .getAverage();
                averages.add(average);
            }
        };
    }

    private static void plainCalcMainThread(int[][] gcds) {
        int n = gcds.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                gcds[i][j] = gcds[j][i] = simpleGcd(j, i);
//                gcds[i][j] = gcds[j][i] = iterativeGcd(j, i);
            }
        }
    }

    //
    private static void plainCalcMainThreadMathLib(int[][] gcds) {
        int n = gcds.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gcds[i][j] = BigInteger.valueOf(i).gcd(BigInteger.valueOf(j)).intValue();
            }
        }
    }

    private static int simpleGcd(int a, int b) {
        if ( a == 0 ) {
            return b;
        }
        return simpleGcd(b % a, a);
    }

    private static int iterativeGcd(int a, int b) {
        while (a != 0) {
            int r = b % a;
            b = a;
            a = r;
        }
        return b;
    }

    // Data races, but overwriting the same value
    // adds unpredictability due thread contention
    private static int simpleGcdDP(int a, int b, int[][] gcds) {
        if (gcds[a][b] != 0) {
            return gcds[a][b];
        }

        if ( a == 0 ) {
            return b;
        }
        int res = simpleGcdDP(b % a, a, gcds);

        gcds[a][b] = res;
        gcds[b][a] = res;
        return res;
    }
}
