/* Generated By:JJTree: Do not edit this line. ASTCreatEnvironment.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTCreatEnvironment extends SimpleNode {
  public ASTCreatEnvironment(int id) {
    super(id);
  }

  public ASTCreatEnvironment(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=64a71333b709f12d5d9bbc883020b795 (do not edit this line) */
