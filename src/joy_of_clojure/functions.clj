(ns joy-of-clojure.functions)

;this version has memory problems
(defn stack-consuming-fibo [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ (stack-consuming-fibo (- n 1))
             (stack-consuming-fibo (- n 2)))))

(stack-consuming-fibo 9)

;this call will throw a StackOverflowException
;(stack-consuming-fibo 1000000)

;tail recursion version
(defn tail-fibo [n]
  (letfn [(fib [current next n]
            (if (zero? n)
              current
              (fib next (+ current next) (dec n))))]
    (fib 0N 1N n)))

(tail-fibo 9)

;booom
;(tail-fibo 10000)


;recur with tco

(defn recur-fibo [n]
  (letfn [(fib [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))]
    (fib 0N 1N n)))

(recur-fibo 9)


;fibonacci lazy seq

(defn lazy-seq-fibo
  ([]
   (concat [0 1] (lazy-seq-fibo 0N 1N)))
  ([a b]
   (let [n (+ a b)]
     (lazy-seq
       (cons n (lazy-seq-fibo b n))))))

(take 10 (lazy-seq-fibo))
(rem (nth (lazy-seq-fibo) 100000) 1000)


; fibonacci sequence with existing seq api's

(defn fibo []
  (map first (iterate (fn [[a b]] [b (+' a b)]) [0 1])))

(take 10 (fibo))

(nth (fibo) 100000)

(defn my-reverse [x]
  (letfn [(my-reverse-iter [x xs]
            (if (empty? x)
              xs
              (recur (rest x) (cons (first x) xs))))]
            (my-reverse-iter x [])))



;(defn count-down [n]
;  (letfn [(iter-count-down [result n]
;   (if (zero? n)
;     result
;     (recur (conj result n) (dec n))))]
;    (iter-count-down [] n)))


(defn count-down
  ([n] (count-down [] n))
  ([result n]
    (if (zero? n)
      result
      (recur (conj result n) (dec n)))))


;(defn count-down
;  ([n] (count-down [] n))
;  ([result n]
;    (if (zero? n)
;      result
;      (lazy-seq (conj (count-down result (dec n)) n)))))

(defn strict-map [f coll]
  (loop [c coll acc []]
    (if (empty? c)
      acc
      (recur (rest c) (conj acc (f (first c)))))))


(defn find-pos [e coll]
  (let [cmp (if (map? coll)
              #(= (second %1) %2)
              #(= %1 %2))]
    (loop [s coll idx 0]
    (when (seq s)
      (if (cmp (first s) e)
      (if (map? coll)
        (first (first s))
        idx)
        (recur (rest s) (inc idx)))))))