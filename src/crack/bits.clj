(ns crack.bits)
(require '[clojure.pprint :refer (cl-format)])

(defn bit-print
  [x]
  (cl-format nil "2r~32,'0',B" x))

(defn p5-1
  "You are given two 32-bit numbers, N and M, and two bit positions, i and j Write a
  method to set all bits between i and j in N equal to M (e g , M becomes a substring of
  N located at i and starting at j)
SOLUTION
EXAMPLE:
Input: N = 10000000000, M = 10101, i = 2, j = 6 Output: N = 10001010100"
  [n m i j]
  (let [max Integer/MAX_VALUE
        left (- max (unchecked-dec (bit-shift-left 1 j)))
        right (unchecked-dec (bit-shift-left 1 i))
        mask (bit-or left right)
        nclear (bit-and n mask)
        mmask (bit-shift-left m i)]
    (bit-or nclear mmask)
    ))
