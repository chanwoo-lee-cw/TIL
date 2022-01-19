class PasswordCheck:
    def __init__(self, password: str, usserType: int = 0):
        self.password = password
        self.userType = usserType

    def __isContainNum(self):
        for item in self.password:
            if item.isdigit():
                return True
        return False

    def __isContainAlpha(self):
        for item in self.password:
            if ord('A') <= ord(item) <= ord('Z') or ord('a') <= ord(item) <= ord('z'):
                return True
        return False

    def __isContainSpecialChar(self):
        specialCharList = ['!', '@', '#', '&']
        for item in self.password:
            if item in specialCharList:
                return True
        return False

    def __checkPasswordLength(self):
        wantLength = 7  # default로 기본 유저로 생각한다.
        if self.userType == 1:
            # 관리자인 경우
            wantLength = 10
        return len(self.password) > wantLength

    def checkPassword(self):
        """
        바이너리 형태로 더한 값이 길이를 만족하느냐의 값
        1 : 최소한의 패스워드 길이가 되는가 여부
        2 : 패스워드에 숫자가 포함되는가 여부
        4 : 패스워드에 영문이 포함되느냐 여부
        8 : 패스워드에 특수문자가 포함되느냐의 여부

        :return:
            int : 조건을 만족한 것들의 함
        """
        answer = []
        answer.append(1 if self.__checkPasswordLength() else 0)
        answer.append(1 if self.__isContainNum() else 0)
        answer.append(1 if self.__isContainAlpha() else 0)
        if self.userType == 1:
            answer.append(1 if self.__isContainSpecialChar() else 0)
        answer.reverse()
        return int(''.join(str(item) for item in answer), base=2)


def checkPassword(password: str):
    check = PasswordCheck(password)
    return check.checkPassword() == 7


if __name__ == "__main__":
    passwordList = ["1234abcd", "a1234567",  # 통과하는 경우
                    "", "12ab", "123456a",  # 길이가 부족한 경우
                    "12345678", "1234", "1234567",  # 숫자만 들어있는 경우
                    "abcdefgh", "가1234567",  # 알파벳이 포함되지 않은 경우
                    ]
    for passowrd in passwordList:
        print(checkPassword(passowrd))
    passwordList = [
        ("123456abcde!", 1), ("12345abcdef@", 1), ("123456abcde#", 1), ("12345abcdef&", 1),  # 조건을 만족하는 경우
        ("", 1), ("123abcdef!", 1), ("12345abc#", 1),  # 길이가 부족한 경우
        ("!@#&!@#&!@&", 1), ("!@#&abcdefgh", 1),  # 숫자가 포함되지 않은 경우
        ("1234!@#&!@", 1), ("123456789010", 1),  # 알파벳이 포홛되있지 않은 경우
        ("123456abcd", 1), ("abcd123141", 1)
    ]
    for passowrd, userType in passwordList:
        check = PasswordCheck(passowrd, userType)
        print(check.checkPassword())
