(ns backend.main
  (:gen-class)
  (:require [mount.core :as mount]
            [backend.mount.aleph]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (mount/start)
  @(promise))
