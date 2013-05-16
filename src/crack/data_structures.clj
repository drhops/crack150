(ns crack.data_structures)

(gen-class
  :name crack.data_structures.LLNode
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
