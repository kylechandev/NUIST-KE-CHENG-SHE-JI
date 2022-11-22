from typing import *

# 回溯算法+动态规划预求解字符串的分割方法
class Solution1:
    # 动态规划判断是否为回文串
    def partition(self, s: str) -> List[List[str]]:
        n = len(s)
        f = [[True] * n for _ in range(n)]

        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = (s[i] == s[j]) and f[i + 1][j - 1]

        ret = list()
        ans = list()
        # 深度优先遍历，找出树枝上的答案
        def dfs(i: int):
            if i == n:
                # 把DFS遍历到的内容加入到ans数组中
                ret.append(ans[:])
                return

            for j in range(i, n):
                if f[i][j]:
                    ans.append(s[i:j+1])
                    dfs(j + 1)
                    # 回溯
                    ans.pop()

        dfs(0)
        return ret

# 回溯算法求解字符串的分割方法
class Solution2:
    def partition(self, s: str) -> List[List[str]]:
        result = []
        path = []
        #判断是否是回文串
        def pending_s(s):
            l, r = 0, len(s) - 1
            while l < r:
                if s[l] != s[r]:
                    return False
                l += 1
                r -= 1
            return True
        # 回溯函数，这里的index作为遍历到的索引位置，也作为终止判断的条件
        def back_track(s, index):
            # 如果对整个字符串遍历完成，并且走到了这一步，则直接加入result
            if index == len(s):
                result.append(path[:])
                return
            # 遍历每个子串
            for i in range(index, len(s)):
                # 剪枝，因为要求每个元素都是回文串，那么我们只对回文串进行递归，不是回文串的部分直接不care它
                # 当前子串是回文串
                if pending_s(s[index : i + 1]):
                    # 加入当前子串到path
                    path.append(s[index: i + 1])
                    # 从当前i+1处重复递归
                    back_track(s, i + 1)
                    # 回溯
                    path.pop()

        back_track(s, 0)
        return result


ex1 = Solution1()
ex2 = Solution2()
# 测试分割字符串aab
print(ex1.partition('aabcdaad'))
print(ex2.partition('aabcdaad'))
