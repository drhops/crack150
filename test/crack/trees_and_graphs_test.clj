(ns crack.trees_and_graphs-test
  (:import (crack.trees_and_graphs TreeNode GraphNode))
  (:use clojure.test
        crack.trees_and_graphs))

(deftest trees-graphs-test
  (testing "ch.4: trees and graphs"
    ;; Tree
    (let [t (tree)]
      (.set-children t (list (tree 3) (tree 4) (tree 5)))
      (is (= 4 (.size t)))
      )
    ;; Graph
    (let [g (graph 5)]
      (.add-edges g [[:0 :1], [:1 :2], [:2 :3 :4]])
      (is (= true (.is-edge g :1 :2)))
      (is (= true (.is-edge g :2 :3)))
      (is (= true (.is-edge g :2 :4)))
      (is (= #{:3 :4}) (.edge-nodes g :2))
      )
    ;; min-depth
    (let [t (tree)]
      (is (= 1 (min-depth t 0)))
      (.set-children t (list (tree) (tree) (tree)))
      (is (= 2 (min-depth t 0)))
      )
    ;; de-dupe
    (is (= [1 2 3 4 5] (de-dupe [1 2 2 2 3 3 3 4 5])))
    ;; p4-1
    (let [t1 (tree)
          t2a (tree)
          t2b (tree)
          t3aa (tree)
          t3ab (tree)
          t3ba (tree)
          t3bb (tree)]
      (.set-children t1 (list t2a t2b))
      (is (= true (p4-1 t1)))
      (.set-children t2a (list t3aa t3ab))
      (is (= true (p4-1 t1)))
      (.set-children t3aa (list (tree)))
      (is (= false (p4-1 t1)))
      (.set-children t2b (list t3ba t3bb))
      (is (= true (p4-1 t1)))
      )
    ;; p4-2
    (let [g (graph 6)]
      (.add-edges g [[:0 :1], [:1 :2], [:2 :3], [:3 :4], [:4 :5]])
      (is (= false (p4-2 g :5 :0)))
      (is (= true (p4-2 g :0 :1)))
      (is (= true (p4-2 g :0 :5)))
      )
    ;; p4-3
    (let [t (p4-3 [1 2 3 3 4 4 5 6 7 7 7])]
      (is (= 7 (.size t)))
      (is (= true (p4-1 t)))
      )
    ;; p4-4
    (let [t (p4-3 [1 2 3 3 4 4 5 6 7 7 7])]
      (is (= (list (list 4) (list 2 6) (list 1 3 5 7)) (p4-4 t)))
      )
  ))

(run-tests)
