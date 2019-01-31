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



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


;; Chapter 6
;; Practice

(def somef (delay (prn 1) (inc 1)))

(realized? somef)

(deref somef)

@somef ;; shortcut for deref function

(def x
  (future (prn "hi") (+ 1 2)))

(deref x)

@x


(def x (promise))

x

(deref x)

(deliver x :something-to-live-for!!)


(def present (promise))

(def gift (future
            (Thread/sleep 5000)
            (deliver present (rand-nth [:puppy :doll :card :money]))))

(deref present)


(def ^:dynamic *somechange* :apple)


(defn cut [] (prn "what is happening witn " *somechange*))

(cut)

(binding [*somechange* :banana] (cut))

(cut)



(def tmp #{})

(dotimes [i 10] (def tmp (conj tmp i)))


tmp

(dotimes [i 10] (future (def tmp (conj tmp i))))


tmp

(def x (atom #{}))

x

(deref x)

x

(reset! x :foo)


x

(def x (atom 0))

(swap! x inc)

(swap! x inc)


(def xs (atom #{}))

(dotimes [i 10] (future (swap! xs conj i)))

@xs

(def xcess (ref 0))
(def ycess (ref 2))

xcess

@xcess

(dosync
 (ref-set xcess 3)
 (ref-set ycess 5))

[@xcess @ycess]

(dosync
 (alter xcess + 2)
 (alter ycess inc))

;; commute

(dosync (alter xcess + (ensure ycess)))


;; Problem Link: https://aphyr.com/posts/306-clojure-from-the-ground-up-state


;; Finding the sum of the first 10000000 numbers takes about 1 second on my machine:

 (defn sum [start end] (reduce + (range start end)))
 (time (sum 0 1e7))

;; Use delay to compute this sum lazily; show that it takes no time to return the delay, but roughly 1 second to deref.

(defn sum* [start end] (delay (reduce + (range start end))))

sum*

(sum* 0 1e7)

(time (deref (time (sum* 0 1e7))))



;; We can do the computation in a new thread directly, using (.start (Thread. (fn [] (sum 0 1e7)))–but this simply runs the (sum) function and discards the results. Use a promise to hand the result back out of the thread. Use this technique to write your own version of the future macro.



(def res (promise))

(.start (Thread. (fn [] (deliver res (sum 0 1e7)))))

@res


(defmacro future*
  [& args]
  (let [s promise]
    (for [x args]
      (.start (Thread. (fn [] (do x)))))
    (deref s)))


(def x
  (future* (prn "hi") (+ 1 2)))

(deref x)
WIP



;; If your computer has two cores, you can do this expensive computation twice as fast by splitting it into two parts: (sum 0 (/ 1e7 2)), and (sum (/ 1e7 2) 1e7), then adding those parts together. Use future to do both parts at once, and show that this strategy gets the same answer as the single-threaded version, but takes roughly half the time.


(time (let [a (future (sum 0 (/ 1e7 2))) b (sum (/ 1e7 2) 1e7)] (+ @a b))
      )

(time (sum 0 1e7))


;; Instead of using reduce, store the sum in an atom and use two futures to add each number from the lower and upper range to that atom. Wait for both futures to complete using deref, then check that the atom contains the right number. Is this technique faster or slower than reduce? Why do you think that might be?

(def ss (atom 0))

ss

(defn sum*
  [start end]
  (let [a (future s)]))


;; Instead of using a lazy list, imagine two threads are removing tasks from a pile of work. Our work pile will be the list of all integers from 0 to 10000:

;; user=> (def work (ref (apply list (range 1e5))))
;; user=> (take 10 @work)
;; (0 1 2 3 4 5 6 7 8 9)

;; And the sum will be a ref as well:

;; user=> (def sum (ref 0))








;; Write a function which, in a dosync transaction, removes the first number in work and adds it to sum.
;; Then, in two futures, call that function over and over again until there’s no work left. Verify that @sum is 4999950000. Experiment with different combinations of alter and commute–if both are correct, is one faster? Does using deref instead of ensure change the result?
