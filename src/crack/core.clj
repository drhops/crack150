(ns crack.core)

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
