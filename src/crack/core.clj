(ns crack.core
  (:require (clojure [set :as set]))
  (:use clojure.core.matrix
        clojure.core.matrix.operators)
  (:refer-clojure :exclude [* - + == /]))

(set-current-implementation :vectorz)

(defn p1-1
  "Implement an algorithm to determine if a string has all unique characters."
  [s]
  (= (count s) (count (apply hash-set s))))

(defn p1-1b
  "What if you can not use additional data structures?"
  [s]
  (loop [lc (sort s)
         pc nil]
    (let [c (first lc)]
      (if-not c
        true
        (if (= c pc)
          false
          (recur (rest lc) c)))))
  )

(defn p1-2
  "Write code to reverse a C-Style String."
  [s]
  ;(apply str (reverse s))
  (loop [in s
         out (empty in)]
    (if (empty? in)
      (apply str out)
      (recur (rest in)
             (conj out (first in)))))
  )

(defn p1-3
  "
Design an algorithm and write code to remove the duplicate characters in a string
without using any additional buffer. NOTE: One or two additional variables are fine.
An extra copy of the array is not."
  [s]
  (loop [in s
         out (empty in)
         pc nil]
    (let [c (first in)]
      (if-not c
        (apply str out)
        (recur (rest in)
               (if (= c pc) out (concat out (list c)))
               c))))
  )

(defn p1-4
  "Write a method to decide if two strings are anagrams or not."
  [s1 s2]
  (= (sort s1) (sort s2)))

(defn p1-5
  "Write a method to replace all spaces in a string with '%20'."
  [s]
  (loop [in s
         out (empty in)]
    (let [c (first in)]
      (if-not c
        (apply str out)
        (recur (rest in)
               (if (= c \space) (concat out (list "%20")) (concat out (list c)))))))
  )

(defn p1-6
  "Given an image represented by an NxN matrix, where each pixel in the image is 4
bytes, write a method to rotate the image by 90 degrees. Can you do this in place?"
  [m]
  (loop [i (dec (/ (row-count m) 2))] ;; Assumes square matrix
    (if (>= i 0)
      (let [n (row-count m)
            nd (dec n)
            ii (- nd i)]
        (doseq [x (range i ii)]
          (let [xi (- nd x)
                v1 (mget m i x)
                v2 (mget m x ii)
                v3 (mget m ii xi)
                v4 (mget m xi i)]
            (mset! m i x v4)
            (mset! m x ii v1)
            (mset! m ii xi v2)
            (mset! m xi i v3)
            ))
        (recur (dec i))
        )))
  m)

(defn zero-index
  [v indices]
  (loop [li indices
         ret nil]
    (if (or (not (nil? ret)) (empty? li))
      ret
      (recur (rest li) (if (zero? (.get v (first li))) (first li) ret))
      )))

(defn p1-7
  "Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
column is set to 0."
  [m]
  (let [rset (set (range 0 (row-count m)))
        cset (set (range 0 (column-count m)))
        [zrows zcols]
        (loop [sr rset
               sc cset
               zr (set [])
               zc (set [])]
    (if-not (or (> (count sr) 0) (> (count sc) 0))
      [zr zc]
      (if (> (count sr) (count sc))
        (let [r (first sr)
              zi (zero-index (get-row m r) (set/difference cset zc))]
          (if-not (nil? zi)
              (recur (set/difference sr (set [r])) (set/difference sc (set [zi])) (set/union zr [r]) (set/union zc [zi]))
              (recur (set/difference sr (set [r])) sc zr zc)
              ))
        (let [c (first sc)
              zi (zero-index (get-column m c) (set/difference rset zr))]
          (if-not (nil? zi)
              (recur (set/difference sr (set [zi])) (set/difference sc (set [c])) (set/union zr [zi]) (set/union zc [c]))
              (recur sr (set/difference sc (set [c])) zr zc)
              ))
        )))]
    (doseq [r zrows]
      (doseq [c (range 0 (column-count m))]
        (mset! m r c 0)))
    (doseq [c zcols]
      (doseq [r (range 0 (row-count m))]
        (mset! m r c 0)))
    m
    ))

(defn p1-8
  "Assume you have a method isSubstring which checks if one word is a substring of
another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
only one call to isSubstring (i.e., “waterbottle” is a rotation of “erbottlewat”)."
  [s1 s2]
  (not (= (.indexOf (apply str (concat s2 s2)) s1) -1))
  )

(defn -main [& args]
  (println "Run 'lein test' to test output."))
