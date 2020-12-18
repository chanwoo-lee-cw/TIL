num = int(input())

card_list = []

a = input()
list_str = a.split()

for i in range(num) :
    card_list.append(int(list_str[i]))

max = 0

dp = [0]

def MAX(card_list,dp, point) :
    max=0
    for i in range(point) :
        temp = dp[i] + card_list[point-(i+1)]
        if(temp > max) :
            max = temp
    return max

for i in range(1,num+1) :
    dp.append(MAX(card_list,dp,i))


print(dp[num])