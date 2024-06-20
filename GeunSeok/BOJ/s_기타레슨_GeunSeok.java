import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2343_기타레슨
 * @author parkrootseok
 *
 * N개의 강의가 존재하고 M개의 블루레이에 이를 녹화할 때 블루레이의 크기를 최소값을 구하라.
 * 단, N개의 강의는 연속으로 녹화되어야 한다.
 *
 * 1. 강의와 블루레이의 개수를 입력
 * 2. 강의에 대한 정보를 입력
 * 3. 이진 탐색을 활용하여 최소값을 탐색
 *  3-1. 현재 mid를 기준으로 총 몇개의 블루레이가 필요한지 계산
 *  3-2. 현재 총 블루레이 개수가 요구한 블루레이 개수보다 클 경우 현재 기준보다 더 많은 강의 시간을 포함시켜야 하므로 start 증가
 *  3-3. 그게 아니고 블루레이 개수가 적거나 같다면 현재 기준보다 더 많이 담을 수 있으므로 end 감소
 */
public class s_기타레슨_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;;

	public static int lectureNumber;
	public static int[] lecture;
	public static int blurayNumber;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 강의와 블루레이의 개수를 입력
		inputs = br.readLine().trim().split(" ");
		lectureNumber = Integer.parseInt(inputs[0]);
		blurayNumber = Integer.parseInt(inputs[1]);

		// 2. 강의에 대한 정보 입력 및 이진 탐색에 활용할 포인터 초기화
		int start = 0;
		int end = 0;
		lecture = new int[lectureNumber];
		inputs = br.readLine().trim().split(" ");
		for (int curLectureIndex = 0; curLectureIndex < lectureNumber ; curLectureIndex++) {
			
			lecture[curLectureIndex] = Integer.parseInt(inputs[curLectureIndex]);
			
			// 시작 지점은 제일 시간이 긴 강의 (나올수 있는 가장 작은 사이즈의 블루레이)
			start = Math.max(start, lecture[curLectureIndex]);
			
			// 끝 지점은 모든 강의 시간 (나올 수 있는 가장 큰 사이즈의 블루레이)
			end += lecture[curLectureIndex];
			
		}

		// 3. 이진 탐색을 활용하여 최소값을 탐색
		while (start <= end) {

			int mid = (start + end) / 2;
			int totalLectureLength = 0;
			int totalblurayNumber = 0;

			// 3-1. 현재 mid를 기준으로 총 몇개의 블루레이가 필요한지 계산
			for (int curLectureIndex = 0; curLectureIndex < lectureNumber; curLectureIndex++) {

				// 총 강의 시간이 현재 확인할 최소 시간 보다 크다면
				if (totalLectureLength + lecture[curLectureIndex] > mid) {
					// 블루레이 개수 증가 및 현재 강의 시간 초기화
					totalblurayNumber++;
					totalLectureLength = 0;
				}
				
				totalLectureLength += lecture[curLectureIndex];
				
			}

			// 만약, 현재 강의 시간이 존재하다면 블루레이 개수를 증가
			if (totalLectureLength > 0) {
				totalblurayNumber++;
			}

			// 3-2. 현재 총 블루레이 개수가 요구한 블루레이 개수보다 클 경우 현재 기준보다 더 많은 강의 시간을 포함시켜야 하므로 start 증가
			if (totalblurayNumber > blurayNumber) {
				start = mid + 1;
			}

			// 3-3. 그게 아니고 블루레이 개수가 적거나 같다면 현재 기준보다 더 많이 담을 수 있으므로 end 감소
			else {
				end = mid - 1;
			}
			
		}

		sb.append(start);
		bw.write(sb.toString());
		bw.close();

	}

}
