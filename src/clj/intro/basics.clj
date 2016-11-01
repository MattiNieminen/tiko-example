(ns intro.basics)


;;
;; So what's the syntax of Clojure like?
;;


;; This is a comment.
; This is also a comment.
;So is this.


;; Numbers.


;; Booleans.


;; Strings.


;; Keywords.


;; nil.


;; Vectors.


;; Maps.


;; Lists.


;; What's the quote before the parentheses?


;; Lists evaluate into function calls if they are not quoted.
;; Code is data! Lisp programs manipulate the same data structures they created
;; with.


;; Calling functions.


;; Your own functions. How to calculate of two numbers?


;; Some useful sequence functions (count conj, first, second nth).
;; There are many more.


;; Some useful map functions (keys, vals, get, assoc, dissoc, merge)


;; Are there any variables? Or constants?


;; if


;; let


;; Java interop



;;
;; Things you may need exploring this codebase!
;;


;; Destructing function argument lists and lets

(let [[a b] [1 2]]
  (+ a b))

(let [{:keys [a b] :as m} {:a 1 :b 2}]
  (+ a b))

;; Atoms (creating an atom, dereffing it, changing it's value)

(def some-name (atom "Matti"))

@some-name ; => "Matti"

(reset! some-name "Eemeli")

@some-name ; => "Eemeli"

(swap! some-name clojure.string/upper-case)

@some-name ; => EEMELI
