(ns user
  (:require [mount.core :as mount]
            [clojure.tools.namespace.repl :as tools-namespace]
            [backend.mount.aleph :as aleph]))

(defn go
  []
  (mount/start)
  :ready)

(defn reset []
  (mount/stop)
  (tools-namespace/refresh :after 'user/go))
