(ns backend.mount.config
  (:require [clojure.edn :as edn]
            [mount.core :as mount]))

(mount/defstate config
  :start
  (do
    (println "Reading config.edn")
    (edn/read-string (slurp "resources/config.edn"))))
