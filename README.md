================================================================
     TOP CODING INTERVIEW PROBLEMS - JAVA SOLUTIONS (OPTIMAL)
================================================================


############################
# SECTION 1: ARRAYS & HASHING
############################

----------------------------------------------------------------
1. TWO SUM
----------------------------------------------------------------
// Time O(n) | Space O(n)
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (seen.containsKey(complement)) {
            return new int[]{seen.get(complement), i};
        }
        seen.put(nums[i], i);
    }
    return new int[]{};
}


----------------------------------------------------------------
2. BEST TIME TO BUY AND SELL STOCK
----------------------------------------------------------------
// Time O(n) | Space O(1)
public int maxProfit(int[] prices) {
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;
    for (int price : prices) {
        minPrice = Math.min(minPrice, price);
        maxProfit = Math.max(maxProfit, price - minPrice);
    }
    return maxProfit;
}


----------------------------------------------------------------
3. CONTAINS DUPLICATE
----------------------------------------------------------------
// Time O(n) | Space O(n)
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (!seen.add(num)) return true;
    }
    return false;
}


----------------------------------------------------------------
4. PRODUCT OF ARRAY EXCEPT SELF
----------------------------------------------------------------
// Time O(n) | Space O(1) extra (output array excluded)
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    int prefix = 1;
    for (int i = 0; i < n; i++) {
        result[i] = prefix;
        prefix *= nums[i];
    }

    int suffix = 1;
    for (int i = n - 1; i >= 0; i--) {
        result[i] *= suffix;
        suffix *= nums[i];
    }
    return result;
}


----------------------------------------------------------------
5. MAXIMUM SUBARRAY (KADANE'S ALGORITHM)
----------------------------------------------------------------
// Time O(n) | Space O(1)
public int maxSubArray(int[] nums) {
    int maxSum = nums[0];
    int currentSum = 0;
    for (int num : nums) {
        currentSum += num;
        maxSum = Math.max(maxSum, currentSum);
        if (currentSum < 0) currentSum = 0;
    }
    return maxSum;
}


----------------------------------------------------------------
6. MOVE ZEROES
----------------------------------------------------------------
// Time O(n) | Space O(1)
public void moveZeroes(int[] nums) {
    int insertPos = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            int temp = nums[insertPos];
            nums[insertPos] = nums[i];
            nums[i] = temp;
            insertPos++;
        }
    }
}


----------------------------------------------------------------
7. MERGE SORTED ARRAYS
----------------------------------------------------------------
// Time O(m + n) | Space O(1)
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1, j = n - 1, k = m + n - 1;
    while (j >= 0) {
        if (i >= 0 && nums1[i] > nums2[j]) {
            nums1[k--] = nums1[i--];
        } else {
            nums1[k--] = nums2[j--];
        }
    }
}


----------------------------------------------------------------
8. VALID ANAGRAM
----------------------------------------------------------------
// Time O(n) | Space O(1) (fixed alphabet size)
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] count = new int[26];
    for (int i = 0; i < s.length(); i++) {
        count[s.charAt(i) - 'a']++;
        count[t.charAt(i) - 'a']--;
    }
    for (int c : count) {
        if (c != 0) return false;
    }
    return true;
}


----------------------------------------------------------------
9. LONGEST COMMON PREFIX
----------------------------------------------------------------
// Time O(n * m) | Space O(1)
public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
        while (!strs[i].startsWith(prefix)) {
            prefix = prefix.substring(0, prefix.length() - 1);
            if (prefix.isEmpty()) return "";
        }
    }
    return prefix;
}


----------------------------------------------------------------
10. VALID PALINDROME
----------------------------------------------------------------
// Time O(n) | Space O(1)
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    while (left < right) {
        while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
        while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}


############################
# SECTION 2: STRINGS / SLIDING WINDOW / HASHMAP
############################

----------------------------------------------------------------
11. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS
----------------------------------------------------------------
// Time O(n) | Space O(min(n, charset))
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> seen = new HashMap<>();
    int left = 0, maxLen = 0;
    for (int right = 0; right < s.length(); right++) {
        char ch = s.charAt(right);
        if (seen.containsKey(ch) && seen.get(ch) >= left) {
            left = seen.get(ch) + 1;
        }
        seen.put(ch, right);
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}


----------------------------------------------------------------
12. GROUP ANAGRAMS
----------------------------------------------------------------
// Time O(n * k log k) | Space O(n * k)
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    for (String s : strs) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String key = new String(chars);
        groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(groups.values());
}


############################
# SECTION 3: LINKED LISTS
############################

