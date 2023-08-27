from sys import stdin

input = stdin.readline


def lower_bound(arr, start, end):
    answer = float('inf')
    while end - start > 0:
        prant_tree = 0
        mid = (start + end) // 2
        is_over = False
        for i in range(n - 1):
            this = arr[i]
            while this + mid != arr[i + 1]:
                if this + mid > arr[i + 1]:
                    is_over = True
                    break
                if this + mid != arr[i + 1]:
                    prant_tree += 1
                this += mid
            if is_over:
                break
        if is_over:
            end = mid
        else:
            start = mid + 1
            answer = answer if answer < prant_tree else prant_tree
    return answer


if __name__ == "__main__":
    n = int(input().strip())
    roadside_tree = [0] * n
    min_distance = float('inf')
    max_distance = float('-inf')

    for i in range(n):
        roadside_tree[i] = int(input().strip())

    for i in range(1, n):
        gab = roadside_tree[i] - roadside_tree[i - 1]
        if min_distance > gab:
            min_distance = gab
        if max_distance < gab:
            max_distance = gab

    print(lower_bound(roadside_tree, 0, max_distance))
