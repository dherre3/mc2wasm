package matwably.ast;

public class ConstLiteral<T extends ValueType> extends Instruction {
    private T type;
    private Number value;
    public ConstLiteral(ValueType.I32 type, int  a)
    {
        this.value = a;
    }
    public ConstLiteral(ValueType.I64 type, long a)
    {
        this.value = a;
    }
    public ConstLiteral(ValueType.F32 type, float  a)
    {
        this.value = a;
    }
    public ConstLiteral(ValueType.F64 type, double  a)
    {
        this.value = a;
    }

    @Override
    public Instruction treeCopyNoTransform() {
        return null;
    }

    @Override
    public Instruction treeCopy() {
        return null;
    }


    @Override
    public Instruction fullCopy() {
        return null;
    }
}
