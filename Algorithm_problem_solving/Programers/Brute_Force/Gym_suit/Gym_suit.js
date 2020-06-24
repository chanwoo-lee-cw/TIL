function solution(n, lost, reserve) {
    var answer = 0;
    var gym_suit = new Array(n + 2).fill(1);

    for (var i = 0; i < lost.length; i++) {
        gym_suit[lost[i]] = 0
    }
    for (var i = 0; i < reserve.length; i++) {
        gym_suit[reserve[i]] += 1
    }

    for (var i = 1; i < n + 1; i++) {
        if (gym_suit[i] < 1) {
            if (gym_suit[i - 1] > 1) {
                gym_suit[i - 1]--;
                gym_suit[i]++;
            }
            else if (gym_suit[i + 1] > 1) {
                gym_suit[i + 1]--;
                gym_suit[i]++;
            }
            else {
                answer++;
            }
        }
    }
    return n - answer;
}