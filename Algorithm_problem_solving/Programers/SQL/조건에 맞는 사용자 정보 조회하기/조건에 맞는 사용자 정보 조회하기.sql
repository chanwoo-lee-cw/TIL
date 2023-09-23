SELECT user_board.USER_ID AS "USER_ID",
       user_board.NICKNAME AS "NICKNAME",
       CONCAT(user_board.CITY, " ", user_board.STREET_ADDRESS1, " ", user_board.STREET_ADDRESS2) AS "전체주소",
       CONCAT(SUBSTR(user_board.TLNO, 1, 3), "-", SUBSTR(user_board.TLNO, 4, 4), "-", SUBSTR(user_board.TLNO, 8)) AS "전화번호"
FROM (SELECT COUNT(BOARD_ID) AS regit_board, WRITER_ID
      FROM USED_GOODS_BOARD
      GROUP BY WRITER_ID
      HAVING COUNT(BOARD_ID) >= 3) AS user_board_count
INNER JOIN USED_GOODS_USER AS user_board ON user_board_count.WRITER_ID = user_board.USER_ID
ORDER BY user_board.USER_ID DESC;
