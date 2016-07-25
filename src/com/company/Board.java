package com.company;

/**
 * Created by rishabh.ja on 22/07/16.
 */
class Board
{
    private String mat[][];
    private int n;
    public int remainingmoves;

        //Constructor for creating a nXn size board
    Board(int m){

        mat= new String[m+1][m+1];
        n=m;
        //0 : empty cell
        //1 : marked by player 1
        //2 : marked by player 2
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++)
                mat[i][j]="*";
        }
        remainingmoves=n*n;
    }

    private boolean insidegrid(int x,int y){
        if(x>=1&&x<=n&&y>=1&&y<=n)
            return true;
        return false;
    }

    private boolean validmove(int x,int y){
        if(insidegrid(x,y)&&(mat[x][y].equals("*")))
            return true;
        return false;
    }

    public boolean makemove(String player,int cx,int cy){
        if(!validmove(cx,cy)){
            System.out.println("Invalid move");
            return false;
        }
        mat[cx][cy]=player;
        remainingmoves--;
        return true;
    }

    public void print(){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++)
                System.out.print(mat[i][j]+" ");
            System.out.print("\n");
        }
    }

    public void print(Player pl){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++)
                pl.write(mat[i][j] + " ");
            pl.write("\n");
        }
    }

    public boolean findresult(String player,int x,int y) {

        boolean flag = false;
        int i=0;

        //check for row
        for (i = 1; i <= n; i++) {
            if (mat[x][i].equals(player)==false) {
                break;
            }
        }
        if (i == (n + 1))
            flag = true;
        //check for column
        for (i = 1; i <= n; i++) {
            if (mat[i][y].equals(player)==false)
                break;
        }
        if (i == (n + 1))
            flag = true;


        //check for 45* diagonal
        for (i = 1; i <= n; i++) {
            if (mat[i][i].equals(player)==false)
                break;
        }
        if (i == (n + 1))
            flag = true;


        //check for 135* diagonal
        for (i = 1; i <= n; i++) {
            if (mat[n - i + 1][i].equals(player)==false)
                break;
        }
        if (i == (n + 1))
            flag = true;

        return flag;
    }
}
