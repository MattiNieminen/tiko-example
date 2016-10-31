(ns backend.todo
  (:require [backend.mount.sente :as sente]))

;; Atom as in memory-database.
;; In real life, you would replace this with database connection using Mount.

(defonce db (atom []))

(defmethod sente/event-msg-handler :todo/fetch-all
  [{:keys [event ?reply-fn]}]
  (?reply-fn {:todos @db}))

(defmethod sente/event-msg-handler :todo/create
  [{:keys [event]}]
  (swap! db conj (-> event second :new-todo))

  ;; Broadcast new todo list to everyone
  (doseq [uid (:any @(:connected-uids sente/sente))]
    ((:send-fn sente/sente)
     uid
     [:todo/push-all {:todos @db}])))
