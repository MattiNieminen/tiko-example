(ns backend.mount.aleph
  (:require [backend.static :as static]
            [mount.core :as mount]
            [aleph.http :as aleph]
            [ring.middleware.defaults :as ring-defaults]))

(defonce port 3000)

(defn create-handler
  []
  (let [static (-> (static/create-handler)
                   (ring-defaults/wrap-defaults ring-defaults/site-defaults))]
    (fn [req]
      (static req))))

(mount/defstate aleph
  :start
  (do
    (println "Starting HTTP server at port" port)
    (aleph/start-server (create-handler) {:port port}))
  :stop
  (do
    (println "Stopping HTTP server")
    (.close aleph)))
