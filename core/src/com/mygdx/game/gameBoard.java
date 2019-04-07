package com.mygdx.game;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class gameBoard {
    int[][] board;
    int[][] allowedBoard;
    int[][] oldAllowedBoard;

    int[][] Ipiece;
    int[][] LPiece;
    int[][] SPiece;
    int[][] TPiece;
    int[][] OPiece;

    int[][] currentPiece;
    int[][] oldPiece;
    int currentRowsTaken;
    int currentColumnsTaken;
    int[][] nextUp;

    int positionRow;
    int positionColumn;
    int oldPositionRow;
    int oldPositionColumn;

    boolean lost = false;
    boolean specialPiece = false;

    int score = 0;


    public gameBoard ()
    {
        board = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        allowedBoard = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        oldAllowedBoard = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        Ipiece = new int[][]{
                {1,1,1,1},
        };

        LPiece = new int[][]{
                {1,0,0},
                {1,1,1},
        };

        SPiece = new int[][]{
                {0,1,1},
                {1,1,0},
        };

        TPiece = new int[][]{
                {0,1,0},
                {1,1,1},
        };

        OPiece = new int [][]{
                {1,1},
                {1,1}
        };

        nextUp = randomizer();


    }

    public int[][] randomizer(){
        Random random = new Random();
        int result = random.nextInt(4) + 1;
        if (result == 1)
            return TPiece;
        if (result == 2)
            return LPiece;
        if (result == 3)
            return SPiece;
        return OPiece;
    }

    public void newPiece(){
        int [][] randomPiece = nextUp;
        currentPiece = randomPiece;
        oldPiece = randomPiece;
        currentColumnsTaken = randomPiece[0].length;
        currentRowsTaken = randomPiece.length;

        positionRow = 0;
        positionColumn = 5;
        oldPositionRow = 0;
        oldPositionColumn = 5;
        nextUp = randomizer();
        specialPiece = false;

    }

    public void spendPoint(){
        if (score > 0 && !specialPiece)
        {
            score = score - 1;
            nextUp = Ipiece;
            specialPiece = true;
        }
    }

    public void applyToBoard(){
        allowedBoard = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };
        for (int i = 0; i < currentPiece.length; i++)
        {
            for (int j = 0; j < currentPiece[0].length; j++)
            {
                allowedBoard[i][5 + j] = currentPiece[i][j];
            }
        }
        oldAllowedBoard = new int[][]{
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };
        checkLegality();
    }

    public void rotatePiece(){
        try {
            int[][] rotatedPiece = new int[currentPiece[0].length][currentPiece.length];

            for (int i = 0; i < currentPiece.length; i++) {
                for (int j = 0; j < currentPiece[0].length; j++) {
                    rotatedPiece[j][(currentPiece.length - 1) - i] = currentPiece[i][j];
                }
            }
            currentPiece = rotatedPiece;
            int currentX = positionColumn;
            int currentY = positionRow;
            allowedBoard = new int[][]{
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,0,0,0,0,0,0,0,0,0},
            };

            for (int y = 0; y < rotatedPiece.length; y++){
                for (int x = 0; x < rotatedPiece[0].length; x++){
                    allowedBoard[currentY + y][currentX + x] = rotatedPiece[y][x];
                }
            }

            checkLegality();

        }
        catch (IndexOutOfBoundsException e)
        {
            checkLegality();
        }



    }

    public void movePieceDown(){
        int[] blank = new int[] {0,0,0,0,0,0,0,0,0,0};
        for (int i = 19; i > 0; i--)
        {
            allowedBoard[i] = allowedBoard[i - 1];
            allowedBoard[i -1] = blank;

        }
        oldPositionRow = positionRow;
        positionRow += 1;
        if (!checkLegality())
        {
            commitBoard();
            clearRows();
            randomizer();
            newPiece();
            applyToBoard();
        }
    }

    public void movePieceLeft(){
        if (positionColumn > 0)
        {
            for (int i = 0; i < 20; i++)
            {
                for (int j = 0; j < 9; j++)
                {
                    allowedBoard[i][j] = allowedBoard[i][j+1];
                    allowedBoard[i][j+1] = 0;
                }
            }
            oldPositionColumn = positionColumn;
            positionColumn -= 1;
        }
        checkLegality();
    }

    public void movePieceRight(){
        if (positionColumn < 10)
        {
            for (int i = 0; i < 20; i++)
            {
                for (int j = 9; j > 0; j--)
                {
                    allowedBoard[i][j] = allowedBoard[i][j-1];
                    allowedBoard[i][j-1] = 0;
                }
            }
            oldPositionColumn = positionColumn;
            positionColumn += 1;
        }
        checkLegality();
    }

    public int[][] compositeBoard(int[][] boardA, int[][] boardB){
        int[][] combine = {
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
        };
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (boardA[i][j] == 1 || boardB[i][j] == 1)
                {
                    combine[i][j] = 1;
                }
            }
        }
        return combine;
    }
    public boolean checkLegality(){
        int[][] newBoard = compositeBoard(board, allowedBoard);
        int sumOldBoard = sumUpBoard(board) + 4;
        int sumCompositeBoard = sumUpBoard(newBoard);
        if (sumOldBoard == sumCompositeBoard) {
            oldAllowedBoard = deepCopy(allowedBoard);
            oldPositionColumn = positionColumn;
            oldPositionRow = positionRow;
            oldPiece = currentPiece;
            return true;
        }
        else
            {
                allowedBoard = deepCopy(oldAllowedBoard);
                positionColumn = oldPositionColumn;
                positionRow = oldPositionRow;
                currentPiece = oldPiece;
                return false;
            }

    }



    public void commitBoard(){
        int[][] newBoard = compositeBoard(board, allowedBoard);
        board = newBoard.clone();

    }
    public int sumUpBoard(int[][] b)
    {
        int sum = 0;
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                sum += b[i][j];
            }
        }
        return sum;
    }

    public void clearRows(){
        int[] blank = new int[] {0,0,0,0,0,0,0,0,0,0};
            for (int i = 0; i < 20; i++) {
                int sum = 0;
                for (int j = 0; j < 10; j++) {
                    sum += board[i][j];
                }
                if (sum >= 10){
                    for (int x = i; x > 0; x--)
                    {
                        board[x] = board[x-1];
                    }
                    i = 0;
                    score++;
                }

            }
    }

    public boolean loseCondition(){
        int sum = 0;
        for (int i = 0; i < 10; i++)
        {
            sum += board[1][i];
        }
        if (sum > 0)
            lost = true;
        return lost;
    }


    public String printBoard() {
        String toPrint = "";
        int[][] printedBoard = compositeBoard(allowedBoard, board);
        for (int i = 0; i < 20; i++)
        {
            toPrint = toPrint + "\n" + Arrays.toString(printedBoard[i]);
        }
        toPrint = toPrint + "\n" + score;
        return toPrint;
    }

    public int[][] getCompositeBoard(){
        return compositeBoard(allowedBoard, board);
    }

    public int[][] deepCopy(int[][] a){
        int[][] copy = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0},
        };

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 10; j++){
                if (a[i][j] == 1)
                    copy[i][j] = 1;
            }
        }
        return copy;
    }

    public int getScore(){
        return score;
    }

    public static void main(String[] args){
        gameBoard test = new gameBoard();
        System.out.println(test.printBoard() + "\n");
        test.newPiece();
        test.applyToBoard();
        boolean exit = false;
        while (!exit)
        {
            String input = "";
            Scanner kin = new Scanner(System.in);
            input = kin.next();
            if (input.equals("l"))
            {
                test.movePieceLeft();
                System.out.println(test.printBoard() + "\n");
            }
            else if (input.equals("r"))
            {
                test.movePieceRight();
                System.out.println(test.printBoard() + "\n");
            }
            else if (input.equals("d"))
            {
                test.movePieceDown();
                System.out.println(test.printBoard() + "\n");
            }
            else if(input.equals("u"))
            {
                test.rotatePiece();
                System.out.println(test.printBoard() + "\n");
            }
            else if (input.equals("s"))
            {
                test.spendPoint();
                System.out.println(test.printBoard() + "\n");
            }
            else
                exit = true;
        }
    }


}
