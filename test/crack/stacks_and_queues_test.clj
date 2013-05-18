(ns crack.stacks_and_queues-test
  (:import (crack.stacks_and_queues StackOfStacks))
  (:use clojure.test
        crack.stacks_and_queues))

(deftest a-test
  (testing "ch.3: stacks and queues"
    ; StackOfStacks
    (let [ss (StackOfStacks. (atom '()))]
      (.push ss 1)
      (.push ss 5)
      (.push ss 13)
      (is (= 13 (.peek ss)))
      (is (= 13 (.pop ss)))
      (is (= 5 (.peek ss)))
      (is (= 5 (.pop ss)))
      (is (= 1 (.peek ss)))
      (is (= 1 (.pop ss)))
      (is (= nil (.peek ss)))
      (is (= nil (.pop ss)))
      )))

