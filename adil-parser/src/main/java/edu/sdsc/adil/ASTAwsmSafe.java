/* Generated By:JJTree: Do not edit this line. ASTAwsmSafe.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTAwsmSafe extends SimpleNode {
  public ASTAwsmSafe(int id) {
    super(id);
  }

  public ASTAwsmSafe(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=0a4cbea7d4ba33d39c96fcc61addac21 (do not edit this line) */
