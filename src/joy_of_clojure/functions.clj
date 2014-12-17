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
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(take 10 (fibo))

(nth (fibo) 100000)
