from random import *

def copy_word_grid(grid):
    """function takes a list of lists of strings of letters, copies the list using splice syntax and a for loop,
    then returns the copied list"""
    new_grid = []
    for x in range(len(grid)):
        new_grid.append(grid[x][:])
    #print(new_grid)
    return new_grid

def create_grid(height, width):
    grid = []
    for y in range(height):
        grid.append([])
        for x in range(width):
            grid[y].append("")
    return grid

def add_word(word, grid, direction, position):
    x, y = position
    delta_x, delta_y = direction
    temp_grid = copy_word_grid(grid)
    for letter in word:
        if y >=len(grid) or x >= len(grid[0]) or x < 0 or y <0:
            return grid, False
        elif temp_grid[y][x] == "" or temp_grid[y][x] == letter:
            temp_grid[y][x] = letter
            x += delta_x
            y += delta_y
        else:
            return grid, False
    return temp_grid, True

def give_random(lower_range, upper_range):
    return randint(lower_range, upper_range)

def generate(width, height, words):
    right = (1,0)
    down = (0,1)
    right_down = (1,1)
    right_up = (1,-1)
    directions = [right, down, right_down, right_up]
    grid = create_grid(height, width)
    list_of_words = []
    alphabet = {0: 'a', 1: 'b', 2: 'c', 3: 'd', 4: 'e', 5: 'f', 6: 'g', 7: 'h', 8:'i', 9:'j', 10:'k', 11:'l', 12:'m', 13:'n', 14:'o', 15:'p', 16:'q', 17:'r', 18:'s', 19: 't', 20:'u', 21:'v', 22:'w', 23:'x', 24:'y', 25:'y'}
    for word in range(len(words)):
        letters = words[word]
        for z in range(100):
            current_grid, stop_going = add_word(letters, grid, directions[give_random(0,3)],(give_random(0,(len(grid[0])-1)), give_random(0,(len(grid)))))
            if stop_going == True:
                grid = copy_word_grid(current_grid)
                list_of_words.append(letters)
                break
            else:
                continue
    for u in range(len(grid)):
        for p in range(len(grid[0])):
            if grid[u][p] == '':
                grid[u][p] = alphabet.get(give_random(0,25))
    return grid, list_of_words

list,list1 = generate(10,10,['logan','jacob','roscoe','fuck','rail','me','cancer','aids','love','respect'])

for y in range(len(list)):
    for x in range(len(list[y])):
        print(list[y][x])

def show_solution(grid, word, default):
    RIGHT = [1, 0]
    DOWN = [0, 1]
    RIGHT_DOWN = [1, 1]
    RIGHT_UP = [1, -1]
    directions = [RIGHT, DOWN, RIGHT_DOWN, RIGHT_UP]
    testing_string = ""
    for i in range(len(directions)-1):
        delta_x = directions[i][0]
        delta_y = directions[i][1]
        for y in range(len(grid)-1):
            for x in range(len(grid[y])-1):
                position_of_x = x
                position_of_y = y
                while(position_of_x != len(grid[y]) and position_of_x>=0 and position_of_y != len(grid) and position_of_y >= 0):
                    testing_string += grid[position_of_y][position_of_x]
                    if testing_string == word:
                        return_list = grid.copy()
                        for z in range(len(testing_string)):
                            grid[y][x] = grid[y][x].upper()
                            y += delta_y
                            x += delta_x
                        print(testing_string.upper(), "can be found as below")
                        print_word_grid(return_list)
                        return 0
                    else:
                        position_of_x += delta_x
                        position_of_y += delta_y
                testing_string = ""
    print(word, "is not found in this word search")
    return False

def print_word_grid(grid):
    rows = len(grid)
    columns = len(grid[0])
    for i in range(rows):
        for j in range(columns):
            print(grid[i][j], end="")
        print()

for z in list1:
    show_solution(list, z, 'ahh')

