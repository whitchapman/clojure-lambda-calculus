(ns lambda-calculus.core
  (:require
   [clojure.pprint :refer [pprint]]))

;; ----- lambda calculus -----

(defn ID [x] x)

(defn TRUE [x y] x)
(defn FALSE [x y] y)

(defn AND [x y] (x y FALSE))
(defn OR [x y] (x TRUE y))
(defn NOT [x] (x FALSE TRUE))
(defn XOR [x y] (x (NOT y) y))

(defn IF_THEN_ELSE [p x y] (p x y)) ;;reduces to ID

(defn ZERO [f x] x)
(defn ONE [f x] (f x))
(defn TWO [f x] (f (f x)))
(defn THREE [f x] (f (f (f x))))

(defn SUCC [n] (fn [f x] (f (n f x))))
(defn PREDv1 [n] ((n (fn [p] (fn [z] (z (SUCC (p TRUE)) (p TRUE))))
                     (fn [z] (z ZERO ZERO)))
                  FALSE))

(defn PAIR [x y] (fn [p] (p x y)))
(defn FIRST [p] (p (fn [x y] x)))  ;;(p TRUE)
(defn SECOND [p] (p (fn [x y] y))) ;;(p FALSE)

(defn SUCC_PAIR [p] (PAIR (SECOND p) (SUCC (SECOND p))))
(defn PRED [n] (FIRST (n SUCC_PAIR (PAIR ZERO ZERO))))

(def FOUR (SUCC THREE))

(defn PLUS [n m] (m SUCC n))
(defn MINUS [n m] (m PRED n))
(defn MULT [n m] (m (partial PLUS n) ZERO))
(defn EXP [n m] (m (partial MULT n) ONE))

(def FIVE (PLUS TWO THREE))
(def SIX (SUCC FIVE))
(def SEVEN (PLUS TWO FIVE))
(def EIGHT (SUCC SEVEN))
(def NINE (PLUS FOUR FIVE))
(def TEN (MULT TWO FIVE))

(defn IS_ZERO [n] (n (fn [m] FALSE) TRUE))
(defn LESS_THAN_OR_EQUAL [n m] (IS_ZERO (MINUS n m)))
(defn LESS_THAN [n m] (AND (LESS_THAN_OR_EQUAL n m)
                           (NOT (IS_ZERO (n PRED m)))))
(defn EQUALS [n m] (AND (LESS_THAN_OR_EQUAL n m)
                        (LESS_THAN_OR_EQUAL m n)))
(defn GREATER_THAN_OR_EQUAL [n m] (IS_ZERO (n PRED m)))
(defn GREATER_THAN [n m] (AND (GREATER_THAN_OR_EQUAL n m)
                              (NOT (IS_ZERO (MINUS n m)))))

(defn COMPOSE [f g x] (f (g x)))
(def NOT_ZERO (partial COMPOSE NOT IS_ZERO))

#_(defn FIX [f]
  (fn [x] (f (fn [y] (x x y))))
  (fn [x] (f (fn [y] (x x y)))))


(defn to-bool [b] (IF_THEN_ELSE b true false))
(defn from-bool [b] (if b TRUE FALSE))
(defn to-int [n] (n (fn [x] (inc x)) 0))
(defn from-int [n] (if (zero? n)
                    (fn [f x] x)
                    (fn [f x] (f ((from-int (dec n)) f x)))))

(defn to-pair [p] {:first (FIRST p) :second (SECOND p)})
(defn to-pair-int [p] {:first (to-int (FIRST p)) :second (to-int (SECOND p))})
