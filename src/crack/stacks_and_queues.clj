(ns crack.stacks_and_queues)

;; data structures
(defprotocol PStack
  (push [this v])
  (pop [this])
  (peek [this])
  (size [this])
  )

(defprotocol PoppableByIndex
  (popAt [this i]))

(deftype Stack [stack]
  PStack
  (push
    [this x]
    (swap! stack conj x))
  (pop
    [this]
    (let [first (first @stack)]
      (swap! stack rest)
      first))
  (peek
    [this]
    (first @stack))
  (size
    [this]
    (count @stack))
  Object
  (toString
    [this]
    (.toString @stack))
  )

;;
(import 'crack.stacks_and_queues.Stack)

(defn stack
  []
  (Stack. (atom '()))
  )

;; problems
(defn p3-1
  "Describe how you could use a single array to implement three stacks."
  []
  "Split the array into three arrays of length N/3"
  )

(defn p3-2
  "How would you design a stack which, in addition to push and pop, also has a function
min which returns the minimum element? Push, pop and min should all operate in
O(1) time."
  []
  "Store current min with each element on the stack"
  )

;; p3-3
(deftype StackOfStacks [stacks stack-limit]
  PStack
  (push
    [this x]
    (let [topstack (.peek @stacks)
          hotstack (if (or (nil? topstack) (= stack-limit (.size topstack)))
                     (stack)
                     (.pop @stacks))]
      (.push hotstack x)
      (.push @stacks hotstack)))
  (pop
    [this]
    (if (nil? (.peek @stacks))
      nil
      (let [ret (.pop (.peek @stacks))]
        (if (= 0 (.size (.peek @stacks)))
          (.pop @stacks))
        ret)))
  (peek
    [this]
    (if-not (nil? (.peek @stacks))
      (.peek (.peek @stacks))
      nil))
  (size
    [this]
    (let [sstacksize (.size @stacks)
          capacity (* sstacksize stack-limit)
          topstack (.peek @stacks)
          remaining (if (nil? topstack) 0 (- stack-limit (.size topstack)))]
      (- capacity remaining)
    ))
  PoppableByIndex  ;; p3-3b
  (popAt  ;; breaks .size
    [this i]
    (let [revstacks (stack)]
      (doseq [x (range 0 i)]
        (.push revstacks (.pop @stacks)))
      (let [ret (.pop this)]
        (doseq [x (range 0 i)]
          (.push @stacks (.pop revstacks)))
        ret)))
  )

;;
(import 'crack.stacks_and_queues.StackOfStacks)

(defn sstack
  [stack-limit]
  (StackOfStacks. (atom (stack)) stack-limit)
  )

(defn p3-3
  "Imagine a (literal) stack of plates. If the stack gets too high, it might topple. There-
fore, in real life, we would likely start a new stack when the previous stack exceeds
some threshold. Implement a data structure SetOfStacks that mimics this. SetOf-
Stacks should be composed of several stacks, and should create a new stack once
the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks.pop() should
behave identically to a single stack (that is, pop() should return the same values as it
would if there were just a single stack).
"
  []
  "see StackOfStacks")

(defn p3-3b
  "FOLLOW UP
Implement a function popAt(int index) which performs a pop operation on a specific
sub-stack."
  []
  "see StackOfStacks")

;; p3-4
(defn hanoi
  [towers ls ld lt n]
  (if (= 1 n)
    (.push (ld towers) (.pop (ls towers)))
    (do
      (hanoi towers ls lt ld (- n 1))
      (.push (ld towers) (.pop (ls towers)))
      (hanoi towers lt ld ls (- n 1))
      )
    ))

(defn p3-4
  "In the classic problem of the Towers of Hanoi, you have 3 rods and N disks of different
sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending
order of size from top to bottom (e.g., each disk sits on top of an even larger one). You
have the following constraints:
(A) Only one disk can be moved at a time.
(B) A disk is slid off the top of one rod onto the next rod.
(C) A disk can only be placed on top of a larger disk.
Write a program to move the disks from the first rod to the last using Stacks."
  []
    (let [towers { :l (stack) :m (stack) :r (stack) }]
      (.push (:l towers) 3)
      (.push (:l towers) 2)
      (.push (:l towers) 1)
      (hanoi towers :l :r :m 3)
      )
  )

;; p3-5
(defprotocol PQueue
  (add [this v])
  (take [this]))

(deftype MyQueue [add-stack take-stack]
  PQueue
  (add
    [this v]
    (doseq [x (range 0 (.size take-stack))]
      (.push add-stack (.pop take-stack)))
    (.push add-stack v))
  (take
    [this]
    (doseq [x (range 0 (.size add-stack))]
      (.push take-stack (.pop add-stack)))
    (.pop take-stack))
  )

;;
(import 'crack.stacks_and_queues.MyQueue)

(defn myqueue
  []
  (MyQueue. (stack) (stack))
  )

(defn p3-5
  "Implement a MyQueue class which implements a queue using two stacks."
  []
  "See MyQueue"
  )

(defn p3-6
  "Write a program to sort a stack in ascending order. You should not make any assump-
tions about how the stack is implemented. The following are the only functions that
should be used to write this program: push | pop | peek | isEmpty."
  [s]
  (let [srev (stack)]
    (loop [n (.size s)]  ;; bubble sort (O(n^2))
      (doseq [i (range 1 n)]
        (.push srev (.pop s))
        (if (> (.peek srev) (.peek s))
          (let [x (.pop s)
                y (.pop srev)]
            (.push s y)
            (.push srev x)
            )))
      (doseq [i (range 1 n)]
        (.push s (.pop srev)))
      (if (> n 1)
        (recur (- n 1)))
      ))
  s
  )
