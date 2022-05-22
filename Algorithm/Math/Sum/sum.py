def sum(n: int) -> int:
    if n == 1:
        return 1
    elif n % 2 == 0:
        return 2 * sum(n // 2) + (n // 2) ** 2
    else:
        return 2 * sum((n - 1) // 2) + ((n - 1) // 2) ** 2 + n
    
    
if __name__ == "__main__":
    print(sum(10))