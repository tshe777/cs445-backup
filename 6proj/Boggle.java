import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle {
	// static long startTime,endTime; // for timing
	// static final long MILLISEC_PER_SEC = 1000;
	static int DFScounter = 0;
	static String[][] board;
	static int MINWORDSIZE = 3;
	static TreeSet<String> match = new TreeSet<String>();
	static TreeSet<String> dictionary = new TreeSet<String>();

	public static void main(String args[]) throws Exception {

		// startTime= System.currentTimeMillis();
		board = loadBoard(args[1]);

		BufferedReader dFile = new BufferedReader(new FileReader(args[0]));
		while (dFile.ready()) {
			String line = dFile.readLine();
			boolean validPossibleWord = line.length() >= MINWORDSIZE && line.length() <= Math.pow(board.length, 2);
			if (validPossibleWord)
				dictionary.add(line);
		}
		dFile.close();

		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs(row, col, "");

		// int numWordsFormed = 0;
		for (String hits : match) {
			System.out.println(hits);
			// ++numWordsFormed;
		}
		System.out.println(DFScounter);
		// System.out.println("NUMBER OF WORDS FORMED: " + numWordsFormed);

		// endTime = System.currentTimeMillis(); // for timing
		// System.out.println("GENERATION COMPLETED: runtime=" +
		// (endTime-startTime)/MILLISEC_PER_SEC );

	} // END MAIN

	static void dfs(int r, int c, String word) {
		DFScounter++;
		word += board[r][c];
		boolean foundMatch = word.length() >= MINWORDSIZE && dictionary.contains(word);
		boolean prefixNotInDict = !dictionary.higher(word).startsWith(word);
		if (foundMatch)
			match.add(word);
		else if (prefixNotInDict)
			return;

		// LOOKING NORTH r-1, c
		if (r - 1 >= 0 && board[r - 1][c] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r - 1, c, word);
			board[r][c] = unMarked;
		}

		// LOOKING NORTH EAST r-1, c+1
		if (r - 1 >= 0 && c + 1 < board[r - 1].length && board[r - 1][c + 1] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r - 1, c + 1, word);
			board[r][c] = unMarked;
		}

		// LOOKING EAST r, c+1
		if (c + 1 < board[r].length && board[r][c + 1] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r, c + 1, word);
			board[r][c] = unMarked;
		}
		// LOOKING SOUTH EAST r+1, c+1
		if (r + 1 < board.length && c + 1 < board[r].length && board[r + 1][c + 1] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r + 1, c + 1, word);
			board[r][c] = unMarked;
		}

		// LOOKING SOUTH r+1, c
		if (r + 1 < board.length && board[r + 1][c] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r + 1, c, word);
			board[r][c] = unMarked;
		}
		// LOOKING SOUTH WEST r+1, c-1
		if (r + 1 < board.length && c - 1 >= 0 && board[r + 1][c - 1] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r + 1, c - 1, word);
			board[r][c] = unMarked;
		}

		// LOOKING WEST r, c-1
		if (c - 1 >= 0 && board[r][c - 1] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r, c - 1, word);
			board[r][c] = unMarked;
		}
		// LOOKING NORTH WEST r-1, c-1
		if (r - 1 >= 0 && c - 1 >= 0 && board[r - 1][c - 1] != null) {
			String unMarked = board[r][c];
			board[r][c] = null;
			dfs(r - 1, c - 1, word);
			board[r][c] = unMarked;
		}

	} // END DFS

	static String[][] loadBoard(String fileName) throws Exception {
		Scanner infile = new Scanner(new File(fileName));
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} // END LOADBOARD

} // END BOGGLE CLASS