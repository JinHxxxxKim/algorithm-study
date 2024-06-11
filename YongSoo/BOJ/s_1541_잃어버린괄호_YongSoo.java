package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 어렵다...
 * 정규표현식 부분은 인터넷을 참고하여 작성
 *
 * 괄호를 적절히 사용하면, 처음 나오는 - 연산자 이후의 모든 연산자를 -로 바꿔서 계산할 수 있다.(이 때가 최소값)
 *
 * 1. 변수 초기화
 * 2. 정규표현식을 이용하여, 식을 숫자 부분(numbers)와 연산자 부분(operators)으로 나눔
 * 3. 처음 나오는 -의 위치(firstMinusOperatorIdx)를 찾는다.
 * 4. firstMinusOperatorIdx 이후의 모든 연산자는, -로 바꿔서 계산한다.
 * 5. 정답을 출력
 */

public class s_1541_잃어버린괄호_YongSoo {
    static BufferedReader br;
    static String equation;
    static ArrayList<Integer> numbers;
    static ArrayList<String> operators;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 정규표현식을 이용하여, 식을 숫자 부분(numbers)와 연산자 부분(operators)으로 나눔
        String patternString = "([+-])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(equation);

        int lastIndex = 0;

        operators = new ArrayList<>();
        numbers = new ArrayList<>();

        while (matcher.find()) {
            numbers.add(Integer.parseInt(equation.substring(lastIndex, matcher.start())));
            operators.add(matcher.group());

            lastIndex = matcher.end();
        }

        // 마지막 숫자 부분을 numbers에 더해 줌
        numbers.add(Integer.parseInt(equation.substring(lastIndex)));

        // 출력할 정답
        int minValue = numbers.get(0);

        // 3. 처음 나오는 -의 위치(firstMinusOperatorIdx)를 찾는다.
        int firstMinusOperatorIdx=0;

        while (firstMinusOperatorIdx < operators.size() && operators.get(firstMinusOperatorIdx).equals("+")) {
            minValue = operate(minValue, "+", numbers.get(firstMinusOperatorIdx+1));
            firstMinusOperatorIdx++;
        }

        // 4. firstMinusOperatorIdx 이후의 모든 연산자는, -로 바꿔서 계산한다.
        for (int idx=firstMinusOperatorIdx; idx<operators.size(); idx++) {
            minValue = operate(minValue, "-", numbers.get(idx+1));
        }

        // 5. 정답을 출력
        System.out.println(minValue);
    }

    private static int operate(int num1, String operator, int num2) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        equation = br.readLine().trim();
    }
}
