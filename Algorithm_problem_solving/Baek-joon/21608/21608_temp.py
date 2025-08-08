from sys import stdin
import heapq

input_readline = stdin.readline


class Student:
    def __init__(self, name, friends):
        self.name = name
        self.friends = friends
        self.happy = 0


def main():
    NEAR_POS = ((-1, 0), (0, -1), (1, 0), (0, 1))
    # 입력
    n = int(input_readline())
    students = []
    for _ in range(n**2):
        stuendt_info = list(map(int, input_readline().strip().split()))
        students.append(Student(stuendt_info.pop(0), stuendt_info))
    # 위치 배정
    desk_map = [([0] * (n + 2)) for _ in range(n + 2)]

    for student in students:
        near_friend = []
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if desk_map[i][j] != 0:
                    continue
                happy = 0
                empty = 0
                for near_i, near_j in NEAR_POS:
                    if desk_map[i + near_i][j + near_j] in student.friends:
                        happy += 1
                    elif (
                        0 < i + near_i <= n
                        and 0 < j + near_j <= n
                        and desk_map[i + near_i][j + near_j] == 0
                    ):
                        empty += 1
                near_friend.append((-happy, -empty, i, j))
        near_friend.sort(key=lambda x: (x[0], x[1], x[2], x[3]))
        _, _, set_y, set_x = near_friend.pop(0)
        desk_map[set_y][set_x] = student.name

    answer = 0
    students_dict = {
        item.name : item.friends for item in students
    }
    for i in range(1, n+1):
        for j in range(1, n+1):
            friends = students_dict[desk_map[i][j]]
            happy = 0
            for near_i, near_j in NEAR_POS:
                if desk_map[i + near_i][j + near_j] in friends:
                    happy += 1
            if happy == 1:
                answer += 1
            elif happy == 2:
                answer += 10
            elif happy == 3:
                answer += 100
    print(answer)



if __name__ == "__main__":
    main()
