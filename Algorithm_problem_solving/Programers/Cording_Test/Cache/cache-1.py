from collections import deque


class Cache:
    def __init__(self, n):
        self.caches = deque(maxlen=n)   # n사이즈의 큐를 만든다.

    def add_cache(self, this_cache):
        """
        return boolean : False가 리턴된다면 Cache miss 발생, True가 리턴된다면 Cache Hit발생
        """
        this_cache = this_cache.lower()
        answer = False
        if this_cache in self.caches:
            self.caches.remove(this_cache)
            answer = True
        self.caches.appendleft(this_cache)
        return answer


def solution(cacheSize, cities):
    answer = 0  # 서비스 처리 시간
    cache = Cache(cacheSize)
    for item in cities:
        if cache.add_cache(item):
            answer += 1
        else:
            answer += 5
    return answer
