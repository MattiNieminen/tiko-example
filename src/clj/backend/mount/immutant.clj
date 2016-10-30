(ns backend.mount.immutant
  (:require [backend.static :as static]
            [backend.mount.sente :as sente]
            [mount.core :as mount]
            [immutant.web :as immutant]
            [ring.middleware.defaults :as ring-defaults]))

(defonce port 3000)

(defn sente-handler
  [req]
  (if (= (:uri req) "/chsk")
    (try
      (case (:request-method req)
        :get ((:ajax-get-or-ws-handshake-fn sente/sente) req)
        :post ((:ajax-post-fn sente/sente) req))
      (catch Exception e
        {:status 400}))))

(defn create-handler
  []
  (let [static-handler (static/create-handler)]
    (-> (fn [req]
          (or (sente-handler req)
              (static-handler req)))
        (ring-defaults/wrap-defaults ring-defaults/site-defaults))))

(mount/defstate aleph
  :start
  (do
    (println "Starting Immutant web server at port" port)
    (immutant/run (create-handler) {:host "localhost" :port 3000 :path "/"}))
  :stop
  (do
    (println "Stopping Immutant web server")
    (immutant/stop)))
