// https://leetcode.cn/problems/longest-palindromic-subsequence/
package main

import "fmt"

var dp [][]int

func initDp(size int, s string) {
	dp = make([][]int, size)
	for i := 0; i < size; i++ {
		dp[i] = make([]int, size)
		dp[i][i] = 1
		for j := i; j < size; j++ {
			if i != j {
				dp[i][j] = 0
			}
		}
	}
}

func f(s string, i int, j int) int {
	if i > j {
		return 0
	}
	if dp[i][j] != 0 {
		return dp[i][j]
	} else {
		if s[i] == s[j] {
			dp[i][j] = f(s, i+1, j-1) + 2
		} else {
			dp[i][j] = max(f(s, i, j-1), f(s, i+1, j))
		}
		return dp[i][j]
	}
}

func max(x int, y int) int {
	if x > y {
		return x
	}
	return y
}

func longestPalindromeSubseq(s string) int {
	size := len(s)
	initDp(size, s)
	return f(s, 0, size-1)
}

func main() {
	s := "a12b3c43def2ghi1kpm"
	fmt.Println(longestPalindromeSubseq(s))
}
