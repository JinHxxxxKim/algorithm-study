package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 모노톤 스택 활용
 * stack 이라는 변수를 두어, 현재까지 오큰수를 찾지 못한 원소들을 저장해 둔다.
 * stack에서 원소를 제거할 때 오큰수의 값을 업데이트해야 하므로, 원소의 값과 인덱스 정보를 함께 저장한다.
 *
 * 1. 변수 초기화
 * 2. 수열 A의 각 원소에 대해, 다음을 반복한다.
 *    2-1. 현재 원소가 특정 원소의 오큰수가 되는 경우를 찾아 스택에서 제거한다.
 *         스택의 마지막 원소의 값이 현재 원소의 값보다 작다면, 스택에서 제거한다.
 *         (마지막 원소부터 확인해도 되는 이유는, 스택이 내림차순 정렬되어 있음이 보장되기 때문)
 *    2-2. 현재 원소는 아직 오큰수를 찾지 못한 상태이므로, 스택에 넣는다.
 * 3. 반복문이 끝난 후 스택에 남아 있는 원소들은 오큰수가 -1이다. 스택에서 원소들을 제거하며 업데이트해 준다.
 * 4. 정답을 출력
 */

public class s_17298_오큰수_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] seq;
    static Deque<Element> stack;
    static int[] NGE;
    static class Element {
        int idx;
        int val;

        public Element(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 수열 A의 각 원소에 대해, 다음을 반복한다.
        for (int idx=0; idx<N; idx++) {
            int val = seq[idx];

            // 2-1. 현재 원소가 특정 원소의 오큰수가 되는 경우
            while (!stack.isEmpty() && stack.getLast().val < val) {
                Element element = stack.removeLast();
                NGE[element.idx] = val;
            }

            // 2-2. 현재 원소는 아직 오큰수를 찾지 못한 상태이므로, 스택에 넣는다.
            stack.addLast(new Element(idx, val));
        }

        // 3. 반복문이 끝난 후 스택에 남아 있는 원소들은 오큰수가 -1이다
        while (!stack.isEmpty()) {
            Element element = stack.removeLast();
            NGE[element.idx] = -1;
        }

        // 4. 정답 출력
        for (int val: NGE) {
            sb.append(val).append(" ");
        }

        System.out.println(sb);

    }



    private static void setInitVariable() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        seq = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<N; idx++) {
            seq[idx] = Integer.parseInt(st.nextToken());
        }

        // 모노톤 스택
        stack = new ArrayDeque<>();
        NGE = new int[N];
    }


}
