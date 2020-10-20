package com.engineersk;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        boolean validInput=false;
        int gridNumber = 0;
        do
        {
            System.out.println("Enter a number between 4-8 to start the game");
            gridNumber=input.nextInt();

            if(gridNumber>=4 && gridNumber<=8) {

                validInput=true;
            }

        }while(validInput==false);

        int[][] grid= new int[gridNumber][gridNumber];

        long[] score = new long[1];

        startGame(grid);
        boolean moved=move(grid, score);
        if(moved==false)
            displayResult(grid, score);
    }

    public static void startGame(int[][] grid){
        emptyGrid(grid);
        displayGrid(grid);
    }

    public static void continueGame(int[][] grid,long[] score)	// This method is used to determine if a player wish
    {          															// to continue immediately he attains high score of
        // 1024 or more.

        displayGrid(grid);  								// display grid cells

        boolean moved=move(grid, score);					// declares and set boolean variable moved to the return
        // value of move method

        if(moved==false)									// check if moved is set to false
            displayResult(grid, score);						// if moved is false, result is displayed
    }

    public static void displayGrid(int[][] grid) {          // This method displays the grid once a move has been made
        for(int i=0; i<grid.length;i++ ) {
            System.out.print("+-----");
            if(i == (grid.length-1))
                System.out.print("+");
        }

        System.out.println();

        for(int i=0; i<grid.length; i++) {
            System.out.print("|");

            for(int j=0; j<grid[i].length; j++) {
                printGridValuesOrSpaces(grid[i][j]);      	//prints the digits if > 0 or space if digit = 0

                System.out.print(" |");
            }

            System.out.println();

            for(int j=0; j<grid[i].length;j++ ) {
                System.out.print("+-----");
            }

            System.out.println("+");
        }
        System.out.print("\n");
    }

    public static void printGridValuesOrSpaces(int value)	// This method prints each value in each cell of the grid one at a time
    { 														// of the grid one at a time

        //purpose: to remove the zeros from the grid
        if(value==0)
            System.out.print("    ");

        else{
            if(value>0){
                String myValue = String.valueOf(value);

                if(myValue.length()<4){

                    for(int i=0; i<(4-myValue.length());++i)
                        System.out.print(" ");

                    System.out.print(value);
                }
            }
        }
    }

    public static void emptyGrid(int [][] grid) {    // This method resets the grid to zeros and select any two random
        // cells to a value of one in each cell

        // counter is used to set number of rows and columns the grid has since number rows are equal to number of columns
        int counter=0;

        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[i].length; j++) {
                grid[i][j]=0;
            }
            ++counter;     // counter variable increases by one after setting all column element of each row to zero
        }
        Random rnd= new Random(); //definition of random variable 'rnd' for shuffling rows and columns

        int randomRow1;            // definition of random row integer index for first cell to be generated randomly
        int randomColumn1;         // definition of random column integer index for first cell to be generated randomly
        int randomRow2;            // definition of random row integer index for second cell to be generated randomly
        int randomColumn2;         // definition of random column integer index for second cell to be generated randomly

        do{

            randomRow1=rnd.nextInt(counter);    // generating row index integer for first cell (any random number from 0 to 3)
            randomRow2=rnd.nextInt(counter);    // generating column index integer for second cell (any random number from 0 to 3)
            randomColumn1=rnd.nextInt(counter); // generating row index integer for first cell (any random number from 0 to 3)
            randomColumn2=rnd.nextInt(counter); // generating column index integer for second cell (any random number from 0 to 3)

        }while(randomRow1==randomRow2 && randomColumn1==randomColumn2); // do-while condition remains true, if random row index
        // for row 1 of first cell is equivalent to random
        // row index for row 2 of second cell and at the
        // same time, random column index for column 1 of first
        // cell is equivalent to random column index for column 2
        // of second cell, for example
        // grid[randomRow1][randomColumn1] reps grid[0][0] and
        // grid[randomRow2][randomColumn2] reps grid[0][0]
        // while condition remains true and program should
        // shuffle as long as condition is true
        // if grid[randomRow1][randomColumn1] reps grid[0][1]
        // and grid[randomRow2][randomColumn2] reps grid[0][0]
        // do-while condition becomes false because grid cells
        // are different and that ends do-while loop


        grid[randomRow1][randomColumn1] = 1;                           //for first cell generated value 1 is set
        grid[randomRow2][randomColumn2] = 1;                           //for second cell generated value 1 is set
    }

    public static boolean move(int [][]grid, long[] score){      // method for determining movement of numbers whether grid
        // elements should move up, down, left or right or player
        // quits it and returns a boolean value of false

        Scanner input=new Scanner(System.in);			// input variable declared for reading values enterd from the keyboard
        boolean validMove;                              // boolean variable that determines if move has been made

        do {                                            // do while loop that handles game play structure
            // depending on the choice of player

            validMove=false;                             // at the beginning of do-while loop validMove is reset to false;


            System.out.println("Enter the following for movement");             // Display message to guide user on controls to
            System.out.print("W - Move Up \nS - Move Down \nA - ");             // either move up, down, left, right or to quit
            System.out.println("Move Left \nD - Move Right\nQ - Quit\n\n\n");   // game

            char value= input.next().charAt(0);					    // char variable value that reads and stores input
            // of the player

            /*if condition*/

            if(value=='W'||value=='w'||value=='A'||value=='a'||
                    value=='S'|| value=='s'|| value=='D'||value=='d') { // if condition to check if values entered by player to
                // validate move either upwards, downwards, left or right

                validMove = validateMove(value, grid, score);       // method call of validateMove() which takes two parameters
                // returns a true or false and sets validMove
                // first-parameter (value) - which is choice of player to
                // move up, down, left or right.
                // second-parameter (grid) - which is the current 2-D
                // array grid.

                displayGrid(grid);                                  // method call of displayGrid() that displays current array
                // values in rows and columns

                System.out.println("Your current score is "+score[0]+"\n\n\n");
            }

            /*else-if condition*/

            else if(value=='Q'||value=='q') {                       // else if condition to check if value of 'Q' or 'q' has been
                // entered by player to quit game

                validMove=false;                                    // if "else if" condition is true, validMove should be set
                // to false to end game
            }


            /*else condition*/

            else{												    // else condition that checks if an invalid character has
                // entered by player

                System.out.println("Invalid entry!!!\n\n");         // if "else" condition is true, this message is printed to
                validMove=true;										// the console and valid move is set to true to prompt
                // the user to enter a valid value

            }

        }while(validMove==true);                                    // while validMove is true, the do-while loop continues to
        // execute and once valid move becomes false, do-while loop
        // stops executing

        return validMove;                                           // validMove is returned once it is false since it comes
        // after the do-while loop
    }

    public static boolean validateMove(char moveValue, int[][] grid, long[]score)		// method used to determine
    {   																					// if grid should be moved
        // left, right, upwards or
        // downwards with respect to
        // the char value of moveValue
        // variable that returns
        // a boolean value

        boolean moved = false;											// boolean variable moved declared false

        switch(moveValue){												// switch statement that detemines the direction
            // which grid moves in accordance to value of moveValue
            case 'a':
            case 'A':
                moved = moveLeft(grid, score);
                break;

            case 'd':
            case 'D':
                moved = moveRight(grid, score);
                break;

            case 'w':
            case 'W':
                moved = moveUpwards(grid, score);
                break;

            case 's':
            case 'S':
                moved = moveDownwards(grid, score);
                break;

        }

        return moved;                                                 // returns true or false with respect to the return value
        // of grid move direction in accordance to moveValue
    }

    //moveLeft shifts the grid to the left
    public static boolean moveLeft(int[][] grid, long[]score)
    {

        boolean isMovedLeft=false;
        int max = 0;
        for(int i=0; i<grid.length; ++i)
        {
            /*======================================================================*/
            // To move the grid to the left, for a number of rows, we iterate through
            // i (row index) to select each vertical component of an array
            // (recall, vertical component go from
            // up to down starting from the top which is index 0).
            /*=======================================================================*/


            for(int j=0; j<grid[i].length; ++j)		/*==========================================================*/
            {    									// for each row of the grid, represented as i,
                // we iterate through j columns which
                // symbolizes column index.

                // 	Columns are horizontal components of an array (recall,
                // 	horizontal components go from left to right starting
                // 	from the left which is index 0)

                // 	example grid[i][j]
                // 	for each i row, i.e from 0 to 3 if we have 4 rows
                // 	once i is set to 0, all the j components
                //	(column elements)
                // 	will run since j is a nested for loop of i

                // 	logically,

                // 	grid[0][0], grid[0][1], grid[0][2]
                // 	and grid[0][3] will run before
                // 	increasing i by 1 and checking if i less
                // 	than 3 before iterating through all j component.

                // 	*PLEASE NOTE*
                // 	I've iterated j in increasing order
                // 	because elements are moving leftwards,
                // 	in order to add the immediate
                // 	two similar numbers which my
                // 	check start from index 0 of j
                /*==========================================================*/

                if(grid[i][j] == 0)              	/*first if condition of nested j checked*/
                    continue;						/*==========================================================*/
                // this condition checks if grid[i][j]
                // is equal to zero, if true it skip the
                // other codes using the continue keyword,
                // immediately, j is incremented to test
                // the next column element and
                // check if j < grid[i].length.
                // (grid[i].length) is the number
                // of columns present in row i.

                // From the structure of the program
                // number of rows i equal to number of
                // columns which produce an array of
                // number of row time number of column
                // element which i 16 elements using
                // four(4) for number of rows and
                // columns each.
                /*==========================================================*/

                if(grid[i][j]>0 && j<=grid[i].length-1)		/*second if condition of nested j checked*/
                {											/*======================================*/
                    // this condition checks if grid[i][j]>0
                    // and column index j is less than
                    // or equal to (number of columns - 1)
                    // if true, against element grid[i][j],
                    // the program iterates through the
                    // rest of the column elements 'j' of the
                    // current row 'i' in increasing order using
                    // index k, an index to check for
                    // immediate similar column element
                    // provided a number greater than 0
                    // not equal to the element has not
                    // been encountered before a number
                    // similar to grid[i][j]
                    /*======================================*/

                    for(int k=j; k<grid[i].length-1; ++k)   /* nested k for loop of j since second if*/
                    { 										/*condition of nested j is true*/

                        /*======================================*/
                        // this condition iterates through
                        // the rest of the column elements 'j'
                        // in increasing order setting index 'k'
                        // to the current column index 'j' on
                        // declaration of int k in the loop
                        // of the current row 'i' till when k is
                        // an index below the maximum column index

                        // this implies that,

                        // if grid[i].length-1 equals 3
                        // this condition remains true if k=0, k=1
                        // or k=2, if k=3 condition becomes false
                        // because 3 is not less than 3

                        // *EXPLANATION*

                        // if we set k=j and we compare with grid[i][j]
                        // for increasing values of k
                        // if k=3 and we try k+1, there is no index 4
                        // the compiler will display out bounds error
                        // if the for loop condition was
                        // k < grid[i].length
                        // that is why the condition in the for loop
                        // is set to k < grid[i].length-1

                        /*======================================*/

                        if(grid[i][k+1] == grid[i][j])      /*first if condition of nested k checked*/
                        { 												/*======================================*/
                            // this condition states that for
                            // increasing column values of k,
                            // starting from k=j,
                            // if grid[i][k+1]==grid[i][j]
                            // the following conditions should be
                            // executed and the k loop should be
                            // broken and we'll be back in the
                            // main column for loop i.e
                            // for(int j=0; j<grid[i].length; ++j)
                            // and condition is checked
                            /*======================================*/

                            grid[i][j]+=grid[i][k+1];					/*======================================*/
                            // grid[i][j] = grid[i][j] + grid[i][k+1]
                            // that very grid cell is increased
                            /*======================================*/

                            grid[i][k+1]=0;								// the immediate cell grid[i][k+1]=0
                            // with the same value as grid[i][j]
                            // whose value was added to grid[i][j]
                            // is set to 0

                            score[0]+=grid[i][j];                       // increases score

                            if(grid[i][j]>=1024)						// condition checks if grid[i][j] >= 1024
                                max = grid[i][j];						// sets max if true

                            isMovedLeft=true;							// sets isMovedLeft to true;
                            break;                         				// breaks out of k loop
                        }

                        if(grid[i][k+1]>0 && grid[i][k+1] != grid[i][j])   /*second if condition of nested k loop checked*/
                            break;    										//if true breaks out of k loop
                    }
                }
            }

            if(max >= 1024)								/*if condition that checked if the grid has a max of 1024*/
                break;									//if true breaks out of j loop

        }

		/*
			The block of nested for loops below moves
			the column element of each row from the right to
			left filling the space
		*/

        for(int i=0; i<grid.length; ++i){               /*
															this for loop iterates through the rows of the grid
															in increasing order
														*/

            for(int j=0; j<grid[i].length; ++j){       	/*
															this for loop iterates through the columns 'j' of each
															row 'i' of the grid in increasing order
														*/


                if(grid[i][j] == 0)		             /*first condition of nested j loop checked*/
                    continue;							// this condition checks if grid[i][j]
                // is equal to zero, if true it skip the
                // other codes using the continue keyword,
                // immediately, j is incremented to test
                // the next column element and
                // check if j < grid[i].length.
                // (grid[i].length) is the number
                // of columns present in row i.

                if(grid[i][j]>0 && j==0)			/*second if condition of nested j loop checked*/
                    continue;							// this condition checks if grid[i][j]
                // is greater than 0 and column index j equals 0,
                // if true it skip the other codes
                // using the continue keyword,
                // immediately, j is incremented to test
                // the next column element and
                // check if j < grid[i].length.
                // (grid[i].length) is the number
                // of columns present in row i.

                // *EXPLANATION*

                // if column index 'j' is equal to 0,
                // we are on the first column of the current
                // row, if the grid element i.e grid[i][0]
                // is greater than 0, there is no need to
                // shift the element along the column
                // because, there is no column before index 0


                if(grid[i][j]>0 && j>0)				/*third if condition of nested j loop checked*/
                {										// this condition checks if grid[i][j]
                    // is greater than 0 and column index 'j'
                    // is greater 0,
                    // if true, against element grid[i][j],
                    // the program iterates through the
                    // rest of the column elements 'j' in
                    // decreasing order using index k,

                    // *EXPLANATION*
                    // Iteration is done in decreasing order
                    // because to move left we check for
                    // immediate spaces as column index of
                    // the current row 'i' decreases

                    for(int k=j; k>0; --k)   		/* nested k for loop of j since third if*/
                    { 									/*condition of nested j is true*/

                        /*======================================*/
                        // this condition iterates through
                        // the rest of the column elements 'j'
                        // in decreasing order setting index 'k'
                        // to the current column index 'j' on
                        // declaration of int k in the loop
                        // of the current row 'i' till when k is
                        // an index 0

                        // this implies that,

                        // if grid[i].length-1 equals 3
                        // this condition remains true if k=1, k=2
                        // or k=3, if k=0 condition becomes false
                        // because 0 is not greater than 0

                        // *EXPLANATION*

                        // if we set k=j and we compare with grid[i][j]
                        // for decreasing values of k
                        // if k=0 and we try k-1, there is no index -1
                        // the compiler will display out bounds error
                        // if the for loop condition was
                        // k >= 0
                        // that is why the condition in the for loop
                        // is set to k > 0
                        /*======================================*/

                        if(grid[i][k-1]>0)				/*first if condition of nested k checked*/
                            break;							/*======================================*/
                        // this condition states that for
                        // decreasing column values of k,
                        // starting from k=j,
                        // if grid[i][k-1]>0
                        // the k loop breaks because
                        // if a number grid[i][k-1] greater than 0
                        // appears immediately before grid[i][k]
                        // there is no need for movement
                        /*======================================*/

                        if(grid[i][k-1]==0)				/*second if condition of nested k checked*/
                        { 									/*======================================*/
                            // this condition states that for
                            // decreasing column values of k,
                            // starting from k=j,
                            // if grid[i][k-1]==0
                            // the following conditions should be
                            // executed
                            /*======================================*/

                            grid[i][k-1]=grid[i][k];		/*======================================*/
                            // grid[i][k-1] which is the immediate
                            // columnof the current row 'i' before
                            // grid[i][k] should be set to the
                            // value of grid[i][k];
                            // this implies that a number has moved
                            // to the left by an immediate column
                            // since the value of that column is 0
                            /*======================================*/
                            grid[i][k]=0;					/*======================================*/
                            // the current value of the column
                            // index before it was moved to the
                            // left is set to 0 to show transitition
                            // from right to immediate left column
                            // has taken place
                            /*======================================*/

                            isMovedLeft=true; 				// this set a boolean value true that
                            // to show movement has taken place
                        }
                    }
                }
            }
        }

		/*
			This if-else block of code determines if value one should be
			randomly inserted after grid has moved left or display some
			messages to the console if grid did not move left
		*/
        if(isMovedLeft==true)  	/* This condition checks if grid moved left since in the two*/
        {					 	/* blocks of nested for loops isMovedLeft is set to true if
								   the grid moved to the left*/

            if(max >= 1024)		  	/* This condition checks if any grid cell has value greater than or equal to 1024 */
                isMovedLeft=false;	//if condition is true isMovedFalse is set false */

            insertValueOneMovingLeftOrRightwards(grid, grid.length-1);  // This method takes two parameter
            // the first parameter is grid array
            // the second parameter is the maximum
            // column index

            // *EXPLANATION*

            // the second parameter is the maximum
            // column index because, once the grid
            // moves left, the last column index
            // 'grid[row].length-1' of each row is
            // more likely to be empty as the indices
            // at the beginninig of the row are most
            // likely to be occupied
        }
        else{                 /*If grid does not move left isMovedLeft remains false and the following executes*/

            if(isGridFilled(grid)==false) 	// this condition checks if grid is filled with the help of a method
            {							  	// isGridFilled() which takes the grid array as the only parameter
                // and returns false if grid has spaces, once the grid has spaces,
                // this condition becomes true and the following executes

                System.out.println("You're stuck, try another move!!!\n\n"); 	// this message is printed to the console
                // to tell the player to try another move

                isMovedLeft=true;		// isMovedLeft is set true since it was previously false.
            }

            else                           /*if isGridFilled() returns true, this means no more moves*/
                System.out.println("You're out of moves!!!\n\n"); //once grid is filled, this message is displayed
            //and the isMovedLeft remains false
        }

        return isMovedLeft;				// if isMovedLeft returns true, it means game continues
        // if it returns false, game ends.
    }

    public static boolean moveRight(int[][] grid, long[] score)
    {

        boolean isMovedRight=false;
        int max = 0;
        for(int i=0; i<grid.length; ++i){

            for(int j=grid[i].length-1; j>=0; --j){
                if(grid[i][j]==0)
                    continue;
                if(grid[i][j]>0 && j<=grid[i].length-1){

                    for(int k=j; k>0; --k){

                        if(grid[i][k-1]==grid[i][j]){
                            grid[i][j]+=grid[i][k-1];
                            grid[i][k-1]=0;
                            score[0]+=grid[i][j];
                            if(grid[i][j]>=1024)
                                max = grid[i][j];
                            isMovedRight=true;
                            break;
                        }
                        if(grid[i][k-1]>0 && grid[i][k-1]!=grid[i][j])
                            break;

                    }
                }
            }
            if(max >= 1024)
                break;
        }

        for(int i=0; i<grid.length; ++i){

            for(int j=grid[i].length-1;j>=0;--j){
                if(grid[i][j]==0)
                    continue;
                if(grid[i][j]>0 && j==grid[i].length-1)
                    continue;
                if(grid[i][j]>0 && j<grid[i].length-1){

                    for(int k=j; k<grid[i].length-1; ++k){
                        if(grid[i][k+1]>0)
                            break;
                        else{
                            if(grid[i][k+1]==0){
                                grid[i][k+1]=grid[i][k];
                                grid[i][k]=0;
                                isMovedRight=true;
                            }
                        }
                    }
                }
            }
        }
        if(isMovedRight==true){
            if(max >= 1024)
                isMovedRight=false;

            insertValueOneMovingLeftOrRightwards(grid, 0);
        }
        else{

            if(isGridFilled(grid)==false){
                System.out.println("You're stuck, try another move!!!\n\n");
                isMovedRight=true;
            }
            else
                System.out.println("You're out of moves!!!\n\n");
        }
        return isMovedRight;
    }

    public static boolean moveUpwards(int[][] grid, long[] score){
        boolean isMovedUpwards=false;
        int max=0;
        for(int j=0; j<grid[0].length; ++j){
            for(int i=0; i<grid.length; ++i){
                if(grid[i][j]==0)
                    continue;
                if(grid[i][j]>0 && i>=0){
                    for(int k=i; k<grid.length-1; ++k){
                        if(grid[k+1][j]==grid[i][j]){
                            grid[i][j]+=grid[k+1][j];
                            grid[k+1][j]=0;
                            score[0]+=grid[i][j];
                            if(grid[i][j]>=1024)
                                max = grid[i][j];
                            isMovedUpwards=true;
                            break;
                        }
                        if(grid[k+1][j]>0 && grid[k+1][j]!=grid[i][j])
                            break;
                    }
                }
            }
            if(max>=1024)
                break;
        }

        for(int j=0; j<grid[0].length; ++j){

            for(int i=0; i<grid.length; ++i){
                if(grid[i][j]==0)
                    continue;
                if(grid[i][j]>0 && i==0)
                    continue;
                if(grid[i][j]>0 && i>0){
                    for(int k=i; k>0; --k){
                        if(grid[k-1][j]>0)
                            break;
                        else{
                            if(grid[k-1][j]==0){
                                grid[k-1][j]=grid[k][j];
                                grid[k][j]=0;
                                isMovedUpwards=true;
                            }
                        }
                    }
                }
            }
        }
        if(isMovedUpwards==true){
            if(max >= 1024)
                isMovedUpwards=false;

            insertValueOneMovingUpOrDownwards(grid, grid.length-1);
        }
        else{
            if(isGridFilled(grid)==false){
                System.out.println("You're stuck, try another move!!!\n\n");
                isMovedUpwards=true;
            }
            else
                System.out.println("You're out of moves!!!\n\n");
        }

        return isMovedUpwards;
    }

    public static boolean moveDownwards(int[][] grid, long[] score){

        boolean isMovedDownwards=false;
        int max = 0;

        for(int j=0; j<grid[0].length; ++j){
            for(int i=grid.length-1; i>=0; --i){
                if(grid[i][j]==0)
                    continue;
                if(grid[i][j]>0 && i<=grid.length-1){
                    for(int k=i; k>0; --k){
                        if(grid[k-1][j]==0)
                            continue;
                        if(grid[k-1][j]==grid[i][j]){
                            grid[i][j]+=grid[k-1][j];
                            grid[k-1][j]=0;
                            score[0]+=grid[i][j];

                            if(grid[i][j]>=1024)
                                max = grid[i][j];

                            isMovedDownwards=true;
                            break;
                        }
                        if(grid[k-1][j]>0 && grid[k-1][j]!=grid[i][j])
                            break;
                    }
                }

            }
            if(max>=1024)
                break;
        }

        for(int j=0; j<grid[0].length; ++j){

            for(int i=grid.length-1; i>=0; --i){
                if(grid[i][j]==0)
                    continue;
                if(grid[i][j]>0 && i==grid.length-1)
                    continue;
                if(grid[i][j]>0 && i<grid.length-1){
                    for(int k=i; k<grid.length-1; ++k){
                        if(grid[k+1][j]>0)
                            break;
                        else{
                            if(grid[k+1][j]==0){
                                grid[k+1][j]=grid[k][j];
                                grid[k][j]=0;
                                isMovedDownwards=true;
                            }
                        }
                    }
                }
            }
        }

        if(isMovedDownwards==true){
            if(max >= 1024)
                isMovedDownwards=false;

            insertValueOneMovingUpOrDownwards(grid, 0);
        }
        else{
            if(isGridFilled(grid)==false){
                System.out.println("You're stuck, try another move!!!\n\n");
                isMovedDownwards=true;
            }
            else
                System.out.println("You're out of moves!!!\n\n");

        }

        return isMovedDownwards;
    }

    public static void insertValueOneMovingUpOrDownwards(int[][] grid, int rowIndex){

        int spaceCounter = 0;
        int rowSpaceCounter=0;

        Random rnd = new Random();

        for(int j=0; j<grid[0].length; ++j){
            if(grid[rowIndex][j]==0)
                ++spaceCounter;
        }

        if(spaceCounter >= 1){
            int columnIndex=0;

            if(spaceCounter == 1){
                for(int j=0; j<grid[0].length; ++j){
                    if(grid[rowIndex][j]==0){
                        columnIndex=j;
                        break;
                    }
                }
            }

            if(spaceCounter > 1){
                int randomColumn;
                do{
                    randomColumn = rnd.nextInt(grid.length);

                    if(grid[rowIndex][randomColumn]==0)
                        columnIndex=randomColumn;

                }while(grid[rowIndex][randomColumn]!=0);
            }
            if(rowIndex==0){
                for(int i=rowIndex; i<grid.length; ++i){
                    if(grid[i][columnIndex]==0)
                        ++rowSpaceCounter;
                    if(grid[i][columnIndex]>0)
                        break;
                }
                if(rowSpaceCounter==1)
                    grid[rowIndex][columnIndex]=1;
                else{
                    int randomRowSpace=rnd.nextInt(rowSpaceCounter);
                    System.out.println("RANDOM rowSpaceCounter = " + randomRowSpace);
                    grid[randomRowSpace][columnIndex]=1;
                }
            }
            else{
                for(int i=rowIndex; i>=0; --i){
                    if(grid[i][columnIndex]==0)
                        ++rowSpaceCounter;
                    if(grid[i][columnIndex]>0)
                        break;
                }

                if(rowSpaceCounter==1)
                    grid[rowIndex][columnIndex]=1;
                else{
                    int randomRowSpace=rowIndex - rnd.nextInt(rowSpaceCounter);
                    grid[randomRowSpace][columnIndex]=1;
                }
            }
        }
    }

    public static void insertValueOneMovingLeftOrRightwards(int[][] grid, int columnIndex){
        int spaceCounter = 0;
        int columnSpaceCounter=0;

        Random rnd = new Random();

        for(int i=0; i<grid.length; ++i){
            if(grid[i][columnIndex]==0)
                ++spaceCounter;
        }

        if(spaceCounter >= 1){
            int rowIndex=0;

            if(spaceCounter == 1){
                for(int i=0; i<grid.length; ++i){
                    if(grid[i][columnIndex]==0){
                        rowIndex=i;
                        break;
                    }
                }
            }

            if(spaceCounter > 1){
                int randomRow=0;
                do{
                    randomRow = rnd.nextInt(grid.length);

                    if(grid[randomRow][columnIndex]==0)
                        rowIndex=randomRow;

                }while(grid[randomRow][columnIndex]!=0);
            }

            if(columnIndex==0){
                for(int j=columnIndex; j<grid[0].length; ++j){
                    if(grid[rowIndex][j]==0)
                        ++columnSpaceCounter;
                    if(grid[rowIndex][j]>0)
                        break;
                }
                if(columnSpaceCounter==1)
                    grid[rowIndex][columnIndex]=1;

                else{
                    int randomColumnSpace=rnd.nextInt(columnSpaceCounter);
                    grid[rowIndex][randomColumnSpace]=1;
                }
            }
            else{
                for(int j=columnIndex; j>=0; --j){
                    if(grid[rowIndex][j]==0)
                        ++columnSpaceCounter;
                    if(grid[rowIndex][j]>0)
                        break;
                }
                if(columnSpaceCounter==1)
                    grid[rowIndex][columnIndex]=1;

                else{
                    int randomColumnSpace=columnIndex - rnd.nextInt(columnSpaceCounter);
                    grid[rowIndex][randomColumnSpace]=1;
                }
            }
        }
    }

    public static boolean isGridFilled(int[][] grid){
        boolean isFilled = true;
        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[i].length; ++j){
                if(grid[i][j]==0){
                    isFilled=false;
                    break;
                }

            }
            if(isFilled==false)
                break;
        }

        return isFilled;
    }

    public static void displayResult(int[][] grid, long[] score){
        int max=0;

        for(int i=0; i<grid.length; ++i){
            for(int j=0; j<grid[i].length; ++j){
                if(grid[i][j]>max)
                    max=grid[i][j];
            }
            if(max>=1024)
                break;
        }

        if(max>=1024){
            System.out.println("*** Your score is "+ score[0] +" ***");
            System.out.println("CONGRATULATIONS!!! *** |||(You WON!!!)||| ***");
            char answer;
            Scanner input= new Scanner(System.in);
            do{
                System.out.println("Do you wish to continue?\nY - yes\nN - no");
                answer=input.next().charAt(0);

                if(answer!='Y'&&answer!='y'&&answer!='N'&&answer!='n')
                    System.out.println("Invalid entry!!!\n");

            }while(answer!='Y'&&answer!='y'&&answer!='N'&&answer!='n');

            if(answer=='Y'||answer=='y')
                continueGame(grid, score);
            else{
                if(max>1024)
                    System.out.println("*** Your new High Score is "+ score[0] +" ***");

                System.out.println("Have a nice day!!!");
            }
        }
        else{
            System.out.println("GAME OVER\n\n");
            System.out.println("*** Your score is " + score[0] +" ***");
            System.out.println("SORRY!!! *** |||(You LOST!!!)||| ***");
        }
    }
}