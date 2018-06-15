package matwably.ast;

import java.util.ArrayList;

public class WasmModule extends WasmNode {
    String name;
    ArrayList<TopLevelDeclaration> declarations = new ArrayList<TopLevelDeclaration>();
    public WasmModule(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        declarations.forEach(WasmNode::toString);
        return "";
    }
}
