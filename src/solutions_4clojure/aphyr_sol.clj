(ns solutions-4clojure.aphyr_sol)

;; Practice




(defmacro so [fun & args]
  (cons fun (reverse args)))

(macroexpand '(so str 1 2 3 4 5 6))


;; Chapter 4
;; Problem Links https://aphyr.com/posts/304-clojure-from-the-ground-up-sequences

;; Write a function to find out if a string is a palindrome–that is, if it looks the same forwards and backwards.

(defn palindrome*
  [s]
  (= (seq s) (seq (reverse s))))


(palindrome* "racecar")

;; Find the number of ‘c’s in “abracadabra”.

(count (filter (fn [x]
           (= x \c))
         "abracadabra"))


;; Write your own version of filter.

(defn filter*
  [f xs]
  (reduce (fn [acc x]
            (if (f x)
              (conj acc x)
              acc))
          [] xs))

(filter* odd? [1 2 3 4 5 6 6])

;; Find the first 100 prime numbers: 2, 3, 5, 7, 11, 13, 17, ….

WIP



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

;; Chapter 5
;; Problem Link https://aphyr.com/posts/305-clojure-from-the-ground-up-macros


;; Using the control flow constructs we’ve learned, write a schedule function which, given an hour of the day, returns what you’ll be doing at that time. (schedule 18), for me, returns : :dinner


(defn schedule
  [hr]
  (cond
    (and (> hr 0) (<= hr 8)) :sleep-time
    (and (> hr 8) (<= hr 9)) :bath
    (and (> hr 9) (<= hr 10)) :breakfast
    (and (> hr 10) (<= hr 13)) :work
    (and (> hr 13) (<= hr 14)) :lunch
    (and (> hr 14) (<= hr 17)) :work
    (and (> hr 17) (<= hr 19)) :dinner
    (and (> hr 8) (<= hr 9)) :bath
    :else :sleep-time))

(schedule 18)


;; Using the threading macros, find how many numbers from 0 to 9999 are palindromes: identical when written forwards and backwards. 121 is a palindrome, as is 7447 and 5, but not 12 or 953.


(->
 (->
  (str 1322)
  (reverse)
  (seq))
 (=
  (->
   (str 1322)
   (seq))))


(defn isPalindrome?
  [n]
  (= (seq (str n)) (seq (reverse (str n)))))

(isPalindrome? 7447)
(isPalindrome? 1234)


;; Write a macro id which takes a function and a list of args: (id f a b c), and returns an expression which calls that function with the given args: (f a b c).


(defmacro id
  [f & args]
  (cons f args))

(macroexpand '(id str "a" "b" "c"))

(eval (macroexpand '(id str "a" "b" "c")))



;; Write a macro log which uses a var, logging-enabled, to determine whether or not to print an expression to the console at compile time. If logging-enabled is false, (log :hi) should macroexpand to nil. If logging-enabled is true, (log :hi) should macroexpand to (prn :hi). Why would you want to do this check during compilation, instead of when running the program? What might you lose?

(def logging-enabled true)

(defmacro log
  [msg]
  (if (= logging-enabled true)
    `(prn ~msg)
    nil))



(macroexpand '(log :hi))

(eval (macroexpand '(log :hi)))

;; 



;; (Advanced) Using the rationalize function, write a macro exact which rewrites any use of +, -, *, or / to force the use of ratios instead of floating-point numbers. (* 2452.45 100) returns 245244.99999999997, but (exact (* 2452.45 100)) should return 245245N


(defmacro exact
  [e]
  `(rationalize ~e))


(exact (* 2452.45 100))
