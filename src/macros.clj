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

(macroexpand-1 '(do-until true (prn 1) false (prn 2)))
