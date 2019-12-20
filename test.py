n, m = input().split();
n = int(n)+1;
m = int(m);

matrix = [[False for i in range(n)] for j in range(n)]

for i in range(m) :
	temp1, temp2 = input().split()
	temp1= int(temp1)
	temp2 = int(temp2)
	matrix[int(temp1)][int(temp2)] = True


def search(matrix,line,start,end) :
	for i in range(start,end) :
		if(matrix[line][i]==True):
			return i
		else :
			return 0
def maximumMatching(matrix,line,biq,n) :
	if(line == 1) :
		biq[1]=search(matrix,line,0,n)
	else :
		pos = search(matrix,line,0,n)-1;
		# print(biq.index(pos))
		if(biq.index(pos)>0) :
			temp = search(matrix, biq.index(pos) + 1, pos, n)
			if(temp!=0):
				biq[biq.index(pos)]=temp
				biq[line]=pos;

pos = search(matrix,1,1,n+1)
biq = [0 for i in range(n)]
for i in range(1,3) :
	maximumMatching(matrix, i, biq, n)

print(biq)

