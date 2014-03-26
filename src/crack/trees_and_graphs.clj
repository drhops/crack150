(ns crack.trees_and_graphs)

;; data structures
(defprotocol PTreeNode
  (children [this])
  (setChildren [this children])
  (size [this])
  )

(deftype TreeNode [children]
  PTreeNode
  (setChildren
    [this newChildren]
    (reset! children newChildren)
    children)
  (children
    [this]
    children)
  (size
    [this]
    (count @children))
  Object
  (toString
    [this]
    (.toString @children))
  )

;;
(import 'crack.trees_and_graphs.TreeNode)

(defn tree
  []
  (TreeNode. (atom '()))
  )

;; problems
(defn p4-1
  "Implement a function to check if a tree is balanced For the purposes of this question,
a balanced tree is defined to be a tree such that no two leaf nodes differ in distance
from the root by more than one."
  []
  (let [t (tree)]
    (t size)
  ))
