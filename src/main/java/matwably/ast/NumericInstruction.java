package matwably.ast;

public abstract class NumericInstruction<T extends ValueType> extends ASTNode<ASTNode> {

    public abstract void apply(ASTNodeVisitor visitor);

}