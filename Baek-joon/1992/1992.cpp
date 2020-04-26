#include <iostream>
#include <sstream>

using namespace std;

int matrix[64][64];
string compression(int xs, int xe, int ys, int ye) {
	if (xs  == xe && ys  == ye) {
		return to_string(matrix[xs][ys]);
	}
	int xmid = (xs + xe) / 2;
	int ymid = (ys + ye) / 2;

	string leftup = compression(xs, xmid, ys, ymid);
	string rightup = compression(xs, xmid, ymid+1, ye);
	string leftdown = compression(xmid+1, xe, ys, ymid);
	string rightdown = compression(xmid+1, xe, ymid+1, ye);

	if (leftup.length() == 1 && rightup.length() == 1 && leftdown.length() == 1 && rightdown.length() == 1) {
		if (leftup.compare(rightup) == 0 && leftdown.compare(rightdown) == 0 && leftup.compare(leftdown) == 0) {
			return leftup;
		}
		else {
			string str = "(";
			str.append(leftup);
			str.append(rightup);
			str.append(leftdown);
			str.append(rightdown);
			str.append(")");
			return str;
		}
	}
	else {
		string str = "(";
		str.append(leftup);
		str.append(rightup);
		str.append(leftdown);
		str.append(rightdown);
		str.append(")");
		return str;
	}
}

int main(void)
{
	int n;
	cin >> n;

	string str;

	for (int i = 0; i < n; i++) {
		cin >> str;
		int j = 0;
		for (string::iterator iter = str.begin(); iter != str.end(); ++iter) {
			matrix[i][j++] = *iter -48;
		}

	}
	//for (int i = 0; i < n; i++) {
	//	for (int j = 0; j < n; j++) {
	//		cout << matrix[i][j] << " ";
	//	}
	//	cout<<endl;
	//}
	cout << compression(0, n-1, 0, n-1)<<endl;

	cin >> n;
}