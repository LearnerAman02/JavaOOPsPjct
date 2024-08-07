import java.util.*;

class TicTacToe {
  static char board[][];

  public TicTacToe() {
    board = new char[3][3];
    initBoard();
    displayBoard();
  }

  // by default value of characters in 2D Matrix is /U0000
  void initBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = ' ';
      }
    }
  }

  static void displayBoard() {
    System.out.println(" -------------");
    for (int i = 0; i < board.length; i++) {
      System.out.print(" | ");
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " | ");
      }
      System.out.println();
      System.out.println(" -------------");
    }
  }

  // placing 'x' or 'o'
  // make sure that initially we dont have ' ' everywhere when we are checking for
  // game win
  static void placeMark(int row, int column, char mark) {
    if (row >= 0 && row < 3 && column >= 0 && column < 3) {
      board[row][column] = mark;
    } else {
      System.out.println("Invalid Input");
    }
  }

  static boolean checkColumnWin() {
    for (int j = 0; j < 3; j++) {
      if (board[0][j] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
        return true;
      }
    }
    return false;
  }

  static boolean checkRowWin() {
    for (int i = 0; i < 3; i++) {
      if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
        return true;
      }
    }
    return false;
  }

  static boolean checkDiagonalWin() {
    if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
      return true;
    } else if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
      return true;
    } else {
      return false;
    }
  }

  static boolean checkDraw() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          return false;
        }
      }
    }
    return true;
  }
}

abstract class Player {
  String name;
  char mark;

  abstract void makeMove();

  boolean isValidMove(int row, int col) {
    if (row >= 0 && row < 3 && col >= 0 && col < 3) {
      if (TicTacToe.board[row][col] == ' ') {
        return true;
      } else {
        // already iss cell pe move ho chuka hai
        System.out.println("Blocked Move");
        return false;
      }
    } else {
      // invalid cell pe move ho rha hai
      System.out.println("Invalid Move!!");
    }
    return false;
  }
}

class HumanPlayer extends Player {

  HumanPlayer(String name, char mark) {
    // to prevent shadowing problem this keyword is used
    this.name = name;
    this.mark = mark;
  }

  void makeMove() {
    // first check move is valid or not
    Scanner sc = new Scanner(System.in);
    int row;
    int col;

    do {
      System.out.println("Enter row and column : ");
      row = sc.nextInt();
      col = sc.nextInt();
    } while (!isValidMove(row, col));
    TicTacToe.placeMark(row, col, mark);
  }

}

class AIPlayer extends Player {

  AIPlayer(String name, char mark) {
    // to prevent shadowing problem this keyword is used
    this.name = name;
    this.mark = mark;
  }

  void makeMove() {
    // first check move is valid or not
    Scanner sc = new Scanner(System.in);
    int row;
    int col;

    do {
      Random r = new Random();
      row = r.nextInt(3);
      col = r.nextInt(3);
    } while (!isValidMove(row, col));
    TicTacToe.placeMark(row, col, mark);
  }
}

public class LaunchGame {
  public static void main(String[] args) {
    TicTacToe t = new TicTacToe();
    HumanPlayer p1 = new HumanPlayer("Virat", 'x');
    HumanPlayer p2 = new HumanPlayer("Aman", 'O');
    // AIPlayer p2 = new AIPlayer("TAI", 'O');
    Player cp;
    cp = p1;

    while (true) {
      System.out.println(cp.name + " turn");
      cp.makeMove();
      TicTacToe.displayBoard();
      if (TicTacToe.checkColumnWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin()) {
        System.out.println(cp.name + " has won");
        break;
      } else if (TicTacToe.checkDraw()) {
        System.out.println("Game Tied!!");
        break;
      } else {
        if (cp == p1) {
          cp = p2;
        } else {
          cp = p1;
        }
      }
    }
  }
}
