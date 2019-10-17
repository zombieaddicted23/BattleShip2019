import java.util.Scanner; 

public class BattleShip {
	public static final int MAX_SHOTS =20;
	public static final int SHIP_SYMBOL = 'S';
	public static final int WATER_SYMBOL = 'O';
	public static final int SUNK_SHIP_SYMBOL = 'X';
	public static final int EMPTY_SYMBOL = '.';
	static int remainingShots;
	public static final int NUM_SHIPS = 10;
	public static final int DIMENSION = 8;	
	static char[][] matrix = new char[DIMENSION][DIMENSION];
	static boolean gameOver;
	static int sunkShipsCounter;
	
	public static void main(String[] args) {
		
		char letter;
		int number;
		Scanner input = new Scanner(System.in);
		sunkShipsCounter=0;	     	     	
		gameOver = false;
		remainingShots = MAX_SHOTS;
	    initMatrix();
	    addShipsToMatrix();
	    	     
	    while(!gameOver) {
	    	printMatrix(false);
	    	System.out.println("Enter row (Letter):");
	    	letter = input.next().toUpperCase().charAt(0);
	    	System.out.println("Enter column (Number): ");
	    	number = input.nextInt();
	    	shoot(letter, number);
	    	checkGameOver();
	    }
	    
	    showResult();
	     
	}
	/*private static void askCoordinates(Scanner input) {
		letter = 'º';
		boolean firstValue = true;
		while (!letterInGoodRange(firstValue)) {
			System.out.println("Enter row (Letter):");
			letter = input.next().toUpperCase().charAt(0);
			firstValue = false;
		}
		number = -1;
		firstValue = true;
		while (!numberInGoodRange(firstValue)) {
			System.out.println("Enter column (Number): ");
			number = input.nextInt();
			firstValue = false;
		}
	}*/
	private static void showResult() {
		if (sunkShipsCounter >= NUM_SHIPS ) {
			System.out.println("You Win");
		}else {
			System.out.println("BETTER LUCK NEXT TIME");
		}
	}

	private static void checkGameOver() {
		if (sunkShipsCounter >= NUM_SHIPS || remainingShots<=0) {
			gameOver = true;
		}
		
		
	}

	private static void shoot(char letter, int number) {
		
		int row = letter - 'A';
		int col = number - 1;
		
		remainingShots --;
		
			if(matrix[row][col] == WATER_SYMBOL || matrix[row][col] == SUNK_SHIP_SYMBOL) {
				System.out.println("You have already shot in that position");
			}else {
				if (matrix[row][col] == SHIP_SYMBOL)	{
					matrix[row][col] = SUNK_SHIP_SYMBOL;
					sunkShipsCounter++;
					
				} else {
				matrix[row][col] = WATER_SYMBOL;
				}
				
			}
			
	}

	private static void addShipsToMatrix() {
		
		long shipCounter = 0;
		int randomRow, randomCol;
		
		while (shipCounter < NUM_SHIPS) {
			randomRow = (int) (Math.random() * DIMENSION);
			randomCol= (int) (Math.random() * DIMENSION);
	
			if (matrix[randomRow][randomCol] != SHIP_SYMBOL) {
				matrix[randomRow][randomCol] = SHIP_SYMBOL;
				shipCounter ++;
			}
		}
		
	}

	public static void initMatrix() {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				matrix[row][col] = EMPTY_SYMBOL;				
			}
		}
	}
	
	public static void printMatrix(boolean debug) {
		printHeader();
		char c = 'A';
		for (int row = 0; row < matrix.length; row++) {
			System.out.print(c + " ");
			c++;
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] == SHIP_SYMBOL) {
					if (debug) {
						System.out.print(matrix[row][col] + " ");
					} else {
						System.out.print(Character.toString(EMPTY_SYMBOL)
								+ " ");
					}
				} else {
					System.out.print(matrix[row][col] + " ");
				}			
			}
			System.out.println();
		}
	}

	private static void printHeader() {
		System.out.print("  ");
		for(int i = 1; i <= matrix[0].length; i++ ) {
			System.out.print(i + " ");
		}
		System.out.println();
		
	}

}
