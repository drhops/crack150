(ns crack.linked_lists-test
  (:import (crack.linked_lists LinkedList))
  (:use clojure.test
        crack.linked_lists))

(deftest a-test
  (testing "ch.2: linked lists"
    ; LinkedList
    (let [ll1 (LinkedList. 5)
          ll2 (LinkedList. 8)]
      (.setNext ll1 ll2)
      (is (= ll2 (.getNext ll1))))
    ; p2-1
    (let [ll1 (LinkedList. 5)
          ll2 (LinkedList. 8)
          ll3 (LinkedList. 5)]
      (.setNext ll1 ll2)
      (is (= [5 8] (ll-all (p2-1 ll1))))
      (.setNext ll2 ll3)
      (is (= [5 8 5] (ll-all ll1)))
      (is (= [5 8] (ll-all (p2-1 ll1))))
      )
    ; p2-1b
    (let [ll1 (LinkedList. 5)
          ll2 (LinkedList. 8)
          ll3 (LinkedList. 5)]
      (.setNext ll1 ll2)
      (is (= [5 8] (ll-all (p2-1b ll1))))
      (.setNext ll2 ll3)
      (is (= [5 8 5] (ll-all ll1)))
      (is (= [5 8] (ll-all (p2-1b ll1))))
      )
    ;
    ))
