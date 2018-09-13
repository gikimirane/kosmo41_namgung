package com.study.jsp.chat;

public class TicTecteo {

	
	
	public void print(char[][] board) {
		
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board.length;j++) {
				if(j==2) {
					System.out.print(" "+board[i][j]);
				}
				else System.out.print(" "+board[i][j]+" |");
			}
			System.out.println();
		}
	}
	
	public void game() {
		
		char[][] board = {{'1','2','3'},{'4','5','6'},{'7','8','9'}};
		
		int winner =0;
		int player= 1;
		char[] who = {' ','X','O'};
		int input =0;
		int xtemp =0;
		int ytemp =0;
		
		for(int count=0;count<9 && winner==0;count++) {
			print(board);
			player = count%2+1;
			System.out.printf("player %d, please enter the number of the square where you want to place you %s :", player,who[player]);
			
			
			xtemp = --input/3;
			ytemp = input%3;
			if(board[xtemp][ytemp]=='X'||board[xtemp][ytemp]=='O') {
				count=count-1;
				continue;
			}else {
				board[xtemp][ytemp]=who[player];
			}
			
			for(int i=0;i<board.length;i++) {
				if(board[i][0]==board[i][1] && board[i][0]==board[i][2]) {
					winner=player; 
					System.out.println("우승자는 player : "+player+" 입니다.");
				}else if(board[0][0]==board[1][1]&&board[0][0]==board[2][2]) {
					winner=player; 
					System.out.println("우승자는 player : "+player+" 입니다.");
				}else if(board[0][i]==board[1][i]&&board[0][i]==board[2][i]) {
					winner=player; 
					System.out.println("우승자는 player : "+player+" 입니다.");
				}
			}
		}
		System.out.println("비겼습니다.");
		
		
	}
}
