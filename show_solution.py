"""def show_solution(grid, word, default):
    testing_string = ""
    RIGHT = [1, 0]
    DOWN = [0, 1]
    RIGHT_DOWN = [1, 1]
    RIGHT_UP = [1, -1]
    directions = [[1,0], [0,1], [1,1], [1,-1]]

    for y in range(0,(len(grid)-1)):
        #print("I made it into for loop")
        for x in range(0,(len(grid[y])-1)):
            old_x = x
            old_y = y
            #print("I made it into the OTHER for loop")
            #position = (x,y)
            #print(position)
            for i in range(0,len(directions)-1,1):
                moveable_x = old_x
                moveable_y = old_y
                delta_x = directions[i][0]
                delta_y = directions[i][1]
                while ((moveable_x != (len(grid[moveable_y]) - 1) and moveable_x >= 0) and (moveable_y != len(grid) - 1 and moveable_y >= 0)):
                    moveable_x += delta_x
                    moveable_y += delta_y
                    testing_string += grid[moveable_y][moveable_x]
                    print(testing_string)
                    if testing_string == word:
                        return True
                testing_string = """""

grid = [['p', 'c', 'n', 'd', 't', 'h', 'g'], ['w','a','x','o','a','x','f'], ['o','t','w','g','d','r','k'], ['l','j','p','i','b','e','t'], ['f','v','l','t','o','w','n']]
#print(show_solution(grid, 'cat', "end"))

def print_word_grid(grid):
    rows = len(grid)
    columns = len(grid[0])
    for i in range(rows):
        for j in range(columns):
            print(grid[i][j], end="")
        print()

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
show_solution(grid, "cat", "ah")



