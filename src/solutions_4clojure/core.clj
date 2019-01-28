(ns solutions-4clojure.core)


;;; Solutions for 4clojure problems

;;; user ID: smangal



;;; Problem ID: 1

;;; Problem Link: http://www.4clojure.com/problem/1

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; 1. (= ___ true)

;;; Problem Solution:

true

;;; Solution Evaluation:

(= true true)


------------------------------------------------------------------------------------------------------

;;; Problem ID: 2

;;; Problem Link: http://www.4clojure.com/problem/2

;;; Problem Statement: Enter a function to evaluate the outer function to true.


;;; 1. (= (- 10 (* 2 3)) __)


;;; Problem Solution:

4

;;; Solution Evaluation:

(= (- 10 (* 2 3)) 4)


------------------------------------------------------------------------------------------------------

;;; Problem ID: 3

;;; Problem Link: http://www.4clojure.com/problem/3

;;; Problem Statement: Enter a function to evaluate the outer function to true.
;;; 1. (= __ (.toUpperCase "hello world"))

;;; Problem Solution:

"HELLO WORLD"

;;; Solution Evaluation:

(= "HELLO WORLD" (.toUpperCase "hello world"))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 4

;;; Problem Link: http://www.4clojure.com/problem/4

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= (list __) '(:a :b :c))

;;; Problem Solution:

:a :b :c

;;; Solution Evaluation:

(= (list :a :b :c) '(:a :b :c))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 5

;;; Problem Link: http://www.4clojure.com/problem/5

;;; Problem Statement: Enter a function to evaluate the outer function to true.


;;; (= __ (conj '(2 3 4) 1))
;;; (= __ (conj '(3 4) 2 1))

;;; Problem Solution:

'(1 2 3 4)

;;; Solution Evaluation:

(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))
