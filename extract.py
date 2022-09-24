def extract(grid, position,direction,max_len):
    x, y = position
    delta_x, delta_y = direction
    return_string = grid[y][x]
    while ((x != (len(grid[y])-1) and x>0) and (y != len(grid)-1 and y>0) and len(return_string) != max_len):
        x += delta_x
       ## if(x == len(grid[y]) or x < 0):
        ##    break
        y += delta_y
       ## if(y == len(grid) or y < 0):
       ##     break
        return_string += grid[y][x]
    #for column in range(y, len(grid), delta_y):
        #for row in range(x, len(grid[y], delta_x))
           # return_string = return_string +
    return return_string

grid = [['p', 'c', 'n', 'd', 't', 'h', 'g'], ['w','a','x','o','a','x','f'], ['o','t','w','g','d','r','k'], ['l','j','p','i','b','e','t'], ['f','v','l','t','o','w','n']]
RIGHT = (1, 0)
DOWN = (0, 1)
RIGHT_DOWN = (1, 1)
RIGHT_UP = (1, -1)
print(extract(grid, (5,2), DOWN, 5))