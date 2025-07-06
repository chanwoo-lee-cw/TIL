# https://www.acmicpc.net/problem/18352
# 시간복잡도 : O(N + M)
import sys
from dataclasses import dataclass
from collections import deque

input = sys.stdin.readline


@dataclass
class CityDistanceItem:
    city: int
    distance: int


class ShortestCity:
    def __init__(
            self,
            city_number: int,
            road_number: int,
    ):
        self.city_number = city_number
        self.road_number = road_number
        self.city_link = [[] for _ in range(self.city_number + 1)]

    """
    도시 연관 관계 추가
    city_link에 연결된 도시 추가
    """
    def set_road(self):
        for _ in range(self.road_number):
            dep, dst = map(int, input().strip().split())
            self.city_link[dep].append(dst)

    
    def adjacent_city_count(
            self,
            distance: int,
            source_city: int
    ):
        """
        source_city로부터 최단거리가 distance인 도시 배열 리턴
        Parameters:
            distance (int): 거리
            source_city (int): 시작 도시
        Returns:
            list[int]: 정렬된 도시 번호 리스트
        """
        visited = [False] * (self.city_number + 1)
        city_distance_queue = deque([
            CityDistanceItem(source_city, distance)
        ])
        visited[source_city] = True
        answer = []

        while city_distance_queue:
            curr: CityDistanceItem = city_distance_queue.popleft()
            if curr.distance == 0:
                answer.append(curr.city)
                continue
            for item in self.city_link[curr.city]:
                if visited[item] is True:
                    continue
                # else
                city_distance_queue.append(CityDistanceItem(item, curr.distance - 1))
                visited[item] = True
        return sorted(answer)


def main():
    n, m, k, x = map(int, input().strip().split())
    shortest_city = ShortestCity(n, m)
    shortest_city.set_road()
    answer = shortest_city.adjacent_city_count(k, x)
    answer.sort()
    if answer:
        for city in answer:
            print(city)
    else:
        print(-1)


if __name__ == "__main__":
    main()
