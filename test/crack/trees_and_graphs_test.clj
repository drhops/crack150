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
    ;; p4-1
    (let [t (tree)]
      (is (= 1 (p4-1 t)))
      (.set-children t (list (tree) (tree) (tree)))
      (is (= 2 (p4-1 t)))
      )
    ))

(run-tests)
