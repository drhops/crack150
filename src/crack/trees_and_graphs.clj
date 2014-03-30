(ns crack.trees_and_graphs)
(use 'clojure.set)

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
  []
  (TreeNode. (atom '()))
  )

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
  (let [children (.children tree)]
    (if (empty? children)
      (+ depth 1)
      (apply min (map
            (fn [c] (min-depth c (+ depth 1)))
            children))
      )))

(defn max-depth
  "Returns maximum depth for a leaf node in the tree."
  [tree depth]
  (let [children (.children tree)]
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

(defn lazy-contains? [col key]
  (some #(= key %) col))

(defn search
  [graph queue node limit]
  (if (= limit 0)
    false
    (let [edge-nodes (apply union (map (fn [x] (.edge-nodes graph x)) queue))]
      (if (lazy-contains? edge-nodes node)
        true
        (recur graph edge-nodes node (- limit 1))))
    ))

(defn p4-2
  "Given a directed graph, design an algorithm to find out whether there is a route
  between two nodes"
  [graph source target]
  (search graph [source] target (num-vertices graph))
  )
