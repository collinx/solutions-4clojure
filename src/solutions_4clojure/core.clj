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

------------------------------------------------------------------------------------------------------

;;; Problem ID: 6

;;; Problem Link: http://www.4clojure.com/problem/6

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= [__] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))

;;; Problem Solution:

:a :b :c

;;; Solution Evaluation:

(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 7

;;; Problem Link: http://www.4clojure.com/problem/7

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= __ (conj [1 2 3] 4))
;;; (= __ (conj [1 2] 3 4))

;;; Problem Solution:

[1 2 3 4]

;;; Solution Evaluation:

(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 8

;;; Problem Link: http://www.4clojure.com/problem/8

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= __ (set '(:a :a :b :c :c :c :c :d :d)))
;;; (= __ (clojure.set/union #{:a :b :c} #{:b :c :d}))


;;; Problem Solution:
    
#{:a :b :c :d}

;;; Solution Evaluation:

(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))


(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 9

;;; Problem Link: http://www.4clojure.com/problem/9

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= #{1 2 3 4} (conj #{1 4 3} __))

;;; Problem Solution:
2

;;; Solution Evaluation:

(= #{1 2 3 4} (conj #{1 4 3} 2))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 10

;;; Problem Link: http://www.4clojure.com/problem/10

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= __ ((hash-map :a 10, :b 20, :c 30) :b))
;;; (= __ (:b {:a 10, :b 20, :c 30}))


;;; Problem Solution:
20

;;; Solution Evaluation:

(= 20 ((hash-map :a 10, :b 20, :c 30) :b))

------------------------------------------------------------------------------------------------------

;;; Problem ID: 11

;;; Problem Link: http://www.4clojure.com/problem/11

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= {:a 1, :b 2, :c 3} (conj {:a 1} __ [:c 3]))

;;; Problem Solution:
{:b 2}

;;; Solution Evaluation:
(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))



------------------------------------------------------------------------------------------------------

;;; Problem ID: 12

;;; Problem Link: http://www.4clojure.com/problem/12

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= __ (first '(3 2 1)))
;;; (= __ (second [2 3 4]))
;;; (= __ (last (list 1 2 3)))

;;; Problem Solution:
3

;;; Solution Evaluation:

(= 3 (first '(3 2 1)))

(= 3 (second [2 3 4]))

(= 3 (last (list 1 2 3)))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 28

;;; Problem Link: http://www.4clojure.com/problem/28

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= (__ '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
;;; (= (__ ["a" ["b"] "c"]) '("a" "b" "c"))
;;; (= (__ '((((:a))))) '(:a))

;;; Problem Solution:

(defn flatten-this
  [xs]
  (reduce (fn flat
            [acc x]
            (if (sequential? x)
              (into acc (flatten-this x))
              (conj acc x))) [] xs))

;;; Solution Evaluation:


(= (flatten-this '((1 2) 3 [4 [5 6]])) [1 2 3 4 5 6]) 

(= (flatten-this ["a" ["b"] "c"]) '("a" "b" "c"))

(= (flatten-this '((((:a)))))
   '(:a))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 62

;;; Problem Link: http://www.4clojure.com/problem/62

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= (take 5 (__ #(* 2 %) 1)) [1 2 4 8 16])
;;; (= (take 100 (__ inc 0)) (take 100 (range)))
;;; (= (take 9 (__ #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))

;;; Problem Solution:

(defn iterate*
  [f i]
  (lazy-seq (cons i (iterate* f (f i)))))


;;; Solution Evaluation:

(= (take 5 (iterate* #(* 2 %) 1)) [1 2 4 8 16])

(= (take 100 (iterate* inc 0)) (take 100 (range)))

(= (take 9 (iterate* #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))

------------------------------------------------------------------------------------------------------

;;; Problem ID: 81

;;; Problem Link: http://www.4clojure.com/problem/81

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= (__ #{0 1 2 3} #{2 3 4 5}) #{2 3})
;;; (= (__ #{0 1 2} #{3 4 5}) #{})
;;; (= (__ #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})

;;; Problem Solution:

(defn intersection*
  [s1 s2]
  (let [s (clojure.set/union s1 s2)]
    (reduce (fn [acc x]
              (if (and (contains? s1 x) (contains? s2 x))
                (conj acc x)
                acc) )
           #{} s)))


(intersection* #{0 1 2 3} #{2 3 4 5})

;;; Solution Evaluation:

(= (intersection* #{0 1 2 3} #{2 3 4 5}) #{2 3})

(= (intersection* #{0 1 2} #{3 4 5}) #{})

(= (intersection* #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})


;;; Another Solution which make above solution look so dumb

#(set (filter %2 %))


------------------------------------------------------------------------------------------------------

;;; Problem ID: 166

;;; Problem Link: http://www.4clojure.com/problem/166

;;; Problem Statement: Enter a function to evaluate the outer function to true.

;;; (= :gt (__ < 5 1))
;;; (= :eq (__ (fn [x y] (< (count x) (count y))) "pear" "plum"))
;;; (= :lt (__ (fn [x y] (< (mod x 5) (mod y 5))) 21 3))
;;; (= :gt (__ > 0 2))

;;; Problem Solution:

(defn compare*
  [cmp x y]
  (if (and  )
    :eq
    (if (cmp x y)
      :lt
      :gt)))

;;; Solution Evaluation:

(= :gt (compare* < 5 1))

(= :eq (compare* (fn [x y] (< (count x) (count y))) "pear" "plum"))

(= :lt (compare* (fn [x y] (< (mod x 5) (mod y 5))) 21 3))

(= :gt (compare* > 0 2))


