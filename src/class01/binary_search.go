package main

import "fmt"

// 给定有序序列 arr，目标值target
// 如果arr中存在target，返回target的索引
// 如果arr中不存在target，返回二元组(l,r)，l是比target小的最大值的索引，r是比target大的最小值的索引
// 样例1：arr = [1,3,5,7]，target = 5，return 2
// 样例2：arr = [1,3,5,7]，target = 4，return (1,2)
type group struct {
	flag bool // 标识是否找到target
	l    int
	r    int
}

func binarySearch(arr []int, target int) group {
	if arr == nil {
		return group{}
	}
	l, r := 0, len(arr)-1
	mid := l + (r-l)/2
	for l <= r {
		if arr[mid] == target {
			return group{true, mid, mid}
		} else if arr[mid] > target {
			r = mid - 1
		} else {
			l = mid + 1
		}
		mid = l + (r-l)/2
	}
	// no match
	l, r = mid-1, mid
	// 边界检查
	if l < 0 {
		l = 0
	}
	if r >= len(arr) {
		r = len(arr) - 1
	}
	return group{false, l, r}
}

func search(arr []int, target int) {
	g := binarySearch(arr, target)
	if g.flag {
		fmt.Print(g.l)
	} else {
		fmt.Printf("(%v,%v)", g.l, g.r)
	}
}

// 暴力求解验证
func expected(arr []int, target int) {
	min, size := 0, len(arr)
	for i := 0; i < size; i++ {
		if arr[i] == target {
			fmt.Println(i)
			break
		}
		if arr[i] < target {
			min = i
		} else {
			break
		}
	}
	l, r := min, min+1
	if l >= size {
		r = size - 1
		l = size - 1
	}
	fmt.Printf("(%v,%v)\n", l, r)
	fmt.Printf("arr[l] = %v,arr[r] = %v", arr[l], arr[r])
}
func main() {
	arr := []int{1, 3, 5, 7}
	//arr := []int{1, 3, 5, 7, 9}
	target := -1
	search(arr, target)
	fmt.Println("\n------------------------------")
	expected(arr, target)
}
