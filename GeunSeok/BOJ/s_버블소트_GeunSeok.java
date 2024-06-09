import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1517_버블소트
 * @author parkrootseok
 *
 * 버블 소트를 진행했을 때 Swap한 횟수를 출력
 *
 * 1. 배열에 대한 정보를 입력
 * 2. Merge Sort를 이용해 Swap 횟수를 카운팅
 */
public class s_버블소트_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int size;
	public static long[] numbers;
	public static long[] sortedNumbers;
	public static long swapCount;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 배열에 대한 정보를 입력
		size = Integer.parseInt(br.readLine().trim());
		numbers = new long[size];
		sortedNumbers = new long[size];

		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < size; index++) {
			numbers[index] = Integer.parseInt(inputs[index]);
		}

		// 2. Merge Sort를 이용해 Swap 횟수를 카운팅
		swapCount = 0;
		mergeSort(0, size - 1);

		sb.append(swapCount);
		bw.write(sb.toString());
		bw.close();

	}

	public static void mergeSort(int left, int right) {

		if (left >= right)  {
			return;
		}

		int mid = (left + right) / 2;
		mergeSort(left, mid);
		mergeSort(mid + 1, right);
		merge(left, mid, right);

	}

	public static void merge(int left, int mid, int right) {

		int index = left;
		int leftPointer = left;
		int rightPointer = mid + 1;
		while (leftPointer <= mid && rightPointer <= right) {

			// 왼쪽 분할 영역에 있는 원소가 더 작은 경우
			if (numbers[leftPointer] <= numbers[rightPointer]) {
				sortedNumbers[index] = numbers[leftPointer++];
			}

			// 오른쪽 분할 영역에 있는 원소가 더 작은 경우
			else {
				sortedNumbers[index] = numbers[rightPointer++];

				// 왼쪽 분할 영역의 남은 원소의 개수를 덧셈
				swapCount += (mid + 1 - leftPointer);
			}

			index++;

		}

		while (leftPointer <= mid) {
			sortedNumbers[index++] = numbers[leftPointer++];
		}

		while (rightPointer <= right) {
			sortedNumbers[index++] = numbers[rightPointer++];
		}

		for (index = left; index <= right; index++) {
			numbers[index] = sortedNumbers[index];
		}

	}


}
