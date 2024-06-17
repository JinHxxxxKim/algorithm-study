package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

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

        for (int idx=0; idx<N; idx++) {
            int val = seq[idx];
            while (!stack.isEmpty() && stack.getLast().val < val) {
                Element element = stack.removeLast();
                NGE[element.idx] = val;
            }

            stack.addLast(new Element(idx, val));
        }

        while (!stack.isEmpty()) {
            Element element = stack.removeLast();
            NGE[element.idx] = -1;
        }

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

        stack = new ArrayDeque<>();
        NGE = new int[N];
    }


}
