/* Generated By:JJTree: Do not edit this line. ASTAwsmDefinedFunction.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTAwsmDefinedFunction extends SimpleNode {
  public ASTAwsmDefinedFunction(int id) {
    super(id);
  }

  public ASTAwsmDefinedFunction(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=62650dbdc07de688df410b26b0d53ea7 (do not edit this line) */