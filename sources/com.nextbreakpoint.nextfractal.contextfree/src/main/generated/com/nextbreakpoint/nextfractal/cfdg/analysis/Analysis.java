/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.analysis;

import com.nextbreakpoint.nextfractal.cfdg.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseACfdg(ACfdg node);
    void caseASizeSizeToken2(ASizeSizeToken2 node);
    void caseASizeTokenSizeToken2(ASizeTokenSizeToken2 node);
    void caseAStartshapeCfdgDeclaration(AStartshapeCfdgDeclaration node);
    void caseAIncludeCfdgDeclaration(AIncludeCfdgDeclaration node);
    void caseABackgroundCfdgDeclaration(ABackgroundCfdgDeclaration node);
    void caseATileCfdgDeclaration(ATileCfdgDeclaration node);
    void caseASizeCfdgDeclaration(ASizeCfdgDeclaration node);
    void caseARuleCfdgDeclaration(ARuleCfdgDeclaration node);
    void caseAPathCfdgDeclaration(APathCfdgDeclaration node);
    void caseAStartshapeDeclaration(AStartshapeDeclaration node);
    void caseAIncludeDeclaration(AIncludeDeclaration node);
    void caseABackgroundDeclaration(ABackgroundDeclaration node);
    void caseATileDeclaration(ATileDeclaration node);
    void caseASizeDeclaration(ASizeDeclaration node);
    void caseARuleDeclaration(ARuleDeclaration node);
    void caseAUnorderedShapeReplacementDeclaration(AUnorderedShapeReplacementDeclaration node);
    void caseAOrderedShapeReplacementDeclaration(AOrderedShapeReplacementDeclaration node);
    void caseASingleShapeReplacementDeclaration(ASingleShapeReplacementDeclaration node);
    void caseAUnorderedShapeReplacement(AUnorderedShapeReplacement node);
    void caseAOrderedShapeReplacement(AOrderedShapeReplacement node);
    void caseABasicShapeReplacementBlock(ABasicShapeReplacementBlock node);
    void caseAListShapeReplacementBlock(AListShapeReplacementBlock node);
    void caseAStarOperator(AStarOperator node);
    void caseAPlusOperator(APlusOperator node);
    void caseAMinusOperator(AMinusOperator node);
    void caseASlashOperator(ASlashOperator node);
    void caseAArrowOperator(AArrowOperator node);
    void caseAPathOperation(APathOperation node);
    void caseAUnorderedPathCommand(AUnorderedPathCommand node);
    void caseAOrderedPathCommand(AOrderedPathCommand node);
    void caseAPathDeclaration(APathDeclaration node);
    void caseAUnorderedPathReplacementDeclaration(AUnorderedPathReplacementDeclaration node);
    void caseAOrderedPathReplacementDeclaration(AOrderedPathReplacementDeclaration node);
    void caseASinglePathReplacementDeclaration(ASinglePathReplacementDeclaration node);
    void caseAOperationPathReplacement(AOperationPathReplacement node);
    void caseACommandPathReplacement(ACommandPathReplacement node);
    void caseABasicPathReplacementBlock(ABasicPathReplacementBlock node);
    void caseAListPathReplacementBlock(AListPathReplacementBlock node);
    void caseAXOperationParameter(AXOperationParameter node);
    void caseAYOperationParameter(AYOperationParameter node);
    void caseAX1OperationParameter(AX1OperationParameter node);
    void caseAY1OperationParameter(AY1OperationParameter node);
    void caseAX2OperationParameter(AX2OperationParameter node);
    void caseAY2OperationParameter(AY2OperationParameter node);
    void caseARxOperationParameter(ARxOperationParameter node);
    void caseARyOperationParameter(ARyOperationParameter node);
    void caseARotateOperationParameter(ARotateOperationParameter node);
    void caseAParametersOperationParameter(AParametersOperationParameter node);
    void caseAColorCommandParameter(AColorCommandParameter node);
    void caseAGeometryCommandParameter(AGeometryCommandParameter node);
    void caseAStrokeCommandParameter(AStrokeCommandParameter node);
    void caseAParametersCommandParameter(AParametersCommandParameter node);
    void caseAHueBackgroundAdjustment(AHueBackgroundAdjustment node);
    void caseABrightnessBackgroundAdjustment(ABrightnessBackgroundAdjustment node);
    void caseASaturationBackgroundAdjustment(ASaturationBackgroundAdjustment node);
    void caseAAlphaBackgroundAdjustment(AAlphaBackgroundAdjustment node);
    void caseAXTileAdjustment(AXTileAdjustment node);
    void caseAYTileAdjustment(AYTileAdjustment node);
    void caseATileAdjustment(ATileAdjustment node);
    void caseAXSizeAdjustment(AXSizeAdjustment node);
    void caseAYSizeAdjustment(AYSizeAdjustment node);
    void caseASizeSizeAdjustment(ASizeSizeAdjustment node);
    void caseAColorShapeAdjustment(AColorShapeAdjustment node);
    void caseAGeometryShapeAdjustment(AGeometryShapeAdjustment node);
    void caseACurrentColorAdjustment(ACurrentColorAdjustment node);
    void caseATargetColorAdjustment(ATargetColorAdjustment node);
    void caseAHueCurrentColorAdjustment(AHueCurrentColorAdjustment node);
    void caseABrightnessCurrentColorAdjustment(ABrightnessCurrentColorAdjustment node);
    void caseASaturationCurrentColorAdjustment(ASaturationCurrentColorAdjustment node);
    void caseAAlphaCurrentColorAdjustment(AAlphaCurrentColorAdjustment node);
    void caseAHueTargetColorAdjustment(AHueTargetColorAdjustment node);
    void caseABrightnessTargetColorAdjustment(ABrightnessTargetColorAdjustment node);
    void caseASaturationTargetColorAdjustment(ASaturationTargetColorAdjustment node);
    void caseAAlphaTargetColorAdjustment(AAlphaTargetColorAdjustment node);
    void caseAXPathAdjustment(AXPathAdjustment node);
    void caseAYPathAdjustment(AYPathAdjustment node);
    void caseASizePathAdjustment(ASizePathAdjustment node);
    void caseASize2PathAdjustment(ASize2PathAdjustment node);
    void caseAFlipPathAdjustment(AFlipPathAdjustment node);
    void caseASkewPathAdjustment(ASkewPathAdjustment node);
    void caseARotatePathAdjustment(ARotatePathAdjustment node);
    void caseAXGeometryAdjustment(AXGeometryAdjustment node);
    void caseAYGeometryAdjustment(AYGeometryAdjustment node);
    void caseAZGeometryAdjustment(AZGeometryAdjustment node);
    void caseASizeGeometryAdjustment(ASizeGeometryAdjustment node);
    void caseASize2GeometryAdjustment(ASize2GeometryAdjustment node);
    void caseASize3GeometryAdjustment(ASize3GeometryAdjustment node);
    void caseAFlipGeometryAdjustment(AFlipGeometryAdjustment node);
    void caseASkewGeometryAdjustment(ASkewGeometryAdjustment node);
    void caseARotateGeometryAdjustment(ARotateGeometryAdjustment node);
    void caseANumberExpression(ANumberExpression node);
    void caseANestedExpression(ANestedExpression node);
    void caseAFunctionExpression(AFunctionExpression node);
    void caseANumberExtendedExpression(ANumberExtendedExpression node);
    void caseAFunctionExtendedExpression(AFunctionExtendedExpression node);
    void caseANestedExtendedExpression(ANestedExtendedExpression node);
    void caseAComposedExtendedExpression(AComposedExtendedExpression node);
    void caseAArg0Function(AArg0Function node);
    void caseAArg1Function(AArg1Function node);
    void caseAArg2Function(AArg2Function node);
    void caseAFirstExpression(AFirstExpression node);
    void caseASecondExpression(ASecondExpression node);
    void caseAThirdExpression(AThirdExpression node);

    void caseTBar(TBar node);
    void caseTStar(TStar node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTSlash(TSlash node);
    void caseTArrow(TArrow node);
    void caseTComma(TComma node);
    void caseTLCbkt(TLCbkt node);
    void caseTRCbkt(TRCbkt node);
    void caseTLRbkt(TLRbkt node);
    void caseTRRbkt(TRRbkt node);
    void caseTLSbkt(TLSbkt node);
    void caseTRSbkt(TRSbkt node);
    void caseTHueToken(THueToken node);
    void caseTSaturationToken(TSaturationToken node);
    void caseTBrightnessToken(TBrightnessToken node);
    void caseTAlphaToken(TAlphaToken node);
    void caseTTargetHueToken(TTargetHueToken node);
    void caseTTargetSaturationToken(TTargetSaturationToken node);
    void caseTTargetBrightnessToken(TTargetBrightnessToken node);
    void caseTTargetAlphaToken(TTargetAlphaToken node);
    void caseTXToken(TXToken node);
    void caseTYToken(TYToken node);
    void caseTZToken(TZToken node);
    void caseTRotateToken(TRotateToken node);
    void caseTSizeToken(TSizeToken node);
    void caseTFlipToken(TFlipToken node);
    void caseTSkewToken(TSkewToken node);
    void caseTParametersToken(TParametersToken node);
    void caseTStrokewidthToken(TStrokewidthToken node);
    void caseTX1Token(TX1Token node);
    void caseTY1Token(TY1Token node);
    void caseTX2Token(TX2Token node);
    void caseTY2Token(TY2Token node);
    void caseTRxToken(TRxToken node);
    void caseTRyToken(TRyToken node);
    void caseTCommand(TCommand node);
    void caseTOperation(TOperation node);
    void caseTFunctionArg0(TFunctionArg0 node);
    void caseTFunctionArg1(TFunctionArg1 node);
    void caseTFunctionArg2(TFunctionArg2 node);
    void caseTStartshape(TStartshape node);
    void caseTBackground(TBackground node);
    void caseTInclude(TInclude node);
    void caseTTile(TTile node);
    void caseTSize(TSize node);
    void caseTRule(TRule node);
    void caseTPath(TPath node);
    void caseTFilename(TFilename node);
    void caseTString(TString node);
    void caseTNumber(TNumber node);
    void caseTWhiteSpace(TWhiteSpace node);
    void caseTLineComment(TLineComment node);
    void caseTBlockComment(TBlockComment node);
    void caseEOF(EOF node);
}
