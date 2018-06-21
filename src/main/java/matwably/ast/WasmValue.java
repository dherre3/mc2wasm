package matwably.ast;

import java.util.function.Function;

public abstract class WasmValue {
    private WasmValue() {}
    public abstract <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64);

    public static final class I32 extends WasmValue {
        int value = 0;
        public I32(int val){this.value = val;}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return i32.apply(this);
        }
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static final class I64 extends WasmValue {
        long value = 0;
        public I64(long val){this.value = val;}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return i64.apply(this);
        }
        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }
    }
    public static final class F32 extends WasmValue {
        private float value = 0;
        public F32(float val){this.value = val;}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return f32.apply(this);
        }
        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }
    public static final class F64 extends WasmValue {
        private double value = 0;
        public F64(double val){this.value = val;}


        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return f64.apply(this);
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}