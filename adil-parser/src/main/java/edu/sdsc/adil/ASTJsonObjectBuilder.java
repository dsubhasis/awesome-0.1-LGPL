/* Generated By:JJTree: Do not edit this line. ASTJsonObjectBuilder.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTJsonObjectBuilder extends SimpleNode {
  public ASTJsonObjectBuilder(int id) {
    super(id);
  }

  public ASTJsonObjectBuilder(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b930139a43ffd6bd2549ceae67b9f961 (do not edit this line) */
