package com.qihang.common.util.stream;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * JAVA8 分组求和使用方法
 *
 * @author qihang
 */
public class CollectorsUtils {

    private static final Set<Collector.Characteristics> CHARACTERISTICS = Collections.emptySet();

    private CollectorsUtils() {
    }

    @SuppressWarnings("unchecked")
    private static <I, R> Function<I, R> check() {
        return i -> (R) i;
    }

    @SuppressWarnings("hiding")
    static class CollectorImpl<T, A, R> implements Collector<T, A, R> {

        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Function<A, R> finisher, Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner, Set<Characteristics> characteristics) {
            this(supplier, accumulator, combiner, check(), characteristics);
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return characteristics;
        }
    }

    /**
     * 求和方法
     *
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, BigDecimal> summingBigDecimal(ToBigDecimalFunction<? super T> mapper) {
        return new CollectorImpl<>(
                () -> new BigDecimal[]{new BigDecimal(0)},
                (a, t) -> {
                    a[0] = a[0].add(mapper.applyAsBigDecimal(t), MathContext.DECIMAL32);
                },
                (a, b) -> {
                    a[0] = a[0].add(b[0], MathContext.DECIMAL32);
                    return a;
                },
                a -> a[0], CHARACTERISTICS
        );
    }

    /**
     * 求最大值，这里的最小MIN值，作为初始条件判断值，如果某些数据范围超过百亿，可以根据需求换成 Long.MIN_VALUE 或者 Double.MIN_VALUE
     *
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, BigDecimal> maxBy(ToBigDecimalFunction<? super T> mapper) {
        return new CollectorImpl<>(
                () -> new BigDecimal[]{new BigDecimal(Integer.MIN_VALUE)},
                (a, t) -> {
                    a[0] = a[0].max(mapper.applyAsBigDecimal(t));
                },
                (a, b) -> {
                    a[0] = a[0].max(b[0]);
                    return a;
                },
                a -> a[0], CHARACTERISTICS
        );
    }

    /**
     * 求最小值，这里的最大MAX值，作为初始条件判断值，如果某些数据范围超过百亿，可以根据需求换成 Long.MAX_VALUE 或者 Double.MAX_VALUE
     *
     * @param mapper
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, BigDecimal> minBy(ToBigDecimalFunction<? super T> mapper) {
        return new CollectorImpl<>(
                () -> new BigDecimal[]{new BigDecimal(Integer.MAX_VALUE)},
                (a, t) -> {
                    a[0] = a[0].min(mapper.applyAsBigDecimal(t));
                },
                (a, b) -> {
                    a[0] = a[0].min(b[0]);
                    return a;
                },
                a -> a[0], CHARACTERISTICS
        );
    }

    /**
     * 求平均，并且保留小数，返回一个平均值
     *
     * @param mapper
     * @param newScale
     * @param roundingMode
     * @param <T>
     * @return
     */
    public static <T> Collector<T, ?, BigDecimal> averagingBigDecimal(ToBigDecimalFunction<? super T> mapper,
                                                                      int newScale, int roundingMode) {
        return new CollectorImpl<>(
                () -> new BigDecimal[]{new BigDecimal(0), new BigDecimal(0)},
                (a, t) -> {
                    a[0] = a[0].add(mapper.applyAsBigDecimal(t));
                    a[1] = a[1].add(BigDecimal.ONE);
                },
                (a, b) -> {
                    a[0] = a[0].min(b[0]);
                    return a;
                },
                a -> a[0].divide(a[1], MathContext.DECIMAL32).setScale(newScale, roundingMode), CHARACTERISTICS
        );
    }

    // 自定义函数
    @FunctionalInterface
    public interface ToBigDecimalFunction<T> {

        BigDecimal applyAsBigDecimal(T t);

    }

}