package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 1. 카드의 개수를 입력받는다.
 * 2. N 번 카드를 큐에 삽입한다.
 * 3. N-1 번 카드부터 1 번 카드까지 큐에 삽입한다.
 *  3-1. 카드의 숫자만큼 tail 의 숫자를 빼어 head 에 삽입을 반복
 */
public class s1835_카드_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int cardCount;

    static Deque<Integer> cardQueue;
    static Iterator<Integer> printIterator;

    public static void main(String[] args) throws IOException {
        cardCount = Integer.parseInt(br.readLine().trim());

        cardQueue = new ArrayDeque<>();

        // 2. N 번 카드를 큐에 삽입한다.
        cardQueue.addFirst(cardCount);
        for (int card = cardCount-1; card >= 1; card--) {
            int nextCard = card;

            // 3. N-1 번 카드부터 1 번 카드까지 큐에 삽입한다.
            cardQueue.addFirst(nextCard);

            // 3-1. 카드의 숫자만큼 tail 의 숫자를 빼어 head 에 삽입을 반복
            while(nextCard-- > 0) {
                int tailToHead = cardQueue.pollLast();
                cardQueue.addFirst(tailToHead);
            }
        }

        // iterator 를 이용한 출력
        printIterator = cardQueue.iterator();
        while (printIterator.hasNext()) {
            int element = printIterator.next();
            sb.append(element).append(" ");
        }

        System.out.println(sb);
    }
}