(ns crack.core-test
  (:use clojure.test
        crack.core))

(deftest a-test
  (testing "c1"
    ; p1-1
    (is (= false (p1-1 "test")))
    (is (= true (p1-1 "pearl")))
    ; p1-2
    (is (= "tset" (p1-2 "test")))
    (is (= "reversed" (p1-2 "desrever")))
    ; p1-3
    (is (= "test" (p1-3 "teeesttt")))
    (is (= "signal" (p1-3 "ssssiignnaaaaaaall")))
    ; p1-4
    (is (= true (p1-4 "ngaaarm", "anagram")))
    (is (= true (p1-4 "tset", "test")))
    (is (= false (p1-4 "tset", "testt")))
    ; p1-5
    (is (= "wo%20o%20t" (p1-5 "wo o t")))
    (is (= "%20%20%20x" (p1-5 "   x")))
    (is (= "noop" (p1-5 "noop")))
    ))
