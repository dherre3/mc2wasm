package matwably.ast;


import java.util.ArrayList;

public abstract class WasmNode {
    ArrayList<WasmNode> children = new ArrayList<>();
    public WasmNode() {

    }
    public void visit() {
        children.forEach(WasmNode::visit);
    }


}
