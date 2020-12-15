# Java ArrayList ↔ Arr

문서 유형: Java
상태: 완료
작성일시: Dec 6, 2020 9:26 PM
작성자: ChanWoo Lee
최종 편집일시: Dec 6, 2020 10:42 PM
최종 편집자: ChanWoo Lee

## arr → Array

```java
ArrayList<Integer> arrList = new ArrayList<>();

arrList.add(1);
arrList.add(2);
arrList.add(3);

int[] arr = arrList.toArray(new int[arrList.size()]);
```

## Array → arr

```java
int[] arr = new int[3];

arr[0] = 1;
arr[1] = 2;
arr[2] = 3;

ArrayList<Integer> arrList = new ArrayList<>(Arrays.asList(arr));
```

## ArrayList 깊은 복사

```java
ArrayList<Integer> arrList1 = new ArrayList<>();

arrList.add(1);
arrList.add(2);
arrList.add(3);

ArrayList<Integer> arrList2 = new ArrayList<>();
arrList2.addAll(arrList1);
```

## arr 깊은 복사

```java
int[] arr1 = new int[3];

arr[0] = 1;
arr[1] = 2;
arr[2] = 3;

int[] arr2 = arr.clone();
```