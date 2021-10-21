from sys import stdin

input = stdin.readline

if __name__ == '__main__':
    n = int(input().strip())
    answer = []
    candy = {}
    for _ in range(n):
        inputLine = list(map(int, input().strip().split()))
        if inputLine[0] == 2:
            if inputLine[1] in candy:
                candy[inputLine[1]] += inputLine[2]
            else:
                candy[inputLine[1]] = inputLine[2]
        else:
            sum = 0
            for key, value in candy.items():
                sum += value
                if sum >= inputLine[1]:
                    answer.append(str(key))
                    candy[key] -= 1
                    break
    print("\n".join(answer))
