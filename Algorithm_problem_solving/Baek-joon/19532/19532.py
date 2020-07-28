if __name__ == "__main__":
    a, b, c, d, e, f = map(int, input().strip().split())

    if a == 0:
        y = int(c/b)
        x = int((f - e*c/b)/d)
    else:
        y = int((f - d*c/a) / (e- d*b/a))
        x = int((c - b*y) / a)

    print(x, y)
