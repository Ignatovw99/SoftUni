public class MathOperation implements Addable {
    @Override
    public int add(int firstOperand, int secondOperand) {
        return firstOperand + secondOperand;
    }

    @Override
    public int add(int firstOperand, int secondOperand, int thirdOperand) {
        return firstOperand + secondOperand + thirdOperand;
    }

    @Override
    public int add(int firstOperand, int secondOperand, int thirdOperand, int fourthOperand) {
        return firstOperand + secondOperand + thirdOperand + fourthOperand;
    }
}
