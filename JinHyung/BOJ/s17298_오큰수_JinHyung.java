package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [오큰수] - BOJ
 * Stack
 *
 * 1. 수열을 입력받는다.
 * 2. 수열의 뒤에서부터 오큰수를 확인한다.
 *   CASE 1. 스택이 비어있다
 *     -> -1 출력 & 현재 확인 중인 수를 스택에 삽입
 *   CASE 2. 스택이 비어있지 않다.
 *     -> peek()한 수가 현재 수보다 클 때까지 poll()
 *     -> peek()한 수가 현재 수보다 크다면 해당 수 출력
 *     -> 현재 확인 중인 수 push()
 */
public class s17298_오큰수_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] numbers; // 수열
    static int[] oNumbers; // 오큰수 배열
    static Stack<Integer> stack; // 오큰수 판별 수열

    public static void main(String[] args) throws Exception {
        // 변수 초기화 및 입력
        N = Integer.parseInt(br.readLine().trim());
        numbers = new int[N];
        oNumbers = new int[N];
        stack = new Stack<>();

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; ++idx) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        // 로직 시작
        // 1. 수열의 뒤에서부터 오큰수를 확인한다.
        for (int idx = N - 1; idx >= 0; --idx) {
            int currNumber = numbers[idx];

            // CASE 2. 스택이 비어있지 않다.
            while (!stack.isEmpty()) {
                // peek()한 수가 현재 수보다 클 때까지 pop()
                if (stack.peek() <= currNumber) {
                    stack.pop();
                }else {
                    // peek()한 수가 현재 수보다 크다면 해당 수 출력
                    oNumbers[idx] = stack.peek();
                    stack.push(currNumber);
                    break;
                }

            }
            // CASE 1. 스택이 비어있다
            if (stack.isEmpty()) {
                oNumbers[idx] = -1;
                stack.push(currNumber);
            }
        }
        for (int oNumber : oNumbers) {
            sb.append(oNumber).append(" ");
        }
        System.out.println(sb);
    }
}
