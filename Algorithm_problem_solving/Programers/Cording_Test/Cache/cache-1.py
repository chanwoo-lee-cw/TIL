from collections import deque


class Cache:
    def __init__(self, n):
        self.caches = deque(maxlen=n)

    def add_cache(self, this_cache):
        this_cache = this_cache.lower()
        answer = False
        if this_cache in self.caches:
            self.caches.remove(this_cache)
            answer = True
        self.caches.appendleft(this_cache)
        return answer


def solution(cacheSize, cities):
    answer = 0
    cache = Cache(cacheSize)
    for item in cities:
        if cache.add_cache(item):
            answer += 1
        else:
            answer += 5
    return answer
