package jdkCommonMisunderstanding;

/**
 * Created with IntelliJ IDEA.
 * User: yujj
 * Date: 4/26/14
 * Time: 2:19 PM
 */
public class FinalVariable {

    public final  Integer variable ;

    public FinalVariable(Integer variable) {
        this.variable = variable;
    }

    public Integer getVariable() {
        return variable;
    }

    public static void main(String[] args) {

        FinalVariable finalVariable = new FinalVariable(10);
        Integer tmp = finalVariable.getVariable();
        tmp = 11;
        System.out.println(tmp);
        System.out.println(finalVariable.getVariable());
    }
}
