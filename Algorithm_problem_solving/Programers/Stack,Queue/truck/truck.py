# https://programmers.co.kr/learn/courses/30/lessons/42583

def solution(bridge_length, weight, truck_weights):
    answer = 0
    # the load on the bridge
    load = 0
    bridge = []

    while True:
        # If all the trucks had crossed the bridge, break
        if len(truck_weights) == 0 and len(bridge) == 0:
            break
        # Every trucks on the bridge advances one by one
        for i in range(len(bridge)):
            bridge[i][1] += 1
        # If a truck reaches the end of the bridge, pop and reduce the load.
        if(len(bridge) > 0 and bridge[0][1] > bridge_length):
            hevy, _ = bridge.pop(0)
            load -= hevy
        # There's a queue in the truck, and if the truck can go up further, raise it.
        if len(truck_weights) > 0 and load + truck_weights[0] <= weight:
            hevy = truck_weights.pop(0)
            load += hevy
            bridge.append([hevy, 1])
        answer += 1

    return answer


if __name__ == "__main__":
    bridge_length = 2
    weight = 10
    truck_weights = [7, 4, 5, 6]
    print(solution(bridge_length, weight, truck_weights))
