import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * BOJ_1546_평균
 * @author parkrootseok
 *
 * 1. 시험 과목 개수와 그에 대한 점수를 입력
 * 2. 평균 계산 후 출력
 */
public class s_평균_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int examNumber;
	public static double[] scores;
	public static int maxScore;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 시험 과목 개수와 그에 대한 점수를 입력
		examNumber = Integer.parseInt(br.readLine().trim());

		maxScore = 0;
		scores = new double[examNumber];
		inputs = br.readLine().trim().split(" ");
		for (int curExam = 0; curExam < examNumber; curExam++) {
			scores[curExam] = Integer.parseInt(inputs[curExam]);
			maxScore = Math.max(maxScore, (int) scores[curExam]);
		}

		// 2. 평균 계산 후 출력
		double result = 0;
		for (double score : scores) {
			result += (score / maxScore * 100.0);
		}

		sb.append(result / examNumber);
		bw.write(sb.toString());
		bw.close();

	}


}
