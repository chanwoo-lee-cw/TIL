import sys

input = sys.stdin.readline
print = sys.stdout.write

def main() :
	n,m = input().split()

	n = int(n)
	m = int(m)

	parent = [i for i in range(n + 1)]
	level = [1 for i in range(n + 1)]

	for i in range(m) :
		line = input().split()
		for j in range(3) :
			line[j]= int(line[j])
		if(line[0]==0) :
			merge(line[1],line[2],level,parent)
		elif (line[0]==1):
			u = find(line[1],parent)
			v = find(line[2],parent)
			if(u==v) :
				print("yes\n")
			else :
				print("no\n")

def find(u,parent) :
	if (u==parent[u]) :
		return u;
	parent[u] = find(parent[u],parent);
	return parent[u];

def merge(u, v,level,parent) :
	u = find(u,parent)
	v = find(v,parent)
	if (u == v) :
		return
	if (level[u] > level[v]) :
		temp =u
		u=v
		v=temp
	parent[u] = v
	if (level[u] == level[v]) :
		level[v]+=1

main()