(ns crack.stacks_and_queues-test
  (:import (crack.stacks_and_queues Stack StackOfStacks))
  (:use clojure.test
        crack.stacks_and_queues))

(deftest a-test
  (testing "ch.3: stacks and queues"
    ;; Stack
    (let [s (stack)]
      (.push s 1)
      (.push s 5)
      (.push s 13)
      (is (= 3 (.size s)))
      (is (= 13 (.peek s)))
      (is (= 13 (.pop s)))
      (is (= 5 (.peek s)))
      (is (= 5 (.pop s)))
      (is (= 1 (.peek s)))
      (is (= 1 (.pop s)))
      (is (= nil (.peek s)))
      (is (= nil (.pop s)))
      )
    ;; p3.3 StackOfStacks
    (let [ss (sstack 2)]
      (.push ss 1)
      (is (= 1 (.peek ss)))
      (.push ss 5)
      (is (= 5 (.peek ss)))
      (.push ss 13)
      (is (= 3 (.size ss)))
      (.push ss 9)
      (.push ss 8)
      (is (= 5 (.size ss)))
      (is (= 8 (.pop ss)))
      (is (= 9 (.pop ss)))
      (is (= 13 (.pop ss)))
      (is (= 5 (.pop ss)))
      (is (= 1 (.pop ss)))
      (is (= nil (.peek ss)))
      (is (= nil (.pop ss)))
      (is (= 0 (.size ss)))
      )
    ;; p3.3b StackOfStacks
    (let [ss (sstack 2)]
      (.push ss 1)
      (.push ss 5)
      (.push ss 13)
      (.push ss 9)
      (.push ss 8)
      (is (= 9 (.popAt ss 1)))
      (is (= 5 (.popAt ss 2)))
      )
    ))
