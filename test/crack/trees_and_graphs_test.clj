(ns crack.trees_and_graphs-test
  (:import (crack.trees_and_graphs TreeNode))
  (:use clojure.test
        crack.trees_and_graphs))

(deftest a-test
  (testing "ch.4: trees and graphs"
    ;; Tree
    (let [t (tree)]
      (.set-children t (list 3 4 5))
      (is (= 3 (.size t)))
      )
    ;; min-depth
    (let [t (tree)]
      (is (= 1 (min-depth t 0)))
      (.set-children t (list (tree) (tree) (tree)))
      (is (= 2 (min-depth t 0)))
      )
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
    ))

(run-tests)
