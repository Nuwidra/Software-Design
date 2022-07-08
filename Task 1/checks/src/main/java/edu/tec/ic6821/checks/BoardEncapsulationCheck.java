package edu.tec.ic6821.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BoardEncapsulationCheck extends AbstractCheck {

    private Set<String> allowedMethods;

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.CLASS_DEF};
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    public void setAllowedMethods(String... methods) {
        this.allowedMethods = new HashSet<>(Arrays.asList(methods));
    }

    @Override
    public void visitToken(DetailAST ast) {
        // we only want to check the "Board" class
        DetailAST ident = ast.findFirstToken(TokenTypes.IDENT);
        if (!"Board".equals(ident.getText())) {
            return;
        }

        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
        DetailAST methodDef = objBlock.findFirstToken(TokenTypes.METHOD_DEF);
        while (methodDef != null) {
            if (methodDef.getType() == TokenTypes.METHOD_DEF) {
                checkMethod(methodDef);
            }
            methodDef = methodDef.getNextSibling();
        }
    }

    private void checkMethod(DetailAST methodDef) {
        DetailAST modifiers = methodDef.findFirstToken(TokenTypes.MODIFIERS);
        DetailAST publicModifier = modifiers.findFirstToken(TokenTypes.LITERAL_PUBLIC);
        // ignore non public methods
        if (publicModifier == null) {
            return;
        }

        DetailAST ident = methodDef.findFirstToken(TokenTypes.IDENT);
        if (ident.getText().startsWith("get")
            || ident.getText().startsWith("set")) {
            log(methodDef.getLineNo(),
                "[IC-6821] el uso de getters y setters en esta clase rompe el principio de encapsulamiento");
        }

        if (!allowedMethods.contains(ident.getText())) {
            log(methodDef.getLineNo(),
                "[IC-6821] los únicos métodos públicos requeridos para esta clase son " + allowedMethods.toString());
        }
    }
}
