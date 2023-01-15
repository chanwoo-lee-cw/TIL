from sys import stdin

input = stdin.readline

Consonant = ['b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'z']

if __name__ == "__main__":
    n: int = int(input())
    for _ in range(n):
        machine_lang = list(map(int, input().strip().split()))
        first_con = None
        translate = []
        for item in machine_lang:
            if 97 <= item <= 122 or 65 <= item <= 90:
                spell = chr(item).lower()
                if first_con is None and spell in Consonant:
                    first_con = spell
                    continue
            else:
                spell = '-'
            translate.append(spell)
        if first_con:
            translate.append(first_con)
        translate.append("ay")
        print(''.join(translate))
