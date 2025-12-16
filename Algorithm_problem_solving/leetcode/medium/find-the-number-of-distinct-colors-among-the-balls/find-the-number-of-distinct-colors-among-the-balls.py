from typing import List


class Solution:
    def queryResults(
            self,
            limit: int,
            queries: List[List[int]]
    ) -> List[int]:
        ball_to_color_map = dict()
        color_to_ball_cnt_map = dict()
        answer = []

        for query in queries:
            ball, color = query
            pre_ball_color =  ball_to_color_map.get(ball, -1)
            if pre_ball_color in color_to_ball_cnt_map:
                color_to_ball_cnt_map[pre_ball_color] -= 1
                if color_to_ball_cnt_map[pre_ball_color] == 0:
                    color_to_ball_cnt_map.pop(pre_ball_color)
            ball_to_color_map[ball] = color
            if color not in color_to_ball_cnt_map:
                color_to_ball_cnt_map[color] = 0
            color_to_ball_cnt_map[color] += 1
            answer.append(len(color_to_ball_cnt_map))
        return answer