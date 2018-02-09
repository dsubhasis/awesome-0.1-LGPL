package edu.sdsc.adil;
public class AdilVisitorAdapter implements AdilVisitor{

public Object visit(ASTCreateDataVerse node, Object data){

return node.childrenAccept(this, data );

}
    public Object visit(SimpleNode node, Object data){

        return node.childrenAccept(this, data );

    }
    public Object visit(ASTADILStatement node, Object data){

        return node.childrenAccept(this, data );

    }
    public Object visit(ASTStatement node, Object data){

        return node.childrenAccept(this, data );

    }
    public Object visit(ASTCreateStatement node, Object data){

        return node.childrenAccept(this, data );

    }
    public Object visit(ASTLoadStatement node, Object data){

        return node.childrenAccept(this, data );

    }

    public Object visit(ASTDeleteStatement node, Object data){

        return node.childrenAccept(this, data );

    }

    public Object visit(ASTOperator node, Object data){

        return node.childrenAccept(this, data );

    }

    public Object visit(ASTOperand node, Object data){

        return node.childrenAccept(this, data );

    }

    public Object visit(ASTaction node, Object data){

        return node.childrenAccept(this, data );

    }





} 
