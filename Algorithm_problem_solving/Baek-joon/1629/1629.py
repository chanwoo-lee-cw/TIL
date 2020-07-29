def pow(num, facotr, C):
    if facotr == 1:
        return num
    elif facotr % 2 == 1:
        return pow(num, facotr-1, C) * num % C
    return pow(num, facotr//2, C)**2 % C


A, B, C = map(int, input().strip().split())
print((pow(A, B, C)) % C)