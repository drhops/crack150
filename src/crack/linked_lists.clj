(ns crack.linked_lists)

(gen-class
  :name crack.linked_lists.LLNode
  :init init
  :constructors {["java.lang.Object"] []}
  :methods [[getNext [] Object]
            [setNext [Object] void]
            [getValue [] Object]
            [setValue [Object] void]]
  :state state)

(defn -init [value]
  [[] (atom {:next nil
             :value value})]
  )

(defmacro setfield
  [this key value]
  `(swap! (.state ~this) into {~key ~value}))

(defmacro getfield
  [this key]
  `(@(.state ~this) ~key))

(defn -getNext [this]
  (getfield this :next))

(defn -setNext [this next]
  (setfield this :next next))

(defn -getValue [this]
  (getfield this :value))

(defn -setValue [this value]
  (setfield this :value value))

(defn ll-all [node]
  (loop [n node
         ret []]
    (if (nil? n)
      ret
      (recur (.getNext n) (conj ret (.getValue n))))))


(defn p2-1
  "Write code to remove duplicates from an unsorted linked list."
  [node]
  (loop [n node
         s (set [])]
    (if-not (nil? n)
      (if-not (nil? (.getNext n))
        (if (contains? s (.getValue (.getNext n)))
          (.setNext n (.getNext (.getNext n))))))
    (if-not (nil? n)
      (recur (.getNext n) (conj s (.getValue n))))
    )
  node)

(defn p2-1b
  "How would you solve this problem if a temporary buffer is not allowed?"
  [node]
  (loop [n node]
    (if-not (nil? n)
      (loop [p n]
        (if-not (nil? p)
          (if-not (nil? (.getNext p))
            (if (= (.getValue n) (.getValue (.getNext p)))
              (.setNext p (.getNext (.getNext p))))))
        (if-not (nil? p)
          (recur (.getNext p)))
          ))
    (if-not (nil? n)
      (recur (.getNext n)))
    )
  node)

(defn ll-index
  [node x]
  (loop [n node
         i 0]
    (if (or (nil? n) (= i x))
      n
      (recur (.getNext n) (+ i 1))
    )))

(defn p2-2
  "Implement an algorithm to find the nth to last element of a singly linked list."
  [node x]
  (loop [n node
         nx (ll-index n x)]
    (if (nil? nx)
      n
      (recur (.getNext n) (.getNext nx))
      )))

(defn p2-3
  "Implement an algorithm to delete a node in the middle of a single linked list, given
only access to that node.
EXAMPLE
Input: the node ‘c’ from the linked list a->b->c->d->e
Result: nothing is returned, but the new linked list looks like a->b->d->e
"
  [node]
  (if-not (nil? (.getNext node))
    (.setValue node (.getValue (.getNext node))))
  (.setNext node (ll-index node 2))
  node
  )
