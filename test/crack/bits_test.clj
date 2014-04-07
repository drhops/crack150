(ns crack.bits-test
  (:use clojure.test
        crack.bits)
)

(deftest bits-test
  (testing "ch.5: bit manipulation"
    ;; p5-1
    (is (= 2r10001010100 (p5-1 2r10000000000 2r10101 2 6)))
    ))

(run-tests)
