# https://www.acmicpc.net/problem/18352
import queue
import sys
from dataclasses import dataclass

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

    def set_road(self):
        for _ in range(self.road_number):
            dep, dst = map(int, input().strip().split())
            self.city_link[dep].append(dst)

    def adjacent_city_count(
            self,
            distance: int,
            source_city: int
    ):
        visited = [False] * (self.city_number + 1)
        city_distance_queue = queue.Queue()
        city_distance_queue.put(
            CityDistanceItem(source_city, distance)
        )
        visited[source_city] = True

        answer = []

        while not city_distance_queue.empty():
            curr: CityDistanceItem = city_distance_queue.get()
            if curr.distance == 0:
                answer.append(curr.city)
                continue
            for item in self.city_link[curr.city]:
                if visited[item] is True:
                    continue
                # else
                city_distance_queue.put(
                    CityDistanceItem(item, curr.distance - 1)
                )
                visited[item] = True
        return answer


def main():
    n, m, k, x = map(int, input().strip().split())
    shortest_city = ShortestCity(n, m)
    shortest_city.set_road()
    answer = shortest_city.adjacent_city_count(k, x)
    answer.sort()
    if answer:
        print('\n'.join([str(item) for item in answer]))
    else:
        print(-1)


if __name__ == "__main__":
    main()
