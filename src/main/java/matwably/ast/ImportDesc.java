package matwably.ast;

import java.util.function.Function;

public abstract class ImportDesc {
    private  ImportDesc() {}
    public abstract <T> T match(Function<FuncImport, T> func_imp,Function<GlobalImport, T> global_imp,
                       Function<TableImport, T> table_imp,Function<MemoryImport, T> mem_imp);

    public static final class FuncImport extends ImportDesc {
        int funcidx;
        public FuncImport(int i) {
            this.funcidx = i;
        }
        public <T> T match(Function<FuncImport, T> func_imp,Function<GlobalImport, T> global_exp,
                           Function<TableImport, T> table_exp,Function<MemoryImport, T> mem_exp){
            return func_imp.apply(this);
        }
    }
    public static final class GlobalImport extends ImportDesc {
        GlobalType globaltype;
        public GlobalImport(GlobalType globtype) {
            this.globaltype = globtype;
        }
        public <T> T match(Function<FuncImport, T> func_imp,Function<GlobalImport, T> global_imp,
                           Function<TableImport, T> table_imp,Function<MemoryImport, T> mem_imp){
            return global_imp.apply(this);
        }
    }
    public static final class MemoryImport extends ImportDesc {
        MemoryType memorytype;
        public MemoryImport(MemoryType memorytype) {
            this.memorytype = memorytype;
        }
        public <T> T match(Function<FuncImport, T> func_imp,Function<GlobalImport, T> global_imp,
                           Function<TableImport, T> table_imp,Function<MemoryImport, T> mem_imp){
            return mem_imp.apply(this);
        }
    }
    public static final class TableImport extends ImportDesc {
        TableType tabletype;
        public TableImport(TableType tabletype) {
            this.tabletype = tabletype;
        }
        public <T> T match(Function<FuncImport, T> func_imp,Function<GlobalImport, T> global_imp,
                           Function<TableImport, T> table_imp,Function<MemoryImport, T> mem_imp){
            return table_imp.apply(this);
        }
    }

}
