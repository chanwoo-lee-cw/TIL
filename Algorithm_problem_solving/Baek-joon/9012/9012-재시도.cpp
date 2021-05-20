#include<iostream>
#include<string>
#include<vector>

using namespace std;

class Bracket {
private:
	string inputLine;

public:
	Bracket(string inputLine) {
		this->inputLine = inputLine;
	}

	bool checkBracket() {
		vector<bool> stk;	// 벡터 공부할겸 스택 대신 벡터로 선언
		for (int i = 0; i < inputLine.size(); i++) {
			if (inputLine[i] == '(') {
				stk.push_back(true);
			}
			else if (inputLine[i] == ')') {
				if (stk.size() == 0)
				{
					return false;
				}
				else
				{
					stk.pop_back();
				}
			}
		}

		return (stk.size() == 0) ? true : false;
	}
};

int main(void) {
	int t;
	string inputLine;
	scanf("%d", &t);
	Bracket* bracket;
	for (int test = 0; test < t; test++) {
		cin >> inputLine;
		bracket = new  Bracket(inputLine);
		string answer = bracket->checkBracket() ? "YES" : "NO";

		cout << answer << endl;
	}

	return 0;
}