package matwably.ast;

import java.util.function.Function;

public abstract class Mut {

    private Mut() {}
    public abstract <T> T  match(Function<Mutable, T> a,Function<Constant, T> b );

    public static final class Mutable extends  Mut{
        public Mutable() {}
        public <T> T match(Function<Mutable, T> a,Function<Constant, T> b ){
            return a.apply(this);
        }
    }
    public static final class Constant extends  Mut{
        public Constant() {}

        public <T> T match(Function<Mutable, T> a,Function<Constant, T> b ){
            return b.apply(this);
        }
    }
}
