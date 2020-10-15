package com.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class DemoQueen {

	static Set<String> createObstaclesSet(int[][] obstacles) {
		Set<String> set = new HashSet<String>();
		for (int[] obstacle : obstacles) {
			set.add(obstacle[0] + "," + obstacle[1]);
		}
		return set;
	}

	static Map<String, Boolean> createObstaclesMap(int[][] obstacles) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (int[] obstacle : obstacles) {
			map.putIfAbsent(obstacle[0] + "," + obstacle[1], true);
		}
		return map;
	}

	static int queensAttackWithMap(int n, int k, int r_q, int c_q, int[][] obstacles) {

		// Set up an array for the intermediate results.
		int[] results = new int[8];

		Map<String, Boolean> map = createObstaclesMap(obstacles);

		Thread horizontalThread_ = new Thread(() -> {
			int score = 0;
			int c = c_q - 1;
			while (c > 0) { // horizontal-
				if (map.getOrDefault(r_q + "," + c, false))
					break;
				else
					score++;
				c--;
			}
			results[0] = score;

		});

		Thread horizontalThread = new Thread(() -> {
			int score = 0;
			int c = c_q + 1;
			while (c <= n) { // horizontal+
				if (map.getOrDefault(r_q + "," + c, false))
					break;
				else
					score++;
				c++;
			}
			results[1] = score;

		});

		Thread verticalThread_ = new Thread(() -> {
			int score = 0;
			int i = r_q - 1;
			while (i > 0) { // vertical-
				if (map.getOrDefault(i + "," + c_q, false))
					break;
				else
					score++;
				i--;
			}
			results[2] = score;

		});

		Thread verticalThread = new Thread(() -> {
			int score = 0;
			int i = r_q + 1;
			while (i <= n) { // vertical+
				if (map.getOrDefault(i + "," + c_q, false))
					break;
				else
					score++;

				i++;
			}
			results[3] = score;

		});

		Thread diagonal1Thread_ = new Thread(() -> {
			int score = 0;
			int i = r_q - 1;
			int c = c_q - 1;
			while (i > 0 && c > 0) {
				if (map.getOrDefault(i + "," + c, false))
					break;
				else
					score++;

				i--;
				c--;
			}

			results[4] = score;

		});

		Thread diagonal1Thread = new Thread(() -> {
			int score = 0;
			int i = r_q + 1;
			int c = c_q + 1;
			while (i <= n && c <= n) {
				if (map.getOrDefault(i + "," + c, false))
					break;
				else
					score++;
				c++;
				i++;
			}
			results[5] = score;

		});

		Thread diagonal2Thread_ = new Thread(() -> {
			// Diagonal 2 -
			int score = 0;
			int i = r_q - 1;
			int c = c_q + 1;
			while (i > 0 && c <= n) {
				if (map.getOrDefault(i + "," + c, false))
					break;
				else
					score++;
				i--;
				c++;

			}
			results[6] = score;

		});
		Thread diagonal2Thread = new Thread(() -> {
			int score = 0;
			int i = r_q + 1;
			int c = c_q - 1;
			while (c > 0 && i <= n) {
				if (map.getOrDefault(i + "," + c, false))
					break;
				else
					score++;

				i++;
				c--;
			}

			results[7] = score;

		});

		horizontalThread_.start();
		horizontalThread.start();
		verticalThread_.start();
		verticalThread.start();
		diagonal1Thread_.start();
		diagonal1Thread.start();
		diagonal2Thread_.start();
		diagonal2Thread.start();
		try {
			horizontalThread_.join();
			horizontalThread.join();
			verticalThread_.join();
			verticalThread.join();
			diagonal1Thread_.join();
			diagonal1Thread.join();
			diagonal2Thread_.join();
			diagonal2Thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return IntStream.of(results).sum();

	}

	static int queensAttackWithSet(int n, int k, int r_q, int c_q, int[][] obstacles) {

		// Set up an array for the intermediate results.
		int[] results = new int[8];

		Set<String> set = createObstaclesSet(obstacles);

		Thread horizontalThread_ = new Thread(() -> {
			int score = 0;
			int c = c_q - 1;
			while (c > 0) { // horizontal-
				if (set.contains(r_q + "," + c))
					break;
				else
					score++;
				c--;
			}
			results[0] = score;

		});

		Thread horizontalThread = new Thread(() -> {
			int score = 0;
			int c = c_q + 1;
			while (c <= n) { // horizontal+
				if (set.contains(r_q + "," + c))
					break;
				else
					score++;
				c++;
			}
			results[1] = score;

		});

		Thread verticalThread_ = new Thread(() -> {
			int score = 0;
			int i = r_q - 1;
			while (i > 0) { // vertical-
				if (set.contains(i + "," + c_q)) {
					break;
				} else
					score++;
				i--;
			}
			results[2] = score;

		});

		Thread verticalThread = new Thread(() -> {
			int score = 0;
			int i = r_q + 1;
			while (i <= n) { // vertical+
				if (set.contains(i + "," + c_q))
					break;
				else
					score++;

				i++;
			}
			results[3] = score;

		});

		Thread diagonal1Thread_ = new Thread(() -> {
			int score = 0;
			int i = r_q - 1;
			int c = c_q - 1;
			while (i > 0 && c > 0) {
				if (set.contains(i + "," + c))
					break;
				else
					score++;

				i--;
				c--;
			}

			results[4] = score;

		});

		Thread diagonal1Thread = new Thread(() -> {
			int score = 0;
			int i = r_q + 1;
			int c = c_q + 1;
			while (i <= n && c <= n) {
				if (set.contains(i + "," + c))
					break;
				else
					score++;
				c++;
				i++;
			}
			results[5] = score;

		});

		Thread diagonal2Thread_ = new Thread(() -> {
			// Diagonal 2 -
			int score = 0;
			int i = r_q - 1;
			int c = c_q + 1;
			while (i > 0 && c <= n) {
				if (set.contains(i + "," + c))
					break;
				else
					score++;
				i--;
				c++;

			}
			results[6] = score;

		});
		Thread diagonal2Thread = new Thread(() -> {
			int score = 0;
			int i = r_q + 1;
			int c = c_q - 1;
			while (c > 0 && i <= n) {

				if (set.contains(i + "," + c))
					break;
				else
					score++;

				i++;
				c--;
			}

			results[7] = score;

		});

		horizontalThread_.start();
		horizontalThread.start();
		verticalThread_.start();
		verticalThread.start();
		diagonal1Thread_.start();
		diagonal1Thread.start();
		diagonal2Thread_.start();
		diagonal2Thread.start();
		// Let the main thread wait until both threads are dead.
		try {
			horizontalThread_.join();
			horizontalThread.join();
			verticalThread_.join();
			verticalThread.join();
			diagonal1Thread_.join();
			diagonal1Thread.join();
			diagonal2Thread_.join();
			diagonal2Thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return IntStream.of(results).sum();

	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException, Exception {

		String[] nk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nk[0]);

		int k = Integer.parseInt(nk[1]);

		String[] r_qC_q = scanner.nextLine().split(" ");

		int r_q = Integer.parseInt(r_qC_q[0]);

		int c_q = Integer.parseInt(r_qC_q[1]);

		int[][] obstacles = new int[k][2];

		if (n <= 0 || n > Math.pow(10, 5) || k < 0 || k > Math.pow(10, 5))
			throw new Exception();

		for (int i = 0; i < k; i++) {
			String[] obstaclesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 2; j++) {
				int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
				obstacles[i][j] = obstaclesItem;
			}
		}

		long startTimeMap = System.nanoTime();
		int resultMap = queensAttackWithMap(n, k, r_q, c_q, obstacles);
		long stopTimeMap = System.nanoTime();

		long startTimeSet = System.nanoTime();
		int resultSet = queensAttackWithSet(n, k, r_q, c_q, obstacles);
		long stopTimeSet = System.nanoTime();

		System.out.println("Possible Moves: " + resultSet);
		System.out.println("--------------------------");
		System.out.println("Time with set: "
				+ TimeUnit.MILLISECONDS.convert(stopTimeSet - startTimeSet, TimeUnit.NANOSECONDS) + "ms");
		System.out.println("Time with map: "
				+ TimeUnit.MILLISECONDS.convert(stopTimeMap - startTimeMap, TimeUnit.NANOSECONDS) + "ms");

		scanner.close();
	}

}
