typedef int element;

typedef struct QueueNode {
	element item;
	struct QueueNode *link;
} QueueNode;

typedef struct {
	QueueNode *front, *rear;
} QueueType;

void init(QueueType *q)
{
	q->front == q->rear = NULL;
}
// 공백 상태 검출 함수
int is_empty(QueueType *q)
{
	return (q->front == NULL);
}
// 포화 상태 검출 함수
int is_full(QueueType *q)
{
	return 0;
}
// 삽입 함수
void enqueue(QueueType *q, element item) {
	QueueNode *temp = (QueueNode *)malloc(sizeof(QueueNode));
    
	if(temp == NULL)
		error("메모리를 할당할 수 없습니다");
	else {
			temp->item = item;
			temp->link = NULL;
		if (is_empty(q)) {
			q->front = temp;
			q->rear = temp;
		}
		else {
			q->rear->link = temp;
			q->rear = temp;
		}
	}
}
// 삭제 함수
element dequeue(QueueType *q) {
	QueueNode *temp = front;
	element item;
	if( is_empty(q) )
		error("큐가 공백상태입니다");
	else {
		item = temp->item;
		q->front = q->front->link;
		if (q->front == NULL)
			q->rear = NULL;
		free(temp);
		return item;
}