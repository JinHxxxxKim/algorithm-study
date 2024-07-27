package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 수열의 크기 입력
 * 2. 수열의 원소 입력
 * 3. 인덱스를 저장할 스택 초기화
 *
 * [ NGE() ]
 * 4. 수열의 원소를 돌며 오큰수를 찾는다
 *  4-1. 현재 스택이 비어있지 않고 && 스택의 peek 이 가르키는 인덱스가 현재 원소보다 작다면 반복
 *      4-1-1. 현재 원소가 더 크므로 pop 한 인덱스에 현재 원소 값 저장
 *  4-2. 스택에 현재 인덱스 push
 * 5. 변경 작업이 끝나고 스택이 비어있지 않다면 스택에 담긴 인덱스는 모두 -1 저장
 * 6. 최종 배열 출력
 */
public class s17298_오큰수_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int elementCount;
    static int[] elements;
    static Stack<Integer> idxStack;

    public static void NGE() {
        // 4. 수열의 원소를 돌며 오큰수를 찾는다
        for (int idx = 0; idx < elementCount; idx++) {

            // 4-1. 현재 스택이 비어있지 않고 && 스택의 peek 이 가르키는 인덱스가 현재 원소보다 작다면 반복
            while (!idxStack.isEmpty() && elements[idxStack.peek()] < elements[idx]) {
                // 4-1-1. 현재 원소가 더 크므로 pop 한 인덱스에 현재 원소 값 저장
                elements[idxStack.pop()] = elements[idx];
            }

            // 4-2. 스택에 현재 인덱스 push
            idxStack.push(idx);
        }

        // 5. 변경 작업이 끝나고 스택이 비어있지 않다면 스택에 담긴 인덱스는 모두 -1 저장
        while (!idxStack.isEmpty()) {
            elements[idxStack.pop()] = -1;
        }

        // 6. 최종 배열 출력
        for (int idx = 0; idx < elementCount; idx++) {
            sb.append(elements[idx]).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();

        NGE();
    }

    public static void init() throws IOException {
        // 1. 수열의 크기 입력
        elementCount = Integer.parseInt(br.readLine().trim());

        // 2. 수열의 원소 입력
        elements = new int[elementCount];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < elementCount; idx++) {
            elements[idx] = Integer.parseInt(st.nextToken());
        }

        // 3. 인덱스를 저장할 스택 초기화
        idxStack = new Stack<>();
    }
}