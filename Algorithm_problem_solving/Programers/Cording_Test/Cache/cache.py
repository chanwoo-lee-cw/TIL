class Cache:
    def __init__(self, n):
        self.caheSize = n   # 캐시의 사이즈
        self.caches = []    # 캐시에 저장된 목록   
        self.usedCahes = set()      # 현재 캐시에 저장되어 있는지 확인

    def add_cache(self, this_cache):
        """
        return boolean : False가 리턴된다면 Cache miss 발생, True가 리턴된다면 Cache Hit발생
        """
        this_cache = this_cache.upper()
        answer = False
        if this_cache in self.usedCahes:
            self.caches.remove(this_cache)
            answer = True
        self.caches.insert(0, this_cache)
        self.usedCahes.add(this_cache)
        if len(self.caches) > self.caheSize:
            temp = self.caches.pop()
            self.usedCahes.remove(temp)
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