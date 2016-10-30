(ns backend.hello
  (:require [backend.mount.sente :as sente]))

;; Example end point for sente showing listening, responding and broadcasting

(defmethod sente/event-msg-handler :friends/hello
  [{:keys [event ?reply-fn]}]
  (println "Client said:" (-> event second :message))
  (?reply-fn {:message "Hello from the other side."})

  ;; Broadcast to everyone
  (doseq [uid (:any @(:connected-uids sente/sente))]
    ((:send-fn sente/sente)
     uid
     [:friends/shout {:message "I found someone new!"}])))
