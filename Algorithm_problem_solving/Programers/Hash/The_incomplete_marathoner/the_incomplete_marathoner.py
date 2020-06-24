# https://programmers.co.kr/learn/courses/30/lessons/42576

def solution(participant, completion):
    person_dic = {}
    for person in participant:
        if person in person_dic:
            person_dic[person] += 1
        else:
            person_dic[person] = 1

    for person in completion:
        if person in person_dic:
            person_num = person_dic.get(person) - 1
            if person_num == 0 :
                person_dic.pop(person)
            else:
                person_dic[person] = person_num

    return ''.join(list(person_dic.keys()))

if __name__ == "__main__":
    person	= ['marina', 'josipa', 'nikola', 'vinko', 'filipa']
    completion = ['josipa', 'filipa', 'marina', 'nikola']
    temp=(solution(person, completion))
    print(temp)