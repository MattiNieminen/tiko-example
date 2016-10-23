(ns common.localization)

(def defaults
  {:page-title "TIKO-example"
   :loading "Please wait while the app is loading"})

(defn tr
  [k]
  (or (get defaults k)
      (str "Not yet localized: " k)))