----------------------------------------------------------------
13. REVERSE LINKED LIST
----------------------------------------------------------------
// Time O(n) | Space O(1)
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public ListNode reverseList(ListNode head) {
    ListNode prev = null, current = head;
    while (current != null) {
        ListNode nextNode = current.next;
        current.next = prev;
        prev = current;
        current = nextNode;
    }
    return prev;
}


----------------------------------------------------------------
14. MIDDLE OF LINKED LIST
----------------------------------------------------------------
// Time O(n) | Space O(1)
public ListNode middleNode(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}


----------------------------------------------------------------
15. DETECT CYCLE IN LINKED LIST
----------------------------------------------------------------
// Time O(n) | Space O(1)
public boolean hasCycle(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) return true;
    }
    return false;
}


----------------------------------------------------------------
16. MERGE TWO SORTED LINKED LISTS
----------------------------------------------------------------
// Time O(n + m) | Space O(1)
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode tail = dummy;
    while (l1 != null && l2 != null) {
        if (l1.val <= l2.val) {
            tail.next = l1;
            l1 = l1.next;
        } else {
            tail.next = l2;
            l2 = l2.next;
        }
        tail = tail.next;
    }
    tail.next = (l1 != null) ? l1 : l2;
    return dummy.next;
}


############################
# SECTION 4: BINARY SEARCH
############################

----------------------------------------------------------------
17. BINARY SEARCH
----------------------------------------------------------------
// Time O(log n) | Space O(1)
public int binarySearch(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}


----------------------------------------------------------------
18. SEARCH IN ROTATED SORTED ARRAY
----------------------------------------------------------------
// Time O(log n) | Space O(1)
public int searchRotated(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        if (nums[left] <= nums[mid]) {           // left half sorted
            if (nums[left] <= target && target < nums[mid]) right = mid - 1;
            else left = mid + 1;
        } else {                                  // right half sorted
            if (nums[mid] < target && target <= nums[right]) left = mid + 1;
            else right = mid - 1;
        }
    }
    return -1;
}


----------------------------------------------------------------
19. FIND FIRST AND LAST POSITION OF ELEMENT IN SORTED ARRAY
----------------------------------------------------------------
// Time O(log n) | Space O(1)
public int[] searchRange(int[] nums, int target) {
    return new int[]{findBound(nums, target, true), findBound(nums, target, false)};
}

private int findBound(int[] nums, int target, boolean isFirst) {
    int left = 0, right = nums.length - 1, result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            result = mid;
            if (isFirst) right = mid - 1;
            else left = mid + 1;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
}


############################
# SECTION 5: TREES
############################

----------------------------------------------------------------
20. MAXIMUM DEPTH OF BINARY TREE
----------------------------------------------------------------
// Time O(n) | Space O(h)
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}


----------------------------------------------------------------
21. LEVEL ORDER TRAVERSAL
----------------------------------------------------------------
// Time O(n) | Space O(n)
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        result.add(level);
    }
    return result;
}


----------------------------------------------------------------
22. LOWEST COMMON ANCESTOR
----------------------------------------------------------------
// Time O(n) | Space O(h)
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    if (left != null && right != null) return root;
    return (left != null) ? left : right;
}


----------------------------------------------------------------
23. VALIDATE BINARY SEARCH TREE
----------------------------------------------------------------
// Time O(n) | Space O(h)
public boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

private boolean validate(TreeNode node, long low, long high) {
    if (node == null) return true;
    if (node.val <= low || node.val >= high) return false;
    return validate(node.left, low, node.val) && validate(node.right, node.val, high);
}


----------------------------------------------------------------
24. INVERT BINARY TREE
----------------------------------------------------------------
// Time O(n) | Space O(h)
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);
    return root;
}


############################
# SECTION 6: GRAPHS
############################

----------------------------------------------------------------
25. NUMBER OF ISLANDS
----------------------------------------------------------------
// Time O(rows * cols) | Space O(rows * cols) worst case
public int numIslands(char[][] grid) {
    if (grid.length == 0) return 0;
    int rows = grid.length, cols = grid[0].length;
    int count = 0;
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            if (grid[r][c] == '1') {
                count++;
                dfs(grid, r, c, rows, cols);
            }
        }
    }
    return count;
}

private void dfs(char[][] grid, int r, int c, int rows, int cols) {
    if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] != '1') return;
    grid[r][c] = '0';
    dfs(grid, r + 1, c, rows, cols);
    dfs(grid, r - 1, c, rows, cols);
    dfs(grid, r, c + 1, rows, cols);
    dfs(grid, r, c - 1, rows, cols);
}


----------------------------------------------------------------
26. CLONE GRAPH
----------------------------------------------------------------
// Time O(V + E) | Space O(V)
class GraphNode {
    int val;
    List<GraphNode> neighbors;
    GraphNode(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }
}

