/* Generated By:JavaCC: Do not edit this line. AdilVisitor.java Version 5.0 */
package edu.sdsc.adil;

public interface AdilVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTADILStatement node, Object data);
  public Object visit(ASTStatement node, Object data);
  public Object visit(ASTImportLibrary node, Object data);
  public Object visit(ASTCreateStatement node, Object data);
  public Object visit(ASTCreateAnalysis node, Object data);
  public Object visit(ASTDeclareAnalysis node, Object data);
  public Object visit(ASTAnalysisStatement node, Object data);
  public Object visit(ASTJsonObjectBuilder node, Object data);
  public Object visit(ASTUserDefinedFunction node, Object data);
  public Object visit(ASTCreateView node, Object data);
  public Object visit(ASTProjectFunction node, Object data);
  public Object visit(ASTReportAnalysis node, Object data);
  public Object visit(ASTFunctionAnalysis node, Object data);
  public Object visit(ASTExecuteSQLPP node, Object data);
  public Object visit(ASTexecuteCypher node, Object data);
  public Object visit(ASTquetedStatement node, Object data);
  public Object visit(ASTFilterOperation node, Object data);
  public Object visit(ASTAnnotateWithStoreOperation node, Object data);
  public Object visit(ASTGetConditionExpression node, Object data);
  public Object visit(ASTGetField node, Object data);
  public Object visit(ASTGetDictionary node, Object data);
  public Object visit(ASTCreateDataSOURCE node, Object data);
  public Object visit(ASTConnectDataSRC node, Object data);
  public Object visit(ASTCreatEnvironment node, Object data);
  public Object visit(ASTfunction node, Object data);
  public Object visit(ASTUseEnvironment node, Object data);
  public Object visit(ASTOperator node, Object data);
  public Object visit(ASTOperand node, Object data);
  public Object visit(ASTaction node, Object data);
}
/* JavaCC - OriginalChecksum=6f635a57c60f4698ddf7e2fe19e9004d (do not edit this line) */