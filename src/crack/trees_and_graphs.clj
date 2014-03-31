(ns crack.trees_and_graphs)
(use 'clojure.set)

;; data structures
(defprotocol PTreeNode
  (value [this])
  (children [this])
  (set-children [this children])
  (size [this])
  )

(deftype TreeNode [m-value x-children]
  PTreeNode
  (set-children
    [this in-children]
    (reset! x-children in-children)
    @x-children)
  (value
   [this]
   m-value)
  (children
    [this]
    @x-children)
  (size
    [this]
    (reduce + 1 (map size (remove nil? @x-children))))
  Object
  (toString
    [this]
    (str m-value (.toString @x-children)))
  )

(defprotocol PGraphNode
  (is-edge [this n1 n2])
  (num-vertices [this])
  (edges [this])
  (edge-nodes [this n])
  (add-edges [this in-edges])
  )

(deftype GraphNode [x-edges]
  PGraphNode
  (is-edge
   [this n1 n2]
   (contains? (get @x-edges n1) n2))
  (edge-nodes
   [this n]
   (get @x-edges n))
  (add-edges
   [this in-edges]
   (swap! x-edges into (map (fn [x] (let [k (first x)
                                          k-edges (get @x-edges k)]
                                      [k (apply conj k-edges (rest x))]))
                            in-edges)))
  (edges
   [this]
   @x-edges)
  (num-vertices
   [this]
   (count (keys @x-edges)))
  Object
  (toString
    [this]
    (.toString @x-edges))
  )

;;
(import 'crack.trees_and_graphs.TreeNode)

(defn tree
  ([value] (TreeNode. value (atom '())))
  ([] (tree nil)))

(import 'crack.trees_and_graphs.GraphNode)

(defn graph
  [num-vertices]
  (GraphNode. (atom (into {}
                          (map (fn [x] [(keyword (str x)) #{}]) (range num-vertices))))
  ))

;; problems
(defn min-depth
  "Returns minimum depth for a leaf node in the tree."
  [tree depth]
  (let [children (remove nil?(.children tree))]
    (if (empty? children)
      (+ depth 1)
      (apply min (map
            (fn [c] (min-depth c (+ depth 1)))
            children))
      )))

(defn max-depth
  "Returns maximum depth for a leaf node in the tree."
  [tree depth]
  (let [children (remove nil? (.children tree))]
    (if (empty? children)
      (+ depth 1)
      (apply max (map
            (fn [c] (max-depth c (+ depth 1)))
            children))
      )))

(defn p4-1
  "Implement a function to check if a tree is balanced For the purposes of this question,
a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
from the root by more than one."
  [tree]
  (let [min (min-depth tree 0)
        max (max-depth tree 0)
        diff (- max min)]
    (< diff 2)
  ))

(defn lazy-contains?
  "Returns true if the lazy sequence contains <key>."
  [col key]
  (some #(= key %) col))

(defn search
  "Searches the graph starting from sources for target.
  Will recurse at most <limit> times."
  [graph sources target limit]
  (if (= limit 0)
    false
    (let [edge-nodes (apply union (map (fn [x] (.edge-nodes graph x)) sources))]
      (if (lazy-contains? edge-nodes target)
        true
        (recur graph edge-nodes target (- limit 1))))
    ))

(defn p4-2
  "Given a directed graph, design an algorithm to find out whether there is a route
  between two nodes"
  [graph source target]
  (search graph [source] target (num-vertices graph))
  )

(defn de-dupe
  "Removes consecutive duplicates (e.g. [1,2,2,3] => [1,2,3])."
  [a]
  (reduce (fn [a b] (if (= (last a) (first b))
                      a; ignore b
                      (conj a (first b))))
          (map (fn [x] [x]) a)))

(defn make-tree
  "Make a binary search tree from an array of values."
  [values]
  (if (empty? values)
    nil
    (let [a values
          n (count a)
          mid (int (/ n 2))
          smaller (subvec a 0 mid)
          bigger (subvec a (+ mid 1) n)
          node (tree (get a mid))
          left (make-tree smaller)
          right (make-tree bigger)]
      (.set-children node [left right])
      node
      )))

(defn p4-3
  "Given a sorted (increasing order) array, write an algorithm to create a binary tree with
  minimal height"
  [values]
  (make-tree (de-dupe values)))

(defn get-lists
  "Get lists for the values at each depth."
  [tree]
  (if (nil? tree) (list)
    (let [node tree
          children (.children tree)
          left (get-lists (first children))
          right (get-lists (second children))]
      (remove empty? (concat (list (list (.value node))) (map concat left right))))))

(defn p4-4
  "Given a binary search tree, design an algorithm which creates a linked list of all the
  nodes at each depth (i e , if you have a tree with depth D, you’ll have D linked lists)"
  [tree]
  (get-lists tree))

(defn p4-5
  "Write an algorithm to find the ‘next’ node (i e , in-order successor) of a given node in
  a binary search tree where each node has a link to its parent."
  []
  "Algorithm:
  If: there is a right child, return the smallest node in the right sub-tree.
  Else: go up until we are a left branch, and return that node.
  Else: we are the biggest node and there is no 'next'.
  ")

(defn p4-6
  "Design an algorithm and write code to find the first common ancestor of two nodes
  in a binary tree Avoid storing additional nodes in a data structure NOTE: This is not
  necessarily a binary search tree."
  []
  "A1 -- O(n) time, O(1) space:
  Recursively search left and right side for both nodes simultaneously.
  When one is found on each side, that is the first common ancestor.
  A2 -- O(n) time, O(logn) space for balanced tree
  Trace path for both nodes and compare to see where they diverge.")

(defn p4-7
  "You have two very large binary trees: T1, with millions of nodes, and T2, with hun-
  dreds of nodes Create an algorithm to decide if T2 is a subtree of T1."
  []
  "A1 -- O(n+m): If T2 is a substring of T1 for both in-order and pre-order traversals.
   A2 -- O(n*m): Brute-force search every node of T2 for every node of T1.")

(defn p4-8
  "You are given a binary tree in which each node contains a value. Design an algorithm
  to print all paths which sum up to that value. Note that it can be any path in the tree
  - it does not have to start at the root."
  []
  "A -- O(nlogn): Search the entire tree, at each node compute all sums starting at that node going up."
  )
