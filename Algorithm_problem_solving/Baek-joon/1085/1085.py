if __name__ == "__main__":
    x, y, w, h = map(int, input().strip().split())
    to_edge = [x, y, w-x, h-y]
    print(min(to_edge))