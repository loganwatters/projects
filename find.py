def find(grid, word):
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
                        print(position, direction)
                        return 0
                    else:
                        position_of_x += delta_x
                        position_of_y += delta_y
                testing_string = ""
    print(word, "is not found in this word search")
    return False
grid = [['p', 'c', 'n', 'd', 't', 'h', 'g'], ['w','a','x','o','a','x','f'], ['o','t','w','g','d','r','k'], ['l','j','p','i','b','e','t'], ['f','v','l','t','o','w','n']]

find(grid, "jpib")