public GraphNode cloneGraph(GraphNode node) {
    if (node == null) return null;
    Map<GraphNode, GraphNode> visited = new HashMap<>();
    return dfs(node, visited);
}

private GraphNode dfs(GraphNode node, Map<GraphNode, GraphNode> visited) {
    if (visited.containsKey(node)) return visited.get(node);
    GraphNode clone = new GraphNode(node.val);
    visited.put(node, clone);
    for (GraphNode neighbor : node.neighbors) {
        clone.neighbors.add(dfs(neighbor, visited));
    }
    return clone;
}


----------------------------------------------------------------
27. COURSE SCHEDULE
----------------------------------------------------------------
// Time O(V + E) | Space O(V + E)  (Kahn's Algorithm)
public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
    int[] indegree = new int[numCourses];

    for (int[] pre : prerequisites) {
        graph.get(pre[1]).add(pre[0]);
        indegree[pre[0]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (indegree[i] == 0) queue.add(i);
    }

    int completed = 0;
    while (!queue.isEmpty()) {
        int current = queue.poll();
        completed++;
        for (int next : graph.get(current)) {
            if (--indegree[next] == 0) queue.add(next);
        }
    }
    return completed == numCourses;
}


----------------------------------------------------------------
28. FLOOD FILL
----------------------------------------------------------------
// Time O(rows * cols) | Space O(rows * cols) worst case
public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    int startColor = image[sr][sc];
    if (startColor != newColor) {
        fill(image, sr, sc, startColor, newColor);
    }
    return image;
}

private void fill(int[][] image, int r, int c, int startColor, int newColor) {
    if (r < 0 || r >= image.length || c < 0 || c >= image[0].length) return;
    if (image[r][c] != startColor) return;
    image[r][c] = newColor;
    fill(image, r + 1, c, startColor, newColor);
    fill(image, r - 1, c, startColor, newColor);
    fill(image, r, c + 1, startColor, newColor);
    fill(image, r, c - 1, startColor, newColor);
}


############################
# SECTION 7: DYNAMIC PROGRAMMING
############################

----------------------------------------------------------------
29. FIBONACCI
----------------------------------------------------------------
// Time O(n) | Space O(1)
public int fibonacci(int n) {
    if (n <= 1) return n;
    int a = 0, b = 1;
    for (int i = 2; i <= n; i++) {
        int temp = a + b;
        a = b;
        b = temp;
    }
    return b;
}


----------------------------------------------------------------
30. CLIMBING STAIRS
----------------------------------------------------------------
// Time O(n) | Space O(1)
public int climbStairs(int n) {
    if (n <= 2) return n;
    int prev2 = 1, prev1 = 2;
    for (int i = 3; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
}


----------------------------------------------------------------
31. HOUSE ROBBER
----------------------------------------------------------------
// Time O(n) | Space O(1)
public int rob(int[] nums) {
    int prev2 = 0, prev1 = 0;
    for (int num : nums) {
        int current = Math.max(prev1, prev2 + num);
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
}


----------------------------------------------------------------
32. COIN CHANGE
----------------------------------------------------------------
// Time O(amount * coins.length) | Space O(amount)
public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int a = 1; a <= amount; a++) {
        for (int coin : coins) {
            if (coin <= a && dp[a - coin] != Integer.MAX_VALUE) {
                dp[a] = Math.min(dp[a], dp[a - coin] + 1);
            }
        }
    }
    return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
}


----------------------------------------------------------------
33. LONGEST INCREASING SUBSEQUENCE
----------------------------------------------------------------
// Time O(n log n) | Space O(n)
public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];
    int size = 0;
    for (int num : nums) {
        int left = 0, right = size;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (tails[mid] < num) left = mid + 1;
            else right = mid;
        }
        tails[left] = num;
        if (left == size) size++;
    }
    return size;
}


----------------------------------------------------------------
34. WORD BREAK
----------------------------------------------------------------
// Time O(n^2) | Space O(n)
public boolean wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict);
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < i; j++) {
            if (dp[j] && wordSet.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[n];
}


############################
# SECTION 8: HEAPS
############################

----------------------------------------------------------------
35. KTH LARGEST ELEMENT
----------------------------------------------------------------
// Time O(n log k) | Space O(k)
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>();   // min-heap
    for (int num : nums) {
        heap.offer(num);
        if (heap.size() > k) heap.poll();
    }
    return heap.peek();
}


----------------------------------------------------------------
36. TOP K FREQUENT ELEMENTS
----------------------------------------------------------------
// Time O(n log k) | Space O(n)
public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);

    PriorityQueue<Integer> heap = new PriorityQueue<>(
        (a, b) -> freq.get(a) - freq.get(b)
    );
    for (int key : freq.keySet()) {
        heap.offer(key);
        if (heap.size() > k) heap.poll();
    }

    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) result[i] = heap.poll();
    return result;
}


