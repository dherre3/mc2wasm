package matwably.ast;


import java.util.function.Function;

public abstract class ValueType extends ASTNode<ASTNode> implements IASTNode{
    private ValueType() {}
    protected int type;
    public int getType(){ return type;}
    public abstract <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64);
    public abstract short size();
    public abstract String toString();
    public static final class I32 extends ValueType {
        public final int type = 1;
        public I32(){}
        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return i32.apply(this);
        }
        public void accept(ASTNodeVisitor visitor){
            visitor.visit(this);
        }
        @Override
        public String toString() {
            return "i32";
        }
        public short size() {
            return 4;
        }
    }
    public static final class I64 extends ValueType {
        public final int type = 2;
        public I64(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return i64.apply(this);
        }
        public void accept(ASTNodeVisitor visitor){
            visitor.visit(this);
        }
        @Override
        public String toString() {
            return "i64";
        }
        public short size() {
            return 8;
        }
    }
    public static final class F32 extends ValueType {
        public final int type = 3;
        public F32(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return f32.apply(this);
        }
        public void accept(ASTNodeVisitor visitor){
            visitor.visit(this);
        }
        @Override
        public String toString() {
            return "f32";
        }
        public short size() {
            return 4;
        }
    }
    public static final class F64 extends ValueType {
        public final int type =4;
        public F64(){}

        public <T> T match(Function<I32, T> i32,Function<I64, T> i64,Function<F32, T> f32,Function<F64, T> f64) {
            return f64.apply(this);
        }
        public void accept(ASTNodeVisitor visitor){
            visitor.visit(this);
        }
        @Override
        public String toString() {
            return "f64";
        }
        public short size() {
            return 8;
        }
    }
}