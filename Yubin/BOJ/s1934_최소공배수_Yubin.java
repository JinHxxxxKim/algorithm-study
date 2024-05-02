import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 두 자연수 쌍이 여러 개 주어질 때, 최소 공배수를 각각 구한다.
 */
public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    static int numOfCases;

    static int solution(int numA, int numB) {
        int smaller = numA < numB ? numA : numB;
        int bigger = numA > numB ? numA : numB;

        while (smaller != 0) {
            int nextBigger = smaller;
            smaller = bigger % smaller;
            bigger = nextBigger;
        }

        return numA * numB / bigger;
    }

    public static void main(String[] args) throws Exception {
        // 구해야 할 최소 공배수의 수
        numOfCases = Integer.parseInt(in.readLine());
        for (int testCase = 0; testCase < numOfCases; testCase++) {
            st = new StringTokenizer(in.readLine());
            int numA = Integer.parseInt(st.nextToken());
            int numB = Integer.parseInt(st.nextToken());

            int result = solution(numA, numB);
            out.append(result).append('\n');
        }
        System.out.println(out);
    }
}
