================================================================
        TOP CODING INTERVIEW PROBLEMS - COMPLETE SOLUTIONS
================================================================
Language used for all code: PYTHON
Format: Problem -> Type -> What Interviewer Checks -> Approach -> Code -> Complexity
================================================================


############################
# SECTION 1: ARRAYS & HASHING
############################

----------------------------------------------------------------
1. TWO SUM
----------------------------------------------------------------
Type: Array + HashMap
Interviewer Checks: Can you optimize from O(n^2) to O(n)?

APPROACH:
- Brute force checks every pair -> O(n^2).
- Optimal: use a HashMap to store {value: index} while iterating.
  For each number, check if (target - number) already exists in map.

CODE:
    def two_sum(nums, target):
        seen = {}
        for i, num in enumerate(nums):
            complement = target - num
            if complement in seen:
                return [seen[complement], i]
            seen[num] = i
        return []

COMPLEXITY: Time O(n) | Space O(n)


----------------------------------------------------------------
2. BEST TIME TO BUY AND SELL STOCK
----------------------------------------------------------------
Type: Array
Interviewer Checks: Ability to track min/max efficiently

APPROACH:
- Track the minimum price seen so far while scanning left to right.
- At each step compute profit = current price - min_price_so_far.
- Keep the maximum profit found.

CODE:
    def max_profit(prices):
        min_price = float('inf')
        max_profit = 0
        for price in prices:
            min_price = min(min_price, price)
            max_profit = max(max_profit, price - min_price)
        return max_profit

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
3. CONTAINS DUPLICATE
----------------------------------------------------------------
Type: Hashing
Interviewer Checks: Knowledge of sets and time complexity

APPROACH:
- Use a set to track numbers seen so far.
- If a number is already in the set, a duplicate exists.

CODE:
    def contains_duplicate(nums):
        seen = set()
        for num in nums:
            if num in seen:
                return True
            seen.add(num)
        return False

    # One-liner alternative:
    # return len(nums) != len(set(nums))

COMPLEXITY: Time O(n) | Space O(n)


----------------------------------------------------------------
4. PRODUCT OF ARRAY EXCEPT SELF
----------------------------------------------------------------
Type: Prefix/Suffix Array
Interviewer Checks: Problem-solving without division

APPROACH:
- Build a prefix product array (product of all elements before i).
- Build a suffix product array (product of all elements after i).
- result[i] = prefix[i] * suffix[i]
- Can be optimized to O(1) extra space using a single output array.

CODE:
    def product_except_self(nums):
        n = len(nums)
        result = [1] * n

        prefix = 1
        for i in range(n):
            result[i] = prefix
            prefix *= nums[i]

        suffix = 1
        for i in range(n - 1, -1, -1):
            result[i] *= suffix
            suffix *= nums[i]

        return result

COMPLEXITY: Time O(n) | Space O(1) extra (excluding output array)


