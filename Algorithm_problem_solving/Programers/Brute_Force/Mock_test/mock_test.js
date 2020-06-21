function solution(answers) {
    var answer = [];
    var first_person = [1, 2, 3, 4, 5];
    var second_person = [2, 1, 2, 3, 2, 4, 2, 5];
    var third_person = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];
    var points = [0, 0, 0];

    for (var i = 0; i < answers.length; i++) {
        if (answers[i] == first_person[i % first_person.length])
            points[0]++;
        if (answers[i] == second_person[i % second_person.length])
            points[1]++;
        if (answers[i] == third_person[i % third_person.length])
            points[2]++;
    }

    // in js function for compare with list
    var maxnum = Math.max.apply(null, points);
    for (var i = 0; i < points.length; i++) {
        if (maxnum == points[i]) {
            answer.push(i + 1);
        }
    }

    return answer;
}