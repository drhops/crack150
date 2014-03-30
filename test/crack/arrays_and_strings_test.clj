(ns crack.arrays_and_strings-test
  (:use clojure.test
        crack.arrays_and_strings
        clojure.core.matrix
        clojure.core.matrix.operators)
  (:refer-clojure :exclude [* - + == /])
)

(set-current-implementation :vectorz)

(deftest arrays-strings-test
  (testing "ch.1: arrays and strings"
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
    ; p1-6
    (is (= (matrix [[3 1] [4 2]]) (p1-6 (matrix [[1 2] [3 4]]))))
    (is (= (matrix [[13 9 5 1] [14 10 6 2] [15 11 7 3] [16 12 8 4]])
           (p1-6 (matrix [[1 2 3 4] [5 6 7 8] [9 10 11 12] [13 14 15 16]]))))
    ; p1-7
    (is (= (matrix [[1 0] [0 0]]) (p1-7 (matrix [[1 2] [3 0]]))))
    (is (= (matrix [[0 0 0 0] [0 6 7 8] [0 10 11 12] [0 14 15 16]])
           (p1-7 (matrix [[0 2 0 4] [5 6 7 8] [0 10 11 12] [13 14 15 16]]))))
    ; p1-8
    (is (= true (p1-8 "test" "test")))
    (is (= true (p1-8 "test" "stte")))
    (is (= true (p1-8 "erbottlewat" "waterbottle")))
    (is (= false (p1-8 "xerbottlewat" "waterbottle")))
    ))
