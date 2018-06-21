package matwably.ast;

public class IntAdd<T extends ValueType.IntValueType> extends IntBinop<T> {
    T type;

    public IntAdd(T type) {
        this.type = type;
    }
    @Override
    public void apply(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
