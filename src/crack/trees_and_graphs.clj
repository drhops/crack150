(ns crack.trees_and_graphs)

;; data structures
(defprotocol PTreeNode
  (children [this])
  (set-children [this children])
  (size [this])
  )

(deftype TreeNode [x-children]
  PTreeNode
  (set-children
    [this in-children]
    (reset! x-children in-children)
    @x-children)
  (children
    [this]
    @x-children)
  (size
    [this]
    (count @x-children))
  Object
  (toString
    [this]
    (.toString @x-children))
  )

;;
(import 'crack.trees_and_graphs.TreeNode)

(defn tree
  []
  (TreeNode. (atom '()))
  )

;; problems
(defn mindepth
  "Returns minimum depth for a leaf node in the tree."
  [tree depth]
  (let [children (.children tree)]
    (if (empty? children)
      (+ depth 1)
      (apply min (map
            (fn [c] (mindepth c (+ depth 1)))
            children))
      )))

(defn p4-1
  "Implement a function to check if a tree is balanced For the purposes of this question,
a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
from the root by more than one."
  [tree]
  (mindepth tree 0)
  )
