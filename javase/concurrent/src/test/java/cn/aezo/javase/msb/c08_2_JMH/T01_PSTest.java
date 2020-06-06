package cn.aezo.javase.msb.c08_2_JMH;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Benchmark                              Mode  Cnt  Score   Error  Units
 * T01_PSTest.testForeach                thrpt    5  0.289 ± 0.099  ops/s
 * T01_PSTest.testParallelStreamForeach  thrpt    5  0.968 ± 1.460  ops/s
 *
 * @author smalle
 * @date 2020-05-30 09:51
 */
@Warmup(iterations = 1, time = 3) // 预热设置
@Fork(5)
@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 1, time = 3)
public class T01_PSTest {

    @Benchmark
    public void testForeach() {
        T01_PS.foreach();
    }

    @Benchmark
    public void testParallelStreamForeach() {
        T01_PS.parallelStreamForeach();
    }
}