############################
# SECTION 9: INTERVALS
############################

----------------------------------------------------------------
37. MERGE INTERVALS
----------------------------------------------------------------
// Time O(n log n) | Space O(n)
public int[][] mergeIntervals(int[][] intervals) {
    if (intervals.length == 0) return intervals;
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    List<int[]> merged = new ArrayList<>();
    merged.add(intervals[0]);
    for (int i = 1; i < intervals.length; i++) {
        int[] last = merged.get(merged.size() - 1);
        if (intervals[i][0] <= last[1]) {
            last[1] = Math.max(last[1], intervals[i][1]);
        } else {
            merged.add(intervals[i]);
        }
    }
    return merged.toArray(new int[merged.size()][]);
}


----------------------------------------------------------------
38. MEETING ROOMS
----------------------------------------------------------------
// Time O(n log n) | Space O(1)
public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < intervals[i - 1][1]) return false;
    }
    return true;
}


############################
# SECTION 10: STACKS
############################

----------------------------------------------------------------
39. MIN STACK
----------------------------------------------------------------
// Time O(1) all operations | Space O(n)
class MinStack {
    private Deque<Integer> stack = new ArrayDeque<>();
    private Deque<Integer> minStack = new ArrayDeque<>();

    public void push(int val) {
        stack.push(val);
        int currentMin = minStack.isEmpty() ? val : Math.min(val, minStack.peek());
        minStack.push(currentMin);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}


----------------------------------------------------------------
40. VALID PARENTHESES
----------------------------------------------------------------
// Time O(n) | Space O(n)
public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char ch : s.toCharArray()) {
        if (ch == '(' || ch == '[' || ch == '{') {
            stack.push(ch);
        } else {
            if (stack.isEmpty()) return false;
            char top = stack.pop();
            if ((ch == ')' && top != '(') ||
                (ch == ']' && top != '[') ||
                (ch == '}' && top != '{')) {
                return false;
            }
        }
    }
    return stack.isEmpty();
}


----------------------------------------------------------------
41. DAILY TEMPERATURES
----------------------------------------------------------------
// Time O(n) | Space O(n)   (Monotonic Stack)
public int[] dailyTemperatures(int[] temperatures) {
    int n = temperatures.length;
    int[] result = new int[n];
    Deque<Integer> stack = new ArrayDeque<>();   // stores indices
    for (int i = 0; i < n; i++) {
        while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
            int prevIndex = stack.pop();
            result[prevIndex] = i - prevIndex;
        }
        stack.push(i);
    }
    return result;
}


############################
# SECTION 11: SYSTEM / DATA STRUCTURE DESIGN
############################

----------------------------------------------------------------
42. LRU CACHE
----------------------------------------------------------------
// Time O(1) get/put | Space O(capacity)   (HashMap + Doubly Linked List)
class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) { this.key = key; this.value = value; }
    }

    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>();
    private final Node head = new Node(0, 0);
    private final Node tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insertFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            remove(map.get(key));
        }
        Node node = new Node(key, value);
        map.put(key, node);
        insertFront(node);
        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}


----------------------------------------------------------------
43. IMPLEMENT QUEUE USING STACKS
----------------------------------------------------------------
// Time O(1) amortized | Space O(n)
class MyQueue {
    private Deque<Integer> inStack = new ArrayDeque<>();
    private Deque<Integer> outStack = new ArrayDeque<>();

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        transfer();
        return outStack.pop();
    }

    public int peek() {
        transfer();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void transfer() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}


----------------------------------------------------------------
44. TRIE IMPLEMENTATION
----------------------------------------------------------------
// Time O(L) per operation | Space O(N * L)
class Trie {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node = node.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = findNode(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            node = node.children.get(ch);
            if (node == null) return null;
        }
        return node;
    }
}


----------------------------------------------------------------
45. DESIGN URL SHORTENER
----------------------------------------------------------------
// Time O(1) average encode/decode | Space O(n)
// Scalability: distributed ID generator (e.g. Snowflake) instead of
// random codes at scale, Redis cache for hot URLs, DB read replicas.
class URLShortener {
    private final Map<String, String> urlToCode = new HashMap<>();
    private final Map<String, String> codeToUrl = new HashMap<>();
    private final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random random = new Random();

    public String encode(String longUrl) {
        if (urlToCode.containsKey(longUrl)) return urlToCode.get(longUrl);
        String code;
        do {
            code = generateCode();
        } while (codeToUrl.containsKey(code));
        urlToCode.put(longUrl, code);
        codeToUrl.put(code, longUrl);
        return code;
    }

    public String decode(String code) {
        return codeToUrl.get(code);
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}


================================================================
                         END OF DOCUMENT
================================================================
