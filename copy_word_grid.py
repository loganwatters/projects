def copy_word_grid(grid):
    new_grid = grid[:]
    return new_grid

def binary_search(data, target):
    low = 0
    high = len(data)-1
    while low <= high:
        mid = (low+high)//2
        mid_elem = data[mid]
        if mid_elem < target:
            low = mid + 1
        elif mid_elem > target:
            high = mid - 1
        else:
            return mid
    return -1

print(binary_search([1,10,7,3,4,12,2],10))
