#include<cstdio>
#include<iostream>
#include <algorithm>
#include <string>

using namespace std;

long long merged[2000001];
long long arr1[1000001];
long long arr2[1000001];

int main(void) {
	int n;
	int m;

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n >> m;
	//scanf("%d %d", &n, &m);

	long long num;

	for (int i = 0; i < n; i++) {
		cin >> arr1[i];
		//scanf("%lld", &arr1[i]);
	}


	for (int i = 0; i < m; i++) {
		cin >> arr2[i];
		//scanf("%lld", &arr2[i]);	
	}

	int nFront = 0;
	int mFront = 0;
	int mergedFront = 0;

	while (nFront < n && mFront < m)
	{
		if (arr1[nFront] < arr2[mFront]) {
			merged[mergedFront++] = arr1[nFront++];
		}
		else {
			merged[mergedFront++] = arr2[mFront++];
		}
	};



	if (nFront < n) {
		std::copy(arr1 + nFront, arr1 + n, merged + mergedFront);
	}
	else {
		std::copy(arr2 + mFront, arr2 + m, merged + mergedFront);
	}


	string out;
	for (int i = 0; i < n + m; i++) {
		//printf("%lld ", merged[i]);
		out.append(to_string(merged[i]));
		out.append(" ");
	}

	cout << out;

	return 0;
}