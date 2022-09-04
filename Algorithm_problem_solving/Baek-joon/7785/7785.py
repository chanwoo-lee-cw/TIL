from sys import stdin

input = stdin.readline

if __name__ == "__main__":
    n = int(input())
    voca_set = set()
    for _ in range(n):
        voca, log = input().strip().split()
        if log == "enter":
            voca_set.add(voca)
        else:
            voca_set.remove(voca)
    voca_list = list(voca_set)
    voca_list.sort(reverse=True)
    print('\n'.join(voca_list))
