package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 남은 일할 날짜의 수를 입력받는다.
 * 2. 상담을 완료하는데 걸리는 시간, 금액을 저장한다.
 *  2-1. 시간과 금액을 저장하는 Consult 클래스
 *
 * [ makePlan() ]
 * 3. 각 상담을 선택 하는 경우와 안하는 경우를 나누어 재귀 호출
 *  3-1. 모든 상담을 선택했다면 종료
 *      3-1-1. 최댓값 갱신
 *  3-2. 현재 상담을 선택하는 날짜 + 선택할 날짜가 N+1 보다 작은 경우에만 선택 가능
 *  3-3. 선택하지 않는다면 1일 증가
 *  3-4. 선택이 끝나면 최댓값 갱신
 */
public class s14501_퇴사_SangHu {
    static class Consult {
        int period;
        int price;

        public Consult(int period, int price) {
            this.period = period;
            this.price = price;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int totalPeriod;
    static Consult[] consultList;

    static int maxTotalPrice;

    public static void makePlan(int selectDay, int curTotalPrice) {
        // 3-1. 모든 상담을 선택했다면 종료
        if (selectDay >= totalPeriod) {
            // 3-1-1. 최댓값 갱신
            maxTotalPrice = Math.max(maxTotalPrice, curTotalPrice);
            return;
        }

        // 3-2. 현재 상담을 선택하는 날짜 + 선택할 날짜가 N+1 보다 작은 경우에만 선택 가능
        if (selectDay + consultList[selectDay].period <= totalPeriod) {
            makePlan(selectDay + consultList[selectDay].period, curTotalPrice + consultList[selectDay].price);
        }

        // 3-3. 선택하지 않는다면 1일 증가
        makePlan(selectDay + 1, curTotalPrice);

        // 3-4. 선택이 끝나면 최댓값 갱신
        maxTotalPrice = Math.max(maxTotalPrice, curTotalPrice);
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        maxTotalPrice = 0;

        // 3. 각 상담을 선택 하는 경우와 안하는 경우를 나누어 재귀 호출
        makePlan(0, 0);

        sb.append(maxTotalPrice);
        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        // 1. 남은 일할 날짜의 수를 입력받는다.
        totalPeriod = Integer.parseInt(br.readLine().trim());

        // 2. 상담을 완료하는데 걸리는 시간, 금액을 저장한다.
        consultList = new Consult[totalPeriod];
        for (int idx = 0; idx < totalPeriod; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int period = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            // 2-1. 시간과 금액을 저장하는 Consult 클래스
            consultList[idx] = new Consult(period, price);
        }
    }
}