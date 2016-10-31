(ns backend.main
  (:gen-class)
  (:require [mount.core :as mount]
            [backend.mount.immutant]

            ;; Don't forget to require all handler namespaces
            [backend.todo]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (mount/start))
