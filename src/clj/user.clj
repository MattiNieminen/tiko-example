(ns user
  (:require [mount.core :as mount]
            [clojure.tools.namespace.repl :as tools-namespace]
            [backend.mount.immutant]

            ;; Don't forget to require all handler namespaces
            [backend.todo]))

(defn go
  []
  (mount/start)
  :ready)

(defn reset
  []
  (mount/stop)
  (tools-namespace/set-refresh-dirs "clj" "cljc")
  (tools-namespace/refresh :after 'user/go))
