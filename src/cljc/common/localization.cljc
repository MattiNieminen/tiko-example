(ns common.localization)

(def defaults
  {:page-title "TIKO-TODO"
   :loading "Please wait while the app is loading"
   :tiko-todo "TIKO-TODO"
   :new-todo "New TODO"})

(defn tr
  [k]
  (or (get defaults k)
      (str "Not yet localized: " k)))
