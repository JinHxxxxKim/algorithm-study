package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 동전의 개수와 만들어야 하는 금액 입력
 * 2. 동전의 단위 입력
 *
 * [ solution ]
 * 3. 가장 큰 동전의 단위부터 선택
 */
public class s11047_동전0_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX_LENG = 100_000_001;

    static int totalCoinCount, targetMoney;
    static int[] coins;
    static int useCoinCount;

    public static void solution() {
        useCoinCount = 0;

        for (int idx = totalCoinCount-1; idx >= 0; idx--) {
            int curCoin = coins[idx];

            if (curCoin > targetMoney)
                continue;

            useCoinCount += targetMoney / curCoin;
            targetMoney = targetMoney % curCoin;

            if (targetMoney == 0)
                break;
        }

        System.out.println(useCoinCount);
    }

    public static void main(String[] args) throws IOException {
        init();

        solution();
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        totalCoinCount = Integer.parseInt(st.nextToken());
        targetMoney = Integer.parseInt(st.nextToken());

        coins = new int[totalCoinCount];
        for (int idx = 0; idx < totalCoinCount; idx++) {
            coins[idx] = Integer.parseInt(br.readLine().trim());
        }
    }
}