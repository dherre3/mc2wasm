package matwably.ast;

import java.util.function.Function;

public abstract class ValueType {
    private ValueType() {}
    public abstract <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64);

    public static final class I32 extends ValueType {

        public I32(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return i32.apply(this);
        }
    }
    public static final class I64 extends ValueType {

        public I64(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return i64.apply(this);
        }
    }
    public static final class F32 extends ValueType {

        public F32(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return f32.apply(this);
        }
    }
    public static final class F64 extends ValueType {

        public F64(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return f64.apply(this);
        }
    }
}