# https://programmers.co.kr/learn/courses/30/lessons/17676?language=python3
import heapq


def solution(lines):
    answer = 0
    count = 0
    timelist = []

    for i in range(len(lines)):
        # 입력받은 데이터를 전부 초로 바꾼다.
        _, start, duration = lines[i].split()
        duration = float(duration[:-1])
        hh, mm, ss = start.split(':')
        endtime = (int(hh) * 60 * 60) + (int(mm)*60) + float(ss)
        starttime = round(endtime - duration, 4)

        # 끝나는 시간을 F로 시작하는 시간을 T로 저장한다.
        # endtime에 1을 더하는 이유 : 
        #   1초간 계산하므로, 끝난 직후에 다른 프로그램이 들어오면 동시 연산으로 계산되기 때문에 1초동안 작동한다고 인식시켜주기 위해
        # starttime에 0.001을 더하는 이유 :
        # 1.0001~ 2초 이런식으로 계산하므로 더해진 직후 0.0001초부터 프로그램이 돌아가는 것으로 계산된다.
        heapq.heappush(timelist, [endtime+1, False])
        heapq.heappush(timelist, [starttime+0.001, True])

    while timelist:
        _, outputType = heapq.heappop(timelist)
        # 현재 돌아가고 있는 프로그램의 객수만 구하면 되므로 프로그램 하나가 시작되면 +1 하나가 종료되면 -1
        if outputType :
            count += 1
        else :
            count -= 1
        if count > answer :
            answer = count

    return answer