import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder out = new StringBuilder();

    static void solution(int numOfCards) {
        Deque<Integer> cards = new LinkedList<>();

        // 숫자가 작은 카드부터 빼므로, 복구할 때는 큰 카드부터 시작
        cards.add(numOfCards);
        for (int card = numOfCards - 1; card >= 1; card--) {
            // card가 맨 앞에 있는 상황을 만들어줌
            cards.addFirst(card);
            // 맨앞에 있는 카드를 뒤로 옮기는 과정을 반대로 수행
            // = 맨 뒤의 카드를 맨 앞으로 옮김
            for (int cnt = 0; cnt < card; cnt++) {
                cards.addFirst(cards.removeLast());
            }
        }

        // 모든 카드에 대한 빼기를 거꾸로 수행했으므로, 출력
        while (!cards.isEmpty()) {
            out.append(cards.removeFirst()).append(' ');
        }
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(in.readLine());
        solution(n);
        System.out.println(out);
    }
}import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder out = new StringBuilder();

    static void solution(int numOfCards) {
        Deque<Integer> cards = new LinkedList<>();

        // 숫자가 작은 카드부터 빼므로, 복구할 때는 큰 카드부터 시작
        cards.add(numOfCards);
        for (int card = numOfCards - 1; card >= 1; card--) {
            // card가 맨 앞에 있는 상황을 만들어줌
            cards.addFirst(card);
            // 맨앞에 있는 카드를 뒤로 옮기는 과정을 반대로 수행
            // = 맨 뒤의 카드를 맨 앞으로 옮김
            for (int cnt = 0; cnt < card; cnt++) {
                cards.addFirst(cards.removeLast());
            }
        }

        // 모든 카드에 대한 빼기를 거꾸로 수행했으므로, 출력
        while (!cards.isEmpty()) {
            out.append(cards.removeFirst()).append(' ');
        }
    }

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(in.readLine());
        solution(n);
        System.out.println(out);
    }
}