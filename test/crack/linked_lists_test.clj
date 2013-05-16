(ns crack.linked_lists-test
  (:import (crack.linked_lists LLNode))
  (:use clojure.test
        crack.linked_lists))

(deftest a-test
  (testing "ch.2: linked lists"
    ; LLNode
    (let [ll1 (LLNode. 5)
          ll2 (LLNode. 8)]
      (.setNext ll1 ll2)
      (is (= ll2 (.getNext ll1))))
    ; p2-1
    (let [ll1 (LLNode. 5)
          ll2 (LLNode. 8)
          ll3 (LLNode. 5)]
      (.setNext ll1 ll2)
      (is (= [5 8] (ll-all (p2-1 ll1))))
      (.setNext ll2 ll3)
      (is (= [5 8 5] (ll-all ll1)))
      (is (= [5 8] (ll-all (p2-1 ll1))))
      )
    ; p2-1b
    (let [ll1 (LLNode. 5)
          ll2 (LLNode. 8)
          ll3 (LLNode. 5)]
      (.setNext ll1 ll2)
      (is (= [5 8] (ll-all (p2-1b ll1))))
      (.setNext ll2 ll3)
      (is (= [5 8 5] (ll-all ll1)))
      (is (= [5 8] (ll-all (p2-1b ll1))))
      )
    ; p2-2
    (let [ll1 (LLNode. 5)
          ll2 (LLNode. 8)
          ll3 (LLNode. 13)
          ll4 (LLNode. 11)
          ll5 (LLNode. 14)
          ll6 (LLNode. 6)
          ll7 (LLNode. 9)]
      (.setNext ll1 ll2)
      (.setNext ll2 ll3)
      (.setNext ll3 ll4)
      (.setNext ll4 ll5)
      (.setNext ll5 ll6)
      (.setNext ll6 ll7)
      (is (= ll1 (ll-index ll1 0)))
      (is (= ll2 (ll-index ll1 1)))
      (is (= ll3 (ll-index ll1 2)))
      (is (= ll1 (p2-2 ll1 10)))
      (is (= ll1 (p2-2 ll1 7)))
      (is (= ll2 (p2-2 ll1 6)))
      (is (= ll3 (p2-2 ll1 5)))
      (is (= ll6 (p2-2 ll1 2)))
      (is (= ll7 (p2-2 ll1 1)))
      )
    ; p2-3
    (let [ll1 (LLNode. 5)
          ll2 (LLNode. 8)
          ll3 (LLNode. 13)
          ll4 (LLNode. 11)]
      (.setNext ll1 ll2)
      (.setNext ll2 ll3)
      (.setNext ll3 ll4)
      (p2-3 ll2)
      (is (= ll4 (ll-index ll1 2)))
      (is (= 13 (.getValue (ll-index ll1 1))))
      (p2-3 ll2)
      (is (= nil (ll-index ll1 2)))
      (is (= 11 (.getValue (ll-index ll1 1))))
      )
    ;
    ))
