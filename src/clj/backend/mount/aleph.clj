(ns backend.mount.aleph
  (:require [mount.core :as mount]
            [aleph.http :as aleph]))

(defonce port 3000)

(defn handler
  [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "hello!"})

(mount/defstate aleph
  :start
  (do
    (println "Starting HTTP server at port " port)
    (aleph/start-server handler {:port port}))
  :stop
  (do
    (println "Stopping HTTP server")
    (.close aleph)))
