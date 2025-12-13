package com.example;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode({
        Mode.Throughput,     // ops/s
        Mode.AverageTime,    // ns/op
        Mode.SampleTime      // 分布（中央値・99p）
})
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(value = 3)
@State(org.openjdk.jmh.annotations.Scope.Thread)
public class MyBenchmark {

    @Benchmark
    public int implA() {
        return workA();
    }

    @Benchmark
    public int implB() {
        return workB();
    }

    /*誤差が大きい場合に数回分を測るやり方
    @Param({"1", "100", "10000"})
    int batchSize;

    @Benchmark
    @OperationsPerInvocation(1)
    public void implA_param() {
        for (int i = 0; i < batchSize; i++) {
            workA();
        }
    }
    --------------------------------*/

    private int workA() {
        return 1 + 2;
    }

    private int workB() {
        return Math.addExact(1, 2);
    }
}
