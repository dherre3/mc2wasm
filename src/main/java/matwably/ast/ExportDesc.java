package matwably.ast;

import java.util.function.Function;

public abstract class ExportDesc {
    int index;

    private  ExportDesc() {}
    public abstract <T> T match(Function<FuncExport, T> func_exp,Function<GlobalExport, T> global_exp,
                                       Function<TableExport, T> table_exp,Function<MemoryExport, T> mem_exp);
    public static final class FuncExport extends ExportDesc {
        public FuncExport(int i) {
            this.index = i;
        }
        public <T> T match(Function<FuncExport, T> func_exp,Function<GlobalExport, T> global_exp,
                            Function<TableExport, T> table_exp,Function<MemoryExport, T> mem_exp){
            return func_exp.apply(this);
        }
    }
    public static final class GlobalExport extends ExportDesc {
        public GlobalExport(int i) {
            this.index = i;
        }
        public <T> T match(Function<FuncExport, T> func_exp,Function<GlobalExport, T> global_exp,
                           Function<TableExport, T> table_exp,Function<MemoryExport, T> mem_exp){
            return global_exp.apply(this);
        }
    }
    public static final class MemoryExport extends ExportDesc {
        public MemoryExport(int i) {
            this.index = i;
        }
        public <T> T match(Function<FuncExport, T> func_exp,Function<GlobalExport, T> global_exp,
                           Function<TableExport, T> table_exp,Function<MemoryExport, T> mem_exp){
            return mem_exp.apply(this);
        }
    }
    public static final class TableExport extends ExportDesc {
        public TableExport(int i) {
            this.index = i;
        }
        public <T> T match(Function<FuncExport, T> func_exp,Function<GlobalExport, T> global_exp,
                           Function<TableExport, T> table_exp,Function<MemoryExport, T> mem_exp){
            return table_exp.apply(this);
        }
    }

}
