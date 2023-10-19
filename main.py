import random
from initSquares import initSquares

global playerSymbol
global aiSymbol
global squares
global initSquares
global row1
global row2
global row3
global column1
global column2
global column3
global diagonal1
global diagonal2
global counter

counter = 0
gameOver = False

def UpdateRowsColumnsDiagonals():
    global row1
    global row2
    global row3
    global column1
    global column2
    global column3
    global diagonal1
    global diagonal2

    row1 = [squares[1], squares[2], squares[3]]  # setting row, column, and diagonal values
    row2 = [squares[4], squares[5], squares[6]]
    row3 = [squares[7], squares[8], squares[9]]
    column1 = [squares[1], squares[4], squares[7]]
    column2 = [squares[2], squares[5], squares[8]]
    column3 = [squares[3], squares[6], squares[9]]
    diagonal1 = [squares[1], squares[5], squares[9]]
    diagonal2 = [squares[3], squares[5], squares[7]]

def resetSquares():
    global squares  # setting default board values
    global initSquares
    squares = initSquares

def PrintBoard():  # print board
    print('', squares[1], '|', squares[2], '|', squares[3], '\n---+---+---\n', squares[4], '|', squares[5], '|',
          squares[6], '\n---+---+---\n', squares[7], '|', squares[8], '|', squares[9])

def StartGame():  # get player symbol and set AI symbol accordingly
    global playerSymbol
    global aiSymbol
    playerSymbol = str(input('Which symbol do you want to use, X or O? ')).upper()
    if not (playerSymbol == 'X' or playerSymbol == 'O'):
        print('please type \'x\' or \'o\'')
        StartGame()
    elif playerSymbol == 'X':
        aiSymbol = 'O'
    elif playerSymbol == 'O':
        aiSymbol = 'X'

def GameLogic():  # get player input and set board value
    global counter
    global gameOver
    while True:

        if not gameOver:
            if counter >= 9:
                print('stalemate')
                break

        UpdateRowsColumnsDiagonals()

        PrintBoard()  # print board

        try:
            playerPlayedSquare = int(input(
                'Which square do you want to take (only squares with numbers are available)? '))
        except ValueError:
            input('please choose a number between 1 and 9 (enter anything to continue)')
            GameLogic()

        if playerPlayedSquare < 1 or playerPlayedSquare > 9:
            input('please choose a number between 1 and 9 (enter anything to continue)')
            GameLogic()

        if squares[playerPlayedSquare] == aiSymbol or squares[playerPlayedSquare] == playerSymbol:
            input('please choose a number that is not taken (enter anything to continue)')
            GameLogic()

        squares[playerPlayedSquare] = playerSymbol
        counter += 1

        UpdateRowsColumnsDiagonals()

        while True:
            aiPlayedSquare = random.randrange(1, 10, 1)
            if counter >= 9:
                # check for 3 in a row from player
                if (row1[0] == row1[1] == row1[2] == playerSymbol) \
                        or (row2[0] == row2[1] == row2[2] == playerSymbol) \
                        or (row3[0] == row3[1] == row3[2] == playerSymbol) \
                        or (column1[0] == column1[1] == column1[2] == playerSymbol) \
                        or (column2[0] == column2[1] == column2[2] == playerSymbol) \
                        or (column3[0] == column3[1] == column3[2] == playerSymbol) \
                        or (diagonal1[0] == diagonal1[1] == diagonal1[2] == playerSymbol) \
                        or (diagonal2[0] == diagonal2[1] == diagonal2[2] == playerSymbol):
                    print('player wins')
                    PrintBoard()
                    gameOver = True
                    break
                print('stalemate')
                break
            if squares[aiPlayedSquare] != playerSymbol and squares[aiPlayedSquare] != aiSymbol:
                squares[aiPlayedSquare] = aiSymbol
                counter += 1
                break

        UpdateRowsColumnsDiagonals()

        if not gameOver:
            # check for 3 in a row from AI
            if (row1[0] == row1[1] == row1[2] == aiSymbol)\
                or (row2[0] == row2[1]== row2[2] == aiSymbol)\
                or (row3[0] == row3[1] == row3[2] == aiSymbol)\
                or (column1[0] == column1[1] == column1[2] == aiSymbol)\
                or (column2[0] == column2[1] == column2[2] == aiSymbol)\
                or (column3[0] == column3[1] == column3[2] == aiSymbol)\
                or (diagonal1[0] == diagonal1[1] == diagonal1[2] == aiSymbol)\
                or (diagonal2[0] == diagonal2[1] == diagonal2[2] == aiSymbol):
                print('ai wins')
                PrintBoard()
                break

        UpdateRowsColumnsDiagonals()

        if not gameOver:
            # check for 3 in a row from player
            if (row1[0] == row1[1] == row1[2] == playerSymbol)\
                or (row2[0] == row2[1]== row2[2] == playerSymbol)\
                or (row3[0] == row3[1] == row3[2] == playerSymbol)\
                or (column1[0] == column1[1] == column1[2] == playerSymbol)\
                or (column2[0] == column2[1] == column2[2] == playerSymbol)\
                or (column3[0] == column3[1] == column3[2] == playerSymbol)\
                or (diagonal1[0] == diagonal1[1] == diagonal1[2] == playerSymbol)\
                or (diagonal2[0] == diagonal2[1] == diagonal2[2] == playerSymbol):
                print('player wins')
                PrintBoard()
                break

        if not gameOver:
            if counter >= 9:
                print('stalemate')
                break

        if gameOver:
            break

    playAgain = input('play again? y/n ')  # prompt to play again
    if playAgain.lower() == 'n':  # check if player says no
        quit()
    elif playAgain.lower() == 'y':  # reset game if player does not say no
        resetSquares()
        counter = 0
        gameOver = False
        GameLogic()
    else:  # prompt again if player does not say yes or no
        print('please enter \'y\' or \'n\'')


StartGame()  # game initialization
resetSquares()
GameLogic()  # main program loop
