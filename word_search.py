from random import *

def copy_word_grid(grid):
    """function takes a list of lists of strings of letters, copies the list using splice syntax and a for loop,
    then returns the copied list"""
    new_grid = []
    for x in range(len(grid)):
        new_grid.append(grid[x][:])
    return new_grid

def print_word_grid(grid):
    """function takes a list of lists of strings of letters, then prints each list in the list like a row 1-by-one"""
    rows = len(grid)
    columns = len(grid[0])
    for i in range(rows):
        for j in range(columns):
            print(grid[i][j], end="")
        print()

def get_size(grid):
    """function takes a list of lists of strings of letters, then calculates through lengths of lists in the given list
    what the amount of rows and columns would be in the list in the case in which it were a grid"""
    rows = len(grid)
    columns = len(grid[0])
    return columns,rows

def show_solution(grid, word, default):
    """function takes a list of lists of strings of letter, a word to be found, and a default response if the word
    is not found in the list. In order to determine this, the function goes to each 'x' coordinate, then slowly adds
    to a string created by following each direction as defined below until either every (x,y) has been searched,
    or the word is found. In the case that the word is found, a new list is printed with the solution in all caps,
    whereas if the word is not found, a statement relaying this message is printed"""
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
                        return_list = copy_word_grid(grid)
                        for z in range(len(testing_string)):
                            char_to = return_list[y][x]
                            return_list[y][x] = char_to.upper()
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
    return 0

def extract(grid, position, direction, max_len):
    """funcition takes a list of list of strings containing a single letter, a starting (x,y) representative position
    as a tuple, a direction to go from the position, and a maximum length of a return string. From there, the function
    goes through the list using the direction as delta_x and delta_y, respectivley. If the length of the string is
    equal to or exceeds the max_len provided, or the 'edge' of the grid is reached, the string is returned to the
    called function"""
    x, y = position
    delta_x, delta_y = direction
    print(max_len)
    if(max_len == 0):
        return ""
    return_string = grid[y][x]
    while x != (len(grid[y])-1) and x>=0 and y != len(grid)-1 and y>=0 and len(return_string) != max_len:
        x += delta_x
        if(x == len(grid[y]) or x < 0):
            break
        y += delta_y
        if(y == len(grid) or y < 0):
            break
        return_string += grid[y][x]
    return return_string

def create_grid(height, width):
    """given two integer values representing a height and width of a list representing a word find, the function creates
    a list of lists of values "" as defined by the parameters through two for loops"""
    grid = []
    for y in range(height):
        grid.append([])
        for x in range(width):
            grid[y].append("")
    return grid

def add_word(word, grid, direction, position):
    """given a string, a list of lists representing a word search, a tuple representing a direction by giving
    delta_y and delta_x, and a position in the form of (x,y), the function goes through each letter in the string
    given and attempts to add all of them into the grid given the start position and the direction. If the word is
    successfully added, then true is returned from the function and the given grid is modified to be equal to the
    temporary grid that is used for testing is also returned"""
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
    """given a lower range and a higher range value, the function uses Python's randint function to return a random
    integer value in the range given"""
    return randint(lower_range, upper_range)

def generate(width, height, words):
    """given a width, height, and list of strings representing words, the function creates a grid representing a word search,
    then calls the add_word function 100 times or until the word is successfully added for each word in the list of words.
    if true is returned from the add_word function, then the function moves onto the next word and adds the word to the
    list of added words. This then fills in the empty values in the grid with random letters from a dictionary and then
    returns the grid and the list of added words"""
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

def find(grid, word):
    """given a list of lists of words and a word that should be found, the function tries every combination of direction
    and position that could possibly occur in the grid and attempts to find the word until an edge is hit by starting
    from the chosen test position going in the chosen direction. If the word eventually is equal to the string being made
    , then the position and direction of where the word is found and in what direction it goes from the start position
    is returned, otherwise, false is returned"""
    RIGHT = [1, 0]
    DOWN = [0, 1]
    RIGHT_DOWN = [1, 1]
    RIGHT_UP = [1, -1]
    directions = [RIGHT, DOWN, RIGHT_DOWN, RIGHT_UP]
    testing_string = ""
    for i in range(len(directions)):
        delta_x = directions[i][0]
        delta_y = directions[i][1]
        for y in range(len(grid)):
            for x in range(len(grid[y])):
                position_of_x = x
                position_of_y = y
                while (position_of_x != len(grid[y]) and position_of_x >= 0 and position_of_y != len(grid) and position_of_y >= 0 and len(testing_string) < len(word)):
                    testing_string += grid[position_of_y][position_of_x]
                    if testing_string == word:
                        position = (x,y)
                        direction = (delta_x, delta_y)
                        #print(position, direction)
                        return position, direction
                    else:
                        position_of_x += delta_x
                        position_of_y += delta_y
                testing_string = ""
    print(word, "is not found in this word search")
    return False

def find_all_helper(grid, word):
    """given a list of lists of words and a word that should be found, the function tries every combination of direction
    and position that could possibly occur in the grid and attempts to find the word until an edge is hit by starting
    from the chosen test position going in the chosen direction. If the word eventually is equal to the string being made
    , then the position and direction of where the word is found and in what direction it goes from the start position
    is returned, otherwise, false is returned"""
    RIGHT = [1, 0]
    DOWN = [0, 1]
    RIGHT_DOWN = [1, 1]
    RIGHT_UP = [1, -1]
    directions = [RIGHT, DOWN, RIGHT_DOWN, RIGHT_UP]
    testing_string = ""
    for i in range(len(directions)):
        delta_x = directions[i][0]
        delta_y = directions[i][1]
        for y in range(len(grid)):
            for x in range(len(grid[y])):
                position_of_x = x
                position_of_y = y
                while (position_of_x != len(grid[y]) and position_of_x >= 0 and position_of_y != len(grid) and position_of_y >= 0):
                    testing_string += grid[position_of_y][position_of_x]
                    if testing_string == word:
                        position = (x,y)
                        direction = (delta_x, delta_y)
                        return position, direction
                    else:
                        position_of_x += delta_x
                        position_of_y += delta_y
                testing_string = ""
    return False

def find_all(grid, words):
    """given a gird and a list of words, the function uses the find_all_helper function for every word to try to find
    the given word in the given grid. If the word is found, then the value from find_all_helper will be the starting
    position of the word and the direction from there in which the word is found. Otherwise, false is returned. These
    respective values are then the value given for the key that is the word attempting to be found"""
    return_dict = {}
    for x in range(len(words)):
        return_dict[words[x]] = find_all_helper(grid, words[x])
    return return_dict

