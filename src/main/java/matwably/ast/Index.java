package matwably.ast;
import java.security.InvalidParameterException;
import java.util.function.Function;
//public abstract class Index {
//    public abstract <T> T match(Function<Identifier, T> funcid, Function<Numeric, T> funcnum );
//    public class Identifier extends Index {
//        String value;
//
//        public Identifier(String str) {
//            if (str.charAt(0) != '$') throw new InvalidParameterException("Identifier must start with $");
//            this.value = str;
//        }
//        public <T> T match(Function<Identifier, T> funcid, Function<Numeric, T> funcnum) {
//            return funcid.apply(this);
//        }
//    }
//    public class Numeric extends Index {
//        int value;
//
//        public Numeric(int val) {
//            if(val < 0) throw new InvalidParameterException("Numeric index must be positive");
//            this.value = val;
//        }
//        public <T> T match(Function<Identifier, T> funcid, Function<Numeric, T> funcnum ){
//            return funcnum.apply(this);
//        }
//    }
//}
