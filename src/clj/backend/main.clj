(ns backend.main
  (:gen-class)
  (:require [mount.core :as mount]
            [backend.mount.immutant]

            ;; Don't forget to require all handler namespaces
            [backend.hello]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (mount/start))
