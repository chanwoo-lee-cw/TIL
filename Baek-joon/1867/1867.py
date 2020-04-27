#직접 짜본 최대 이분 매칭 그래프

n, m = input().split();
n = int(n)+1;
m = int(m);

matrix = [[False for i in range(n)] for j in range(n)]

for i in range(m) :
	temp1, temp2 = input().split()
	temp1= int(temp1)
	temp2 = int(temp2)
	matrix[int(temp1)][int(temp2)] = True
	biq = [0 for i in range(n)]

def maximumMatching(matrix,line,biq,start) :
	if((True in matrix[line][start:])!=True) :
		#정해진 배열을 초과했다면 그냥 실패
		return False
	find=matrix[line].index(True,start)
	if(line==1) :
	## 아예 처음줄을 검색할때는 무조건 넣을 수 있다.
		biq[line]=find;
	else :
		if(find in biq) :
			# if(search(matrix,line,biq)) :
			temp_biq = [biq[i] for i in range(line)]
			## 재배치를 하기 위해서 일단 배치 되어 있는 것을 백업한다,
			for i in range(1, line):
				if (True in matrix[i][biq[i] + 1:]):
					# temp_biq[i]=matrix[i].index(True,biq[i]+1)
					temp = matrix[i].index(True, biq[i] + 1)
					## 현재 라인의 전에 있는 이분 배열에서 다른 곳으로 이어져 있는지 검사
					if((temp in temp_biq)==False):
						temp_biq[i] = temp
					## 이어져 있는게 다른 곳과 이어져 있지 않다면 임시 배치에 넣는다.
					if ((find in temp_biq)==False):
					## 없다면 다시 넣는다.
						biq[line] = find
						for i in range(1, line):
							if (temp_biq[i] != 0):
								biq[i] = temp_biq[i]
					

				else:
					if (i == line - 1):
							maximumMatching(matrix, line, biq, find+1)
				#만약 search에서 재배열이 성공했다면 넣는다.
		else :
			biq[line] = find;



for i in range(1,n) :
	if(maximumMatching(matrix, i, biq,0)) :
		# print(i-1)
		break;
cnt=0
for i in range(1,n) :
	if(biq[i]>0) :
		cnt+=1

print(cnt)

for i in range(1,n) :
	print(matrix[i])

print(biq)