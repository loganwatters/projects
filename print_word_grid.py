def print_word_grid(grid):
    rows = len(grid)
    columns = len(grid[0])
    for i in range(rows):
        for j in range(columns):
            print(grid[i][j], end="")
        print()

grid = [['a','b','c'],['d','e','f']]
print_word_grid(grid)