----------------------------------------------------------------
5. MAXIMUM SUBARRAY (KADANE'S ALGORITHM)
----------------------------------------------------------------
Type: Dynamic Programming
Interviewer Checks: Can you identify optimal substructure?

APPROACH:
- Keep a running sum "current_sum".
- If current_sum becomes negative, reset it to 0 (start fresh).
- Track the maximum sum seen at any point.

CODE:
    def max_subarray(nums):
        max_sum = nums[0]
        current_sum = 0
        for num in nums:
            current_sum += num
            max_sum = max(max_sum, current_sum)
            if current_sum < 0:
                current_sum = 0
        return max_sum

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
6. MOVE ZEROES
----------------------------------------------------------------
Type: Two Pointers
Interviewer Checks: In-place array manipulation

APPROACH:
- Use a pointer "insert_pos" to track where the next non-zero
  element should go.
- Walk through the array; whenever a non-zero is found, swap it
  to insert_pos and increment insert_pos.

CODE:
    def move_zeroes(nums):
        insert_pos = 0
        for i in range(len(nums)):
            if nums[i] != 0:
                nums[insert_pos], nums[i] = nums[i], nums[insert_pos]
                insert_pos += 1
        return nums

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
7. MERGE SORTED ARRAYS
----------------------------------------------------------------
Type: Two Pointers
Interviewer Checks: Efficient merging logic

APPROACH:
- Use two pointers, one for each array, starting from index 0.
- Compare elements, push the smaller one to the result.
- If merging in-place (like LeetCode "Merge Sorted Array"),
  fill from the back to avoid overwriting.

CODE:
    def merge_sorted(nums1, m, nums2, n):
        i, j, k = m - 1, n - 1, m + n - 1
        while j >= 0:
            if i >= 0 and nums1[i] > nums2[j]:
                nums1[k] = nums1[i]
                i -= 1
            else:
                nums1[k] = nums2[j]
                j -= 1
            k -= 1
        return nums1

COMPLEXITY: Time O(m + n) | Space O(1)


----------------------------------------------------------------
8. VALID ANAGRAM
----------------------------------------------------------------
Type: Hashing / String
Interviewer Checks: Frequency counting techniques

APPROACH:
- If lengths differ, return False immediately.
- Count character frequency of both strings using a dictionary
  or Counter and compare.

CODE:
    from collections import Counter

    def is_anagram(s, t):
        if len(s) != len(t):
            return False
        return Counter(s) == Counter(t)

COMPLEXITY: Time O(n) | Space O(1) (limited alphabet size)


----------------------------------------------------------------
9. LONGEST COMMON PREFIX
----------------------------------------------------------------
Type: String
Interviewer Checks: String traversal and edge cases

APPROACH:
- Take the first string as a reference prefix.
- Compare it character by character against every other string,
  shrinking the prefix whenever a mismatch is found.

CODE:
    def longest_common_prefix(strs):
        if not strs:
            return ""
        prefix = strs[0]
        for s in strs[1:]:
            while not s.startswith(prefix):
                prefix = prefix[:-1]
                if not prefix:
                    return ""
        return prefix

COMPLEXITY: Time O(n * m) | Space O(1)  (n = number of strings, m = length)


----------------------------------------------------------------
10. VALID PALINDROME
----------------------------------------------------------------
Type: String + Two Pointers
Interviewer Checks: Data cleaning and pointer usage

APPROACH:
- Use two pointers, left and right, moving inward.
- Skip non-alphanumeric characters.
- Compare lowercase versions of characters; mismatch -> not palindrome.

CODE:
    def is_palindrome(s):
        left, right = 0, len(s) - 1
        while left < right:
            while left < right and not s[left].isalnum():
                left += 1
            while left < right and not s[right].isalnum():
                right -= 1
            if s[left].lower() != s[right].lower():
                return False
            left += 1
            right -= 1
        return True

COMPLEXITY: Time O(n) | Space O(1)


############################
# SECTION 2: STRINGS / SLIDING WINDOW / HASHMAP
############################

----------------------------------------------------------------
11. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
----------------------------------------------------------------
Type: Sliding Window
Interviewer Checks: Window optimization skills

APPROACH:
- Maintain a window [left, right] and a set/dict of characters
  currently inside the window.
- Expand right; if a duplicate is found, shrink from the left
  until the duplicate is removed.
- Track the maximum window size.

CODE:
    def longest_unique_substring(s):
        seen = {}
        left = 0
        max_len = 0
        for right, ch in enumerate(s):
            if ch in seen and seen[ch] >= left:
                left = seen[ch] + 1
            seen[ch] = right
            max_len = max(max_len, right - left + 1)
        return max_len

COMPLEXITY: Time O(n) | Space O(min(n, alphabet size))


----------------------------------------------------------------
12. GROUP ANAGRAMS
----------------------------------------------------------------
Type: HashMap
Interviewer Checks: Pattern recognition and hashing

APPROACH:
- For each string, compute a "signature" - the sorted version of
  the string (anagrams share the same sorted form).
- Group strings in a dictionary keyed by this signature.

CODE:
    from collections import defaultdict

    def group_anagrams(strs):
        groups = defaultdict(list)
        for s in strs:
            key = ''.join(sorted(s))
            groups[key].append(s)
        return list(groups.values())

COMPLEXITY: Time O(n * k log k) | Space O(n * k)  (k = max string length)


############################
# SECTION 3: LINKED LISTS
############################

----------------------------------------------------------------
13. REVERSE LINKED LIST
----------------------------------------------------------------
Type: Linked List
Interviewer Checks: Pointer manipulation fundamentals

APPROACH:
- Iterate through the list, keeping track of "prev" node.
- At each node, save "next", point current.next to prev,
  then move prev and current forward.

CODE:
    class ListNode:
        def __init__(self, val=0, next=None):
            self.val = val
            self.next = next

    def reverse_list(head):
        prev = None
        current = head
        while current:
            next_node = current.next
            current.next = prev
            prev = current
            current = next_node
        return prev

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
14. MIDDLE OF LINKED LIST
----------------------------------------------------------------
Type: Fast & Slow Pointer
Interviewer Checks: Knowledge of Floyd's technique

APPROACH:
- Slow pointer moves 1 step, fast pointer moves 2 steps.
- When fast reaches the end, slow is at the middle.

CODE:
    def middle_node(head):
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
        return slow

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
15. DETECT CYCLE IN LINKED LIST
----------------------------------------------------------------
Type: Fast & Slow Pointer
Interviewer Checks: Cycle detection concepts

APPROACH:
- Floyd's Cycle Detection (Tortoise and Hare).
- Slow moves 1 step, fast moves 2 steps.
- If they ever meet, there is a cycle. If fast hits None, no cycle.

CODE:
    def has_cycle(head):
        slow = fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                return True
        return False

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
16. MERGE TWO SORTED LINKED LISTS
----------------------------------------------------------------
Type: Linked List
Interviewer Checks: Recursive vs iterative thinking

APPROACH (Iterative):
- Use a dummy head node. Compare current nodes of both lists,
  attach the smaller one, and advance that list's pointer.

CODE:
    def merge_two_lists(l1, l2):
        dummy = ListNode()
        tail = dummy
        while l1 and l2:
            if l1.val <= l2.val:
                tail.next = l1
                l1 = l1.next
            else:
                tail.next = l2
                l2 = l2.next
            tail = tail.next
        tail.next = l1 if l1 else l2
        return dummy.next

    # Recursive alternative:
    def merge_two_lists_recursive(l1, l2):
        if not l1: return l2
        if not l2: return l1
        if l1.val <= l2.val:
            l1.next = merge_two_lists_recursive(l1.next, l2)
            return l1
        else:
            l2.next = merge_two_lists_recursive(l1, l2.next)
            return l2

COMPLEXITY: Time O(n + m) | Space O(1) iterative / O(n+m) recursive (call stack)


############################
# SECTION 4: BINARY SEARCH
############################

----------------------------------------------------------------
17. BINARY SEARCH
----------------------------------------------------------------
Type: Searching
Interviewer Checks: Understanding of divide and conquer

APPROACH:
- Maintain left and right boundaries.
- Repeatedly check the middle element; narrow the search space
  by half each time based on comparison with target.

CODE:
    def binary_search(nums, target):
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target:
                left = mid + 1
            else:
                right = mid - 1
        return -1

COMPLEXITY: Time O(log n) | Space O(1)


----------------------------------------------------------------
18. SEARCH IN ROTATED SORTED ARRAY
----------------------------------------------------------------
Type: Binary Search
Interviewer Checks: Advanced binary search logic

APPROACH:
- Standard binary search, but at each step determine which half
  (left or right of mid) is properly sorted.
- Use that sorted half to decide whether the target could lie
  within it, and move boundaries accordingly.

CODE:
    def search_rotated(nums, target):
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = (left + right) // 2
            if nums[mid] == target:
                return mid
            if nums[left] <= nums[mid]:        # left half sorted
                if nums[left] <= target < nums[mid]:
                    right = mid - 1
                else:
                    left = mid + 1
            else:                                # right half sorted
                if nums[mid] < target <= nums[right]:
                    left = mid + 1
                else:
                    right = mid - 1
        return -1

COMPLEXITY: Time O(log n) | Space O(1)


----------------------------------------------------------------
19. FIND FIRST AND LAST POSITION OF ELEMENT IN SORTED ARRAY
----------------------------------------------------------------
Type: Binary Search
Interviewer Checks: Boundary conditions

APPROACH:
- Run two separate binary searches:
  one biased to find the leftmost occurrence,
  another biased to find the rightmost occurrence.

CODE:
    def search_range(nums, target):
        def find_bound(is_first):
            left, right = 0, len(nums) - 1
            result = -1
            while left <= right:
                mid = (left + right) // 2
                if nums[mid] == target:
                    result = mid
                    if is_first:
                        right = mid - 1
                    else:
                        left = mid + 1
                elif nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid - 1
            return result

        return [find_bound(True), find_bound(False)]

COMPLEXITY: Time O(log n) | Space O(1)


############################
# SECTION 5: TREES
############################

----------------------------------------------------------------
20. MAXIMUM DEPTH OF BINARY TREE
----------------------------------------------------------------
Type: Tree DFS
Interviewer Checks: Recursive thinking

APPROACH:
- Recursively find the depth of the left and right subtrees.
- The depth of the current node is 1 + max(left depth, right depth).

CODE:
    class TreeNode:
        def __init__(self, val=0, left=None, right=None):
            self.val = val
            self.left = left
            self.right = right

    def max_depth(root):
        if not root:
            return 0
        return 1 + max(max_depth(root.left), max_depth(root.right))

COMPLEXITY: Time O(n) | Space O(h) (h = tree height, recursion stack)


----------------------------------------------------------------
21. LEVEL ORDER TRAVERSAL
----------------------------------------------------------------
Type: Tree BFS
Interviewer Checks: Queue usage

APPROACH:
- Use a queue to process nodes level by level.
- For each level, pop all current nodes, record their values,
  and push their children for the next level.

CODE:
    from collections import deque

    def level_order(root):
        if not root:
            return []
        result = []
        queue = deque([root])
        while queue:
            level_size = len(queue)
            level = []
            for _ in range(level_size):
                node = queue.popleft()
                level.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            result.append(level)
        return result

COMPLEXITY: Time O(n) | Space O(n)


----------------------------------------------------------------
22. LOWEST COMMON ANCESTOR
----------------------------------------------------------------
Type: Trees
Interviewer Checks: Tree traversal understanding

APPROACH (Binary Tree, general case):
- Recursively search left and right subtrees for p and q.
- If both are found in different subtrees, the current node is
  the LCA. Otherwise, return whichever side found a match.

CODE:
    def lowest_common_ancestor(root, p, q):
        if not root or root == p or root == q:
            return root
        left = lowest_common_ancestor(root.left, p, q)
        right = lowest_common_ancestor(root.right, p, q)
        if left and right:
            return root
        return left if left else right

COMPLEXITY: Time O(n) | Space O(h)


----------------------------------------------------------------
23. VALIDATE BINARY SEARCH TREE
----------------------------------------------------------------
Type: Trees
Interviewer Checks: BST property knowledge

APPROACH:
- Recursively validate each node against a valid (low, high) range.
- Left child must be less than node value; right child greater.
- Pass updated bounds down as you recurse.

CODE:
    def is_valid_bst(root, low=float('-inf'), high=float('inf')):
        if not root:
            return True
        if not (low < root.val < high):
            return False
        return (is_valid_bst(root.left, low, root.val) and
                is_valid_bst(root.right, root.val, high))

COMPLEXITY: Time O(n) | Space O(h)


----------------------------------------------------------------
24. INVERT BINARY TREE
----------------------------------------------------------------
Type: Trees
Interviewer Checks: Recursive traversal

APPROACH:
- Recursively swap the left and right children of every node.

CODE:
    def invert_tree(root):
        if not root:
            return None
        root.left, root.right = invert_tree(root.right), invert_tree(root.left)
        return root

COMPLEXITY: Time O(n) | Space O(h)


############################
# SECTION 6: GRAPHS
############################

----------------------------------------------------------------
25. NUMBER OF ISLANDS
----------------------------------------------------------------
Type: Graph DFS/BFS
Interviewer Checks: Graph traversal skills

APPROACH:
- Scan the grid; whenever a '1' (land) is found that hasn't been
  visited, increment island count and run DFS/BFS to mark the
  entire connected island as visited.

CODE:
    def num_islands(grid):
        if not grid:
            return 0
        rows, cols = len(grid), len(grid[0])
        count = 0

        def dfs(r, c):
            if r < 0 or r >= rows or c < 0 or c >= cols or grid[r][c] != '1':
                return
            grid[r][c] = '0'   # mark visited
            dfs(r + 1, c)
            dfs(r - 1, c)
            dfs(r, c + 1)
            dfs(r, c - 1)

        for r in range(rows):
            for c in range(cols):
                if grid[r][c] == '1':
                    count += 1
                    dfs(r, c)
        return count

COMPLEXITY: Time O(rows * cols) | Space O(rows * cols) worst case (recursion)


----------------------------------------------------------------
26. CLONE GRAPH
----------------------------------------------------------------
Type: Graph
Interviewer Checks: Deep copy concepts

APPROACH:
- Use DFS with a hashmap {original_node: cloned_node}.
- For each node, if not already cloned, create a clone, then
  recursively clone its neighbors and link them.

CODE:
    class GraphNode:
        def __init__(self, val=0, neighbors=None):
            self.val = val
            self.neighbors = neighbors if neighbors else []

    def clone_graph(node):
        if not node:
            return None
        visited = {}

        def dfs(n):
            if n in visited:
                return visited[n]
            clone = GraphNode(n.val)
            visited[n] = clone
            for neighbor in n.neighbors:
                clone.neighbors.append(dfs(neighbor))
            return clone

        return dfs(node)

COMPLEXITY: Time O(V + E) | Space O(V)


----------------------------------------------------------------
27. COURSE SCHEDULE
----------------------------------------------------------------
Type: Graph + Topological Sort
Interviewer Checks: Dependency management

APPROACH:
- Model courses and prerequisites as a directed graph.
- Use Topological Sort (Kahn's Algorithm with BFS, or DFS with
  cycle detection). If a valid topological order covering all
  courses exists, all courses can be finished. A cycle means
  it's impossible.

CODE:
    from collections import deque, defaultdict

    def can_finish(num_courses, prerequisites):
        graph = defaultdict(list)
        indegree = [0] * num_courses

        for course, prereq in prerequisites:
            graph[prereq].append(course)
            indegree[course] += 1

        queue = deque([c for c in range(num_courses) if indegree[c] == 0])
        completed = 0

        while queue:
            current = queue.popleft()
            completed += 1
            for nxt in graph[current]:
                indegree[nxt] -= 1
                if indegree[nxt] == 0:
                    queue.append(nxt)

        return completed == num_courses

COMPLEXITY: Time O(V + E) | Space O(V + E)


----------------------------------------------------------------
28. FLOOD FILL
----------------------------------------------------------------
Type: DFS/BFS
Interviewer Checks: Traversal basics

APPROACH:
- Starting from the given pixel, recursively (DFS) or iteratively
  (BFS) change connected pixels of the same original color to
  the new color.

CODE:
    def flood_fill(image, sr, sc, new_color):
        rows, cols = len(image), len(image[0])
        start_color = image[sr][sc]
        if start_color == new_color:
            return image

        def dfs(r, c):
            if r < 0 or r >= rows or c < 0 or c >= cols:
                return
            if image[r][c] != start_color:
                return
            image[r][c] = new_color
            dfs(r + 1, c)
            dfs(r - 1, c)
            dfs(r, c + 1)
            dfs(r, c - 1)

        dfs(sr, sc)
        return image

COMPLEXITY: Time O(rows * cols) | Space O(rows * cols) worst case


############################
# SECTION 7: DYNAMIC PROGRAMMING
############################

----------------------------------------------------------------
29. FIBONACCI
----------------------------------------------------------------
Type: DP
Interviewer Checks: Memoization understanding

APPROACH:
- Naive recursion recomputes the same subproblems -> O(2^n).
- Use memoization (top-down) or bottom-up iteration with O(1)
  rolling variables to get O(n) time.

CODE:
    def fibonacci(n):
        if n <= 1:
            return n
        a, b = 0, 1
        for _ in range(2, n + 1):
            a, b = b, a + b
        return b

    # Memoized version:
    def fibonacci_memo(n, cache={}):
        if n <= 1:
            return n
        if n in cache:
            return cache[n]
        cache[n] = fibonacci_memo(n - 1, cache) + fibonacci_memo(n - 2, cache)
        return cache[n]

COMPLEXITY: Time O(n) | Space O(1) iterative / O(n) memoized


----------------------------------------------------------------
30. CLIMBING STAIRS
----------------------------------------------------------------
Type: DP
Interviewer Checks: Pattern recognition

APPROACH:
- Ways to reach step n = ways to reach (n-1) + ways to reach (n-2)
  (this is exactly the Fibonacci pattern).
- Use two rolling variables instead of an array.

CODE:
    def climb_stairs(n):
        if n <= 2:
            return n
        prev2, prev1 = 1, 2
        for _ in range(3, n + 1):
            prev2, prev1 = prev1, prev1 + prev2
        return prev1

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
31. HOUSE ROBBER
----------------------------------------------------------------
Type: DP
Interviewer Checks: State transition thinking

APPROACH:
- At each house, decide: rob it (current house value + best up to
  two houses back) or skip it (best up to previous house).
- dp[i] = max(dp[i-1], dp[i-2] + nums[i])

CODE:
    def rob(nums):
        prev2, prev1 = 0, 0
        for num in nums:
            current = max(prev1, prev2 + num)
            prev2 = prev1
            prev1 = current
        return prev1

COMPLEXITY: Time O(n) | Space O(1)


----------------------------------------------------------------
32. COIN CHANGE
----------------------------------------------------------------
Type: DP
Interviewer Checks: Optimization problem solving

APPROACH:
- dp[amount] = minimum coins needed to make that amount.
- dp[0] = 0. For each amount from 1 to target, try every coin
  and take the minimum: dp[a] = min(dp[a], dp[a - coin] + 1)

CODE:
    def coin_change(coins, amount):
        dp = [float('inf')] * (amount + 1)
        dp[0] = 0
        for a in range(1, amount + 1):
            for coin in coins:
                if coin <= a:
                    dp[a] = min(dp[a], dp[a - coin] + 1)
        return dp[amount] if dp[amount] != float('inf') else -1

COMPLEXITY: Time O(amount * len(coins)) | Space O(amount)


----------------------------------------------------------------
33. LONGEST INCREASING SUBSEQUENCE
----------------------------------------------------------------
Type: DP
Interviewer Checks: Advanced DP concepts

APPROACH (O(n^2) DP):
- dp[i] = length of the longest increasing subsequence ending at i.
- For each i, check all j < i; if nums[j] < nums[i],
  dp[i] = max(dp[i], dp[j] + 1)

APPROACH (Optimal O(n log n)):
- Maintain a list "tails" where tails[k] = smallest possible tail
  value of an increasing subsequence of length k+1.
- Use binary search to find where each number fits.

CODE (O(n log n)):
    import bisect

    def length_of_lis(nums):
        tails = []
        for num in nums:
            pos = bisect.bisect_left(tails, num)
            if pos == len(tails):
                tails.append(num)
            else:
                tails[pos] = num
        return len(tails)

COMPLEXITY: Time O(n log n) | Space O(n)


----------------------------------------------------------------
34. WORD BREAK
----------------------------------------------------------------
Type: DP
Interviewer Checks: String partitioning logic

APPROACH:
- dp[i] = True if s[0:i] can be segmented into valid dictionary words.
- dp[0] = True (empty string).
- For each i, check all j < i; if dp[j] is True and s[j:i] is in
  the word set, then dp[i] = True.

CODE:
    def word_break(s, word_dict):
        word_set = set(word_dict)
        n = len(s)
        dp = [False] * (n + 1)
        dp[0] = True
        for i in range(1, n + 1):
            for j in range(i):
                if dp[j] and s[j:i] in word_set:
                    dp[i] = True
                    break
        return dp[n]

COMPLEXITY: Time O(n^2) | Space O(n)


############################
# SECTION 8: HEAPS
############################

----------------------------------------------------------------
35. KTH LARGEST ELEMENT
----------------------------------------------------------------
Type: Heap
Interviewer Checks: Priority queue usage

APPROACH:
- Maintain a min-heap of size k.
- Push elements one by one; if heap size exceeds k, pop the
  smallest. The top of the heap is the kth largest element.

CODE:
    import heapq

    def find_kth_largest(nums, k):
        heap = []
        for num in nums:
            heapq.heappush(heap, num)
            if len(heap) > k:
                heapq.heappop(heap)
        return heap[0]

COMPLEXITY: Time O(n log k) | Space O(k)


----------------------------------------------------------------
36. TOP K FREQUENT ELEMENTS
----------------------------------------------------------------
Type: Heap + HashMap
Interviewer Checks: Combining data structures

APPROACH:
- Count frequency of each element using a HashMap.
- Use a heap (or heapq.nlargest) to extract the k elements with
  the highest frequency.

CODE:
    import heapq
    from collections import Counter

    def top_k_frequent(nums, k):
        freq = Counter(nums)
        return heapq.nlargest(k, freq.keys(), key=freq.get)

COMPLEXITY: Time O(n log k) | Space O(n)


############################
# SECTION 9: INTERVALS
############################

----------------------------------------------------------------
37. MERGE INTERVALS
----------------------------------------------------------------
Type: Intervals
Interviewer Checks: Sorting and merging logic

APPROACH:
- Sort intervals by start time.
- Iterate through; if the current interval overlaps with the
  last merged interval (start <= last_end), merge them by
  extending the end. Otherwise, add as a new interval.

CODE:
    def merge_intervals(intervals):
        if not intervals:
            return []
        intervals.sort(key=lambda x: x[0])
        merged = [intervals[0]]
        for start, end in intervals[1:]:
            last_end = merged[-1][1]
            if start <= last_end:
                merged[-1][1] = max(last_end, end)
            else:
                merged.append([start, end])
        return merged

COMPLEXITY: Time O(n log n) | Space O(n)


----------------------------------------------------------------
38. MEETING ROOMS
----------------------------------------------------------------
Type: Intervals
Interviewer Checks: Conflict detection

APPROACH:
- Sort meetings by start time.
- Check consecutive meetings: if the current meeting starts
  before the previous one ends, there's a conflict
  (a single person cannot attend all meetings).

CODE:
    def can_attend_meetings(intervals):
        intervals.sort(key=lambda x: x[0])
        for i in range(1, len(intervals)):
            if intervals[i][0] < intervals[i - 1][1]:
                return False
        return True

COMPLEXITY: Time O(n log n) | Space O(1)


############################
# SECTION 10: STACKS
############################

----------------------------------------------------------------
39. MIN STACK
----------------------------------------------------------------
Type: Stack Design
Interviewer Checks: Custom data structure design

APPROACH:
- Maintain two stacks: one for actual values, one that tracks
  the minimum value at each point in time.
- Push the current minimum onto the min-stack every time a
  value is pushed; pop both together.

CODE:
    class MinStack:
        def __init__(self):
            self.stack = []
            self.min_stack = []

        def push(self, val):
            self.stack.append(val)
            current_min = min(val, self.min_stack[-1] if self.min_stack else val)
            self.min_stack.append(current_min)

        def pop(self):
            self.stack.pop()
            self.min_stack.pop()

        def top(self):
            return self.stack[-1]

        def get_min(self):
            return self.min_stack[-1]

COMPLEXITY: Time O(1) for all operations | Space O(n)


----------------------------------------------------------------
40. VALID PARENTHESES
----------------------------------------------------------------
Type: Stack
Interviewer Checks: Stack fundamentals

APPROACH:
- Push opening brackets onto a stack.
- On a closing bracket, check if the top of the stack is the
  matching opening bracket; if so pop it, otherwise it's invalid.
- String is valid if the stack is empty at the end.

CODE:
    def is_valid(s):
        stack = []
        pairs = {')': '(', ']': '[', '}': '{'}
        for ch in s:
            if ch in '([{':
                stack.append(ch)
            else:
                if not stack or stack.pop() != pairs[ch]:
                    return False
        return not stack

COMPLEXITY: Time O(n) | Space O(n)


----------------------------------------------------------------
41. DAILY TEMPERATURES
----------------------------------------------------------------
Type: Monotonic Stack
Interviewer Checks: Advanced stack application

APPROACH:
- Use a stack of indices with decreasing temperatures.
- For each day, pop all indices from the stack whose temperature
  is less than the current day's temperature, and record the
  distance (days waited) for each popped index.

CODE:
    def daily_temperatures(temperatures):
        n = len(temperatures)
        result = [0] * n
        stack = []   # stores indices
        for i, temp in enumerate(temperatures):
            while stack and temperatures[stack[-1]] < temp:
                prev_index = stack.pop()
                result[prev_index] = i - prev_index
            stack.append(i)
        return result

COMPLEXITY: Time O(n) | Space O(n)


############################
# SECTION 11: SYSTEM / DATA STRUCTURE DESIGN
############################

----------------------------------------------------------------
42. LRU CACHE
----------------------------------------------------------------
Type: Design + HashMap + Doubly Linked List
Interviewer Checks: System design fundamentals

APPROACH:
- Combine a HashMap (for O(1) lookup) with a Doubly Linked List
  (for O(1) removal/insertion to track recency order).
- On "get": move the accessed node to the front (most recent).
- On "put": if key exists, update and move to front. If capacity
  exceeded, evict the node at the back (least recently used).
- Python's OrderedDict gives this behavior out of the box.

CODE:
    from collections import OrderedDict

    class LRUCache:
        def __init__(self, capacity):
            self.cache = OrderedDict()
            self.capacity = capacity

        def get(self, key):
            if key not in self.cache:
                return -1
            self.cache.move_to_end(key)
            return self.cache[key]

        def put(self, key, value):
            if key in self.cache:
                self.cache.move_to_end(key)
            self.cache[key] = value
            if len(self.cache) > self.capacity:
                self.cache.popitem(last=False)

COMPLEXITY: Time O(1) for get and put | Space O(capacity)


----------------------------------------------------------------
43. IMPLEMENT QUEUE USING STACKS
----------------------------------------------------------------
Type: Design
Interviewer Checks: Data structure transformation

APPROACH:
- Use two stacks: "in_stack" for enqueue operations and
  "out_stack" for dequeue operations.
- When dequeuing, if out_stack is empty, pour all elements from
  in_stack into out_stack (this reverses their order to FIFO).

CODE:
    class MyQueue:
        def __init__(self):
            self.in_stack = []
            self.out_stack = []

        def push(self, x):
            self.in_stack.append(x)

        def pop(self):
            self._transfer()
            return self.out_stack.pop()

        def peek(self):
            self._transfer()
            return self.out_stack[-1]

        def empty(self):
            return not self.in_stack and not self.out_stack

        def _transfer(self):
            if not self.out_stack:
                while self.in_stack:
                    self.out_stack.append(self.in_stack.pop())

COMPLEXITY: Time O(1) amortized per operation | Space O(n)


----------------------------------------------------------------
44. TRIE IMPLEMENTATION
----------------------------------------------------------------
Type: Trie
Interviewer Checks: Data structure design

APPROACH:
- Each Trie node holds a dictionary of {character: child_node}
  and a flag marking the end of a word.
- "insert" walks/creates nodes character by character.
- "search" walks nodes and checks the end-of-word flag.
- "starts_with" walks nodes without requiring the end-of-word flag.

CODE:
    class TrieNode:
        def __init__(self):
            self.children = {}
            self.is_end_of_word = False

    class Trie:
        def __init__(self):
            self.root = TrieNode()

        def insert(self, word):
            node = self.root
            for ch in word:
                if ch not in node.children:
                    node.children[ch] = TrieNode()
                node = node.children[ch]
            node.is_end_of_word = True

        def search(self, word):
            node = self._find_node(word)
            return node is not None and node.is_end_of_word

        def starts_with(self, prefix):
            return self._find_node(prefix) is not None

        def _find_node(self, prefix):
            node = self.root
            for ch in prefix:
                if ch not in node.children:
                    return None
                node = node.children[ch]
            return node

COMPLEXITY: Time O(L) per operation (L = word/prefix length) | Space O(N * L) total


----------------------------------------------------------------
45. DESIGN URL SHORTENER
----------------------------------------------------------------
Type: System Design
Interviewer Checks: Scalability thinking

APPROACH (Conceptual, not just code):
- Core idea: map a long URL to a short, unique code (and back).
- Encoding: maintain a counter or use a hash of the URL, then
  convert it to a base-62 string (a-z, A-Z, 0-9) for compactness.
- Store the mapping {short_code: long_url} in a fast key-value
  store (e.g., a hash map in-memory for interview purposes, or
  a distributed database like DynamoDB/Redis in real systems).
- Decoding: look up the short_code in the store and redirect.
- At scale, discuss: database sharding, caching layer (Redis) for
  hot URLs, collision handling, and using a distributed counter
  (e.g., Snowflake IDs) instead of a single incrementing counter.

CODE (Simplified in-memory version):
    import string
    import random

    class URLShortener:
        def __init__(self):
            self.url_to_code = {}
            self.code_to_url = {}
            self.chars = string.ascii_letters + string.digits

        def encode(self, long_url):
            if long_url in self.url_to_code:
                return self.url_to_code[long_url]
            code = ''.join(random.choices(self.chars, k=6))
            while code in self.code_to_url:
                code = ''.join(random.choices(self.chars, k=6))
            self.url_to_code[long_url] = code
            self.code_to_url[code] = long_url
            return code

        def decode(self, code):
            return self.code_to_url.get(code, None)

COMPLEXITY: Time O(1) average for encode/decode | Space O(n)
SCALABILITY NOTES:
  - Use a distributed ID generator instead of random codes
    to avoid collisions at scale.
  - Cache frequently accessed URLs (Redis/Memcached).
  - Use a database with read replicas for high read throughput.


================================================================
                         END OF DOCUMENT
================================================================
STUDY TIP: For each problem, practice explaining your APPROACH
out loud in under 60 seconds before writing code — this is what
most interviewers evaluate first.
================================================================
