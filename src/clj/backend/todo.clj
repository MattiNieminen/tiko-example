(ns backend.todo
  (:require [backend.mount.sente :as sente]))

;; Atom as in memory-database.
;; In real life, you would replace this with database connection using Mount.

(defonce db (atom []))

(defmethod sente/event-msg-handler :todo/fetch-all
  [{:keys [event ?reply-fn]}]
  (?reply-fn {:todos @db}))

;; Exercise 6. Fix function so that it does change the db
;; or broadcast if the created todo item was was empty.
(defmethod sente/event-msg-handler :todo/create
  [{:keys [event]}]
  (let [new-todo (-> event second :new-todo)]
    (swap! db conj new-todo)

    ;; Broadcast new todo list to everyone
    (doseq [uid (:any @(:connected-uids sente/sente))]
      ((:send-fn sente/sente)
       uid
       [:todo/push-all {:todos @db}]))))
