(ns solutions-4clojure.brave_true)

;; Brave and True Exercises

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
;; Exercise Link : https://www.braveclojure.com/do-things/
;; Reference Code

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])


(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))



(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))


(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))


(hit asym-hobbit-body-parts)
; => {:name "right-upper-arm", :size 3}

(hit asym-hobbit-body-parts)
; => {:name "chest", :size 10}

(hit asym-hobbit-body-parts)
; => {:name "left-eye", :size 1}

------------------------------------------------------------------------------------------------------

;; Problem 1
;; Use the str, vector, list, hash-map, and hash-set functions.

(str "Hello, " "World" "! " 007)

(vector 1 2 3 4 5 :s :3 "dskd")

(list 23 43 "sd" :sd :sd)

(= list vector)

(= (list 1 2 3) (vector 1 2 3))

(hash-map :a 1)

(hash-set 1 2 3 2 1)


;; Problem 2
;; Write a function that takes a number and adds 100 to it.

(defn add100
  [x]
  (+ 100 x))

(add100 4)


;; Problem 3
;; Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
;; (def dec9 (dec-maker 9))
;; (dec9 10)
;; => 1

(defn dec-maker
  [x]
  #(- % x))

(def dec9 (dec-maker 9))

(dec9 10)

;; Problem 4
;; Write a function, mapset, that works like map except the return value is a set:
;; (mapset inc [1 1 2 2])
 ;; => #{2 3}

(defn mapset
  [f xs]
  (set (map f xs)))

(mapset inc [1 1 2 2])


;; Problem 5
;; Create a function that’s similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.

(defn matching-part-alien
  [part]
  )

((fn t
   [part]
   (reduce (fn [acc x]
             (assoc acc
                    :name (clojure.string/replace (:name part) #"^left"))) {} ["right","top","center","bottom"])) {:name "left-eye" :size 1}))
(defn symmetrize-body-parts-alien
  [])



;; Problem 6
;; Create a function that generalizes symmetrize-body-parts and the function you created in Exercise 5. The new function should take a collection of body parts and the number of matching body parts to add. If you’re completely new to Lisp languages and functional programming, it probably won’t be obvious how to do this. If you get stuck, just move on to the next chapter and revisit the problem later.




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
