-- https://school.programmers.co.kr/learn/courses/30/lessons/164671
SELECT concat("/home/grep/src/", file.BOARD_ID, "/", file.FILE_ID, file.FILE_NAME, file.FILE_EXT) AS FILE_PATH
FROM (SELECT MAX(VIEWS), BOARD_ID FROM USED_GOODS_BOARD) AS board join USED_GOODS_FILE as file
ON board.BOARD_ID = file.BOARD_ID
ORDER BY FILE_ID ASC