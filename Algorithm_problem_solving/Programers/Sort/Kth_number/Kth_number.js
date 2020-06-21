// https://programmers.co.kr/learn/courses/30/lessons/42748

function solution(array, commands) {
    var answer = [];
    for (var i = 0; i < commands.length; i++) {
        var this_command = commands[i];
        var temp_array = array.slice(this_command[0] - 1, this_command[1])
        // js's sort compares the ASCII, declares a function comparing a and b.
        temp_array.sort((a, b) => a - b);
        answer.push(temp_array[this_command[2] - 1])
    }
    return answer;
}