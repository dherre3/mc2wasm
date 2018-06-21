package matwably.ast;

public class Clz<T extends ValueType.IntValueType> extends IntUnop<T>{
    T type;

    public Clz(T type){
        this.type = type;
    }

    @Override
    public void apply(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
