(ns macros)

(let [x 9, y '(- x)]
  (println y)
  (println `y)
  (println ``y)
  (println ``~y)
  (println ``~~y))




(defmacro do-until [& clauses]
  (when clauses
    (list 'when (first clauses)
          (if (next clauses)
            (second clauses)
            (throw (IllegalArgumentException. "do-until requires an even number of forms")))
          (cons 'do-until (nnext clauses)))))

(macroexpand '(do-until true (prn 1) false (prn 2)))


(defmacro code-critic
  [{:keys [good bad]}] ;Map destructuring
  `(do (println "Great squid of Madrid, this is bad code:" (quote ~bad))
       (println "Sweet gorilla of Manilla, this is good code:" (quote ~good))))

(clojure.pprint/pprint (macroexpand '(code-critic {:good (+ 1 1) :bad (1 + 1)} )))

(defn criticize-code [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic1
  [{:keys [good bad]}]
  `(do
     ~(criticize-code "Holy cow of Moscow, this is bad code:" bad)
     ~(criticize-code "Holy lion of Zion, this is good code:" good)))

(clojure.pprint/pprint (macroexpand-1 '(code-critic1 {:good (+ 1 1) :bad (1 + 1)} )))


;(defmacro code-critic2
;  [{:keys [good bad]}]
;  `(do ~(map #(apply criticize-code %)
;             [["this is bad code:" bad]
;              ["this is good code:" good]])))

;;This code will break with NullPointerExpection beacause it will try to evaluate the
;;result of the println
;(code-critic2 {:good (+ 1 1) :bad (1 + 1)})
;(clojure.pprint/pprint (macroexpand-1 '(code-critic2 {:good (+ 1 1) :bad (1 + 1)} )))

(defmacro code-critic3
  [{:keys [good bad]}]
  `(do ~@(map #(apply criticize-code %)
              [["This is good code:" good]
               ["This is bad code:" bad]])))

(code-critic3 {:good (+ 1 1) :bad (1 + 1)})
(clojure.pprint/pprint (macroexpand-1 '(code-critic3 {:good (+ 1 1) :bad (1 + 1)})))

