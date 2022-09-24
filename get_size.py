def get_size(grid):
    rows = len(grid)
    columns = len(grid[0])
    return rows,columns

list = [['a','b']]
print(get_size(list))