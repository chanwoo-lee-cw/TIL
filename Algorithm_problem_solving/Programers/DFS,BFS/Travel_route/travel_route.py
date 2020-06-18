#  https://programmers.co.kr/learn/courses/30/lessons/43164
class Route:
    def __init__(self, tickets):
        self.path = {}
        self.country = set()
        self.answer = []
        self.used_ticket_cnt = 0
        self.ticket_cnt = 0
        for ticket in tickets:
            self.ticket_cnt += 1
            self.country.add(ticket[0])
            self.country.add(ticket[1])
            if((ticket[0], ticket[1]) in self.path):
                 self.path[(ticket[0], ticket[1])] = self.path[(
                     ticket[0], ticket[1])] + 1
            else :
                self.path[(ticket[0], ticket[1])] = 1
        self.country = list(self.country)
        self.country.sort()

    def dfs(self, start):
        self.answer.append(start)
        if(self.used_ticket_cnt == self.ticket_cnt):
            return
        for next in self.country:
            if self.path.get((start,next)):
                self.path[(start,next)] = self.path[(start,next)] -1
                self.used_ticket_cnt += 1
                self.dfs(next)
                if(self.used_ticket_cnt == self.ticket_cnt):
                    return
                self.path[(start,next)] = self.path[(start,next)] +1
                self.used_ticket_cnt -= 1
        self.answer.pop()
        

def solution(tickets):
    route = Route(tickets)
    
    route.dfs('ICN')
    # print(route.answer)

    return route.answer


if __name__ == "__main__":

    # tickets = [['ICN', 'JFK'], ['HND', 'IAD'], ['JFK', 'HND']]
    # tickets = [['ICN', 'SFO'], ['ICN', 'ATL'], ['SFO', 'ATL'], ['ATL', 'ICN'], ['ATL','SFO']]
    tickets = [['ICN', 'SFO'], ['SFO','ICN'],['ICN', 'SFO'], ['SFO','ICN'],['ICN', 'SFO'], ['SFO','ICN']]
    print(solution(tickets))
